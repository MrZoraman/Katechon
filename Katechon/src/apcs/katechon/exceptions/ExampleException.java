package apcs.katechon.exceptions;

/**
 * This is an example of the way to make exceptions
 * @author Floating-Imp
 *
 */
public class ExampleException extends Exception
{
	//I would like to use exceptions where applicable
	
	private static final long serialVersionUID = 1373770751187609117L;

	public ExampleException(String message)
	{
		super(message);
	}
}
