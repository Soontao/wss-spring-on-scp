package corp.sap.hana.spring.wss.services.impl;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import corp.sap.hana.spring.wss.model.Teacher;
import corp.sap.hana.spring.wss.repository.TeacherRepository;
import corp.sap.hana.spring.wss.services.MainService;

@Service
@WebService(name = "Main")
public class MainServiceImpl implements MainService {
	
	@Autowired
	private TeacherRepository teacherRepository;

	public String sayHello(String myname) {
		try {
			return "Hello, Welcome to CXF Spring boot " + myname + "!!!";

		} catch (java.lang.Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}
	
	public List<Teacher> getAllTeachers() {
		return this.teacherRepository.findAll();
	}
	
	public Teacher createTeacher(Teacher teacher) {
		return this.teacherRepository.save(teacher);
	}
	
}
