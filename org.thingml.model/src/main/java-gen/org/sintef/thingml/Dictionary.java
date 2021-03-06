/**
 * Copyright (C) 2011 SINTEF <franck.fleurey@sintef.no>
 *
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE, Version 3, 29 June 2007;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sintef.thingml;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Dictionary</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.sintef.thingml.Dictionary#getIndexType <em>Index Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.sintef.thingml.ThingmlPackage#getDictionary()
 * @model
 * @generated
 */
public interface Dictionary extends Property {
	/**
	 * Returns the value of the '<em><b>Index Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Index Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Index Type</em>' reference.
	 * @see #setIndexType(Type)
	 * @see org.sintef.thingml.ThingmlPackage#getDictionary_IndexType()
	 * @model required="true"
	 * @generated
	 */
	Type getIndexType();

	/**
	 * Sets the value of the '{@link org.sintef.thingml.Dictionary#getIndexType <em>Index Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Index Type</em>' reference.
	 * @see #getIndexType()
	 * @generated
	 */
	void setIndexType(Type value);

} // Dictionary
