package demo.composeKey;

import java.io.Serializable;
import java.util.Objects;

public class EmployeePK  implements Serializable {
    private static final long serialVersionUID = 1L;
    private String lastName;
    private String firstName;

    public EmployeePK() {
    }
    public EmployeePK(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }
    public String getNom() {
        return lastName;
    }


    public void setNom(String lastName) {
        this.lastName = lastName;
    }

    public String getPrelastName() {
        return firstName;
    }

    public void setPrelastName(String firstName) {
        this.firstName = firstName;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeePK)) return false;
        EmployeePK that = (EmployeePK) o;
        return Objects.equals(lastName, that.lastName) && Objects.equals(firstName, that.firstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName);
    }
}
