package model;
import java.util.Objects;
import java.util.regex.Pattern;

public class Customer {
    private String email;
    private String firstName;
    private String lastName;

    public Customer (String email, String firstName, String lastName) {
        String emailRegex = "^(.+)@(.+).(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);

        if (!pattern.matcher(email).matches()) {
            throw new IllegalArgumentException("Error, invalid email");
        }
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) { this.lastName = lastName; }

    @Override
    public String toString() {
        return "First Name: " + firstName + ", Last Name: " + lastName + ", Email: " + email;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Customer)) {
            return false;
        }
        Customer customer = (Customer) obj;
        boolean emailEquals = (this.email == null && customer.email == null) || (this.email != null && this.email.equals(customer.email));
        boolean firstNameEquals = (this.firstName == null && customer.firstName == null) || (this.firstName != null && this.firstName.equals(customer.firstName));
        boolean lastNameEquals = (this.lastName == null && customer.lastName == null) || (this.lastName != null && this.lastName.equals(customer.lastName));
        return emailEquals && firstNameEquals && lastNameEquals;
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, firstName, lastName);
    }
}
