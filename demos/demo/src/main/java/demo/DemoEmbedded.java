package demo;

import demo.embbeded.Agency;
import demo.embbeded.AgencyId;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class DemoEmbedded {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        AgencyId agencyId = new AgencyId(59025, "Lille-Agency");

        Agency agency = new Agency(agencyId, "Rue des postes");

        session.save(agency);
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();


    }
}
