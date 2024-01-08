import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import heritage.single_table.CreditCardPayment2;
import heritage.single_table.PaypalPayment2;


import java.util.Date;

public class Main {
    public static void main(String[] args) {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.getTransaction();
            tx.begin();

            // Exemple joined table :

//            CreditCardPayment creditCardPayment = new CreditCardPayment();
//            creditCardPayment.setCardNumber("178456");
//            creditCardPayment.setAmount(4000.0);
//            creditCardPayment.setPaymentDate(new Date());
//            creditCardPayment.setExpirationDate("12/2026");
//
//
//            PaypalPayment paypalPayment = new PaypalPayment();
//            paypalPayment.setAccountNumber("45878");
//            paypalPayment.setPaymentDate(new Date());
//            paypalPayment.setAmount(10000.0);
//
//            session.save(creditCardPayment);
//            session.save(paypalPayment);
//
//            System.out.println("creditCardPayment " + creditCardPayment);
//            System.out.println("paypalPayment " + paypalPayment);


            // Exemple single table :
            CreditCardPayment2 creditCardPayment2 = new CreditCardPayment2();
            creditCardPayment2.setCardNumber("17121992");
            creditCardPayment2.setAmount(10000);
            creditCardPayment2.setPaymentDate(new Date());
            creditCardPayment2.setExpirationDate("06/2027");

            PaypalPayment2 paypalPayment2 = new PaypalPayment2();
            paypalPayment2.setAccountNumber("147852");
            paypalPayment2.setPaymentDate(new Date());
            paypalPayment2.setAmount(142058.254);

            session.save(creditCardPayment2);
            session.save(paypalPayment2);

            System.out.println("creditCardPayment " + creditCardPayment2);
            System.out.println("paypalPayment " + paypalPayment2);

            tx.commit();

        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
                ex.printStackTrace();
            }
        } finally {
            session.close();
            sessionFactory.close();
        }


    }
}
