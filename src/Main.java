import entities.Customer;
import entities.Order;
import entities.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    static List<Product> warehouse = new ArrayList<>();
    static List<Customer> customers = new ArrayList<>();
    static List<Order> orders = new ArrayList<>();

    public static void main(String[] args) {
        initializeWarehouse();
        createCustomers();
        placeOrders();

        System.out.println("--------------------------------- PRODUCTS ----------------------------------");
        warehouse.forEach(System.out::println);
        System.out.println("--------------------------------- CUSTOMERS ----------------------------------");
        customers.forEach(System.out::println);
        System.out.println("--------------------------------- ORDERS ----------------------------------");
        orders.forEach(System.out::println);

        System.out.println("---------------------------------- ES 1 ------------------------------------");
        getBooks().forEach(System.out::println);
        System.out.println("---------------------------------- ES 2 ------------------------------------");
        getBabyOrders().forEach(System.out::println);
        System.out.println("---------------------------------- ES 3 ------------------------------------");
        getBoysProductsWithDiscount().forEach(System.out::println);
        System.out.println("---------------------------------- ES 4 ------------------------------------");
        getTier2Products().forEach(System.out::println);
    }

    // 1
    public static List<Product> getBooks() {
        return warehouse.stream().filter(product -> product.getCategory().equals("Books") && product.getPrice() > 100).toList();
    }

    // 2
    public static List<Order> getBabyOrders() {

        return orders.stream().filter(order -> order.getProducts().stream().anyMatch(product -> product.getCategory().equals("Baby"))).toList();

    // 3
    public static List<Product> getBoysProductsWithDiscount() {
        return warehouse.stream().filter(p -> p.getCategory().equals("Boys")).map(product -> {
            product.setPrice(product.getPrice() * 0.90);
            return product;
        }).toList();
    }

    // 4
    public static List<Product> getTier2Products() {
        List<Order> filteredByTierAndDates = orders.stream()
                .filter(order -> order.getCustomer().getTier() == 2
                        && order.getOrderDate().isBefore(LocalDate.parse("2023-05-09"))
                        && order.getOrderDate().isAfter(LocalDate.parse("2023-05-01")))
                .toList();

        List<Product> products = new ArrayList<>();

        for (Order order : filteredByTierAndDates) {
            products.addAll(order.getProducts());
        }
        return products;

    public static void initializeWarehouse() {
        Product iPhone = new Product("IPhone", "Smartphones", 2000.0);
        Product lotrBook = new Product("LOTR", "Books", 101);
        Product itBook = new Product("IT", "Books", 2);
        Product davinciBook = new Product("Da Vinci's Code", "Books", 2);
        Product diapers = new Product("Pampers", "Baby", 3);
        Product toyCar = new Product("Car", "Boys", 15);
        Product toyPlane = new Product("Plane", "Boys", 25);
        Product lego = new Product("Lego Star Wars", "Boys", 500);

        warehouse.addAll(Arrays.asList(iPhone, lotrBook, itBook, davinciBook, diapers, toyCar, toyPlane, lego));
    }

    public static void createCustomers() {
        Customer aldo = new Customer("Aldo Baglio", 1);
        Customer giovanni = new Customer("Giovanni Storti", 2);
        Customer giacomo = new Customer("Giacomo Poretti", 3);
        Customer marina = new Customer("Marina Massironi", 2);

        customers.add(aldo);
        customers.add(giovanni);
        customers.add(giacomo);
        customers.add(marina);
    }

    public static void placeOrders() {
        Order aldoOrder = new Order(customers.get(0));
        Order giovanniOrder = new Order(customers.get(1));
        Order giacomoOrder = new Order(customers.get(2));
        Order marinaOrder = new Order(customers.get(3));
        Order giacomoOrder2 = new Order(customers.get(2));

        Product iPhone = warehouse.get(0);
        Product lotrBook = warehouse.get(1);
        Product itBook = warehouse.get(2);
        Product davinciBook = warehouse.get(3);
        Product diaper = warehouse.get(4);

        aldoOrder.addProduct(iPhone);
        aldoOrder.addProduct(lotrBook);
        aldoOrder.addProduct(diaper);

        giovanniOrder.addProduct(itBook);
        giovanniOrder.addProduct(davinciBook);
        giovanniOrder.addProduct(iPhone);

        giacomoOrder.addProduct(lotrBook);
        giacomoOrder.addProduct(diaper);

        marinaOrder.addProduct(diaper);

        giacomoOrder2.addProduct(iPhone);

        orders.add(aldoOrder);
        orders.add(giovanniOrder);
        orders.add(giacomoOrder);
        orders.add(marinaOrder);
        orders.add(giacomoOrder2);

    }
}