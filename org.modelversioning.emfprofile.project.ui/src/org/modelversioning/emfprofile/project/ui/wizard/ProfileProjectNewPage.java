package org.modelversioning.emfprofile.project.ui.wizard;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;

public class ProfileProjectNewPage extends WizardNewProjectCreationPage {

	private ProfileProjectData data;
	private IStructuredSelection selection;
	private Text profileNameField;
	private Text profileNamespaceField;
	
	private Listener fieldModifyListener = new Listener() {
        public void handleEvent(Event e) {
            boolean valid = validatePage();
            setPageComplete(valid);
                
        }
    };

	public ProfileProjectNewPage(String pageName,
			ProfileProjectData data, IStructuredSelection selection) {
		super(pageName);
		this.selection = selection;
		this.data = data;
	}
	
	@Override
	public void createControl(Composite parent) {
		super.createControl(parent);
		Composite control = (Composite) getControl();
		GridLayout layout = new GridLayout();
		control.setLayout(layout);
		
		createProfileInformationGroup(control);
		createWorkingSetGroup(control, selection, new String[] {"org.eclipse.jdt.ui.JavaWorkingSetPage", //$NON-NLS-1$
				"org.eclipse.pde.ui.pluginWorkingSet", "org.eclipse.ui.resourceWorkingSetPage"}); //$NON-NLS-1$ //$NON-NLS-2$
		
		Dialog.applyDialogFont(control);
		setControl(control);
	}

	private void createProfileInformationGroup(Composite container) {
		Group group = new Group(container, SWT.NONE);
		group.setText("EMF Profile Information");
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		group.setLayout(layout);
		group.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		// profile name label
		Label profileLabel = new Label(group, SWT.NONE);
        profileLabel.setText("Profile name");
        profileLabel.setFont(container.getFont());
        
		// new profile name entry field
        profileNameField = new Text(group, SWT.BORDER);
        GridData data = new GridData(GridData.FILL_HORIZONTAL);
        data.widthHint = 250;
        profileNameField.setLayoutData(data);
        profileNameField.setFont(container.getFont());
        profileNameField.addListener(SWT.Modify, fieldModifyListener);
        
		// profile uri label
		Label profileURILabel = new Label(group, SWT.NONE);
        profileURILabel.setText("Profile namespace");
        profileURILabel.setFont(container.getFont());
        
		// new profile name entry field
        profileNamespaceField = new Text(group, SWT.BORDER);
        data = new GridData(GridData.FILL_HORIZONTAL);
        data.widthHint = 250;
        profileNamespaceField.setLayoutData(data);
        profileNamespaceField.setFont(container.getFont());
        profileNamespaceField.addListener(SWT.Modify, fieldModifyListener);
	}
	
	@Override
	protected boolean validatePage() {
		if (!super.validatePage()) return false;
		
        if (getProfileName().equals("")) { //$NON-NLS-1$
            setErrorMessage(null);
            setMessage("Enter a name for the profile.");
            return false;
        }
        
        if (getProfileNamespace().equals("")) { //$NON-NLS-1$
            setErrorMessage(null);
            setMessage("Enter a namespace for the profile.");
            return false;
        }
		
		setErrorMessage(null);
        setMessage(null);
        return true;
	}
	
	public String getProfileName() {
        if (profileNameField == null) {
			return ""; //$NON-NLS-1$
		}
        return profileNameField.getText().trim();
    }
	
	public String getProfileNamespace() {
        if (profileNamespaceField == null) {
			return ""; //$NON-NLS-1$
		}
        return profileNamespaceField.getText().trim();
    }
	
	public void updateData() {
		data.setProjectName(getProjectName());
		data.setProfileName(getProfileName());
		data.setProfileNamespace(getProfileNamespace());
		data.setProjectHandle(this.getProjectHandle());
		data.setLocationPath(getLocationPath());
	}

}
