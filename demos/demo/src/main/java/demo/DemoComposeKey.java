package demo;

import demo.composeKey.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class DemoComposeKey {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Employee employee = new Employee();
        employee.setLastName("Martin");
        employee.setFirstName("John");
        session.save(employee);

        session.getTransaction().commit();

        session.close();
        sessionFactory.close();

    }
}
