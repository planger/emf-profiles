/**
 * Copyright (c) 2012 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofile.application.registry.ui.propertytester;

import org.eclipse.core.expressions.PropertyTester;
import org.modelversioning.emfprofile.application.registry.ProfileApplicationDecorator;

/**
 * @author <a href="mailto:becirb@gmail.com">Becir Basic</a>
 *
 */
public class CheckIfProfileApplicationIsDirty extends PropertyTester {

	/**
	 * 
	 */
	public CheckIfProfileApplicationIsDirty() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.expressions.IPropertyTester#test(java.lang.Object, java.lang.String, java.lang.Object[], java.lang.Object)
	 */
	@Override
	public boolean test(Object receiver, String property, Object[] args,
			Object expectedValue) {
		// this property tester is called from command framework only if receiver object is
		// instance of ProfileApplicationDecorator
		return ((ProfileApplicationDecorator)receiver).isDirty();
	}

}
