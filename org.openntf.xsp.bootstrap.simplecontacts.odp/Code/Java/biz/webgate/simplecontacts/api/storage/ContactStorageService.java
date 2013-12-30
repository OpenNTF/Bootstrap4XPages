package biz.webgate.simplecontacts.api.storage;

import java.util.UUID;

import org.openntf.xpt.core.dss.AbstractStorageService;

import biz.webgate.simplecontacts.Contact;

public class ContactStorageService extends AbstractStorageService<Contact> {

	private static ContactStorageService m_Service;

	private ContactStorageService() {

	}

	public static ContactStorageService getInstance() {
		if (m_Service == null) {
			m_Service = new ContactStorageService();
		}
		return m_Service;
	}

	@Override
	public Contact createObject() {
		Contact con = new Contact();
		con.setID(UUID.randomUUID().toString());
		return con;
	}

}
