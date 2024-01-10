package heritage.single_table;


import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "paypal2")
@DiscriminatorValue("1")
public class PaypalPayment2 extends Payment2 {

    private String accountNumber;

    @Override
    public String toString() {
        return "PaypalPayment{" +
                "accountNumber='" + accountNumber + '\'' +
                "} " + super.toString();
    }
}
