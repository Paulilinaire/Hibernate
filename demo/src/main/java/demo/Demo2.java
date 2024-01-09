package demo;

import demo.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.Iterator;
import java.util.List;

public class Demo2 {


    public static void main(String[] args) {

    StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        //HQL : Hibernate Query Language
        Query<Person> personQuery = session.createQuery("from Person");

        List<Person> personList = personQuery.list();

        for (Person p: personList) {
            System.out.println("Personne : " + p);
        }

        Iterator<Person> personIterator = personQuery.iterate();

        while (personIterator.hasNext()){
            Person p = personIterator.next();
            System.out.println("Personne avec iterator : " + p);
        }

        // une requête avec filter pour récupérer une liste
        Query<Person> personQuery2 = session.createQuery("from Person where lastname = 'Pain'");
        List<Person> list = personQuery2.list();

        for (Person p : list) {
            System.out.println("Personne avec un nom en paramètre " + p );
        }

        // une requête avec filter pour récupérer une personne avec id
        Query<Person> personQuery3 = session.createQuery("from Person where id = 4");
        Person person2 = personQuery3.getSingleResult();

        System.out.println("Une seule personne avec l'id 4 : " + person2);

    }

}
