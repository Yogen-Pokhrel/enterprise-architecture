package customers;

import customers.domain.*;
import customers.repository.CustomerRepository;
import customers.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;


@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private StudentRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
        // create customer
		Customer customer = new Customer(101,"John doe", "johnd@acme.com", "0622341678");
		CreditCard creditCard = new CreditCard("12324564321", "Visa", "11/23");
		customer.setCreditCard(creditCard);
		customerRepository.save(customer);
		customer = new Customer(109,"John Jones", "jones@acme.com", "0624321234");
		creditCard = new CreditCard("657483342", "Visa", "09/23");
		customer.setCreditCard(creditCard);
		customerRepository.save(customer);
		customer = new Customer(66,"James Johnson", "jj123@acme.com", "068633452");
		creditCard = new CreditCard("99876549876", "MasterCard", "01/24");
		customer.setCreditCard(creditCard);
		customerRepository.save(customer);
		//get customers
		System.out.println(customerRepository.findById(66).get());
		System.out.println(customerRepository.findById(101).get());
		System.out.println("-----------All customers ----------------");
		System.out.println(customerRepository.findAll());
		//update customer
		customer = customerRepository.findById(101).get();
		customer.setEmail("jd@gmail.com");
		customerRepository.save(customer);
		System.out.println("-----------find by phone ----------------");
		System.out.println(customerRepository.findByPhone("0622341678"));
		System.out.println("-----------find by email ----------------");
		System.out.println(customerRepository.findCustomerWithEmail("jj123@acme.com"));
		System.out.println("-----------find customers with a certain type of creditcard ----------------");
		List<Customer> customers = customerRepository.findByCreditCardType("Visa");
		for (Customer cust : customers){
			System.out.println(cust);
		}

		saveStudents();
		studentsTest();
	}

	private void saveStudents(){
		Student student = new Student();
		Address address = new Address();
		Grade grade = new Grade();

		student = new Student();
		student.setStudentNumber(617585L);
		student.setName("Yogen");
		student.setPhone("0622341678");
		address.setStreet("123 Main St");
		address.setCity("San Francisco");
		address.setZip("657");
		student.setAddress(address);

		grade.setCourseName("EA");
		grade.setGrade("A+");
		student.getGrades().add(grade);
		studentRepository.save(student);

		student = new Student();
		student.setStudentNumber(617586L);
		student.setName("Dikshya");
		student.setPhone("0622341679");
		address.setStreet("124 Main St");
		address.setCity("San Francisco");
		address.setZip("658");
		student.setAddress(address);

		grade.setCourseName("EA");
		grade.setGrade("A+");
		student.getGrades().add(grade);
		studentRepository.save(student);

		student = new Student();
		student.setStudentNumber(617589L);
		student.setName("Sujan");
		student.setPhone("0622341678");
		address.setStreet("123 Main St");
		address.setCity("San Francisco");
		address.setZip("651");
		student.setAddress(address);

		grade.setCourseName("SWE");
		grade.setGrade("B+");
		student.getGrades().add(grade);

		grade = new Grade();
		grade.setCourseName("MWA");
		grade.setGrade("A-");
		student.getGrades().add(grade);

		grade = new Grade();
		grade.setCourseName("EA");
		grade.setGrade("B");
		student.getGrades().add(grade);

		studentRepository.save(student);

		student = new Student();
		student.setStudentNumber(617599L);
		student.setName("Saubin");
		student.setPhone("0622341672");
		address.setStreet("128 Main St");
		address.setCity("Burlington");
		address.setZip("657");
		student.setAddress(address);

		grade.setCourseName("EA");
		grade.setGrade("A+");
		student.getGrades().add(grade);

		grade = new Grade();
		grade.setCourseName("MWA");
		grade.setGrade("A+");
		student.getGrades().add(grade);

		studentRepository.save(student);
	}

	private void studentsTest() {
		List<Student> students = null;
		System.out.println("Find the Students with a certain name");
		students = studentRepository.findStudentByName("Sujan");
		System.out.println("Found " + students.size() + " students");
		System.out.println(students);

		System.out.println("\n\nFind the Students with a certain phone number");
		students = studentRepository.findStudentByPhone("0622341678");
		System.out.println("Found " + students.size() + " students");
		System.out.println(students);

		System.out.println("\n\nFind the Students from a certain city ");
		students = studentRepository.findStudentByAddressCity("Burlington");
		System.out.println("Found " + students.size() + " students");
		System.out.println(students);

		System.out.println("\n\nFind the Students that took a certain course with a given name");
		students = studentRepository.findStudentsByGradesCourseName("MWA");
		System.out.println("Found " + students.size() + " students");
		System.out.println(students);

		System.out.println("\n\nFind the Students with an A+ for a certain course name");
		students = studentRepository.findStudentsByGradesCourseNameAndGradesGrade("EA", "A+");
		System.out.println("Found " + students.size() + " students");
		System.out.println(students);


	}
}
