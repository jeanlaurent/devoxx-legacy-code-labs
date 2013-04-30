package legacy.error;

import java.io.Serializable;

/**
 * <p>
 *     Title: legacy.error.CheckResult
 * </p>
 * <P>
 *     Description: We tried to make a monad out of this class, but we somehow failed.
 *     We thought that we could make it, but we couldn't. It's partly because we are such lazy guys,
 *     and partly because we don't have a clue what a monad stand for. But we think it's cool
 *     to talk about the fact that we like monad. When in any geeky discussion in a conference
 *     we say the word : "Monad", then suddenly all eyes are open in shock, and you can read the
 *     mind of the people around you "WTF ! is a monad, I don't know, Let's pretend I know, and nod
 *     my head". Well it was worth a try, now back to minesweeper.
 * </P>
 * @author gLagaffe
 * @version 1
 * @creationDate October 17, 2015
 */
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
}
