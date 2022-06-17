package ClothesShop.servlet;

import ClothesShop.dao.Database;
import ClothesShop.dao.UserDao;
import ClothesShop.domain.Users;
import ClothesShop.exception.UserAlreadyExistException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


@WebServlet ("/add-modify-user")
public class AddModifyUserServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String adress= request.getParameter("adress");
        String email = request.getParameter("email");
        String telephone = request.getParameter("telephone");
        Users user = new Users(username, password, adress, email, telephone);
        String action = request.getParameter("action");
        String oldUser = request.getParameter("UserName");
        Database database = new Database();
        UserDao userDao = new UserDao(database.getConnection());
        try {
            if (action.equals("register")) {
                userDao.add(user);
                out.println("<div class='alert alert-success' role='alert'>Singed up succesfully</div>");
            } else {
                userDao.modifyApp(oldUser, user);
                out.println("<div class='alert alert-success' role='alert'>The user has been modified succesfully, to see the changes please</div>");
            }
        } catch (UserAlreadyExistException uaee) {
            out.println("<div class='alert alert-danger' role='alert'>El usuario ya existe en la base de datos</div>");
            uaee.printStackTrace();
        } catch (SQLException sqle) {
            out.println(username + password + adress + email + telephone + oldUser);
            sqle.printStackTrace();
        }

    }
}



