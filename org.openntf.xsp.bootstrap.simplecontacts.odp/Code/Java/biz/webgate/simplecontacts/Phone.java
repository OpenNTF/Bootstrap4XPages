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

@DominoStore(Form = "frmPhone", View = "LUPPhoneByID", PrimaryKeyField = "ID", PrimaryFieldClass = String.class)
public class Phone extends AbstractBusinessObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@DominoEntity(FieldName="Number")
	private String m_Number;
	@DominoEntity(FieldName="CountryCode")
	private String m_CountryCode;
	@DominoEntity(FieldName="AreaCode")
	private String m_AreaCode;

	@DominoEntity(FieldName="PhoneType")
	private String m_PhoneType;

	public String getNumber() {
		return m_Number;
	}

	public void setNumber(String number) {
		m_Number = number;
	}

	public String getCountryCode() {
		return m_CountryCode;
	}

	public void setCountryCode(String countryCode) {
		m_CountryCode = countryCode;
	}

	public String getAreaCode() {
		return m_AreaCode;
	}

	public void setAreaCode(String areaCode) {
		m_AreaCode = areaCode;
	}

	public String getPhoneType() {
		return m_PhoneType;
	}

	public void setPhoneType(String phoneType) {
		m_PhoneType = phoneType;
	}

}
