package bg.softuni.coffeeshop.model.dto;

public class EmployeeDTO {
    private String firstName;
    private String lastName;
    private int numberOfOrders;

    public EmployeeDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public EmployeeDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public EmployeeDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public int getNumberOfOrders() {
        return numberOfOrders;
    }

    public EmployeeDTO setNumberOfOrders(int numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
        return this;
    }

    public String getFullName() {
        if (this.getFirstName().isEmpty()) {
            return this.getLastName();
        }

        return this.firstName + " " + this.lastName;
    }
}
