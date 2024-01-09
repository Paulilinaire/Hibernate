package impl;

import dao.ProductDAO;
import model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    private final SessionFactory sessionFactory;

    public ProductDAOImpl() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        this.sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    @Override
    public void addProduct(Product product) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(product);
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


    @Override
    public void deleteProduct(long id) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Product productToDelete = session.get(Product.class, id);
            session.delete(productToDelete);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void updateProduct(long id, Product updatedProduct) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            Product productToUpdate = session.get(Product.class, id);

            if (productToUpdate != null) {
                productToUpdate.setReference(updatedProduct.getReference());
                productToUpdate.setBrand(updatedProduct.getBrand());
                productToUpdate.setSaleDate(updatedProduct.getSaleDate());
                productToUpdate.setPrice(updatedProduct.getPrice());
                productToUpdate.setStorage(updatedProduct.getStorage());

                session.update(productToUpdate);
            }

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


    @Override
    public List<Product> getAllProducts() {
        List<Product> productList = null;
        try (Session session = sessionFactory.openSession()) {
            Query<Product> productQuery = session.createQuery("from Product", Product.class);
            productList = productQuery.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }

    @Override
    public Product getProductById(long id) {
        Product product = null;
        try (Session session = sessionFactory.openSession()) {
            product = session.get(Product.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }

    public List<Product> filterByPrice(double min) {
        List<Product> productList = null;
        Session session = null;

        try {
            if (min >= 0) {
                session = sessionFactory.openSession();

                Query<Product> productQuery = session.createQuery("from Product where price >= :min", Product.class);
                productQuery.setParameter("min", min);

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


    public List<Product> filterByDate(LocalDate min, LocalDate max) {
        List<Product> productList = null;
        Session session = null;

        try {
            if (min.isBefore(max)) {
                session = sessionFactory.openSession();

                Query<Product> productQuery = session.createQuery("from Product where saleDate >= :min and saleDate <= :max", Product.class);
                productQuery.setParameter("min", min);
                productQuery.setParameter("max", max);

                productList = productQuery.list();
            } else {
                throw new Exception("Erreur de date : min doit être avant max.");
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

    public List<Product> filterByStorage(int max) {
        List<Product> productList = null;
        Session session = null;

        try {
            if (max >= 0) {
                session = sessionFactory.openSession();

                Query<Product> productQuery = session.createQuery("from Product where storage < :max", Product.class);
                productQuery.setParameter("max", max);

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

    public double calculateStockValueByBrand(String brand) {
        double stockValue = 0;
        Session session = null;

        try {
            session = sessionFactory.openSession();

            Query<Double> stockValueQuery = session.createQuery("select sum(price * storage) from Product where brand = :brand", Double.class);
            stockValueQuery.setParameter("brand", brand);

            stockValue = stockValueQuery.uniqueResult(); // Retourne la somme du prix total des produits de la marque
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return stockValue;
    }

    public double calculateAveragePrice() {
        double avgPrice = 0;
        Session session = null;

        try {
            session = sessionFactory.openSession();

            Query<Double> avgPriceQuery = session.createQuery("select avg(price) from Product", Double.class);
            avgPrice = avgPriceQuery.uniqueResult(); // Retourne le prix moyen du produit

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

        return avgPrice;
    }

    public List<Product> getBrandProducts(String brand) {
        Session session = null;
        List<Product> productList = new ArrayList<>();

        try {
            session = sessionFactory.openSession();

            Query<Product> brandProductsQuery = session.createQuery("from Product where brand in :brand", Product.class);
            brandProductsQuery.setParameter("brand", brand);

            productList = brandProductsQuery.list();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return productList;
    }


    public void deleteProductsFromSpecificBrand(String brand) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            Query<Product> deleteQuery = session.createQuery("delete from Product where brand = :brand");
            deleteQuery.setParameter("brand", brand);
            deleteQuery.executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }




}
