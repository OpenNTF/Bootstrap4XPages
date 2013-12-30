package biz.webgate.simplecontacts.api.storage;

import java.util.UUID;

import org.openntf.xpt.core.dss.AbstractStorageService;

import biz.webgate.simplecontacts.Address;

public class AddressStorageService extends AbstractStorageService<Address> {

	private static AddressStorageService m_Service;

	private AddressStorageService() {
	};

	public static AddressStorageService getInstance() {
		if (m_Service == null) {
			m_Service = new AddressStorageService();
		}
		return m_Service;
	}

	@Override
	public Address createObject() {
		Address adr = new Address();
		adr.setID(UUID.randomUUID().toString());
		return null;
	}

}
