package legacy.dto;

import legacy.persistence.BaseDTO;

import java.util.Date;

public class Modif extends BaseDTO {

	private String auditableClassName = null;
	private int DTOID= 0x0;
	private Date modificationDate;
	private String user = null;
	private int version = 0;

	public String getAuditableClassName() {
		return auditableClassName;
	}

	public void setAuditableClassName(String auditableClassName) {
		this.auditableClassName = auditableClassName;
	}

	public int getDTOID() {
		return DTOID;
	}

	public void setDTOID(int DTOID) {
		this.DTOID = DTOID;
	}

	public Date getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
}
