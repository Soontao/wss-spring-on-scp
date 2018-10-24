package corp.sap.hana.spring.wss.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Teacher {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2") // necessary
	private String id;

	private String teacherName;

	private Integer teacherAge;

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "teachers") // necessary
	private List<Class> classes;

	public List<Class> getClasses() {
		return classes;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the teacherAge
	 */
	public Integer getTeacherAge() {
		return teacherAge;
	}

	/**
	 * @return the teacherName
	 */
	public String getTeacherName() {
		return teacherName;
	}

	/**
	 * @param classes
	 *            the classes to set
	 */
	public void setClasses(List<Class> classes) {
		this.classes = classes;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param teacherAge
	 *            the teacherAge to set
	 */
	public void setTeacherAge(Integer teacherAge) {
		this.teacherAge = teacherAge;
	}

	/**
	 * @param teacherName
	 *            the teacherName to set
	 */
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Teacher [" + (id != null ? "id=" + id + ", " : "")
				+ (teacherName != null ? "teacherName=" + teacherName + ", " : "")
				+ (teacherAge != null ? "teacherAge=" + teacherAge : "") + "]";
	}

}
