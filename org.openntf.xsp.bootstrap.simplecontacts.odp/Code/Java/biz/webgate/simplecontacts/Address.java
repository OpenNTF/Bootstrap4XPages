package biz.webgate.simplecontacts;

import java.io.Serializable;


public class Address extends AbstractBusinessObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String m_Street;
	private String m_StreetAdd1;
	private String m_StreetAdd2;
	private String m_ZIP;
	private String m_Location;
	private String m_Country;
	private String m_State;

	private String m_AddressType;

	public void setStreet(String street) {
		m_Street = street;
	}

	public String getStreet() {
		return m_Street;
	}

	public String getStreetAdd1() {
		return m_StreetAdd1;
	}

	public void setStreetAdd1(String streetAdd1) {
		m_StreetAdd1 = streetAdd1;
	}

	public String getStreetAdd2() {
		return m_StreetAdd2;
	}

	public void setStreetAdd2(String streetAdd2) {
		m_StreetAdd2 = streetAdd2;
	}

	public String getZIP() {
		return m_ZIP;
	}

	public void setZIP(String zip) {
		m_ZIP = zip;
	}

	public String getLocation() {
		return m_Location;
	}

	public void setLocation(String location) {
		m_Location = location;
	}

	public String getCountry() {
		return m_Country;
	}

	public void setCountry(String country) {
		m_Country = country;
	}

	public String getState() {
		return m_State;
	}

	public void setState(String state) {
		m_State = state;
	}

	public String getAddressType() {
		return m_AddressType;
	}

	public void setAddressType(String addressType) {
		m_AddressType = addressType;
	}

}
