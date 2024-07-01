package service;

import domain.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import repositories.CourseRepository;
import repositories.DepartmentRepository;
import repositories.GradeRepository;
import repositories.StudentRepository;

import java.util.List;

@Service
public class CommonService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    GradeRepository gradeRepository;

    @Transactional
    public void runActions(){
        createRecords();
        System.out.println("\n\nGet all students from a certain department");
        System.out.println(studentRepository.findByDepartmentName("Computer Science"));

        System.out.println("\n\nGet all students who took a course with a certain name.");
        System.out.println(studentRepository.findDistinctByGradesCourseName("Software Engineering"));

        System.out.println("\n\nGet all students from a certain department");
        System.out.println(studentRepository.findAllByDepartment("Computer Science"));

        System.out.println("\n\nGet all students who took a course with a certain name.");
        System.out.println(studentRepository.findAllByCourseName("Software Engineering"));
    }

    public void createRecords(){
        Department dept1 = new Department();
        dept1.setName("Computer Science");
        dept1 = departmentRepository.save(dept1);

        Department dept2 = new Department();
        dept2.setName("MBA");
        dept2 = departmentRepository.save(dept2);

        Course course1 = new Course();
        course1.setName("Enterprise Architecture");
        course1 = courseRepository.save(course1);

        Course course2 = new Course();
        course2.setName("Software Engineering");
        course2 = courseRepository.save(course2);

        Course course3 = new Course();
        course3.setName("MPP");
        course3 = courseRepository.save(course3);

        Student student1 = new Student();
        student1.setName("Dikshya Prasai");
        student1.setDepartment(dept1);

        Grade grade = new Grade();
        grade.setStudent(student1);
        grade.setGrade('A');
        grade.setCourse(course1);
        student1.addGrade(grade);

        grade = new Grade();
        grade.setStudent(student1);
        grade.setGrade('B');
        grade.setCourse(course2);
        student1.addGrade(grade);

        studentRepository.save(student1);

        Student student2 = new Student();
        student2.setName("Jane Henry");
        student2.setDepartment(dept1);

        grade = new Grade();
        grade.setStudent(student2);
        grade.setGrade('B');
        grade.setCourse(course1);
        student2.addGrade(grade);

        grade = new Grade();
        grade.setStudent(student2);
        grade.setGrade('A');
        grade.setCourse(course2);
        student2.addGrade(grade);

        studentRepository.save(student2);

        Student student3 = new Student();
        student3.setName("Yogen Pokhrel");
        student3.setDepartment(dept2);
        studentRepository.save(student3);
    }
}
