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
        statement.setString(5, users.getTelephone());

        statement.executeUpdate();
    }

    public Optional<Users> getUser (String username, String password) throws SQLException, UserNotFoundException{
        if (!existUser(username))
            throw new UserNotFoundException();

        String sql = "SELECT * FROM USERS WHERE USERNAME = ? AND PASWORD = ?";
        Users users = null;

        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1, username);
        st.setString(2, password);

        ResultSet res = st.executeQuery();
        while (res.next()){
            users = new Users();
            users.setUsername(res.getString("USERNAME"));
            users.setPassword(res.getString("PASWORD"));
            users.setAdress(res.getString("ADRESS"));
            users.setEmail(res.getString("EMAIL"));
            users.setTelephone(res.getString("TELEPHONE"));
        }
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

    public boolean modifyApp(String userName, Users newUser) throws SQLException {

        String sql = "UPDATE USERS SET USERNAME = ?, PASWORD = ?, ADRESS = ?, EMAIL = ?, TELEPHONE = ? WHERE USERNAME = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, newUser.getUsername());
        statement.setString(2, newUser.getPassword());
        statement.setString(3, newUser.getAdress());
        statement.setString(4, newUser.getEmail());
        statement.setString(5, newUser.getTelephone());
        statement.setString(6, userName);
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
        }
        return Optional.ofNullable(user);
    }
    public boolean existUser(String username) throws SQLException {
        Optional<Users> user = findByUsername(username);
        return user.isPresent();
    }
}
