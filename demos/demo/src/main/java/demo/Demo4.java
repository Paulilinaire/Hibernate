package demo;

import demo.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class Demo4 {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        // Fonctions d'agrégation
        // Max()
        Query<Integer> query = session.createQuery("select max(age) from Person");
        int maxAge = query.uniqueResult();
        System.out.println("âge max : " + maxAge);

        // Avg()
        double avgAge = (double)session.createQuery("select avg(age) from Person").uniqueResult();
        System.out.println("La moyenne d'âge : " + avgAge);

        // Utilisation IN
        List names = new ArrayList<String>();

        names.add("Clément");
        names.add("Ine");
        Query<Person> query1 = session.createQuery("from Person where lastname in :names");
        query1.setParameter("names", names);

        List<Person> personList = query1.list();

        for (Person p: personList){
            System.out.println("Personne : " + p.getFirstname());
        }

        // Utilisation execute Update
        String update_query = "update Person set lastname = :lastnameP where id=2";
        Query query2 = session.createQuery(update_query);
        query2.setParameter("lastnameP", "Pauline");
        int success = query2.executeUpdate();

        session.getTransaction().commit();
        System.out.println("nbr de ligne modifiée : " + success);

    }
}
