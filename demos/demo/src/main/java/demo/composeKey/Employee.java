package demo.composeKey;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;


@Entity
@IdClass(EmployeePK.class)

public class Employee {

    @Id
    private  String firstName;
    @Id
    private  String lastName;
    private Integer size;

    public Employee() {
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

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}