package ClothesShop.dao;

import ClothesShop.domain.Clothes;
import ClothesShop.domain.Styles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StylesDao {

    private Connection connection;

    public ArrayList<Styles> findAll (String name) throws SQLException {
        String sql = "SELECT * FROM STYLES ORDER BY NAME";

        ArrayList<Styles> styleS = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet res = statement.executeQuery();

        while (res.next()) {
            Styles styles = new Styles();
            styles.setId(res.getInt("style_id"));
            styles.setName(res.getString("style_name"));
            styleS.add(styles);
        }
        statement.close();
        return styleS;
    }



}
