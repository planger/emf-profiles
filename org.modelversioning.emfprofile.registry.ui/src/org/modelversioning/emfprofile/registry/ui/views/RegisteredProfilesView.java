package org.modelversioning.emfprofile.registry.ui.views;

import java.util.Observable;
import java.util.Observer;

import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.part.DrillDownAdapter;
import org.eclipse.ui.part.ViewPart;
import org.modelversioning.emfprofile.registry.IProfileRegistry;
import org.modelversioning.emfprofile.registry.ui.EMFProfileRegistryUIPlugin;
import org.modelversioning.emfprofile.registry.ui.provider.ProfileProviderContentAdapter;
import org.modelversioning.emfprofile.registry.ui.provider.ProfileProviderLabelAdapter;

public class RegisteredProfilesView extends ViewPart implements Observer {

	public static final String ID = "org.modelversioning.emfprofile.registry.ui.views.RegisteredProfilesView"; //$NON-NLS-1$

	private TreeViewer viewer;
	private DrillDownAdapter drillDownAdapter;

	private IProfileRegistry profileRegistry;

	public RegisteredProfilesView() {
		profileRegistry = IProfileRegistry.eINSTANCE;
		profileRegistry.addObserver(this);
	}

	public void createPartControl(Composite parent) {
		viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		drillDownAdapter = new DrillDownAdapter(viewer);
		viewer.setContentProvider(new ProfileProviderContentAdapter());
		viewer.setLabelProvider(new ProfileProviderLabelAdapter());
		viewer.setSorter(new ViewerSorter());
		viewer.setInput(profileRegistry.getRegisteredProfileProviders());
		hookContextMenu();
	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				RegisteredProfilesView.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}

	private void fillContextMenu(IMenuManager manager) {
		drillDownAdapter.addNavigationActions(manager);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	public void setFocus() {
		viewer.getControl().setFocus();
	}

	@Override
	public void update(Observable o, Object arg) {
		refreshViewer();
	}

	private void refreshViewer() {
		EMFProfileRegistryUIPlugin.getDisplay().syncExec(new Runnable() {
			public void run() {
				viewer.refresh(true);
			}
		});
	}
}