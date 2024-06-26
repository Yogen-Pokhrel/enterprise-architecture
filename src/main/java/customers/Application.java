package customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private CustomerDAO customerDao;

	@Autowired
	private ProductDAO productDAO;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		customerDao.clearDB();
		Customer customer = new Customer(101,"John doe", "johnd@acme.com", "0622341678");
		CreditCard creditCard = new CreditCard("12324564321", "Visa", "11/23");
		customer.setCreditCard(creditCard);
		customerDao.save(customer);
		customer = new Customer(66,"James Johnson", "jj123@acme.com", "068633452");
		creditCard = new CreditCard("99876549876", "MasterCard", "01/24");
		customer.setCreditCard(creditCard);
		customerDao.save(customer);
		System.out.println(customerDao.getCustomer(101));
		System.out.println(customerDao.getCustomer(66));
		System.out.println("-----------All customers ----------------");
		System.out.println(customerDao.getAllCustomers());

		System.out.println("\n\n\n-----------Products ----------------");
		productDAO.clearDB();
		Product product = new Product(124,"Large LG TV", 1200.3F);
		Supplier supplier = new Supplier(145, "Yogen", "5678455");
		product.setSupplier(supplier);
		productDAO.save(product);

		product = new Product(125,"Samsung Refrigerator", 800.50F);
		supplier = new Supplier(146, "Dikshya", "56784555");
		product.setSupplier(supplier);
		productDAO.save(product);

		System.out.println(productDAO.findByProductNumber(125));
		System.out.println(productDAO.findByProductName("Large LG TV"));
		System.out.println("-----------All Products ----------------");
		System.out.println(productDAO.getAllProducts());

		System.out.println("\n\n-----------Remove Product 125 ----------------");
		System.out.println("Removed: " + productDAO.removeProduct(125));
	}
}
