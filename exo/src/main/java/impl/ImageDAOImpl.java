package impl;

import dao.ImageDAO;
import model.Image;
import model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


import java.util.List;

public class ImageDAOImpl implements ImageDAO {
    private final SessionFactory sessionFactory;

    public ImageDAOImpl() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        this.sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }
    @Override
    public void addImage(Image image, long productId) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            Product product = session.get(Product.class, productId);

            if (product != null) {
                image.setProduct(product);
                session.save(image);

                transaction.commit();
            } else {
                System.out.println("Produit non trouvé avec l'ID : " + productId);
            }
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }



    @Override
    public Image getImageById(long id) {
        Image image = null;
        try (Session session = sessionFactory.openSession()) {
            image = session.get(Image.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }
}
