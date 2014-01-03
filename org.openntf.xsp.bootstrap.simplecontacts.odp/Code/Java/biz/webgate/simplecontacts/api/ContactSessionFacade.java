package biz.webgate.simplecontacts.api;

import java.util.List;

import biz.webgate.simplecontacts.AbstractAddressRelation;
import biz.webgate.simplecontacts.AbstractBusinessObject;
import biz.webgate.simplecontacts.Address;
import biz.webgate.simplecontacts.Company;
import biz.webgate.simplecontacts.Contact;
import biz.webgate.simplecontacts.EMail;
import biz.webgate.simplecontacts.Phone;
import biz.webgate.simplecontacts.SocialEntity;
import biz.webgate.simplecontacts.api.storage.AddressStorageService;
import biz.webgate.simplecontacts.api.storage.CompanyStorageService;
import biz.webgate.simplecontacts.api.storage.ContactStorageService;
import biz.webgate.simplecontacts.api.storage.EMailStorageService;
import biz.webgate.simplecontacts.api.storage.PhoneStorageService;
import biz.webgate.simplecontacts.api.storage.SocialStorageService;

public class ContactSessionFacade {

	public static final String BEAN_NAME = "contactBean";
	public static final String LUPCONTACT_BY_PARENT_ID = "LUPContactByParentID";
	public static final String LUPADDRESS_BY_PARENT_ID = "LUPAddressByParentID";
	public static final String LUPEMAIL_BY_PARENT_ID = "LUPEMailByParentID";
	public static final String LUPPHONE_BY_PARENT_ID = "LUPPhoneByParentID";
	public static final String LUPSOCIALENTITY_BY_PARENT_ID = "LUPSocialEntityByParentID";

	// Contact API
	public Contact createContact() {
		return ContactStorageService.getInstance().createObject();
	}

	public List<Contact> getAllContacts() {
		return ContactStorageService.getInstance().getAll("allContacts");
	}

	public void saveContact(Contact con) {
		ContactStorageService.getInstance().save(con);
	}

	public void deleteContact(Contact con) {
		try {
			ContactStorageService.getInstance().hardDelete(con, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Contact getContactByID(String strID) {
		return ContactStorageService.getInstance().getById(strID);
	}

	public void addTag(Contact con, String strTag) {
		con.addTag(strTag);
		saveContact(con);
	}

	public void addAddress(Contact con, Address addr) {
		con.addAddress(addr);
		saveAddress(addr);
	}

	public void addPhone(Contact con, Phone phone) {
		con.addPhone(phone);
		savePhone(phone);
	}

	public void addEMail(Contact con, EMail eml) {
		con.addEMail(eml);
		saveEMail(eml);
	}

	public void addSocial(Contact con, SocialEntity social) {
		con.addSocial(social);
		saveSocial(social);
	}

	public void addCompany(Contact con, Company comp) {
		con.setParentID(comp.getID());
		saveContact(con);

		// TODO: Company mit Contacts erweitern.
	}

	// Company API
	public Company createCompany() {
		return CompanyStorageService.getInstance().createObject();
	}

	public Company getCompanyByID(String strID) {
		return CompanyStorageService.getInstance().getById(strID);
	}

	public void saveCompany(Company comp) {
		CompanyStorageService.getInstance().save(comp);
	}

	public void deleteCompany(Company com) {
		try {
			CompanyStorageService.getInstance().hardDelete(com, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Address API
	public Address createNewAddress(String strParentID) {
		Address adr = AddressStorageService.getInstance().createObject();
		adr.setParentID(strParentID);
		return adr;
	}

	public Address getAddressByID(String strID) {
		return AddressStorageService.getInstance().getById(strID);
	}

	public void saveAddress(Address addr) {
		AddressStorageService.getInstance().save(addr);
	}

	public void deleteAddress(AbstractAddressRelation aar,Address adr) {
		try {
			AddressStorageService.getInstance().hardDelete(adr, true);
			aar.removeAddress(adr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// EMail API
	public EMail createNewEMail(String strParentID) {
		EMail eml = EMailStorageService.getInstance().createObject();
		eml.setParentID(strParentID);
		return eml;
	}

	public EMail getEMailByID(String strID) {
		return EMailStorageService.getInstance().getById(strID);
	}

	public void saveEMail(EMail email) {
		EMailStorageService.getInstance().save(email);
	}

	public void deleteEMail(AbstractAddressRelation aar, EMail eml) {
		try {
			EMailStorageService.getInstance().hardDelete(eml, true);
			aar.removeEMail(eml);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Phone API
	public Phone createNewPhone(String strParentID) {
		Phone phone = PhoneStorageService.getInstance().createObject();
		phone.setParentID(strParentID);
		return phone;
	}

	public Phone getPhoneByID(String strID) {
		return PhoneStorageService.getInstance().getById(strID);
	}

	public void savePhone(Phone phone) {
		PhoneStorageService.getInstance().save(phone);
	}

	public void deletePhone(AbstractAddressRelation aar,Phone phone) {
		try {
			PhoneStorageService.getInstance().hardDelete(phone, true);
			aar.removePhone(phone);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Social API
	public SocialEntity createNewSocial(String strParentID) {
		SocialEntity soc = SocialStorageService.getInstance().createObject();
		soc.setParentID(strParentID);
		return soc;
	}

	public SocialEntity getSocialByID(String strID) {
		return SocialStorageService.getInstance().getById(strID);
	}

	public void saveSocial(SocialEntity soc) {
		SocialStorageService.getInstance().save(soc);
	}

	public void deleteSocial(Contact con, SocialEntity social) {
		try {
			SocialStorageService.getInstance().hardDelete(social, true);
			con.removeSocial(social);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
