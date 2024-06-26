package customers;

public class Product {
    private int productnumber;
    private String name;
    private float price;
    private Supplier supplier;

    Product(int productnumber, String name, float price) {
        this.productnumber = productnumber;
        this.name = name;
        this.price = price;
    }

    public int getProductnumber() {
        return productnumber;
    }

    public void setProductnumber(int productnumber) {
        this.productnumber = productnumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Supplier getSupplier() {return supplier;}

    @Override
    public String toString() {
        return "Product [productnumber=" + productnumber + ", name=" + name + ", price=" + price + ", supplier=" + supplier + "]";
    }
}
