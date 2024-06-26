package app;

import java.util.List;
import java.util.Optional;

import app.domain.Book;
import app.domain.Customer;
import app.repositories.BookRepository;
import app.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@SpringBootApplication
public class CustomerApplication implements CommandLineRunner{
	
	@Autowired
	CustomerRepository customerrepository;

	@Autowired
	BookRepository bookRepository;

	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		customerrepository.save(new Customer("Jack", "Bauer", "jack@acme.com"));
		customerrepository.save(new Customer("Chloe", "O'Brian", "chloe@acme.com"));
		customerrepository.save(new Customer("Kim", "Bauer", "kim@acme.com"));
		customerrepository.save(new Customer("David", "Palmer", "dpalmer@gmail.com"));
		customerrepository.save(new Customer("Michelle", "Dessler", "mich@hotmail.com"));

		// fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (Customer customer : customerrepository.findAll()) {
			System.out.println(customer);
		}
		System.out.println();

		// fetch an individual customer by ID
		Optional<Customer> custOpt = customerrepository.findById(1L);
		Customer customer = custOpt.get();
		System.out.println("Customer found with findOne(1L):");
		System.out.println("--------------------------------");
		System.out.println(customer);
		System.out.println();
		bookActions();
	}

	private void bookActions(){
		bookRepository.save(new Book("Lord of rings", "Yogen", "76 YUG 78", 120.40D));
		bookRepository.save(new Book("Headway", "Dikshya", "79 YUG 78", 110.40D));
		bookRepository.save(new Book("Enterprise architecture", "Dr. Helina", "69 YUG 78", 180.40D));

		printAllBooks();

		// update a book with id 1;
		System.out.println("Updating a book price with id 1");
		Book  book = bookRepository.findById(1L).get();
		book.setPrice(125.00);
		book.setAuthor("Bhimsen");
		bookRepository.save(book);

		book = bookRepository.findById(1L).get();
		System.out.println("Updated book" + book + "\n");

		// fetch an individual customer by ID
		Optional<Book> custOpt = bookRepository.findById(2L);
		book = custOpt.get();
		System.out.println("Book found with findOne(2):");
		System.out.println("--------------------------------");
		System.out.println(book);
		System.out.println();

		System.out.println("Deleting a book with id 3");
		System.out.println("--------------------------------");
		book = bookRepository.findById(3L).get();
		bookRepository.delete(book);

		System.out.println("\n\nprint all books after deletion");
		printAllBooks();
	}

	private void printAllBooks(){
		// fetch all books
		System.out.println("Books found with findAll():");
		System.out.println("-------------------------------");
		for (Book book : bookRepository.findAll()) {
			System.out.println(book + "\n");
		}
		System.out.println();
	}
}
