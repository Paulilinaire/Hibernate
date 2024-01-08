package heritage.single_table;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "credit_card2")
@DiscriminatorValue("2")
public class CreditCardPayment2 extends Payment2 {

    private String cardNumber;

    private String expirationDate;

//    public CreditCardPayment() {
//        super();
//    }

    @Override
    public String toString() {
        return "CreditCardPayment{" +
                "cardNumber='" + cardNumber + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                "} " + super.toString();
    }
}
