package cu.uci.coj.model;

public class Roles {

	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	public static final String ROLE_SUPER_PSETTER = "ROLE_SUPER_PSETTER";
	public static final String ROLE_PSETTER = "ROLE_PSETTER";
	public static final String ROLE_USER = "ROLE_USER";
	public static final String ROLE_TEAM = "ROLE_TEAM";
	public static final String ROLE_FILE_MANAGER = "ROLE_FILE_MANAGER";
	public static final String ROLE_TRANSLATOR = "ROLE_TRANSLATOR";
	public static final String ROLE_ENTRIES_MANAGER = "ROLE_ENTRIES_MANAGER";
	@Deprecated
	public static final String[] ROLE_ADMIN_LIST = new String[] {
			Roles.ROLE_ADMIN, Roles.ROLE_SUPER_PSETTER, Roles.ROLE_PSETTER,
			 Roles.ROLE_FILE_MANAGER, Roles.ROLE_USER };
	@Deprecated
	public static final String[] ROLE_SUPER_PSETTER_LIST = new String[] {
			Roles.ROLE_SUPER_PSETTER, Roles.ROLE_PSETTER, 
			Roles.ROLE_FILE_MANAGER, Roles.ROLE_USER };
	@Deprecated
	public static final String[] ROLE_PSETTER_LIST = new String[] {
			Roles.ROLE_PSETTER, Roles.ROLE_FILE_MANAGER, Roles.ROLE_USER };
	@Deprecated
	public static final String[] ROLE_FILE_MANAGER_LIST = new String[] {
			Roles.ROLE_FILE_MANAGER, Roles.ROLE_USER };
	@Deprecated
	public static final String[] ROLE_USER_LIST = new String[] { Roles.ROLE_USER };
	@Deprecated
	public static final String[] ROLE_TEAM_LIST = new String[] { Roles.ROLE_TEAM };
	@Deprecated
	public static final String[] ROLE_TRANSLATOR_LIST = new String[] {
			Roles.ROLE_TRANSLATOR, Roles.ROLE_USER };
	@Deprecated
	public static final String[] ROLE_ENTRIES_MANAGER_LIST = new String[] {
			Roles.ROLE_ENTRIES_MANAGER, Roles.ROLE_USER };
	@Deprecated
	public static String[] authoritiesByRole(String authority) {
		switch (authority) {
		case ROLE_ADMIN:
			return ROLE_ADMIN_LIST;
		case ROLE_SUPER_PSETTER:
			return ROLE_SUPER_PSETTER_LIST;
		case ROLE_PSETTER:
			return ROLE_PSETTER_LIST;
		case ROLE_USER:
			return ROLE_USER_LIST;
		
		case ROLE_TEAM:
			return ROLE_TEAM_LIST;
		case ROLE_FILE_MANAGER:
			return ROLE_FILE_MANAGER_LIST;
		case ROLE_TRANSLATOR:
			return ROLE_TRANSLATOR_LIST;
		case ROLE_ENTRIES_MANAGER:
			return ROLE_ENTRIES_MANAGER_LIST;
		}
		return null;
	}
}
