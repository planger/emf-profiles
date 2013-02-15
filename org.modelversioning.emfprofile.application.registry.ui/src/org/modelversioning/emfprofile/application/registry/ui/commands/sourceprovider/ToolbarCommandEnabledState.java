/**
 * Copyright (c) 2012 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofile.application.registry.ui.commands.sourceprovider;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.ui.AbstractSourceProvider;
import org.eclipse.ui.ISources;

/**
 * @author <a href="mailto:becirb@gmail.com">Becir Basic</a>
 *
 */
public class ToolbarCommandEnabledState extends AbstractSourceProvider {
	public static final String MY_STATE = "org.modelversioning.emfprofile.application.registry.ui.commands.sourceprovider.active";
	
	public final static String ENABLED = "ENABLED";
	  public final static String DISABLED = "DISABLED";
	  private boolean enabled = false;


	  @Override
	  public void dispose() {
	  }

	  // We could return several values but for this example one value is sufficient
	  @Override
	  public String[] getProvidedSourceNames() {
	    return new String[] { MY_STATE };
	  }
	  
	  // You cannot return NULL
	  @Override
	  public Map<String,String> getCurrentState() {
	    Map<String, String> map = new HashMap<>(1);
	    String value = enabled ? ENABLED : DISABLED;
	    map.put(MY_STATE, value);
	    return map;
	  }

	  public void setEnabled(boolean enabled){
		  this.enabled = enabled;
		  String value = enabled ? ENABLED : DISABLED;
		  fireSourceChanged(ISources.WORKBENCH, MY_STATE, value);
	  }
	  
}
