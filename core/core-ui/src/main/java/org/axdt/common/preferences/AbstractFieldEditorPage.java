/*******************************************************************************
 * Copyright (c) 2010 Martin Schnabel <mb0@mb0.org>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.axdt.common.preferences;

import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

public abstract class AbstractFieldEditorPage extends FieldEditorPreferencePage {

	protected final PrefPage page;
	protected Composite content;
	
	public AbstractFieldEditorPage(PrefPage page) {
		super(GRID);
		this.page = page;
		setDescription(page.getDescription());
	}
	
	protected abstract IPreferenceStore retrievePreferenceStore(AbstractPreferences prefs);
	
	@Override
	protected void createFieldEditors() {
		content = getFieldEditorParent();
		content.setLayout(new GridLayout(1, false));
		FieldSpec<?>[] specs = page.getFieldSpecs();
		Composite tmp = content;
		for (int index = 0; index < specs.length; index++) {
			PrefGroup group = page.openGroup(index);
			if (group != null && isExcluded(group)) {
				index = group.getEnd()-1;
				continue;
			}
			tmp = openGroup(group, tmp);
			FieldSpec<?> spec = specs[index];
			FieldEditor field = createFieldFromSpec(spec, tmp);
			if (field != null) addField(field);
			tmp = closeGroup(page.closeGroup(index+1), tmp);
		}
	}

	protected boolean isExcluded(PrefGroup group) {
		return false;
	}

	@Override
	protected void adjustGridLayout() {
	}
	protected Composite closeGroup(PrefGroup closeGroup, Composite current) {
		if (closeGroup != null) {
			GridLayout layout = (GridLayout) current.getLayout();
			layout.marginHeight = 10;
			if (current instanceof Group)
				layout.marginWidth = 10;
			return current.getParent();
		}
		return current;
	}

	protected Composite openGroup(PrefGroup pref, Composite parent) {
		if (pref == null) return parent;
		Composite groupComp;
		if (pref.getTitle() == null) {
			groupComp = new Composite(parent, SWT.NONE);
		} else {
			Group group = new Group(parent, SWT.NONE);
			group.setText(pref.getTitle());
			groupComp = group;
		}
		groupComp.setData("keepLayout", pref.keepLayout());
		GridLayout layout = new GridLayout(1, false);
		groupComp.setLayout(layout);
		GridData data = new GridData(GridData.FILL_HORIZONTAL|GridData.GRAB_HORIZONTAL);
		groupComp.setLayoutData(data);
		return groupComp;
	}

	protected FieldEditor createFieldFromSpec(FieldSpec<?> spec, Composite composite) {
		if (Boolean.FALSE.equals(composite.getData("keepLayout"))) {
			composite = new Composite(composite, SWT.NONE);
			composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL|GridData.GRAB_HORIZONTAL));
		}
		return spec.createFieldEditor(composite);
	}
}
