import java.util.*;

// Class representing a Medicine
class Medicine {
    private String name;
    private int quantity;
    private double price;
    private Date expiryDate;

    public Medicine(String name, int quantity, double price, Date expiryDate) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.expiryDate = expiryDate;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }
}

// Class representing a Customer
class Customer {
    private String name;
    private String address;
    private String phoneNumber;

    public Customer(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}

// Class representing the Pharmacy
class Pharmacy {
    private Map<String, Medicine> inventory;
    private List<Customer> customers;

    public Pharmacy() {
        inventory = new HashMap<>();
        customers = new ArrayList<>();
    }

    // Add medicine to inventory
    public void addMedicine(String name, int quantity, double price, Date expiryDate) {
        if (inventory.containsKey(name)) {
            Medicine med = inventory.get(name);
            med.setQuantity(med.getQuantity() + quantity);
        } else {
            inventory.put(name, new Medicine(name, quantity, price, expiryDate));
        }
    }

    // Sell medicine to a customer
    public void sellMedicine(String name, int quantity, Customer customer) {
        if (inventory.containsKey(name)) {
            Medicine med = inventory.get(name);
            if (med.getQuantity() >= quantity) {
                med.setQuantity(med.getQuantity() - quantity);
                System.out.println("Sold " + quantity + " " + name + " to " + customer.getName());
            } else {
                System.out.println("Not enough quantity of " + name + " available.");
            }
        } else {
            System.out.println(name + " not found in inventory.");
        }
    }

    // Add a new customer
    public void addCustomer(String name, String address, String phoneNumber) {
        customers.add(new Customer(name, address, phoneNumber));
    }

    // Display all customers
    public void displayCustomers() {
        System.out.println("Registered Customers:");
        for (Customer customer : customers) {
            System.out.println("Name: " + customer.getName() + ", Address: " + customer.getAddress() + ", Phone: " + customer.getPhoneNumber());
        }
    }

    // Display current inventory
    public void displayInventory() {
        System.out.println("Current Inventory:");
        for (Medicine med : inventory.values()) {
            System.out.println(med.getName() + " - Quantity: " + med.getQuantity() + ", Price: $" + med.getPrice() + ", Expiry Date: " + med.getExpiryDate());
        }
    }
}

public class PharmacySystem {
    public static void main(String[] args) {
        Pharmacy pharmacy = new Pharmacy();

        // Adding medicines to inventory
        pharmacy.addMedicine("Paracetamol", 100, 2.5, new Date(2024 - 1900, 4, 30));
        pharmacy.addMedicine("Aspirin", 50, 3.0, new Date(2024 - 1900, 8, 15));

        // Adding customers
        pharmacy.addCustomer("John Doe", "123 Main St", "555-1234");
        pharmacy.addCustomer("Jane Smith", "456 Oak St", "555-5678");

        // Displaying inventory and customers
        pharmacy.displayInventory();
        pharmacy.displayCustomers();

        // Selling medicine to a customer
        pharmacy.sellMedicine("Paracetamol", 50, pharmacy.customers.get(0));

        // Displaying updated inventory
        pharmacy.displayInventory();
    }
}
