package exceptions;

public class ColonneNonTrouveeException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ColonneNonTrouveeException(String label){
		System.err.println("La colonne \""+label+"\" n'existe pas.");
	}
}