package legacy.error;

import java.io.Serializable;

public class CheckResult<T>  implements Serializable {
	private boolean checkIsOk;
	private T result = null; // service result

	public CheckResult() {
		// By default the check is ok
		checkIsOk = true;
	}

	public boolean isCheckIsOk() {
		return checkIsOk;
	}

	public void setCheckIsOk(boolean checkIsOk) {
		this.checkIsOk = checkIsOk;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

    @Override
    public String toString() {
        return "CheckResult{" +
                "checkIsOk=" + checkIsOk +
                ", result=" + result +
                '}';
    }
}
