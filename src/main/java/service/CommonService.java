package service;

import domain.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.CustomerRepository;
import repositories.OrderRepository;
import repositories.ProductRepository;

@Service
public class CommonService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    public void runProductActions(){
        Product product = new DVD("Movies");
        product.setName("Hibernate 3");
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
                "New york", "43221");
        c1.addOrder(o1);
        o1.setCustomer(c1);
        o1 = orderRepository.save(o1);

        printOrder(o1.getId());
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
            System.out.print("Product: " + product.getName() + " "
                    + product.getDescription() + " " + product.getPrice());
            if(product instanceof CD){
                System.out.println(" CD: " + ((CD) product).getArtist());
            }

            if(product instanceof DVD){
                System.out.println(" DVD: " + ((DVD) product).getGenre());
            }

            if(product instanceof Book){
                System.out.println(" Book: " + ((Book) product).getIsbn());
            }
        }
    }
}
