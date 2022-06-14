package ClothesShop.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class OrderDao {

    private Connection connection;

    public OrderDao (Connection connection) { this.connection = connection;}
    
}
