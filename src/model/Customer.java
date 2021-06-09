package model;
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
}
