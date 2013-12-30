package biz.webgate.simplecontacts;

import java.io.Serializable;

public class SocialEntity extends AbstractBusinessObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String m_Service;
	private String m_UserName;
	private String m_URL;

	public String getService() {
		return m_Service;
	}

	public void setService(String service) {
		m_Service = service;
	}

	public String getUserName() {
		return m_UserName;
	}

	public void setUserName(String userName) {
		m_UserName = userName;
	}

	public String getURL() {
		return m_URL;
	}

	public void setURL(String url) {
		m_URL = url;
	}

}
