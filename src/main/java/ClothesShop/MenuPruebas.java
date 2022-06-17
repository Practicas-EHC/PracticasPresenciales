package ClothesShop;

import ClothesShop.dao.Database;

import java.sql.Connection;

public class MenuPruebas {
    private Database database;
    private Connection connection;

    public void connect() {
        database = new Database();
        connection = database.getConnection();
    }
    public void showMenu()  {
        connect();
    }

}
