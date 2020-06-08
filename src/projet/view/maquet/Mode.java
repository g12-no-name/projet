package projet.view.maquet;

public enum Mode {
	observe("Veuillez selectionner une option."),
	addPlot("Cliquez sur la carte pour poser un poste."),
	addMultPlots("Cliquez sur la carte pour poser un poste."),
	modifyPlot("Cliquez sur la carte pour definir une nouvelle position.")
	;
	
	String message;
	Mode(String s){
		message=s;
	}
	
	String getMessage(){
		return message;
	}
}
