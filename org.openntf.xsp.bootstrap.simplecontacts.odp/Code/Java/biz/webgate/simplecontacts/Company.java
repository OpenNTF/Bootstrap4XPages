package biz.webgate.simplecontacts;

import java.io.Serializable;
import java.util.List;

public class Company extends AbstractBusinessObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String m_CompanyName;
	private List<Address> m_Address;
	private List<EMail> m_EMail;
	private List<Phone> m_Phone;

	private String m_HomePage;
	private List<String> m_Tags;

	public String getCompanyName() {
		return m_CompanyName;
	}

	public void setCompanyName(String companyName) {
		m_CompanyName = companyName;
	}

	public List<Address> getAddress() {
		return m_Address;
	}

	public void setAddress(List<Address> address) {
		m_Address = address;
	}

	public List<EMail> getEMail() {
		return m_EMail;
	}

	public void setEMail(List<EMail> mail) {
		m_EMail = mail;
	}

	public List<Phone> getPhone() {
		return m_Phone;
	}

	public void setPhone(List<Phone> phone) {
		m_Phone = phone;
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

}
