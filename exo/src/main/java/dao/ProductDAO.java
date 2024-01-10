package dao;

import model.Product;

import java.util.List;

public interface ProductDAO {

    public void addProduct(Product product);

    public void deleteProduct(long id);

    public void updateProduct(long id, Product product);

    public List<Product> getAllProducts();

    public Product getProductById(long id);

}