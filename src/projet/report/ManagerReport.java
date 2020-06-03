package projet.report;

import java.awt.Desktop;
import java.awt.print.PrinterAbortException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.sql.DataSource;
import javax.swing.SwingUtilities;

import javafx.application.Platform;
import javafx.embed.swing.SwingNode;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.SimpleJasperReportsContext;
import net.sf.jasperreports.repo.FileRepositoryPersistenceServiceFactory;
import net.sf.jasperreports.repo.FileRepositoryService;
import net.sf.jasperreports.repo.PersistenceServiceFactory;
import net.sf.jasperreports.repo.RepositoryService;
import net.sf.jasperreports.swing.JRViewer;
import projet.view.ManagerGui;


public class ManagerReport {
	
	
	// Constantes
	
	private final String PREFIX_FILE_TEMP = "report-";
	private final String SUFEFIX_FILE_PDF = ".pdf";
	
	private final boolean FLAG_SAVE_REPORt = false;

	
	// Champs

	@Inject
	private ManagerGui	managerGui;
	@Inject
	private DataSource	dataSource;
	
	private File		dirReports;
	
	
	// Setters
	
	public void setDirReports( File dirReports ) {
		this.dirReports = dirReports;
		if ( ! dirReports.exists() ) {
			throw new RuntimeException( new FileNotFoundException( dirReports.getAbsolutePath() ) );
		}
	}
	
	public void setDirReports( String pathDirReports ) {
		setDirReports( new File( pathDirReports ) );
	}
	
	
	// Actions de haut niveau
	
	
	public void openFilePdf( IEnumReport report, Map<String, Object> params ) {
		openFilePdf( report, params, null );
	}
	
	
	public void openFilePdf( IEnumReport report, Map<String, Object> params, String pathPdf ) {
		managerGui.execTask( () -> {
			JasperPrint print = fillReport( report.getPath(), params );
			openFilePdf( print, pathPdf );
		});
	}
	
	
	public void showViewer( IEnumReport report, Map<String, Object> params ) {
		managerGui.execTask( () -> {
			JasperPrint print = fillReport( report.getPath(), params );
			showViewer( print );
		});
	}
	
	
	public void print( IEnumReport report, Map<String, Object> params ) {
		managerGui.execTask( () -> {
	        try {
	        	JasperPrint print = fillReport( report.getPath(), params );
				JasperPrintManager.printReport( print, true );
			} catch ( JRException e) {
				if ( ! (e.getCause() instanceof PrinterAbortException) ) {
					throw e;
				}
			}
		});
	}
	
	
	// Actions de bas niveau
	
	
	public JasperPrint fillReport( String pathReport, Map<String, Object> params, Connection cn )  {
		
		File 			fileDesign;
		File 			fileReport;
		JasperReport 	report = null;
		
		try {
			
			File fileParam = new File( pathReport );
			if ( ! fileParam.isAbsolute()  ) {
				fileParam = new File( dirReports, pathReport );
			}
			if ( ! fileParam.exists() ) {
				throw new FileNotFoundException( fileParam.getAbsolutePath() );
			}
			
			String extension = "";
			int i = fileParam.getName().lastIndexOf('.');
			if (i > 0) {
			    extension = fileParam.getName().substring( i );
			}

			if ( extension.equals( ".jrxml" ) ) {
				fileDesign = fileParam;
				String path2 = fileDesign.getAbsolutePath();
				fileReport = new File ( path2.subSequence( 0, path2.length() - 6) + ".jasper" );
				if ( ( ! fileReport.exists() ) 
						|| ( fileReport.lastModified() < fileDesign.lastModified() ) ) {
					// Compile le design
					if ( FLAG_SAVE_REPORt ) {
						JasperCompileManager.compileReportToFile( fileDesign.getAbsolutePath(), fileReport.getAbsolutePath() );
					} else {
						report = JasperCompileManager.compileReport( fileDesign.getAbsolutePath() );
					}
				}
			} else if ( extension.equals( ".jasper" ) ) {
				fileReport = fileParam;
			} else {
				throw new IllegalArgumentException( pathReport );
			}
			
			// Crée le contexte
		    SimpleJasperReportsContext context = new SimpleJasperReportsContext();
		    RepositoryService repositoryService = new FileRepositoryService(context, fileReport.getParent(), true );
		    context.setExtensions(RepositoryService.class, Collections.singletonList(repositoryService));
		    context.setExtensions(PersistenceServiceFactory.class, Collections.singletonList(FileRepositoryPersistenceServiceFactory.getInstance()));

		    // Execution du rapport
			if ( FLAG_SAVE_REPORt ) {
			    return JasperFillManager.getInstance(context).fill( fileReport.getAbsolutePath(), params, cn );
			} else {
				return JasperFillManager.getInstance(context).fill( report, params, cn );
			}

		
		} catch (JRException | IOException e) {
			throw new RuntimeException(e);
		}
		
	}

	
	public JasperPrint fillReport( String pathReport, Map<String, Object> params )  {
		try {
			return fillReport( pathReport, params, dataSource.getConnection() );
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	public void exportToFilePdf( JasperPrint print, String pathPdf ) {
	    try {
			JasperExportManager.exportReportToPdfFile( print, pathPdf );
		} catch (JRException e) {
			new RuntimeException(e);
		}
	}


	public void openFilePdf( JasperPrint print, String pathPdf ) {
	    try {
	    	if ( pathPdf == null || pathPdf.isEmpty() ) {
		    	File filePdf = File.createTempFile( PREFIX_FILE_TEMP, SUFEFIX_FILE_PDF );
		    	pathPdf = filePdf.getAbsolutePath();
	    	}
		    exportToFilePdf(print, pathPdf );
			Desktop.getDesktop().open( new File( pathPdf ) );
	    } catch ( IOException e ) {
			new RuntimeException(e);
		}
	}
	
	
	public JRViewer createViewer( String pathReport, Map<String, Object> params ) {
		JasperPrint print = fillReport(pathReport, params);
		JRViewer viewer = new JRViewer(print);
		viewer.setOpaque(true);
		viewer.setVisible(true);
		return viewer;
	}
	
	
	public void showViewer( JasperPrint print ) {

		JRViewer viewer = new JRViewer(print);
		viewer.setOpaque(true);
		viewer.setVisible(true);

		SwingNode swingNode = new SwingNode();
		SwingUtilities.invokeLater( () ->
				swingNode.setContent( viewer )
		);
		StackPane pane = new StackPane();
		Scene scene = new Scene( pane );
		pane.getChildren().add(swingNode);
		Platform.runLater( () -> {
			Stage stage = new Stage();
			stage.setScene( scene );
			stage.initModality( Modality.APPLICATION_MODAL );
			Stage owner = managerGui.getStage();
			stage.initOwner( owner );
			stage.setTitle( owner.getTitle() );
			stage.getIcons().addAll( owner.getIcons() );
			stage.initModality( Modality.APPLICATION_MODAL ); 
			stage.show();
		});
	}
	
	
	
	/**
	 * Supprime les fichiers temporaires dont le nom commence par le 
	 * prefixe indique dans la constante PREFIX_FILE_TEMP,
	 * s'ils ont ete crees il y a plus de 24 heures.
	 */
	@PostConstruct
	public void cleanDirTemp() {
	    File dirTemp = new File( System.getProperty( "java.io.tmpdir" ) );
	    String[] paths = dirTemp.list();
	    for ( String path : paths ) {
	    	File file = new File( dirTemp, path );
	    	if ( path.startsWith( PREFIX_FILE_TEMP )
	    			&& System.currentTimeMillis() - file.lastModified() > 24 * 3600000 ) {
		    	file.delete();
	    	}
	    }
	}
	
}
