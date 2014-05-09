package apcs.katechon.exceptions;

/**
 * This is an example of the way to make exceptions
 * @author Floating-Imp
 *
 */
public class ExampleException extends Exception
{
	//I would like to use exceptions where applicable
	
	public ExampleException()
	{
		super();
	}
	
	public ExampleException(String message)
	{
		super(message);
	}
	
	public ExampleException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
	public ExampleException(String message, Throwable cause, boolean enableSuppression, boolean writeableStackTrace)
	{
		super(message, cause, enableSuppression, writeableStackTrace);
	}
	
	public ExampleException(Throwable cause)
	{
		super(cause);
	}
}
