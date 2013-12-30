package biz.webgate.simplecontacts;

public abstract class AbstractBusinessObject {

	private String m_ID;
	private String m_ParentID;

	public void setID(String iD) {
		m_ID = iD;
	}
	public String getID() {
		return m_ID;
	}
	public void setParentID(String parentID) {
		m_ParentID = parentID;
	}
	public String getParentID() {
		return m_ParentID;
	}
}
