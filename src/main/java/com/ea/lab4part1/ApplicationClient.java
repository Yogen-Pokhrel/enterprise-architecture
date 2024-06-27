package com.ea.lab4part1;

import com.ea.lab4part1.domain.*;
import com.ea.lab4part1.repository.BookRepository;
import com.ea.lab4part1.repository.PublisherRepository;
import com.ea.lab4part1.service.*;
import com.ea.lab4part1.utils.Utils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class ApplicationClient {
    @Autowired
    DepartmentService departmentService;

    @Autowired
    BookService bookService;

    @Autowired
    PublisherService publisherService;
    @Autowired
    private PassengerService passengerService;

    @Autowired
    private SchoolService schoolService;

    @Autowired
    private StudentService studentService;


    public void runDepartmentActions(){
        Department department = new Department();
        department.setName("Software Engineering");
        Employee employee = new Employee();
        employee.setName("Yogen");

        Employee employee2 = new Employee();
        employee2.setName("Dikshya");

        department.addEmployee(employee);
        department.addEmployee(employee2);
        departmentService.save(department);

        System.out.println("\n\n--------------Print all Department--------------------");
        System.out.println(departmentService.findAll());

    }

    @Transactional
    public void runBookActions(){
        Publisher author= new Publisher();
        author.setName("Yogen");
        Book book = new Book();
        book.setIsbn("567 899");
        book.setTitle("Software Engineering");
        book.setAuthor(author);
        bookService.save(book);


        book = new Book();
        book.setIsbn("567 811");
        book.setTitle("Enterprise Architecture");
        bookService.save(book);

        Publisher publisher = publisherService.findById(1L);
        book = new Book();
        book.setIsbn("567 922");
        book.setTitle("Data Engineering");
        if(publisher != null){
            book.setAuthor(publisher);
        }
        bookService.save(book);

        System.out.println("----------------Printing all books -------------");
        System.out.println(bookService.findAll());
    }

    @Transactional
    public void runPassengerActions(){
        Passenger passenger = new Passenger();
        passenger.setName("Yogen");

        Flight flight = new Flight();
        flight.setTo("Kathmandu");
        flight.setFrom("De Moines");
        flight.setFlightNumber("FL 656");
        flight.setDate(Utils.dateFromString("2024/07/24"));
        passenger.addFlight(flight);

        flight = new Flight();
        flight.setTo("Bhadrapur");
        flight.setFrom("Kathmandu");
        flight.setFlightNumber("YT 651");
        flight.setDate(Utils.dateFromString("2024/07/26"));
        passenger.addFlight(flight);

        passengerService.save(passenger);


        passenger = new Passenger();
        passenger.setName("Dikshya");
        flight = new Flight();
        flight.setTo("Kathmandu");
        flight.setFrom("Bhadrapur");
        flight.setFlightNumber("FL 656");
        flight.setDate(Utils.dateFromString("2024/07/24"));
        passenger.addFlight(flight);
        passengerService.save(passenger);

        System.out.println("--------------Print all passengers -------------");
        System.out.println(passengerService.findAll());

    }

    @Transactional
    public void runSchoolActions(){
        School school = new School();
        school.setName("Maharishi International University");

        Student student = new Student();
        student.setFirstName("Dikshya");
        student.setLastName("Prasai");
        student = studentService.save(student);
        school.addStudent(student);

        student = new Student();
        student.setFirstName("Yogen");
        student.setLastName("Pokhrel");
        student = studentService.save(student);
        school.addStudent(student);
        schoolService.save(school);

        school = new School();
        school.setName("Harikul");

        student = new Student();
        student.setFirstName("Saurab");
        student.setLastName("Ghimire");
        student = studentService.save(student);
        school.addStudent(student);
        schoolService.save(school);

        System.out.println("--------------Print all schools -------------");
        System.out.println(schoolService.findAll());

    }
}
