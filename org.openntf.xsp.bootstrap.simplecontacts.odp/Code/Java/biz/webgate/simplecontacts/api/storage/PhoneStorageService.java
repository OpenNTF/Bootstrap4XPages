package biz.webgate.simplecontacts.api.storage;

import java.util.UUID;

import org.openntf.xpt.core.dss.AbstractStorageService;

import biz.webgate.simplecontacts.Phone;

public class PhoneStorageService extends AbstractStorageService<Phone> {

	private static PhoneStorageService m_Service;

	private PhoneStorageService() {

	}

	public static synchronized PhoneStorageService getInstance() {
		if (m_Service == null) {
			m_Service = new PhoneStorageService();
		}
		return m_Service;
	}

	@Override
	public Phone createObject() {
		Phone phone = new Phone();
		phone.setID(UUID.randomUUID().toString());
		return phone;
	}

}
