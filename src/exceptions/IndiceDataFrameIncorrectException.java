package exceptions;

public class IndiceDataFrameIncorrectException extends Exception 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/***
	 * Constructor
	 */
	public IndiceDataFrameIncorrectException()
	{
		System.err.println("L'indice est incorrect ou il n'existe pas dans la Data Frame");
	}

}
