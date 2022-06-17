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

    public ClothesDao(Connection connection) {
        this.connection = connection;
    }

    public  ArrayList<Clothes> findAll() throws SQLException {
        String sql = "SELECT * FROM CLOTHES";
        ArrayList<Clothes> clothes = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet res = statement.executeQuery();
        while (res.next()) {
            Clothes cloth = new Clothes();
            cloth.setId(res.getInt("CLOTHES_ID"));
            cloth.setStyles(res.getString("STYLE_ID"));
            cloth.setCollection(res.getString("COLLECTION"));
            cloth.setName(res.getString("CLOTHES_NAME"));
            cloth.setPrice(res.getFloat("PRICE"));
            clothes.add(cloth);
        }
        return clothes;
    }

    public Clothes findByName(String name) throws SQLException {
        String sql = "SELECT * FROM CLOTHES JOIN STYLES ON CLOTHES.STYLE_ID=STYLES.STYLE_ID  where CLOTHES_NAME = ? ";

        Clothes cloth = null;

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, name);
        ResultSet res = statement.executeQuery();
        if(res.next()){
            cloth=new Clothes();
            cloth.setId(res.getInt("CLOTHES_ID"));
            cloth.setStyles(res.getString("STYLE_NAME"));
            cloth.setCollection(res.getString("COLLECTION"));
            cloth.setPrice(res.getFloat("PRICE"));
            cloth.setName(res.getString("CLOTHES_NAME"));
        }
        return cloth;
    }

    public Clothes findById(int id) throws SQLException {
        String sql = "SELECT * FROM CLOTHES JOIN STYLES ON CLOTHES.STYLE_ID=STYLES.STYLE_ID  where CLOTHES_ID = ? ";

      Clothes cloth = null;

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet res = statement.executeQuery();
        if(res.next()){
            cloth=new Clothes();
            cloth.setId(res.getInt("CLOTHES_ID"));
            cloth.setStyles(res.getString("STYLE_NAME"));
            cloth.setCollection(res.getString("COLLECTION"));
            cloth.setPrice(res.getFloat("PRICE"));
            cloth.setName(res.getString("CLOTHES_NAME"));
        }
    return cloth;
    }

    public ArrayList<Clothes> findByStyle(String style) throws SQLException {
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

    public ArrayList<Clothes> findByCollection(String collection) throws SQLException {
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


    public ArrayList<Clothes> findByText(String text) throws SQLException {
        String sql = "SELECT STYLE_NAME ,COLLECTION, CLOTHES_NAME FROM STYLES ST JOIN CLOTHES CL  ON ST.STYLE_ID = CL.STYLE_ID  WHERE INSTR(STYLE_NAME,?) != 0 OR INSTR(COLLECTION, ?) != 0 OR INSTR(CLOTHES_NAME, ?) != 0  ORDER BY CLOTHES_NAME";

        ArrayList<Clothes> clotheS = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,text);
        statement.setString(2,text);
        statement.setString(3,text);

        ResultSet res = statement.executeQuery();

        while (res.next()) {
            Clothes clothes = new Clothes();
            clothes.setName(res.getString("CLOTHES_NAME"));
            clothes .setCollection(res.getString("COLLECTION"));
            clothes .setStyles(res.getString("STYLE_NAME"));

            clotheS.add(clothes);
        }
        return clotheS;
    }

}


