package legacy.dto;

public enum DayTypeScheme {

	D("D"),
	M("M"),
	W("W"),
	Y("Y"),
	Q("Q");

	private final String v;

	private DayTypeScheme(String v) {
		this.v = v;
	}

	public static DayTypeScheme fromValue(String v) {
		for (DayTypeScheme c : DayTypeScheme.values()) {
			if (c.v.equals(v)) {
				return c;
			}
		}
		throw new IllegalArgumentException(v);
	}
}
