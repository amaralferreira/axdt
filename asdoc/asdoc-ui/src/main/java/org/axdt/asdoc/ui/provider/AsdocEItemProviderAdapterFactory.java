/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.axdt.asdoc.ui.provider;

import java.util.ArrayList;
import java.util.Collection;

import org.axdt.asdoc.util.AsdocEAdapterFactory;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers.
 * The adapters generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}.
 * The adapters also support Eclipse property sheets.
 * Note that most of the adapters are shared among multiple instances.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class AsdocEItemProviderAdapterFactory extends AsdocEAdapterFactory implements ComposeableAdapterFactory, IChangeNotifier, IDisposable {
	/**
	 * This keeps track of the root adapter factory that delegates to this adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComposedAdapterFactory parentAdapterFactory;

	/**
	 * This is used to implement {@link org.eclipse.emf.edit.provider.IChangeNotifier}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IChangeNotifier changeNotifier = new ChangeNotifier();

	/**
	 * This keeps track of all the supported types checked by {@link #isFactoryForType isFactoryForType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Collection<Object> supportedTypes = new ArrayList<Object>();

	/**
	 * This constructs an instance.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AsdocEItemProviderAdapterFactory() {
		supportedTypes.add(IEditingDomainItemProvider.class);
		supportedTypes.add(IStructuredItemContentProvider.class);
		supportedTypes.add(ITreeItemContentProvider.class);
		supportedTypes.add(IItemLabelProvider.class);
		supportedTypes.add(IItemPropertySource.class);
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.axdt.asdoc.model.AsdocPackage} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AsdocPackageItemProvider asdocPackageItemProvider;

	/**
	 * This creates an adapter for a {@link org.axdt.asdoc.model.AsdocPackage}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createAsdocPackageAdapter() {
		if (asdocPackageItemProvider == null) {
			asdocPackageItemProvider = new AsdocPackageItemProvider(this);
		}

		return asdocPackageItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.axdt.asdoc.model.AsdocRoot} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AsdocRootItemProvider asdocRootItemProvider;

	/**
	 * This creates an adapter for a {@link org.axdt.asdoc.model.AsdocRoot}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createAsdocRootAdapter() {
		if (asdocRootItemProvider == null) {
			asdocRootItemProvider = new AsdocRootItemProvider(this);
		}

		return asdocRootItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.axdt.asdoc.model.AsdocClass} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AsdocClassItemProvider asdocClassItemProvider;

	/**
	 * This creates an adapter for a {@link org.axdt.asdoc.model.AsdocClass}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createAsdocClassAdapter() {
		if (asdocClassItemProvider == null) {
			asdocClassItemProvider = new AsdocClassItemProvider(this);
		}

		return asdocClassItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.axdt.asdoc.model.AsdocInterface} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AsdocInterfaceItemProvider asdocInterfaceItemProvider;

	/**
	 * This creates an adapter for a {@link org.axdt.asdoc.model.AsdocInterface}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createAsdocInterfaceAdapter() {
		if (asdocInterfaceItemProvider == null) {
			asdocInterfaceItemProvider = new AsdocInterfaceItemProvider(this);
		}

		return asdocInterfaceItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.axdt.asdoc.model.AsdocField} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AsdocFieldItemProvider asdocFieldItemProvider;

	/**
	 * This creates an adapter for a {@link org.axdt.asdoc.model.AsdocField}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createAsdocFieldAdapter() {
		if (asdocFieldItemProvider == null) {
			asdocFieldItemProvider = new AsdocFieldItemProvider(this);
		}

		return asdocFieldItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.axdt.asdoc.model.AsdocProperty} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AsdocPropertyItemProvider asdocPropertyItemProvider;

	/**
	 * This creates an adapter for a {@link org.axdt.asdoc.model.AsdocProperty}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createAsdocPropertyAdapter() {
		if (asdocPropertyItemProvider == null) {
			asdocPropertyItemProvider = new AsdocPropertyItemProvider(this);
		}

		return asdocPropertyItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.axdt.asdoc.model.AsdocNamespace} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AsdocNamespaceItemProvider asdocNamespaceItemProvider;

	/**
	 * This creates an adapter for a {@link org.axdt.asdoc.model.AsdocNamespace}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createAsdocNamespaceAdapter() {
		if (asdocNamespaceItemProvider == null) {
			asdocNamespaceItemProvider = new AsdocNamespaceItemProvider(this);
		}

		return asdocNamespaceItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.axdt.asdoc.model.AsdocParameter} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AsdocParameterItemProvider asdocParameterItemProvider;

	/**
	 * This creates an adapter for a {@link org.axdt.asdoc.model.AsdocParameter}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createAsdocParameterAdapter() {
		if (asdocParameterItemProvider == null) {
			asdocParameterItemProvider = new AsdocParameterItemProvider(this);
		}

		return asdocParameterItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.axdt.asdoc.model.AsdocConstructor} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AsdocConstructorItemProvider asdocConstructorItemProvider;

	/**
	 * This creates an adapter for a {@link org.axdt.asdoc.model.AsdocConstructor}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createAsdocConstructorAdapter() {
		if (asdocConstructorItemProvider == null) {
			asdocConstructorItemProvider = new AsdocConstructorItemProvider(this);
		}

		return asdocConstructorItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.axdt.asdoc.model.AsdocOperation} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AsdocOperationItemProvider asdocOperationItemProvider;

	/**
	 * This creates an adapter for a {@link org.axdt.asdoc.model.AsdocOperation}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createAsdocOperationAdapter() {
		if (asdocOperationItemProvider == null) {
			asdocOperationItemProvider = new AsdocOperationItemProvider(this);
		}

		return asdocOperationItemProvider;
	}

	/**
	 * This returns the root adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComposeableAdapterFactory getRootAdapterFactory() {
		return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory();
	}

	/**
	 * This sets the composed adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory) {
		this.parentAdapterFactory = parentAdapterFactory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object type) {
		return supportedTypes.contains(type) || super.isFactoryForType(type);
	}

	/**
	 * This implementation substitutes the factory itself as the key for the adapter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter adapt(Notifier notifier, Object type) {
		return super.adapt(notifier, this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object adapt(Object object, Object type) {
		if (isFactoryForType(type)) {
			Object adapter = super.adapt(object, type);
			if (!(type instanceof Class<?>) || (((Class<?>)type).isInstance(adapter))) {
				return adapter;
			}
		}

		return null;
	}

	/**
	 * This adds a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void addListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.addListener(notifyChangedListener);
	}

	/**
	 * This removes a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void removeListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.removeListener(notifyChangedListener);
	}

	/**
	 * This delegates to {@link #changeNotifier} and to {@link #parentAdapterFactory}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void fireNotifyChanged(Notification notification) {
		changeNotifier.fireNotifyChanged(notification);

		if (parentAdapterFactory != null) {
			parentAdapterFactory.fireNotifyChanged(notification);
		}
	}

	/**
	 * This disposes all of the item providers created by this factory. 
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void dispose() {
		if (asdocPackageItemProvider != null) asdocPackageItemProvider.dispose();
		if (asdocRootItemProvider != null) asdocRootItemProvider.dispose();
		if (asdocClassItemProvider != null) asdocClassItemProvider.dispose();
		if (asdocInterfaceItemProvider != null) asdocInterfaceItemProvider.dispose();
		if (asdocFieldItemProvider != null) asdocFieldItemProvider.dispose();
		if (asdocPropertyItemProvider != null) asdocPropertyItemProvider.dispose();
		if (asdocNamespaceItemProvider != null) asdocNamespaceItemProvider.dispose();
		if (asdocParameterItemProvider != null) asdocParameterItemProvider.dispose();
		if (asdocConstructorItemProvider != null) asdocConstructorItemProvider.dispose();
		if (asdocOperationItemProvider != null) asdocOperationItemProvider.dispose();
	}

}
