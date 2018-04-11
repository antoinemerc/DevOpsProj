package exceptions;

public class ColonneNonTrouveeException extends Exception {
	public ColonneNonTrouveeException(String label){
		System.err.println("La colonne "+label+" n'existe pas.");
	}
}
