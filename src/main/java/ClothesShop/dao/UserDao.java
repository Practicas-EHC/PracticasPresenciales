package ClothesShop.dao;

import ClothesShop.domain.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDao{

    private Connection connection;

    public UserDao(Connection connection) {this.connection=connection;}

    public Optional<Users> getUsuario (String username, String password) throws SQLException{
        String sql = "select from USERS WHERE USERNAME = ? AND PASWORD = ?";
        Users users = null;

        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1,username);
        st.setString(2,password);

        ResultSet res = st.executeQuery();
        while (res.next()){
            users = new Users();
            users.setUsername(res.getString("username"));
            users.setPassword(res.getString("pasword"));
            users.setAdress(res.getString("adress"));
            users.setEmail(res.getString("email"));
            users.setTelephone(res.getString("telephone"));
        }
        st.close();
        return Optional.ofNullable(users);
    }
}
