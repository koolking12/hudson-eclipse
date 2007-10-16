package dk.contix.eclipse.hudson.views.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.Shell;

public class SecurityTokenAction extends Action {

	private final Shell shell;

	public SecurityTokenAction(Shell shell) {
		this.shell = shell;
		setText("Set security token...");
		setToolTipText("Configure the security token used to schedule builds");
	}

	public void run() {

	}
}
