package apcs.katechon.logging;

/**
 * This is a loger that....... doesnt' log. Most of our logs use printlns or arrayslists, which can get pretty slow. If we fear that the logger is being a bottleneck, we can plug this logger in to verify.
 * @author Matt
 *
 */
public class SilentLogger implements ILogger {

	@Override
	public void saveLog() {
	}

	@Override
	public void log(String message) {
	}

	@Override
	public void chainLog(String[] messages) {
	}
}
