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


public abstract class AbstractBusinessObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@DominoEntity(FieldName="ID")
	private String m_ID = "";
	@DominoEntity(FieldName="ParentID")
	private String m_ParentID = "";

	public void setID(String iD) {
		m_ID = iD;
	}
	public String getID() {
		return m_ID;
	}
	public void setParentID(String parentID) {
		m_ParentID = parentID;
	}
	public String getParentID() {
		return m_ParentID;
	}
	@Override
	public boolean equals(Object obj) {
		return obj instanceof AbstractBusinessObject && m_ID.equals(((AbstractBusinessObject)obj).getID());
	}
	
	@Override
	public int hashCode() {
		return m_ID.hashCode();
	}
	
	@Override
	public String toString() {
		return getClass().getCanonicalName() +" / ID: "+ m_ID;
	}
}
