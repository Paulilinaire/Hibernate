package heritage.table_per_class;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) // plus de table commune, il y a seulement une classe par table, dans ce cas il y aura 2 tables
public abstract class Payment3 {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) // sequence important ici pour éviter d'avoir le même numéro d'id dans les 2 tables
    private long paymentId;

    private double amount;

    private Date paymentDate = new Date();

    public Payment3() {

    }

}
