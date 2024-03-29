package dk.contix.eclipse.hudson.views.actions;

import org.eclipse.jface.preference.BooleanPropertyAction;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.widgets.Event;

import dk.contix.eclipse.hudson.Activator;

public class FilterAction extends BooleanPropertyAction {

	private final TableViewer viewer;

	private final ViewerFilter filter;

	public FilterAction(TableViewer viewer, String title, String tooltip, String property, ViewerFilter filter) {
		super(title, Activator.getDefault().getPreferenceStore(), property);
		this.viewer = viewer;
		this.filter = filter;

		setToolTipText(tooltip);
		viewer.addFilter(filter);
	}

	public void runWithEvent(Event event) {
		super.runWithEvent(event);

		viewer.removeFilter(filter);
		viewer.addFilter(filter);
	}

}
