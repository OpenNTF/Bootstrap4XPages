package biz.webgate.simplecontacts;

import java.util.ArrayList;
import java.util.List;

import org.openntf.xpt.core.dss.annotations.DominoEntity;
import org.openntf.xpt.core.dss.annotations.DominoStore;

import biz.webgate.simplecontacts.api.ContactSessionFacade;
import biz.webgate.simplecontacts.api.storage.ContactStorageService;

@DominoStore(Form = "frmCompany", View = "LUPCompanyByID", PrimaryFieldClass = String.class, PrimaryKeyField = "ID")
public class Company extends AbstractAddressRelation {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@DominoEntity(FieldName = "CompanyName")
	private String m_CompanyName;
	private List<Contact> m_Contacts;

	@DominoEntity(FieldName = "HomePage")
	private String m_HomePage;
	@DominoEntity(FieldName = "Tags")
	private List<String> m_Tags;

	public String getCompanyName() {
		return m_CompanyName;
	}

	public void setCompanyName(String companyName) {
		m_CompanyName = companyName;
	}

	public String getHomePage() {
		return m_HomePage;
	}

	public void setHomePage(String homePage) {
		m_HomePage = homePage;
	}

	public List<String> getTags() {
		return m_Tags;
	}

	public void setTags(List<String> tags) {
		m_Tags = tags;
	}

	public void setContacts(List<Contact> contacts) {
		m_Contacts = contacts;
	}

	public List<Contact> getContacts() {
		if (m_Contacts == null) {
			m_Contacts = ContactStorageService.getInstance().getObjectsByForeignId(this.getID(),
					ContactSessionFacade.LUPCONTACT_BY_PARENT_ID);
		}
		return m_Contacts;
	}

	public void addContact(Contact con) {
		if (m_Contacts == null) {
			m_Contacts = new ArrayList<Contact>();
			m_Contacts.add(con);
			return;
		}
		int posOf = m_Contacts.indexOf(con);
		if (posOf > -1) {
			m_Contacts.set(posOf, con);
		} else {
			m_Contacts.add(con);
		}
	}

}
