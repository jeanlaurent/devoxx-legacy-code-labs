package legacy.security;

public class UserSessionsManager {

	private static final UserSessionsManager usmgr;
	private ThreadLocal<User> local;

	static {
		usmgr = new UserSessionsManager();
	}

	public static UserSessionsManager getInstance() {
		return usmgr;
	}

	private UserSessionsManager() {
		super();
		local = new ThreadLocal<User>();
	}

	public void login(User user) {
		local.set(user);
	}

	public User getCurrentUser() {
		return (User) local.get();
	}
}
