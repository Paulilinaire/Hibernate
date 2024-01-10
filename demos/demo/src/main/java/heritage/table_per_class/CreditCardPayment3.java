package heritage.table_per_class;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "credit_card3")
public class CreditCardPayment3 extends Payment3 {

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
