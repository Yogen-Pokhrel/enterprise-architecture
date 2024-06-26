package customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class CustomerDAO {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void clearDB() {
        Map<String,Object> namedParameters = new HashMap<String,Object>();
        jdbcTemplate.update("DELETE from customer",namedParameters);
        jdbcTemplate.update("DELETE from creditcard",namedParameters);
    }

    public void save(Customer customer) {
        Map<String,Object> namedParameters = new HashMap<String,Object>();
        namedParameters.put("customernumber", customer.getCustomerNumber());
        namedParameters.put("name", customer.getName());
        namedParameters.put("email", customer.getEmail());
        namedParameters.put("phone", customer.getPhone());
        jdbcTemplate.update("INSERT INTO customer VALUES ( :customernumber, :name, :email, :phone)",namedParameters);

        // save creditcard
        Map<String,Object> namedParameterscc = new HashMap<String,Object>();
        namedParameterscc.put("customernumber", customer.getCustomerNumber());
        namedParameterscc.put("cardnumber", customer.getCreditCard().getCardNumber());
        namedParameterscc.put("type", customer.getCreditCard().getType());
        namedParameterscc.put("validationDate", customer.getCreditCard().getValidationDate());
        jdbcTemplate.update("INSERT INTO creditcard VALUES ( :cardnumber, :type, :validationDate, :customernumber)",namedParameterscc);
    }



    public Customer getCustomer(int customerNumber){
        Map<String,Object> namedParameters = new HashMap<String,Object>();
        namedParameters.put("customerNumber", customerNumber);
        Customer customer = jdbcTemplate.queryForObject("SELECT * FROM customer WHERE "
                        + "customerNumber =:customerNumber ",
                namedParameters,
                (rs, rowNum) -> new Customer( rs.getInt("customerNumber"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone")));

        CreditCard creditCard = getCreditCardForCustomer(customer.getCustomerNumber());
        customer.setCreditCard(creditCard);
        return customer;

    }

    public List<Customer> getAllCustomers(){
        List<Customer> customers = jdbcTemplate.query("SELECT * FROM customer",
                new HashMap<String, Customer>(),
                (rs, rowNum) -> new Customer( rs.getInt("customerNumber"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone")));

        for (Customer customer: customers){
            CreditCard creditCard = getCreditCardForCustomer(customer.getCustomerNumber());
            customer.setCreditCard(creditCard);
        }
        return customers;
    }

    CreditCard getCreditCardForCustomer(int customerNumber){
        Map<String,Object> namedParameters = new HashMap<String,Object>();
        namedParameters.put("customerNumber", customerNumber);
        CreditCard creditCard = jdbcTemplate.queryForObject("SELECT * FROM creditcard WHERE "
                        + "customerNumber =:customerNumber ",
                namedParameters,
                (rs, rowNum) -> new CreditCard( rs.getString("cardnumber"),
                        rs.getString("type"),
                        rs.getString("validationDate")));

        return creditCard;
    }
}
