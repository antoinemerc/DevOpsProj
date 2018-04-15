package exceptions;

public class IndiceDebutTropGrandException extends Exception
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/***
	 * Constructor
	 */
	public IndiceDebutTropGrandException()
	{
		System.err.println("L'indice est incorrect ou il n'existe pas dans la Data Frame");
	}


}