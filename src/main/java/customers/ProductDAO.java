package customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductDAO {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void clearDB() {
        Map<String,Object> namedParameters = new HashMap<String,Object>();
        jdbcTemplate.update("DELETE from product",namedParameters);
        jdbcTemplate.update("DELETE from supplier",namedParameters);
    }

    public void save(Product product){
        Map<String,Object> namedParameters = new HashMap<String,Object>();
        namedParameters.put("productnumber", product.getProductnumber());
        namedParameters.put("name", product.getName());
        namedParameters.put("price", product.getPrice());
        jdbcTemplate.update("INSERT INTO product VALUES ( :productnumber, :name, :price)",namedParameters);

        //save supplier
        if(product.getSupplier() != null){
            Map<String,Object> namedParameterscc = new HashMap<String,Object>();
            namedParameterscc.put("id", product.getSupplier().getId());
            namedParameterscc.put("name", product.getSupplier().getName());
            namedParameterscc.put("phone", product.getSupplier().getPhone());
            namedParameterscc.put("productnumber", product.getProductnumber());
            jdbcTemplate.update("INSERT INTO supplier VALUES (:id, :name, :phone, :productnumber)",namedParameterscc);
        }
    }

    public Product findByProductNumber(int productnumber){
        Map<String,Object> namedParameters = new HashMap<String,Object>();
        namedParameters.put("productnumber", productnumber);
        try{
            Product product = jdbcTemplate.queryForObject("SELECT * FROM product WHERE "
                            + "productnumber =:productnumber ",
                    namedParameters,
                    (rs, rowNum) -> new Product( rs.getInt("productnumber"),
                            rs.getString("name"),
                            rs.getFloat("price")));
            assert product != null;
            Supplier supplier = getSupplierOfProduct(product.getProductnumber());
            product.setSupplier(supplier);
            return product;
        }catch (Exception e){
            System.out.println("Error: "+e.getMessage());
            return null;
        }
    }

    public List<Product> getAllProducts(){
        List<Product> products = jdbcTemplate.query("SELECT * FROM product",
                new HashMap<String, Product>(),
                (rs, rowNum) -> new Product( rs.getInt("productnumber"),
                        rs.getString("name"),
                        rs.getFloat("price")));
        for (Product product: products){
            Supplier supplier = getSupplierOfProduct(product.getProductnumber());
            product.setSupplier(supplier);
        }
        return products;
    }

    public Product findByProductName(String name){
        Map<String,Object> namedParameters = new HashMap<String,Object>();
        namedParameters.put("name", name);
        try{
            Product product = jdbcTemplate.queryForObject("SELECT * FROM product WHERE "
                            + "name =:name ",
                    namedParameters,
                    (rs, rowNum) -> new Product( rs.getInt("productnumber"),
                            rs.getString("name"),
                            rs.getFloat("price")));
            assert product != null;
            Supplier supplier = getSupplierOfProduct(product.getProductnumber());
            product.setSupplier(supplier);
            return product;
        }catch (Exception e){
            System.out.println("Error: "+e.getMessage());
            return null;
        }
    }

    public Product removeProduct(int productnumber){
        Product product = findByProductNumber(productnumber);
        if(product != null){
            Map<String, Object> namedParameters = new HashMap<>();
            namedParameters.put("productnumber", productnumber);
            jdbcTemplate.update("DELETE FROM product WHERE productnumber = :productnumber", namedParameters);
            jdbcTemplate.update("DELETE FROM supplier WHERE productnumber = :productnumber", namedParameters);
            return product;
        }else{
            System.out.println("No product found for product number: "+productnumber);
            return null;
        }
    }

    Supplier getSupplierOfProduct(int productnumber){
        Map<String,Object> namedParameters = new HashMap<String,Object>();
        namedParameters.put("productnumber", productnumber);
        Supplier supplier = jdbcTemplate.queryForObject("SELECT * FROM supplier WHERE "
                        + "productnumber =:productnumber ",
                namedParameters,
                (rs, rowNum) -> new Supplier(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("phone")));

        return supplier;
    }

}
