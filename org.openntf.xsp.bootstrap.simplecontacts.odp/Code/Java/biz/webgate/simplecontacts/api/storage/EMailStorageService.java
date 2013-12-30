package biz.webgate.simplecontacts.api.storage;

import java.util.UUID;

import org.openntf.xpt.core.dss.AbstractStorageService;

import biz.webgate.simplecontacts.EMail;

public class EMailStorageService extends AbstractStorageService<EMail> {

	private static EMailStorageService m_Service;

	private EMailStorageService() {

	}

	public static EMailStorageService getInstance() {
		if (m_Service == null) {
			m_Service = new EMailStorageService();
		}
		return m_Service;
	}

	@Override
	public EMail createObject() {
		EMail eml = new EMail();
		eml.setID(UUID.randomUUID().toString());
		return eml;
	}

}
