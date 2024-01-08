package heritage.joined_table;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "paypal")
@PrimaryKeyJoinColumn(name = "paymentId")
public class PaypalPayment extends Payment{

    private String accountNumber;

    @Override
    public String toString() {
        return "PaypalPayment{" +
                "accountNumber='" + accountNumber + '\'' +
                "} " + super.toString();
    }
}
