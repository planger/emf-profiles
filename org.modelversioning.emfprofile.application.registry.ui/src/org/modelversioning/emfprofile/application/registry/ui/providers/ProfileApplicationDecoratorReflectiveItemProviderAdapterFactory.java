/**
 * Copyright (c) 2012 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofile.application.registry.ui.providers;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.modelversioning.emfprofile.application.registry.ui.observer.ActiveEditorObserver;

/**
 * @author <a href="mailto:becirb@gmail.com">Becir Basic</a>
 *
 */
public class ProfileApplicationDecoratorReflectiveItemProviderAdapterFactory
		extends ReflectiveItemProviderAdapterFactory {

	/**
	 * 
	 */
	public ProfileApplicationDecoratorReflectiveItemProviderAdapterFactory() {
		super();
	}

	@Override
	public void fireNotifyChanged(org.eclipse.emf.common.notify.Notification notification) {
		super.fireNotifyChanged(notification);
		int eventType = notification.getEventType();
		if(eventType == Notification.ADD 
				|| eventType == Notification.ADD_MANY
				|| eventType == Notification.MOVE
				|| eventType == Notification.REMOVE
				|| eventType == Notification.REMOVE_MANY
				|| eventType == Notification.SET
				|| eventType == Notification.UNSET
		)
		{
			ActiveEditorObserver.INSTANCE.setProfileApplicationChanged();
		}
		
	}
}
