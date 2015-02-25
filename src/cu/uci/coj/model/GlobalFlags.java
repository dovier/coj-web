package cu.uci.coj.model;

public class GlobalFlags {

    private boolean enabled_source_code_view;
    private boolean enabled_mail;
    private boolean enabled_submission;
    private boolean maintenanceMode;
    private boolean mailNotificationDisabled;

    public GlobalFlags() {
    }

    public GlobalFlags(boolean mailNotificationDisabled,boolean enabled_source_code_view, boolean enabled_mail, boolean enabled_submission,boolean maintenanceMode) {
        this.enabled_source_code_view = enabled_source_code_view;
        this.enabled_mail = enabled_mail;
        this.enabled_submission = enabled_submission;
        this.maintenanceMode=maintenanceMode;
        this.mailNotificationDisabled = mailNotificationDisabled;
    }

    



	public boolean isMailNotificationDisabled() {
		return mailNotificationDisabled;
	}

	public void setMailNotificationDisabled(boolean mailNotificationDisabled) {
		this.mailNotificationDisabled = mailNotificationDisabled;
	}

	public boolean isMaintenanceMode() {
		return maintenanceMode;
	}

	public void setMaintenanceMode(boolean maintenanceMode) {
		this.maintenanceMode = maintenanceMode;
	}


	public boolean isEnabled_mail() {
		return enabled_mail;
	}

	public void setEnabled_mail(boolean enabled_mail) {
		this.enabled_mail = enabled_mail;
	}

	public boolean isEnabled_source_code_view() {
        return enabled_source_code_view;
    }

    public void setEnabled_source_code_view(boolean enabled_source_code_view) {
        this.enabled_source_code_view = enabled_source_code_view;
    }

    public boolean isEnabled_submission() {
        return enabled_submission;
    }

    public void setEnabled_submission(boolean enabled_submission) {
        this.enabled_submission = enabled_submission;
    }
}
