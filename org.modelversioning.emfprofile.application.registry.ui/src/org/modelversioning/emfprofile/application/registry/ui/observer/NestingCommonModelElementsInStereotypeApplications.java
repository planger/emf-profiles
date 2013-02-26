/**
 * Copyright (c) 2012 modelversioning.org
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 */
package org.modelversioning.emfprofile.application.registry.ui.observer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IContributionManager;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.SubContributionItem;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.modelversioning.emfprofile.application.registry.ProfileApplicationDecorator;
import org.modelversioning.emfprofileapplication.StereotypeApplication;

/**
 * @author <a href="mailto:becirb@gmail.com">Becir Basic</a>
 *
 */
public class NestingCommonModelElementsInStereotypeApplications implements
		ISelectionChangedListener, IMenuListener {
	
	public static final String NESTING_COMMON_MODEL_ELEMENTS_MENU_NAME = "Nesting Common Model Elements Menu";
	public static final String NESTING_COMMON_MODEL_ELEMENTS_MENU_ID = "EMFProfileApplicationRegistryUI_NestingCommonModelElementsMenuID";
	public static final String UI_CREATE_CHILD_MENU_ITEM = "&New Child";
	public static final String UI_CREATE_SIBLING_MENU_ITEM = "N&ew Sibling";

	/**
	 * This will contain one {@link org.eclipse.emf.edit.ui.action.CreateChildAction} corresponding to each descriptor
	 * generated for the current selection by the item provider.
	 */
	private Collection<IAction> createChildActions;

	/**
	 * This is the menu manager into which menu contribution items should be added for CreateChild actions.
	 */
	private IMenuManager createChildMenuManager;

	/**
	 * This will contain one {@link org.eclipse.emf.edit.ui.action.CreateSiblingAction} corresponding to each descriptor
	 * generated for the current selection by the item provider.
	 */
	private Collection<IAction> createSiblingActions;

	/**
	 * This is the menu manager into which menu contribution items should be added for CreateSibling actions.
	 */
	private IMenuManager createSiblingMenuManager;
	
	
	
	/**
	 * Default Constructor
	 */
	public NestingCommonModelElementsInStereotypeApplications() {
		super();
	}

	/**
	 * This adds to the context menu a menu and the sub-menus for object creation items.
	 */
	public void contributeToMenu(IMenuManager menuManager) {
//		super.contributeToMenu(menuManager);

		IMenuManager submenuManager = new MenuManager(NESTING_COMMON_MODEL_ELEMENTS_MENU_NAME, NESTING_COMMON_MODEL_ELEMENTS_MENU_ID);
		menuManager.insertAfter("additions", submenuManager);
		submenuManager.add(new Separator("settings"));
		submenuManager.add(new Separator("actions"));
		submenuManager.add(new Separator("additions"));
		submenuManager.add(new Separator("additions-end"));

		// Prepare for CreateChild item addition or removal.
		//
		createChildMenuManager = new MenuManager(UI_CREATE_CHILD_MENU_ITEM);
		submenuManager.insertBefore("additions", createChildMenuManager);

		// Prepare for CreateSibling item addition or removal.
		//
		createSiblingMenuManager = new MenuManager(UI_CREATE_SIBLING_MENU_ITEM);
		submenuManager.insertBefore("additions", createSiblingMenuManager);

		// Force an update because Eclipse hides empty menus now.
		//
		submenuManager.addMenuListener
			(new IMenuListener() {
				 public void menuAboutToShow(IMenuManager menuManager) {
					 menuManager.updateAll(true);
				 }
			 });
		
//		addGlobalActions(submenuManager);
	}

	
	/**
	 * This implements {@link org.eclipse.jface.viewers.ISelectionChangedListener},
	 * handling {@link org.eclipse.jface.viewers.SelectionChangedEvent}s by querying for the children and siblings
	 * that can be added to the selected object and updating the menus accordingly.
	 */
	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		if(ActiveEditorObserver.INSTANCE.getLastActiveEditorPart() == null){
			return;
		}
		
		if (createChildMenuManager != null) {
			depopulateManager(createChildMenuManager, createChildActions);
		}
		if (createSiblingMenuManager != null) {
			depopulateManager(createSiblingMenuManager, createSiblingActions);
		}

		ISelection selection = event.getSelection();
		if (selection instanceof IStructuredSelection && ((IStructuredSelection)selection).size() == 1) {
			EObject eObject = (EObject) ((IStructuredSelection)selection).getFirstElement();
			// if selected object is ProfileApplicationDecorator then return
			if(eObject instanceof ProfileApplicationDecorator){
				return;				
			}
			
			ProfileApplicationDecorator profileApplication = ActiveEditorObserver.INSTANCE.findProfileApplicationDecorator(
					eObject);
			createChildActions = generateCreateChildActions(eObject, profileApplication);
			
			if(eObject instanceof StereotypeApplication){
				createSiblingActions = Collections.emptyList();
			}else{
				createSiblingActions = generateCreateSiblingActions(eObject, profileApplication);
			}
		}

		if (createChildMenuManager != null) {
			populateManager(createChildMenuManager, createChildActions, null);
			createChildMenuManager.update(true);
		}
		if (createSiblingMenuManager != null) {
			populateManager(createSiblingMenuManager, createSiblingActions, null);
			createSiblingMenuManager.update(true);
		}
	}

	private Collection<IAction> generateCreateChildActions(final EObject eObject,
			final ProfileApplicationDecorator profileApplication) {
		ArrayList<IAction> actions = new ArrayList<>();
		EList<EReference> references = eObject.eClass().getEAllContainments();
		for (final EReference reference : references) {
			final EClass type = getInstantiableClass(reference.getEReferenceType());
			if(type == null 
					|| type.getName().equals("EAnnotation")
					|| type.getName().equals("ETypeParameter")
					|| type.getName().equals("EOperation")
					|| type.getName().equals("EAttribute")
					|| type.getName().equals("EGenericType")){
				continue;
			}
			IAction action = new Action() {
				@Override
				public void run() {
					EObject newEObject = EcoreUtil.create(type);
					profileApplication.addNestedEObject(eObject, reference, newEObject);
					
					ActiveEditorObserver.INSTANCE.refreshViewer(eObject);
					ActiveEditorObserver.INSTANCE.updateViewer(profileApplication);
					ActiveEditorObserver.INSTANCE.revealElement(newEObject);
					
					boolean isEnabled = false;
					if(reference.isMany()){
						if(reference.getUpperBound() == -1 || reference.getUpperBound() == -2)
							isEnabled = true;
						else if(((Collection<?>)eObject.eGet(reference)).size() < reference.getUpperBound() )
							isEnabled = true;
					}else{
						if(!eObject.eIsSet(reference))
							isEnabled = true;
					}
					setEnabled(isEnabled);
				}
				
			};
			action.setText(reference.getName() + " " + type.getName());
			action.setToolTipText(reference.getName() + " " + type.getName());
			action.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
					getImageDescriptor(ISharedImages.IMG_OBJ_ELEMENT));
			boolean isEnabled = false;
			if(reference.isMany()){
				if(reference.getUpperBound() == -1 || reference.getUpperBound() == -2)
					isEnabled = true;
				else if(((Collection<?>)eObject.eGet(reference)).size() < reference.getUpperBound())
					isEnabled = true;
			}else{
				if(!eObject.eIsSet(reference))
					isEnabled = true;
			}
			action.setEnabled(isEnabled);
			actions.add(action);
		}
		return actions;
	}
	
	private Collection<IAction> generateCreateSiblingActions(EObject eObject,
			ProfileApplicationDecorator profileApplication) {
		return generateCreateChildActions(eObject.eContainer(), profileApplication);
	}

	/**
	 * This populates the specified <code>manager</code> with {@link org.eclipse.jface.action.ActionContributionItem}s
	 * based on the {@link org.eclipse.jface.action.IAction}s contained in the <code>actions</code> collection,
	 * by inserting them before the specified contribution item <code>contributionID</code>.
	 * If <code>contributionID</code> is <code>null</code>, they are simply added.
	 */
	public void populateManager(IContributionManager manager, Collection<? extends IAction> actions, String contributionID) {
		if (actions != null) {
			for (IAction action : actions) {
				if (contributionID != null) {
					manager.insertBefore(contributionID, action);
				}
				else {
					manager.add(action);
				}
			}
		}
	}
		
	/**
	 * This removes from the specified <code>manager</code> all {@link org.eclipse.jface.action.ActionContributionItem}s
	 * based on the {@link org.eclipse.jface.action.IAction}s contained in the <code>actions</code> collection.
	 */
	public void depopulateManager(IContributionManager manager, Collection<? extends IAction> actions) {
		if (actions != null) {
			IContributionItem[] items = manager.getItems();
			for (int i = 0; i < items.length; i++) {
				// Look into SubContributionItems
				//
				IContributionItem contributionItem = items[i];
				while (contributionItem instanceof SubContributionItem) {
					contributionItem = ((SubContributionItem)contributionItem).getInnerItem();
				}

				// Delete the ActionContributionItems with matching action.
				//
				if (contributionItem instanceof ActionContributionItem) {
					IAction action = ((ActionContributionItem)contributionItem).getAction();
					if (actions.contains(action)) {
						manager.remove(contributionItem);
					}
				}
			}
		}
	}

	/**
	 * This populates the pop-up menu before it appears.
	 */
	@Override
	public void menuAboutToShow(IMenuManager menuManager) {
//		super.menuAboutToShow(menuManager);
		MenuManager submenuManager = null;

		submenuManager = new MenuManager(UI_CREATE_CHILD_MENU_ITEM);
		populateManager(submenuManager, createChildActions, null);
		menuManager.insertBefore("edit", submenuManager);

		submenuManager = new MenuManager(UI_CREATE_SIBLING_MENU_ITEM);
		populateManager(submenuManager, createSiblingActions, null);
		menuManager.insertBefore("edit", submenuManager);
	}

	private static EClass getInstantiableClass(EClass target){
		if(!target.isAbstract()) return target;
		List<?> classifiers = target.getEPackage().getEClassifiers();
		for (Object object : classifiers) {
			if(object instanceof EClass){
				EClass eClass = (EClass) object;
				if(!eClass.isAbstract() && target.isSuperTypeOf(eClass)){
					return eClass;
				}
			}
		}
		return null;
	}
	
//	/**
//	 * This inserts global actions before the "additions-end" separator.
//	 */
//	public void addGlobalActions(IMenuManager menuManager) {
//		menuManager.insertAfter("additions-end", new Separator("ui-actions"));
//		menuManager.insertAfter("ui-actions", showPropertiesViewAction);
//
//		refreshViewerAction.setEnabled(refreshViewerAction.isEnabled());		
//		menuManager.insertAfter("ui-actions", refreshViewerAction);
//
//		super.addGlobalActions(menuManager);
//	}
	
}
