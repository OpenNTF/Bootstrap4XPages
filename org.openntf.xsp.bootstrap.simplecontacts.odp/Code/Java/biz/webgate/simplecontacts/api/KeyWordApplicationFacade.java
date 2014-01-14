package biz.webgate.simplecontacts.api;

import java.io.Serializable;
import java.util.List;

public class KeyWordApplicationFacade implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<String> m_PhoneTypes;
	private List<String> m_EMailTypes;
	private List<String> m_AddressTypes;

	public void setPhoneTypes(List<String> phoneTypes) {
		m_PhoneTypes = phoneTypes;
	}

	public List<String> getPhoneTypes() {
		return m_PhoneTypes;
	}

	public void setEMailTypes(List<String> eMailTypes) {
		m_EMailTypes = eMailTypes;
	}

	public List<String> getEMailTypes() {
		return m_EMailTypes;
	}

	public void setAddressTypes(List<String> addressTypes) {
		m_AddressTypes = addressTypes;
	}

	public List<String> getAddressTypes() {
		return m_AddressTypes;
	}

}
