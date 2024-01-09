package demo;

import demo.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Demo {

    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

        Session session = sessionFactory.openSession();
        session.beginTransaction();

//        // créer une personne :
//        Person p = new Person();
//        p.setAge(25);
//        p.setLastname("Holland");
//        p.setFirstname("Tom");
//        session.save(p);
//
//        System.out.println("Personne : " + p.getId());

//
//        // récupérer une personne
//
//        Person person = session.load(Person.class, 5L);
//        System.out.println("Personne : " + person.getLastname());
//
//        // modifier une personne
//
//        person.setAge(22);
//        session.update(person);
//
//        System.out.println("Personne change " + person);
//
//        // supprimer une personne
//
//        session.delete(person);
//
//        session.beginTransaction().commit();



        session.close();
        sessionFactory.close();
    }
}
