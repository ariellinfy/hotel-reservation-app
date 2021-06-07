package service;

import model.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerService {

    private static Map<String, Customer> customerList = new HashMap<String, Customer>();

    public static void addCustomer (String email, String firstName, String lastName) {
        Customer customer = new Customer(email, firstName, lastName);
        customerList.put(email, customer);
    }

    public static Customer getCustomer (String customerEmail) {
        return customerList.get(customerEmail);
    }

    public static Collection<Customer> getAllCustomers () {
        return customerList.values();
    }
}
