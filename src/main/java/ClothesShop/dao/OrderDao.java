package ClothesShop.dao;

import ClothesShop.domain.Clothes;
import ClothesShop.domain.Orders;
import ClothesShop.domain.Orders_List;
import ClothesShop.domain.Users;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {

    private Connection connection;

    public OrderDao(Connection connection) {
        this.connection = connection;
    }

    public void addOrder(Users users,Clothes cloth, String size, int quantity) throws SQLException {
        String orderSql = "INSERT INTO ORDERS (ORDER_DATE, USERNAME) VALUES (?,?)";

        PreparedStatement orderStatement = connection.prepareStatement(orderSql);
        orderStatement.setDate(1, Date.valueOf(LocalDate.now()));
        orderStatement.setString(2, users.getUsername());
        orderStatement.executeUpdate();

        String sql = "SELECT MAX(ORDER_ID) FROM orders";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            int orderId = resultSet.getInt(1);

                String bookSql = "INSERT INTO ORDERS_LIST (CLOTHES_ID, ORDER_ID, CLOTHE_SIZE, QUANTITY) VALUES (?, ?, ?, ?)";
                PreparedStatement bookStatement = connection.prepareStatement(bookSql);
                bookStatement.setInt(1, cloth.getId());
                bookStatement.setInt(2, orderId);
                bookStatement.setString(3, size);
                bookStatement.setInt(4, quantity);
                bookStatement.executeUpdate();

        }
    }

    public void delete(int idOrder) throws SQLException {
        String orderSql = "DELETE FROM ORDERS_LIST WHERE ORDER_ID=?";
        PreparedStatement orderStatement = connection.prepareStatement(orderSql);
        orderStatement.setInt(1, idOrder);
        orderStatement.executeUpdate();

        String sql = "DELETE FROM ORDERS WHERE ORDER_ID=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, idOrder);
        statement.executeUpdate();
    }

    public ArrayList<Orders> getUserOrder(String username) throws SQLException {

        String sql = "SELECT * FROM ORDERS O JOIN USERS U ON O.USERNAME=U.USERNAME WHERE U.USERNAME = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        ArrayList<Orders> orders = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Orders order = new Orders();
            order.setId(resultSet.getInt("ORDER_ID"));
            order.setUsername(resultSet.getString("USERNAME"));
            order.setDate(resultSet.getDate("ORDER_DATE"));
            orders.add(order);
        }
        return orders;

    }
    public Orders getOrder(int id) throws SQLException {

        String sql = "SELECT * FROM ORDERS O JOIN ORDERS_LIST L ON O.ORDER_ID = L.ORDER_ID JOIN CLOTHES C ON L.CLOTHES_ID=C.CLOTHES_ID  WHERE O.ORDER_ID = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        Orders order =null;
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            order = new Orders();
            order.setId(resultSet.getInt("ORDER_ID"));
            order.setUsername(resultSet.getString("USERNAME"));
            order.setDate(resultSet.getDate("ORDER_DATE"));
            order.setClothes(resultSet.getString("CLOTHES_NAME"));
        }
        return order;

    }

    public boolean modifyOrders_List(String  order_id, Orders_List newOrderList) throws SQLException {

        String sql = "UPDATE ORDERS_LIST SET CLOTHE_SIZE = ?, QUANTITY = ? WHERE ORDER_ID = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, newOrderList.getClothe_size());;
        statement.setString(2, String.valueOf(newOrderList.getQuantity()));
        statement.setString(3, order_id);

        int rows = statement.executeUpdate();
        return  rows == 1;


    }

}
