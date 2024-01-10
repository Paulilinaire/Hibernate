package heritage.single_table;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@Entity
@Table(name = "payment2")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // comme son nom l'indique, il n'y aura qu'une table payment en bdd avec des colonnes PaypalPayment, creditCardPayment
@DiscriminatorColumn(name = "discriminator", discriminatorType = DiscriminatorType.INTEGER)
public abstract class Payment2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long paymentId;

    private double amount;

    private Date paymentDate = new Date();

    public Payment2() {

    }

}
