/*
* generated by Xtext
*/
package org.axdt.as3.ui.labeling;

import java.util.List;

import org.axdt.as3.model.As3Class;
import org.axdt.as3.model.As3FieldBinding;
import org.axdt.as3.model.As3FieldDefinition;
import org.axdt.as3.model.As3Import;
import org.axdt.as3.model.As3ImportList;
import org.axdt.as3.model.As3Interface;
import org.axdt.as3.model.As3Namespace;
import org.axdt.as3.model.As3Operation;
import org.axdt.as3.model.As3Package;
import org.axdt.as3.model.As3Parameter;
import org.axdt.as3.model.As3PropertyIdentifier;
import org.axdt.as3.model.As3Type;
import org.axdt.as3.model.As3Variable;
import org.axdt.avm.model.AvmDeclaredTypeReference;
import org.axdt.avm.model.AvmGenericReference;
import org.axdt.avm.model.AvmParameter;
import org.axdt.avm.model.AvmType;
import org.axdt.avm.model.AvmTypeReference;
import org.axdt.avm.model.AvmVisibility;
import org.axdt.avm.model.AvmVoidReference;
import org.axdt.core.ui.img.AxdtImages;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.ui.label.DeclarativeLabelProvider;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

/**
 * see http://www.eclipse.org/Xtext/documentation/latest/xtext.html#labelProvider
 */
public class As3LabelProvider extends DeclarativeLabelProvider implements AxdtImages {
	Object image(As3Package ele) {
    	return PACKAGE;
    }
	Object text(As3Package ele) {
		return ele.getName() == null ? "Top Level" : ele.getName();
	}
	Object image(As3ImportList ele) {
    	return IMPORTS;
    }
	Object text(As3ImportList ele) {
    	return "imports";
    }
    Object image(As3Import ele) {
    	return IMPORT;
    }
    Object text(As3Import ele) {
    	return ele.getQualifiedName();
    }
    Object image(As3Class ele) {
    	return CLASS;
    }
    Object image(As3Interface ele) {
    	return INTERFACE;
    }
    Object text(As3Type ele) {
    	return ele.getName();
    }
    Object text(As3Operation ele) {
    	EList<As3Parameter> params = ele.getParameters();
    	StringBuilder result = new StringBuilder();
    	result.append(ele.getName()).append(doGetText(params));
    	if (ele.getReturnType() != null) {
    		result.append(':').append(doGetText(ele.getReturnType()));
    	}
    	return result.toString();
    }
    Object text(List<AvmParameter> params) {
    	if (params == null || params.size() == 0) return "()";
    	StringBuilder result = new StringBuilder();
    	for (AvmParameter param:params)
    		( result.length() == 0 
    		? result.append('(')
    		: result.append(", ")
    		).append(getText(param));
    	return result.append(')').toString();
    }
    Object text(AvmTypeReference ele) {
    	if (ele instanceof AvmGenericReference) return "*";
    	if (ele instanceof AvmVoidReference) return "void";
    	if (ele.getType() == null) return "";
    	return doGetText(ele.getType());
    }
    Object text(As3PropertyIdentifier ele) {
    	return ele.getName() != null
    		? ele.getName()
    		: doGetText(ele.getReference());
    }
    Object text(EObject eObject) {
    	if (eObject == null) return null;
    	List<EAttribute> list = Lists.transform(eObject.eClass().getEAllAttributes(), new Function<EAttribute, EAttribute>() {
			public EAttribute apply(EAttribute from) {
				if ("name".equals(from.getName()))
					return from;
				return null;
			}
    	});
    	for (EAttribute attr:list) {
    		Object value = eObject.eGet(attr);
			if (value != null) {
				return value.toString();
			}
    	}
		return null;
    }
    Object image(As3Operation ele) {
    	AvmVisibility visibility = ele.getVisibility();
		if (AvmVisibility.PUBLIC.equals(visibility))
			return METHOD_PUBLIC;
		if (AvmVisibility.PRIVATE.equals(visibility))
			return METHOD_PRIVATE;
		if (AvmVisibility.PROTECTED.equals(visibility))
			return METHOD_PROTECTED;
    	return METHOD_DEFAULT;
    }
    Object text(As3FieldBinding ele) {
    	return ele.getName()
			+ (ele.getType() != null ? ":"+getText(ele.getType()) : "");
    }
    Object text(AvmGenericReference ele) {
    	return "*";
    }
    Object text(AvmVoidReference ele) {
    	return "void";
    }
    Object text(AvmDeclaredTypeReference ele) {
    	AvmType type = ele.getType();
    	return type == null ? "null" : type.getName();
    }
    Object text(As3Namespace ele) {
    	return ele.getName();
    }
    Object image(As3Namespace ele) {
    	return NAMESPACE;
    }
    Object image(As3FieldBinding ele) {
		As3FieldDefinition def = (As3FieldDefinition) ele.eContainer();
		AvmVisibility visibility = def.getVisibility();
		if (AvmVisibility.PUBLIC.equals(visibility))
			return FIELD_PUBLIC;
		if (AvmVisibility.PRIVATE.equals(visibility))
			return FIELD_PRIVATE;
		if (AvmVisibility.PROTECTED.equals(visibility))
			return FIELD_PROTECTED;
   		return FIELD_DEFAULT;
    }
    Object text(As3Variable ele) {
    	return ele.getName()
			+ (ele.getType() != null ? ":"+doGetText(ele.getType()) : "");
    }
    Object image(As3Variable ele) {
    	return VARIABLE;
    }
}
