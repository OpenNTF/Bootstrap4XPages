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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openntf.xpt.core.dss.annotations.DominoEntity;
import org.openntf.xpt.core.dss.annotations.DominoStore;

import biz.webgate.simplecontacts.api.ContactSessionFacade;
import biz.webgate.simplecontacts.api.storage.SocialStorageService;

import com.ibm.xsp.http.MimeMultipart;

@DominoStore(Form = "frmContact", View = "LUPContactByID", PrimaryFieldClass = String.class, PrimaryKeyField = "ID")
public class Contact extends AbstractAddressRelation{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@DominoEntity(FieldName = "FirstName")
	private String m_FirstName;
	@DominoEntity(FieldName = "LastName")
	private String m_LastName;
	@DominoEntity(FieldName = "MiddleName")
	private String m_MiddleName;
	@DominoEntity(FieldName = "Title")
	private String m_Title;
	@DominoEntity(FieldName = "Birthday")
	private Date m_Birthday;
	@DominoEntity(FieldName = "Salutation")
	private String m_Salutation;
	@DominoEntity(FieldName = "Tags")
	private List<String> m_Tags;
	@DominoEntity(FieldName = "Comment")
	private MimeMultipart m_Comment;

	private List<SocialEntity> m_Social;

	public void setFirstName(String firstName) {
		m_FirstName = firstName;
	}

	public String getFirstName() {
		return m_FirstName;
	}

	public void setLastName(String lastName) {
		m_LastName = lastName;
	}

	public String getLastName() {
		return m_LastName;
	}

	public void setMiddleName(String middleName) {
		m_MiddleName = middleName;
	}

	public String getMiddleName() {
		return m_MiddleName;
	}

	public void setTitle(String title) {
		m_Title = title;
	}

	public String getTitle() {
		return m_Title;
	}

	public void setBirthday(Date birthday) {
		m_Birthday = birthday;
	}

	public Date getBirthday() {
		return m_Birthday;
	}

	public void setSalutation(String salutation) {
		m_Salutation = salutation;
	}

	public String getSalutation() {
		return m_Salutation;
	}

	public void setComment(MimeMultipart comment) {
		m_Comment = comment;
	}

	public MimeMultipart getComment() {
		return m_Comment;
	}

	public void setTags(List<String> tags) {
		m_Tags = tags;
	}

	public List<String> getTags() {
		return m_Tags;
	}

	public void addTag(String strTag) {
		if (m_Tags == null) {
			m_Tags = new ArrayList<String>();
		}
		m_Tags.add(strTag);
	}

	public void setSocial(List<SocialEntity> social) {
		m_Social = social;
	}

	public List<SocialEntity> getSocial() {
		if (m_Social == null) {
			m_Social = SocialStorageService.getInstance().getObjectsByForeignId(this.getID(),
					ContactSessionFacade.LUPSOCIALENTITY_BY_PARENT_ID);
		}
		return m_Social;
	}

	public void addSocial(SocialEntity soc) {
		if (m_Social == null) {
			m_Social = new ArrayList<SocialEntity>();
			m_Social.add(soc);
			return;
		}
		int posOf = m_Social.indexOf(soc);
		if (posOf > -1) {
			m_Social.set(posOf, soc);
		} else {
			m_Social.add(soc);
		}
	}

	public void removeSocial(SocialEntity social) {
		if (m_Social != null) {
			m_Social.remove(social);
		}

	}

}
