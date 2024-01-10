package impl;

import dao.CommentDAO;
import model.Comment;
import model.Comment;
import model.Comment;
import model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.List;

public class CommentDAOImpl implements CommentDAO {

    private final SessionFactory sessionFactory;

    public CommentDAOImpl() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        this.sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    @Override
    public void addComment(Comment comment, long productId) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            Product product = session.get(Product.class, productId);

            if (product != null) {
                comment.setProduct(product);
                session.save(comment);

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
    public Comment getCommentById(long id) {
        Comment comment = null;
        try (Session session = sessionFactory.openSession()) {
            comment = session.get(Comment.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return comment;
    }

    public List<Product> filterByGrade(int grade){
        List<Product> productList = null;
        Session session = null;
        try {
            if (grade >= 0) {
                session = sessionFactory.openSession();

                Query<Product> productQuery = session.createQuery("from Comment where grade >= :grade", Product.class);
                productQuery.setParameter("grade", grade);

                productList = productQuery.list();
            }
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return productList;
    }


}
