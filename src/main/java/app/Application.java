package app;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class Application implements CommandLineRunner{
	
	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	SchoolRepository schoolRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {

		insertCustomers();
		retrieveCustomers();
		updateCustomers();

		createSchoolsAndAddStudents();
		fetchSchools();
		fetchSchoolsEager();
	}

	private void insertCustomers() {
		for (int x=0; x<50000; x++) {
			Customer customer = new Customer("John Doe "+x);
			Account account = new Account("123"+x);
			customer.setAccount(account);
			customerRepository.save(customer);
			System.out.println("Inserting customer  "+x);
		}
	}

	private void retrieveCustomers() {
		System.out.println("Retrieving all customers ...");
		long start = System.currentTimeMillis();

		List<Customer> customers = customerRepository.findAll();
		long finish = System.currentTimeMillis();
		long timeElapsed = finish - start;
		System.out.println("To retrieve all Customers took "+timeElapsed+" ms");
	}

	private void updateCustomers() {
		System.out.println("Change the name of all customers ...");
		long start = System.currentTimeMillis();

//		List<Customer> customers = customerRepository.findAll();
//		for(Customer c: customers){
//			c.setName("James Bond");
//			customerRepository.save(c);
//		}

		customerRepository.updateCustomerName("James Bond");
		long finish = System.currentTimeMillis();
		long timeElapsed = finish - start;
		System.out.println("To change the name of all customers took "+timeElapsed+" ms");
	}

	public void createSchoolsAndAddStudents() {
		School school1 = new School();
		school1.setName("BTI");
		schoolRepository.save(school1);
		addStudentsToSchool(school1.getId(), 1000);

		School school2 = new School();
		school2.setName("Maharishi");
		schoolRepository.save(school2);
		addStudentsToSchool(school2.getId(), 1000);

		School school3 = new School();
		school3.setName("Harikul");
		schoolRepository.save(school3);
		addStudentsToSchool(school3.getId(), 10000);
	}

	public void addStudentsToSchool(Long schoolId, int numberOfStudents) {
		School school = schoolRepository.findById(schoolId)
				.orElseThrow(() -> new RuntimeException("School not found"));

		List<Student> students = new ArrayList<>();
		for (int i = 0; i < numberOfStudents; i++) {
			Student student = new Student();
			student.setFirstName("Yogen " + i);
			student.setLastName("Pokhrel " + i);
			student.setEmail("student" + i + "@school.com");
			students.add(student);
			school.addStudent(student);
		}

		schoolRepository.save(school);
	}

	public void fetchSchools() {
		System.out.println("Fetching schools ...");
		long start = System.currentTimeMillis();
		schoolRepository.findAll();
		long finish = System.currentTimeMillis();
		long timeElapsed = finish - start;
		System.out.println("To fetch schools took "+timeElapsed+" ms");
	}

	public void fetchSchoolsEager() {
		System.out.println("Fetching schools with students ...");
		long start = System.currentTimeMillis();
		schoolRepository.findAllEager();
		long finish = System.currentTimeMillis();
		long timeElapsed = finish - start;
		System.out.println("To fetch schools with students it took "+timeElapsed+" ms");
	}


}
