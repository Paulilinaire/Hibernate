package dao;

import model.Order;

import java.util.List;

public interface OrderDAO {

    public void addOrder(Order order, List<Long> productIds);

    public List<Order> getAllOrders();

    public Order getOrderById(long id);

}
