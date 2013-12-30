package biz.webgate.simplecontacts.api.storage;

import java.util.UUID;

import org.openntf.xpt.core.dss.AbstractStorageService;

import biz.webgate.simplecontacts.SocialEntity;

public class SocialStorageService extends AbstractStorageService<SocialEntity> {

	private static SocialStorageService m_Service;

	private SocialStorageService() {

	}

	public static SocialStorageService getInstance() {
		if (m_Service == null) {
			m_Service = new SocialStorageService();
		}
		return m_Service;
	}

	@Override
	public SocialEntity createObject() {
		SocialEntity social = new SocialEntity();
		social.setID(UUID.randomUUID().toString());
		return social;
	}

}
