package biz.webgate.simplecontacts.api.storage;

import java.util.UUID;

import org.openntf.xpt.core.dss.AbstractStorageService;

import biz.webgate.simplecontacts.Company;

public class CompanyStorageService extends AbstractStorageService<Company> {

	private static CompanyStorageService m_Service;

	private CompanyStorageService() {

	}

	public static synchronized CompanyStorageService getInstance() {
		if (m_Service == null) {
			m_Service = new CompanyStorageService();
		}
		return m_Service;
	}

	@Override
	public Company createObject() {
		Company company = new Company();
		company.setID(UUID.randomUUID().toString());
		return company;
	}

}
