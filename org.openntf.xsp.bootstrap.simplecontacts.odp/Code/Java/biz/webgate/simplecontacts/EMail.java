package biz.webgate.simplecontacts;

import java.io.Serializable;

public class EMail extends AbstractBusinessObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String m_EMail;
	private String m_EMailType;
	public String getEMail() {
		return m_EMail;
	}
	public void setEMail(String mail) {
		m_EMail = mail;
	}
	public String getEMailType() {
		return m_EMailType;
	}
	public void setEMailType(String mailType) {
		m_EMailType = mailType;
	}
	
	
}
