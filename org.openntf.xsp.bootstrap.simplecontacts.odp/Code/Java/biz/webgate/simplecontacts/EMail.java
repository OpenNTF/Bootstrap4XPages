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

@DominoStore(Form = "frmEMail", View="LUPEMailByID", PrimaryFieldClass=String.class, PrimaryKeyField="ID")
public class EMail extends AbstractBusinessObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@DominoEntity(FieldName="EMail")
	private String m_EMail;
	@DominoEntity(FieldName="EMailType")
	private String m_EMailType;
	public String getEMail() {
		return m_EMail;
	}
	public void setEMail(String mail) {
		m_EMail = mail;
	}
	public String getEMailType() {
		return m_EMailType;
	}
	public void setEMailType(String mailType) {
		m_EMailType = mailType;
	}
	
	
}
