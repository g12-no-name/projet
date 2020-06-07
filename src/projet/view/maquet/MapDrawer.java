package projet.view.maquet;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import projet.data.Poste;

public interface MapDrawer {
	
	@FXML
	void plot();
	
	default void plotDaPlots(ListView<Poste> listViewP, ModelPagePrincipale mpp, Canvas canvas, int plotSize) {
		drawMap(mpp, canvas);
		try {drawSelectedPlotMarker(listViewP.getSelectionModel().getSelectedItem(), canvas, plotSize);}catch(Exception e) {}
		for(Poste p : mpp.getPostes()) {draw(p, canvas, plotSize);}
	}
	
	default void drawMap(ModelPagePrincipale mpp, Canvas canvas) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.drawImage(mpp.imageCarteProperty().getValue(),0,0, canvas.getWidth(), canvas.getHeight());
	}

	default void draw(Poste p, Canvas canvas, int plotSize) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		try {
			int x = (int) (p.getX()*canvas.getWidth()/1000), y = (int) (p.getY()*canvas.getHeight()/1000);
			gc.setFill(Color.RED);
			gc.fillOval(x-plotSize/2, y-plotSize/2, plotSize, plotSize);
		} catch (Exception e) {return;}
	}
	
	default void drawSelectedPlotMarker(Poste p, Canvas canvas, int plotSize) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		int x = (int) (p.getX()*canvas.getWidth()/1000), y = (int) (p.getY()*canvas.getHeight()/1000);
		gc.setFill(Color.BLACK);
		gc.setLineWidth(6);
		gc.strokeOval(x-plotSize/2, y-plotSize/2, plotSize, plotSize);
	}
	
	default void checkForProximityWithAPlot(int x, int y, ListView<Poste> listViewP, Canvas canvas, int plotSize) {
		for(Poste p : listViewP.getItems()) {
			try {
				int xp=(int) (p.getX()*canvas.getWidth()/1000); int yp=(int) (p.getY()*canvas.getHeight()/1000);
				if((xp-x)*(xp-x)+(yp-y)*(yp-y)<plotSize*plotSize) {
					//select the corresponding plot in the list
					listViewP.getSelectionModel().select(p);
					drawSelectedPlotMarker(p, canvas, yp);
				}
			} catch (Exception e) {}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
