package apcs.katechon.logging;

import java.io.PrintStream;

/**
 * Logs info to the java console using System.out.println and System.err.println. Log file is not saved.
 * @author Matt
 *
 */
public class PrintstreamLogger extends TopDownLogger
{
	/**
	 * Creates a new PrintlnLogger
	 */
	public PrintstreamLogger(PrintStream stream)
	{
		this.out = stream;
	}
	
	private final PrintStream out;
	
	@Override
	public void saveLog() {}

	@Override
	public void log(String message) {
		out.println(message);
	}
}
