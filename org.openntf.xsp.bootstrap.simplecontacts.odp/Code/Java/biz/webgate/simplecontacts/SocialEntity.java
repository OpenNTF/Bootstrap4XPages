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

@DominoStore(Form = "frmSocialEntity", View = "LUPSocialEntityByID", PrimaryFieldClass = String.class, PrimaryKeyField = "ID")
public class SocialEntity extends AbstractBusinessObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@DominoEntity(FieldName = "Service")
	private String m_Service;
	@DominoEntity(FieldName = "UserName")
	private String m_UserName;
	@DominoEntity(FieldName = "URL")
	private String m_URL;

	public String getService() {
		return m_Service;
	}

	public void setService(String service) {
		m_Service = service;
	}

	public String getUserName() {
		return m_UserName;
	}

	public void setUserName(String userName) {
		m_UserName = userName;
	}

	public String getURL() {
		return m_URL;
	}

	public void setURL(String url) {
		m_URL = url;
	}

}
