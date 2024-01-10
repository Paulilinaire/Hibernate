package impl;

import dao.OrderDAO;
import model.Order;
import model.Order;
import model.Order;
import model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    private final SessionFactory sessionFactory;

    public OrderDAOImpl() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        this.sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    @Override
    public void addOrder(Order order, List<Long> productIds) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            // Charge les produits associés à la commande
            List<Product> products = new ArrayList<>();
            for (Long productId : productIds) {
                Product product = session.get(Product.class, productId);
                if (product != null) {
                    products.add(product);
                }
            }

            // Associe les produits à la commande
            order.setProducts(products);

            // Enregistre la date et le total de la commande
            order.setOrderDate(LocalDate.now());
            double total = products.stream().mapToDouble(Product::getPrice).sum();
            order.setTotal(total);

            session.save(order);

            transaction.commit();
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


    // Méthode pour récupérer les produits par leurs IDs
    private List<Product> getProductsByIds(Session session, List<Long> productIds) {
        Query<Product> productQuery = session.createQuery("from Product where id in :ids", Product.class);
        productQuery.setParameterList("ids", productIds);
        return productQuery.list();
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> orderList = null;
        try (Session session = sessionFactory.openSession()) {
            Query<Order> orderQuery = session.createQuery("from Order", Order.class);
            orderList = orderQuery.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderList;
    }

    @Override
    public Order getOrderById(long id) {
        Order order = null;
        try (Session session = sessionFactory.openSession()) {
            order = session.get(Order.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return order;
    }

    public List<Order> getOrdersOfTheDay() {
        List<Order> orderList = null;
        Session session = null;

        try {
            session = sessionFactory.openSession();

            LocalDate currentDate = LocalDate.now();

            Query<Order> orderQuery = session.createQuery("from Order where orderDate = :currentDate", Order.class);
            orderQuery.setParameter("currentDate", currentDate);

            orderList = orderQuery.list();
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

        return orderList;
    }

}
