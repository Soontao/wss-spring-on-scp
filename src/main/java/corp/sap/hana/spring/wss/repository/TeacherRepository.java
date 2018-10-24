package corp.sap.hana.spring.wss.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import corp.sap.hana.spring.wss.model.Teacher;

public interface TeacherRepository extends PagingAndSortingRepository<Teacher, String> {
	List<Teacher> findAll();
}
