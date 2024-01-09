package demo;

import demo.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.hibernate.type.StringType;

import java.util.List;

public class Demo3 {

    public static void main(String[] args) {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

        Session session = sessionFactory.openSession();
        session.beginTransaction();


        // recherche de personne avec un paramètre nommé avec like
        String search = "C";

        Query<Person> personQuery = session.createQuery("from Person where firstname like :firstname");
        personQuery.setParameter("firstname", search+"%", StringType.INSTANCE); // on demande de récupérer les personnes avec un prénom commençant par C
        List<Person> personListStartWithC = personQuery.list();

        for (Person p : personListStartWithC) {
            System.out.println("Personne avec un prénom commençant par C : " + p);
        }

        Query<Person> personQuery2 = session.createQuery("from Person where firstname like ?1");
        personQuery2.setParameter(1, search+"%", StringType.INSTANCE);
        List<Person> personListStartWithC2 = personQuery.list();

        for (Person p : personListStartWithC2) {
            System.out.println("Personne avec un prénom commençant par C : " + p);
        }

    }
}
