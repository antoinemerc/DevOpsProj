package exceptions;

public class CsvParsingException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CsvParsingException(){
		System.err.println("Erreur lors du parsing du fichier CSV: Colonne inattendue");
	}
}
