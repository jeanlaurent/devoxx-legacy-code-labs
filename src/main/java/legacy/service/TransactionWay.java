package legacy.service;

public enum TransactionWay {
	LONG("L"), SHORT("S");

    private String valueForDto;

    private TransactionWay(String valueForDto) {
        this.valueForDto = valueForDto;
    }

    public String getValueForDto() {
        return valueForDto;
    }
}
