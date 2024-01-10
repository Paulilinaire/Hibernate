package demo;

import heritage.table_per_class.CreditCardPayment3;
import heritage.table_per_class.PaypalPayment3;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


import java.util.Date;

public class DemoHeritage {
    public static void main() {

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
//            CreditCardPayment2 creditCardPayment2 = new CreditCardPayment2();
//            creditCardPayment2.setCardNumber("17121992");
//            creditCardPayment2.setAmount(10000);
//            creditCardPayment2.setPaymentDate(new Date());
//            creditCardPayment2.setExpirationDate("06/2027");
//
//            PaypalPayment2 paypalPayment2 = new PaypalPayment2();
//            paypalPayment2.setAccountNumber("147852");
//            paypalPayment2.setPaymentDate(new Date());
//            paypalPayment2.setAmount(142058.254);
//
//            session.save(creditCardPayment2);
//            session.save(paypalPayment2);
//
//            System.out.println("creditCardPayment " + creditCardPayment2);
//            System.out.println("paypalPayment " + paypalPayment2);
//
//            tx.commit();

            // Exemple table per class
            CreditCardPayment3 creditCardPayment3 = new CreditCardPayment3();
            creditCardPayment3.setCardNumber("04011991");
            creditCardPayment3.setAmount(10500);
            creditCardPayment3.setPaymentDate(new Date());
            creditCardPayment3.setExpirationDate("07/2026");

            PaypalPayment3 paypalPayment3 = new PaypalPayment3();
            paypalPayment3.setAccountNumber("147853");
            paypalPayment3.setPaymentDate(new Date());
            paypalPayment3.setAmount(1500.25);

            session.save(creditCardPayment3);
            session.save(paypalPayment3);

            System.out.println("creditCardPayment " + creditCardPayment3);
            System.out.println("paypalPayment " + paypalPayment3);

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
