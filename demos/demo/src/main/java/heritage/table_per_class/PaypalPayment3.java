package heritage.table_per_class;


import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "paypal3")
public class PaypalPayment3 extends Payment3 {

    private String accountNumber;

    @Override
    public String toString() {
        return "PaypalPayment{" +
                "accountNumber='" + accountNumber + '\'' +
                "} " + super.toString();
    }
}
