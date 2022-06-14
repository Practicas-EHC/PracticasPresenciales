package ClothesShop.dao;

import ClothesShop.domain.Users;
import ClothesShop.exception.UserAlreadyExistException;
import ClothesShop.exception.UserNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDao{

    private Connection connection;

    public UserDao(Connection connection) {this.connection=connection;}
    public void add(Users users) throws SQLException, UserAlreadyExistException {
        if (existUser(users.getUsername()))
            throw new UserAlreadyExistException();

        String sql = "INSERT INTO USERS (USERNAME, PASWORD, ADRESS, EMAIL, TELEPHONE) VALUES (?, ?, ? ,? ,?)";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, users.getUsername());
        statement.setString(2, users.getPassword());
        statement.setString(3, users.getAdress());
        statement.setString(4, users.getEmail());
        statement.setString(4, users.getTelephone());

        statement.executeUpdate();
    }

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
    public boolean delete(String username) throws SQLException, UserNotFoundException {
        if (!existUser(username))
            throw new UserNotFoundException();
        String sql = "DELETE FROM USERS WHERE USERNAME = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        int rows = statement.executeUpdate();

        return rows == 1;
    }

    public Optional<Users> findByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM USERS WHERE USERNAME = ?";
        Users user = null;

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            user = new Users();
            user.setUsername(resultSet.getString("USERNAME"));
            user.setPassword(resultSet.getString("PASWORD"));
            user.setAdress(resultSet.getString("ADRESS"));
            user.setEmail(resultSet.getString("EMAIL"));
            user.setTelephone(resultSet.getString("TELEPHONE"));
        }
        return Optional.ofNullable(user);
    }
    public boolean existUser(String username) throws SQLException {
        Optional<Users> user = findByUsername(username);
        return user.isPresent();
    }
}
