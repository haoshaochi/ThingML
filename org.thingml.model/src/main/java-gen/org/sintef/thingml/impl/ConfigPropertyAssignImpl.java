/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.sintef.thingml.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.sintef.thingml.ConfigPropertyAssign;
import org.sintef.thingml.Expression;
import org.sintef.thingml.InstanceRef;
import org.sintef.thingml.Property;
import org.sintef.thingml.ThingmlPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Config Property Assign</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.sintef.thingml.impl.ConfigPropertyAssignImpl#getInit <em>Init</em>}</li>
 *   <li>{@link org.sintef.thingml.impl.ConfigPropertyAssignImpl#getProperty <em>Property</em>}</li>
 *   <li>{@link org.sintef.thingml.impl.ConfigPropertyAssignImpl#getInstance <em>Instance</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConfigPropertyAssignImpl extends AnnotatedElementImpl implements ConfigPropertyAssign {
	/**
	 * The cached value of the '{@link #getInit() <em>Init</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInit()
	 * @generated
	 * @ordered
	 */
	protected Expression init;

	/**
	 * The cached value of the '{@link #getProperty() <em>Property</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProperty()
	 * @generated
	 * @ordered
	 */
	protected Property property;

	/**
	 * The cached value of the '{@link #getInstance() <em>Instance</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInstance()
	 * @generated
	 * @ordered
	 */
	protected InstanceRef instance;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConfigPropertyAssignImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ThingmlPackage.Literals.CONFIG_PROPERTY_ASSIGN;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getInit() {
		return init;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInit(Expression newInit, NotificationChain msgs) {
		Expression oldInit = init;
		init = newInit;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ThingmlPackage.CONFIG_PROPERTY_ASSIGN__INIT, oldInit, newInit);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInit(Expression newInit) {
		if (newInit != init) {
			NotificationChain msgs = null;
			if (init != null)
				msgs = ((InternalEObject)init).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ThingmlPackage.CONFIG_PROPERTY_ASSIGN__INIT, null, msgs);
			if (newInit != null)
				msgs = ((InternalEObject)newInit).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ThingmlPackage.CONFIG_PROPERTY_ASSIGN__INIT, null, msgs);
			msgs = basicSetInit(newInit, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ThingmlPackage.CONFIG_PROPERTY_ASSIGN__INIT, newInit, newInit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Property getProperty() {
		if (property != null && property.eIsProxy()) {
			InternalEObject oldProperty = (InternalEObject)property;
			property = (Property)eResolveProxy(oldProperty);
			if (property != oldProperty) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ThingmlPackage.CONFIG_PROPERTY_ASSIGN__PROPERTY, oldProperty, property));
			}
		}
		return property;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Property basicGetProperty() {
		return property;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProperty(Property newProperty) {
		Property oldProperty = property;
		property = newProperty;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ThingmlPackage.CONFIG_PROPERTY_ASSIGN__PROPERTY, oldProperty, property));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InstanceRef getInstance() {
		return instance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInstance(InstanceRef newInstance, NotificationChain msgs) {
		InstanceRef oldInstance = instance;
		instance = newInstance;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ThingmlPackage.CONFIG_PROPERTY_ASSIGN__INSTANCE, oldInstance, newInstance);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInstance(InstanceRef newInstance) {
		if (newInstance != instance) {
			NotificationChain msgs = null;
			if (instance != null)
				msgs = ((InternalEObject)instance).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ThingmlPackage.CONFIG_PROPERTY_ASSIGN__INSTANCE, null, msgs);
			if (newInstance != null)
				msgs = ((InternalEObject)newInstance).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ThingmlPackage.CONFIG_PROPERTY_ASSIGN__INSTANCE, null, msgs);
			msgs = basicSetInstance(newInstance, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ThingmlPackage.CONFIG_PROPERTY_ASSIGN__INSTANCE, newInstance, newInstance));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ThingmlPackage.CONFIG_PROPERTY_ASSIGN__INIT:
				return basicSetInit(null, msgs);
			case ThingmlPackage.CONFIG_PROPERTY_ASSIGN__INSTANCE:
				return basicSetInstance(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ThingmlPackage.CONFIG_PROPERTY_ASSIGN__INIT:
				return getInit();
			case ThingmlPackage.CONFIG_PROPERTY_ASSIGN__PROPERTY:
				if (resolve) return getProperty();
				return basicGetProperty();
			case ThingmlPackage.CONFIG_PROPERTY_ASSIGN__INSTANCE:
				return getInstance();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ThingmlPackage.CONFIG_PROPERTY_ASSIGN__INIT:
				setInit((Expression)newValue);
				return;
			case ThingmlPackage.CONFIG_PROPERTY_ASSIGN__PROPERTY:
				setProperty((Property)newValue);
				return;
			case ThingmlPackage.CONFIG_PROPERTY_ASSIGN__INSTANCE:
				setInstance((InstanceRef)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ThingmlPackage.CONFIG_PROPERTY_ASSIGN__INIT:
				setInit((Expression)null);
				return;
			case ThingmlPackage.CONFIG_PROPERTY_ASSIGN__PROPERTY:
				setProperty((Property)null);
				return;
			case ThingmlPackage.CONFIG_PROPERTY_ASSIGN__INSTANCE:
				setInstance((InstanceRef)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ThingmlPackage.CONFIG_PROPERTY_ASSIGN__INIT:
				return init != null;
			case ThingmlPackage.CONFIG_PROPERTY_ASSIGN__PROPERTY:
				return property != null;
			case ThingmlPackage.CONFIG_PROPERTY_ASSIGN__INSTANCE:
				return instance != null;
		}
		return super.eIsSet(featureID);
	}

} //ConfigPropertyAssignImpl