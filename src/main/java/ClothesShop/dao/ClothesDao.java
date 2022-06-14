package ClothesShop.dao;

import ClothesShop.domain.Clothes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class ClothesDao {
    private Connection connection;

    public ClothesDao (Connection connection) { this.connection = connection;}

    public ArrayList<Clothes> findByName (String name) throws SQLException {
        String sql = "SELECT * FROM CLOTHES where name = ? ";

        ArrayList<Clothes> clothes = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, name);
        ResultSet res = statement.executeQuery();

        while (res.next()) {
            Clothes cloth = new Clothes();
            cloth.setId(res.getInt("id"));
            cloth.setStyles(res.getString("style"));
            cloth.setOrders(res.getString("order"));
            cloth.setPrice(res.getFloat("price"));
            cloth.setName(res.getString("name"));
            clothes.add(cloth);
        }
        statement.close();
        return clothes;
    }
    public ArrayList<Clothes> findByStyle (String style) throws SQLException {
        String sql = "SELECT * FROM CLOTHES JOIN STYLES ON CLOTHES.STYLE_ID=STYLES.STYLE_ID WHERE style = ? ";

        ArrayList<Clothes> clothes = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, style);
        ResultSet res = statement.executeQuery();

        while (res.next()) {
            Clothes cloth = new Clothes();
            cloth.setId(res.getInt("id"));
            cloth.setStyles(res.getString("style"));
            cloth.setOrders(res.getString("order"));
            cloth.setPrice(res.getFloat("price"));
            cloth.setName(res.getString("name"));
            clothes.add(cloth);
        }
        statement.close();
        return clothes;
    }
    public ArrayList<Clothes> findByCollection (String collection) throws SQLException {
        String sql = "SELECT * FROM CLOTHES JOIN COLLECTIONS ON CLOTHES.COLLECTION_ID=COLLECTIONS.COLLECTION_ID WHERE collection = ? ";

        ArrayList<Clothes> clothes = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, collection);
        ResultSet res = statement.executeQuery();

        while (res.next()) {
            Clothes cloth = new Clothes();
            cloth.setId(res.getInt("id"));
            cloth.setStyles(res.getString("style"));
            cloth.setOrders(res.getString("order"));
            cloth.setPrice(res.getFloat("price"));
            cloth.setName(res.getString("name"));
            clothes.add(cloth);
        }
        statement.close();
        return clothes;
    }


}
