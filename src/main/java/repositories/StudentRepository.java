package repositories;

import domain.Department;
import domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByDepartmentName(String department);

    List<Student> findDistinctByGradesCourseName(String courseName);

    @Query("SELECT s FROM Student s WHERE s.department.name = :department")
    List<Student> findAllByDepartment(@Param("department") String departmentName);

    @Query("SELECT DISTINCT s FROM Student s JOIN s.grades g JOIN g.course c WHERE c.name = :courseName")
    List<Student> findAllByCourseName(@Param("courseName") String courseName);


}
