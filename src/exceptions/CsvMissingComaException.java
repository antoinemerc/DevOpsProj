package exceptions;

public class CsvMissingComaException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CsvMissingComaException(){
		System.err.println("Erreur lors du parsing du fichier CSV: Virgule attendue et non trouvée");
	}
}