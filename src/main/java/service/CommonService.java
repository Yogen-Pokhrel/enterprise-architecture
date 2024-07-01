package service;

import domain.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import repositories.*;

import java.util.List;

@Service
public class CommonService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CDRepository cdRepository;

    @Autowired
    AddressRepository addressRepository;

    public void runProductActions(){
        Product product = new DVD("Movies");
        product.setName("Rocky3");
        product.setDescription("Good book on Hibernate");
        product.setPrice(35.50);
        OrderLine ol1 = new OrderLine(2, product);

        Product product2 = new CD("Arjit Singh");
        product2.setName("The best of Queen");
        product2.setDescription("Album from 1995");
        product2.setPrice(12.98);
        OrderLine ol2 = new OrderLine(4, product2);

        Order o1 = new Order("234743", "12/10/06", "open");
        o1.addOrderLine(ol1);
        o1.addOrderLine(ol2);

        Customer c1 = new Customer("Frank", "Brown", "Mainstreet 1",
                "New york", "2389HJ");
        Customer c2 = new Customer("Yogen", "Pokhrel", "Mainstreet 1",
                "New york", "43221", "Nepal");
        c1.addOrder(o1);
        o1.setCustomer(c1);
        o1 = orderRepository.save(o1);
        customerRepository.save(c2);

        printOrder(o1.getId());
        testQueries();
    }

    public void printOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order == null) {
            System.out.println("Order with ID: " + orderId + " is null");
            return;
        }

        System.out.println("Order with orderNumber: " + order.getOrdernr());
        System.out.println("Order date: " + order.getDate());
        System.out.println("Order status: " + order.getStatus());
        Customer cust = order.getCustomer();
        System.out.println("Customer: " + cust.getFirstname() + " "
                + cust.getLastname());
        for (OrderLine orderline : order.getOrderlines()) {
            System.out.println("Order line: quantity= "
                    + orderline.getQuantity());
            Product product = orderline.getProduct();
            System.out.println("Product " + product);
        }
    }


    private void testQueries(){
        List<Customer> customerList = customerRepository.findAll();
        System.out.println("\n\n------- Printing Customer List -------");
        System.out.println(customerList);

        System.out.println("\n\nGive all CD’s from U2 with a price smaller than 10 euro");
        System.out.println(cdRepository.findByArtistAndPriceLessThan("Arjit Singh", 20));

        System.out.println("\n\nGive all customers with zip code 2389HJ ");
        System.out.println(customerRepository.findByAddressZip("2389HJ"));

        System.out.println("\n\nGive all customers who ordered a DVD with the name Rocky3 ");
        System.out.println(customerRepository.findCustomersByOrderedDVDName("Rocky3"));

        System.out.println("\n\nGive all customers from the USA.");
        System.out.println(customerRepository.findAllCustomersFromUSA());

        System.out.println("\n\nGive all CD’s from a certain artist");
        System.out.println(productRepository.findCDsByArtist("Arjit Singh"));

        System.out.println("\n\nGive the ordernumbers of all orders with status ‘closed’");
        System.out.println(orderRepository.findOrderNumbersByStatusClosed());

        System.out.println("\n\nGive the first and lastnames of all customers who live in Amsterdam.");
        System.out.println(customerRepository.findFirstAndLastNamesByCityAmsterdam());

        System.out.println("\n\nGive the ordernumbers of all orders from customers who live in a certain city (city is a parameter).");
        System.out.println(orderRepository.findOrderNumbersByCustomerCity("New york"));

        System.out.println("\n\nGive all CD’s from a certain artist with a price bigger than a certain amount (artist and amount are parameter2).");
        System.out.println(productRepository.findCDsByArtistAndPriceGreaterThan("Arjit Singh", 20));

        System.out.println("\n\nGive all addresses in Amsterdam.");
        System.out.println(addressRepository.findAllAddressesInAmsterdam());

        System.out.println("\n\nGive all CD’s from U2.");
        System.out.println(cdRepository.findAllCDsByArtistU2("Arjit Singh"));

        System.out.println("\n\nGive the ordernumbers of all orders with status ‘closed’");
        Specification<Order> spec = OrderSpecifications.hasStatus("closed");
        List<Order> closedOrders = orderRepository.findAll(spec);
        System.out.println(closedOrders);

        System.out.println("\n\nGive all customers who live in Amsterdam.");
        Specification<Customer> customerSpecification = CustomerSpecifications.livesInCity("Amsterdam");
        System.out.println(customerRepository.findAll(customerSpecification));

        System.out.println("\n\nGive all CD’s from a certain artist with a price bigger than a certain amount (artist and amount are parameter2).");
        Specification<CD> cdSpecification = CDSpecifications.byArtistAndPriceGreaterThan("Arjit Singh", 20);
        System.out.println(cdRepository.findAll(cdSpecification));



    }
}
