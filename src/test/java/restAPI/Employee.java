package restAPI;

public class Employee {
    private String name;
    private String city;
    private String department;
    private String designation;

    public Employee(){
        super();
    }

    public Employee(String name, String city, String department, String designation) {
        this.name = name;
        this.city = city;
        this.department = department;
        this.designation = designation;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
