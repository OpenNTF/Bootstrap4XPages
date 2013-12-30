package biz.webgate.simplecontacts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ibm.xsp.http.MimeMultipart;

public class Contact extends AbstractBusinessObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String m_FirstName;
	private String m_LastName;
	private String m_MiddleName;
	private String m_Title;
	private Date m_Birthday;
	private String m_Salutation;
	private List<String> m_Tags;
	private MimeMultipart m_Comment;

	public void setFirstName(String firstName) {
		m_FirstName = firstName;
	}

	public String getFirstName() {
		return m_FirstName;
	}

	public void setLastName(String lastName) {
		m_LastName = lastName;
	}

	public String getLastName() {
		return m_LastName;
	}

	public void setMiddleName(String middleName) {
		m_MiddleName = middleName;
	}

	public String getMiddleName() {
		return m_MiddleName;
	}

	public void setTitle(String title) {
		m_Title = title;
	}

	public String getTitle() {
		return m_Title;
	}

	public void setBirthday(Date birthday) {
		m_Birthday = birthday;
	}

	public Date getBirthday() {
		return m_Birthday;
	}

	public void setSalutation(String salutation) {
		m_Salutation = salutation;
	}

	public String getSalutation() {
		return m_Salutation;
	}

	public void setComment(MimeMultipart comment) {
		m_Comment = comment;
	}

	public MimeMultipart getComment() {
		return m_Comment;
	}

	public void setTags(List<String> tags) {
		m_Tags = tags;
	}

	public List<String> getTags() {
		return m_Tags;
	}

	public void addTag(String strTag) {
		if (m_Tags == null) {
			m_Tags = new ArrayList<String>();
		}
		m_Tags.add(strTag);
	}

}
