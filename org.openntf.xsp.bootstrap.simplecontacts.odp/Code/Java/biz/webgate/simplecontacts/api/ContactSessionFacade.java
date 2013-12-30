package biz.webgate.simplecontacts.api;

import java.util.List;

import biz.webgate.simplecontacts.Address;
import biz.webgate.simplecontacts.Company;
import biz.webgate.simplecontacts.Contact;
import biz.webgate.simplecontacts.EMail;
import biz.webgate.simplecontacts.Phone;
import biz.webgate.simplecontacts.SocialEntity;

public class ContactSessionFacade {

	public static String BEAN_NAME = "contactBean";

	// Contact API
	public Contact createContact() {
		return null;
	}

	public List<Contact> getAllContacts() {
		return null;
	}

	public void saveContact(Contact con) {

	}

	public Contact getContactByID(String strID) {
		return null;
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
		return null;
	}

	public Company getCompanyByID(String strID) {
		return null;
	}

	public void saveCompany(Company comp) {

	}

	// Address API
	public Address createNewAddress(String strParentID) {
		return null;
	}

	public Address getAddressByID(String strID) {
		return null;
	}

	public void saveAddress(Address addr) {

	}

	// EMail API
	public EMail createNewEMail(String strParentID) {
		return null;
	}

	public EMail getEMailByID(String strID) {
		return null;
	}

	public void saveEMail(EMail email) {

	}

	// Phone API
	public Phone createNewPhone(String strParentID) {
		return null;
	}

	public Phone getPhoneByID(String strID) {
		return null;
	}

	public void savePhone(Phone phone) {

	}

	// Social API
	public SocialEntity createNewSocial(String strParentID) {
		return null;
	}

	public SocialEntity getSocialByID(String strID) {
		return null;
	}

	public void saveSocial(SocialEntity soc) {

	}
}
