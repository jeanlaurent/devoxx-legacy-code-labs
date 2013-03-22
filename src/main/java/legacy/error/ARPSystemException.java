package legacy.error;

public class ARPSystemException extends RuntimeException {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Exception Severity
	 */
	private ExceptionSeverityConst severity = ExceptionSeverityConst.DEFAULT;

	/**
	 * legacy.error.ARPSystemException Constructor
	 */
	public ARPSystemException() {

	}

	/**
	 * legacy.error.ARPSystemException Constructor
	 */
	public ARPSystemException(String message) {
		super(message);
	}

	/**
	 * legacy.error.ARPSystemException Constructor
	 */
	public ARPSystemException(Throwable cause) {
		super(cause);
	}

	/**
	 * legacy.error.ARPSystemException Constructor
	 */
	public ARPSystemException(String message, ExceptionSeverityConst severity) {
		super(message);
		this.severity = severity;
	}

	/**
	 * legacy.error.ARPSystemException Constructor
	 */
	public ARPSystemException(Throwable cause, ExceptionSeverityConst severity) {
		super(cause);
		this.severity = severity;
	}

	public ExceptionSeverityConst getSeverity() {
		return severity;
	}

	public void setSeverity(ExceptionSeverityConst severity) {
		this.severity = severity;
	}
}
