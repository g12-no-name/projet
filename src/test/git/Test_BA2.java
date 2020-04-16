package test.git;

public class Test_BA2 {
	
	
	public void bonjour() {
		System.out.println( "Bonjoour" );
		System.out.println( "Ca va ?" );
	}
	
	
	private String[] adresses = {
			"99 Rue Mozart, Paris",
			"77 Rue Picasso, Toulouse", 
			"111 Rue des fleurs, Limoges",
	};

	
	public String getAdresse( int i ) {

		if ( 0 <= i && i < adresses.length ) {
			return adresses[i];
		} else {
			return "Erreur";
		}
	}
	
}
