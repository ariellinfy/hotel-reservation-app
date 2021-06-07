package service;

import model.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerService {

    Map<String, Customer> customerList = new HashMap<String, Customer>();

    public void addCustomer (String email, String firstName, String lastName) {
        Customer customer = new Customer(email, firstName, lastName);
        customerList.put(email, customer);
    }

    public Customer getCustomer (String customerEmail) {
        return customerList.get(customerEmail);
    }

    public Collection<Customer> getAllCustomers () {
        return customerList.values();
    }
}
