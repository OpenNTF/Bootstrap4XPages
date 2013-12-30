/*
 * © Copyright WebGate Consulting AG, 2013
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at:
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or 
 * implied. See the License for the specific language governing 
 * permissions and limitations under the License.
 */
package biz.webgate.simplecontacts;

import java.io.Serializable;

import org.openntf.xpt.core.dss.annotations.DominoEntity;
import org.openntf.xpt.core.dss.annotations.DominoStore;

@DominoStore(Form="frmAddress",View="LUPAddressByID", PrimaryFieldClass=String.class,PrimaryKeyField="ID")
public class Address extends AbstractBusinessObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@DominoEntity(FieldName="Street")
	private String m_Street;
	@DominoEntity(FieldName="StreetAdd1")
	private String m_StreetAdd1;
	@DominoEntity(FieldName="StreetAdd2")
	private String m_StreetAdd2;
	@DominoEntity(FieldName="ZIP")
	private String m_ZIP;
	@DominoEntity(FieldName="Location")
	private String m_Location;
	@DominoEntity(FieldName="Country")
	private String m_Country;
	@DominoEntity(FieldName="State")
	private String m_State;
	@DominoEntity(FieldName="AddressType")
	private String m_AddressType;

	public void setStreet(String street) {
		m_Street = street;
	}

	public String getStreet() {
		return m_Street;
	}

	public String getStreetAdd1() {
		return m_StreetAdd1;
	}

	public void setStreetAdd1(String streetAdd1) {
		m_StreetAdd1 = streetAdd1;
	}

	public String getStreetAdd2() {
		return m_StreetAdd2;
	}

	public void setStreetAdd2(String streetAdd2) {
		m_StreetAdd2 = streetAdd2;
	}

	public String getZIP() {
		return m_ZIP;
	}

	public void setZIP(String zip) {
		m_ZIP = zip;
	}

	public String getLocation() {
		return m_Location;
	}

	public void setLocation(String location) {
		m_Location = location;
	}

	public String getCountry() {
		return m_Country;
	}

	public void setCountry(String country) {
		m_Country = country;
	}

	public String getState() {
		return m_State;
	}

	public void setState(String state) {
		m_State = state;
	}

	public String getAddressType() {
		return m_AddressType;
	}

	public void setAddressType(String addressType) {
		m_AddressType = addressType;
	}

}
