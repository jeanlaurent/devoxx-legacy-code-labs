package legacy.persistence;

import legacy.dto.Modif;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *     Title: legacy.persistence.ObjectDTO
 * </p>
 * <P>
 *     <b>Description</b>: Abstract class that defines the basic Data Transfer Object taht must be subclassed by all objects that
 *     could want to become a DTP (Data Transfer Object) You can't subclass hit if your object or class is not going
 *     to be a plain old DTO. This is often also called a POJO, but in legacy we prefer to call it a DTO.
 *     -- Michel & Sabrina & Shrikant
 *     <pre>
 *
 *     </pre>
 * </P>
 * @author mpolnareff
 * @author Shrikant
 *
 *
 * @version 1.0.1 -- added updateVersion for optimistic locking - Shrikant
 * @version 1.0 -- initial revision for 3.2 release with creation date added back from BaseDTO -- Sabrina
 * @version 0.1 -- initial version with updation Date -- Michel
 *
 *
 * @creationDate May 12, 2007
 */
public abstract class ObjectDTO  implements Serializable {

	private static final long serialVersionUID = -1L;
	/*
	 * this object's updation date
	 */
	private Date updateDate;
	/*
	 * this object's creation date
	 */
	private Date creDate;
	/*
	 * legacy.persistence.lastModification
	 */
	private Modif lastModificatin = null;
	/*
	 * this objets's audit version
	 */
	private int version = 0;
	/*
	 * specifies the update version number for optimistic lock
	 */
	private int updateVersion;

	public ObjectDTO() {
		this.creDate = new Date();
	}

	public Date getCreDate() {
		return creDate;
	}

	public void setCreDate(Date creDate) {
		this.creDate = creDate;
	}

	public Modif getLastModification() {
		return lastModificatin;
	}

	public void setLastModification(Modif lastModification) {
		this.lastModificatin = lastModificatin;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public int getUpdateVersion() {
		return updateVersion;
	}

	public void setUpdateVersion(int updateVersion) {
		this.updateVersion = updateVersion;
	}

	/**
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(Object)
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ObjectDTO o2 = (ObjectDTO) o;
		if (version != o2.version) return false;
		if (creDate != null ? !creDate.equals(o2.creDate) : o2.creDate != null) return false;
		if (updateDate != null ? !updateDate.equals(o2.updateDate) : o2.updateDate != null) return false;
		return true;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() { // hascode
		int result = updateDate != null ? updateDate.hashCode() : 0;
		result = 31 * result + (creDate != null ? creDate.hashCode() : 0);
		result = 31 * result + (updateDate != null ? updateDate.hashCode() : 0);
		result = 31 * result + version;
		return result;
	}

	// clone
	@Override
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

    @Override
    public String toString() {
        return "ObjectDTO{" +
                "updateDate=" + updateDate +
                ", lastModificatin=" + lastModificatin +
                ", version=" + version +
                ", updateVersion=" + updateVersion +
                '}';
    }
}
