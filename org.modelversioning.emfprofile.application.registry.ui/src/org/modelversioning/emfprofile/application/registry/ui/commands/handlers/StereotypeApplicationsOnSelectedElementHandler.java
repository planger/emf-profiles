/**
 * Copyright (c) 2012 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofile.application.registry.ui.commands.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.State;
import org.eclipse.ui.handlers.RegistryToggleState;
import org.modelversioning.emfprofile.application.registry.ui.observer.ActiveEditorObserver;

/**
 * @author <a href="mailto:becirb@gmail.com">Becir Basic</a>
 *
 */
public class StereotypeApplicationsOnSelectedElementHandler extends
		AbstractHandler implements IHandler {
	
	public static final String COMMAND_ID = "org.modelversioning.emfprofile.application.registry.ui.commands.stereotypeapplicationsonelement";
	
	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Command command = event.getCommand();
		State state = command.getState(RegistryToggleState.STATE_ID);
		if(state == null)
			throw new ExecutionException("The command does not have a toggle state"); //$NON-NLS-1$
		 if(!(state.getValue() instanceof Boolean))
			throw new ExecutionException("The command's toggle state doesn't contain a boolean value"); //$NON-NLS-1$
			 
		boolean oldValue = ((Boolean) state.getValue()).booleanValue();
		state.setValue(new Boolean(!oldValue));
		ActiveEditorObserver.INSTANCE.setActivateViewFilter(((Boolean)state.getValue()).booleanValue());
//		MessageDialog.openInformation(HandlerUtil.getActiveWorkbenchWindow(event).getShell(), "Info", "profile apps on selected element! State: " + state.getValue().toString());
		return null;
	}

}
