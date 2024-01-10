package heritage.joined_table;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "credit_card")
@PrimaryKeyJoinColumn(name = "paymentId") // l'id qui sera une clé primaire dans la table payment et sera un fk dans la table credit_card
// l'id ne sera visible uniquement dans la carte payment
public class CreditCardPayment extends Payment{

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
