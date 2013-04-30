package legacy.dto;

public enum ErrorLevel {
	FUNCTIONAL_ERROR,
	CONNECT_ERROR,
	FATAL_ERROR,
	BOOKING_MALFUNCTION;

	public String createHMsgFromError() {
		StringBuffer b = new StringBuffer();
		b.append("HP Error :");
		b.append(this.name());
		return b.toString();
	}
}
