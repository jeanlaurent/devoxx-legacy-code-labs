package legacy.security;

import legacy.persistence.BaseDTO;

/**
 * <p>
 *     Title: legacy.hedge.User
 * </p>
 *
 * user class enough said
 *
 * @author vHugo
 * @version 1.0
 * @creationDate May 7, 2007
 */
public class User extends BaseDTO {
	private String name;
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
