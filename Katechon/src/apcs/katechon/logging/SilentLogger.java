package apcs.katechon.logging;

/**
 * This is a loger that....... doesnt' log. Most of our logs use printlns or arrayslists, which can get pretty slow. If we fear that the logger is being a bottleneck, we can plug this logger in to verify.
 * @author Matt
 *
 */
public class SilentLogger implements ILogger {
	@Override
	public void info(String message) {
	}

	@Override
	public void error(String message) {
	}

	@Override
	public void fatal(String message) {
	}

	@Override
	public void exception(Exception ex) {
	}

	@Override
	public void setDebugging(boolean debugging) {
	}

	@Override
	public void debug(String message) {
	}

	@Override
	public void saveLog() {
	}
}
