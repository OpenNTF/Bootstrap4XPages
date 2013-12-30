package biz.webgate.simplecontacts;

import java.io.Serializable;

public class Phone extends AbstractBusinessObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String m_Number;
	private String m_CountryCode;
	private String m_AreaCode;

	private String m_PhoneType;

	public String getNumber() {
		return m_Number;
	}

	public void setNumber(String number) {
		m_Number = number;
	}

	public String getCountryCode() {
		return m_CountryCode;
	}

	public void setCountryCode(String countryCode) {
		m_CountryCode = countryCode;
	}

	public String getAreaCode() {
		return m_AreaCode;
	}

	public void setAreaCode(String areaCode) {
		m_AreaCode = areaCode;
	}

	public String getPhoneType() {
		return m_PhoneType;
	}

	public void setPhoneType(String phoneType) {
		m_PhoneType = phoneType;
	}
	
	
}
