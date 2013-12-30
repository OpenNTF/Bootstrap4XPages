package biz.webgate.simplecontacts;

import java.util.ArrayList;
import java.util.List;

import biz.webgate.simplecontacts.api.ContactSessionFacade;
import biz.webgate.simplecontacts.api.storage.AddressStorageService;
import biz.webgate.simplecontacts.api.storage.EMailStorageService;
import biz.webgate.simplecontacts.api.storage.PhoneStorageService;

public abstract class AbstractAddressRelation extends AbstractBusinessObject {

	private List<Address> m_Address;
	private List<EMail> m_EMail;
	private List<Phone> m_Phone;

	public AbstractAddressRelation() {
		super();
	}

	public List<Address> getAddress() {
		if (m_Address == null) {
			m_Address = AddressStorageService.getInstance().getObjectsByForeignId(this.getID(),
					ContactSessionFacade.LUPADDRESS_BY_PARENT_ID);
		}
		return m_Address;
	}

	public void setAddress(List<Address> address) {
		m_Address = address;
	}

	public void addAddress(Address adr) {
		if (m_Address == null) {
			m_Address = new ArrayList<Address>();
		}
		m_Address.add(adr);
	}

	public List<EMail> getEMail() {
		if (m_EMail == null) {
			m_EMail = EMailStorageService.getInstance().getObjectsByForeignId(this.getID(), ContactSessionFacade.LUPEMAIL_BY_PARENT_ID);
		}
		return m_EMail;
	}

	public void addEMail(EMail eml) {
		if (m_EMail == null) {
			m_EMail = new ArrayList<EMail>();
		}
		m_EMail.add(eml);
	}

	public void setEMail(List<EMail> mail) {
		m_EMail = mail;
	}

	public List<Phone> getPhone() {
		if (m_Phone == null) {
			m_Phone = PhoneStorageService.getInstance().getObjectsByForeignId(this.getID(), ContactSessionFacade.LUPPHONE_BY_PARENT_ID);
		}
		return m_Phone;
	}

	public void setPhone(List<Phone> phone) {
		m_Phone = phone;
	}

	public void addPhone(Phone phone) {
		if (m_Phone == null) {
			m_Phone = new ArrayList<Phone>();
		}
		m_Phone.add(phone);
	}

}