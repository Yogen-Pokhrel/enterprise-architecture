package customers.repository;

import customers.domain.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends MongoRepository<Student, String> {
    List<Student> findStudentByName(@Param("name") String name);
    List<Student> findStudentByPhone(@Param("phone") String phone);
    List<Student> findStudentByAddressCity(@Param("city") String city);
    List<Student> findStudentsByGradesCourseName(@Param("name") String name);
    List<Student> findStudentsByGradesCourseNameAndGradesGrade(@Param("courseName") String courseName, @Param("grade") String grade);
}
