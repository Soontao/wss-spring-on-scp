package corp.sap.hana.spring.wss.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Class {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	private String id;

	private String className;
	private String credit;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "class_teacher_rel") // necessary
	private List<Teacher> teachers;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Class other = (Class) obj;
		if (className == null) {
			if (other.className != null)
				return false;
		} else if (!className.equals(other.className))
			return false;
		if (credit == null) {
			if (other.credit != null)
				return false;
		} else if (!credit.equals(other.credit))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (teachers == null) {
			if (other.teachers != null)
				return false;
		} else if (!teachers.equals(other.teachers))
			return false;
		return true;
	}

	/**
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * @return the credit
	 */
	public String getCredit() {
		return credit;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the teachers
	 */
	public List<Teacher> getTeachers() {
		return teachers;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((className == null) ? 0 : className.hashCode());
		result = prime * result + ((credit == null) ? 0 : credit.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((teachers == null) ? 0 : teachers.hashCode());
		return result;
	}

	/**
	 * @param className
	 *            the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * @param credit
	 *            the credit to set
	 */
	public void setCredit(String credit) {
		this.credit = credit;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param teachers
	 *            the teachers to set
	 */
	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Class [" + (id != null ? "id=" + id + ", " : "")
				+ (className != null ? "className=" + className + ", " : "")
				+ (credit != null ? "credit=" + credit + ", " : "")
				+ (teachers != null ? "teachers=" + teachers + ", " : "")
				+ (getClassName() != null ? "getClassName()=" + getClassName() + ", " : "")
				+ (getCredit() != null ? "getCredit()=" + getCredit() + ", " : "")
				+ (getId() != null ? "getId()=" + getId() + ", " : "")
				+ (getTeachers() != null ? "getTeachers()=" + getTeachers() + ", " : "")
				+ (getClass() != null ? "getClass()=" + getClass() + ", " : "") + "hashCode()=" + hashCode() + ", "
				+ (super.toString() != null ? "toString()=" + super.toString() : "") + "]";
	}

}
