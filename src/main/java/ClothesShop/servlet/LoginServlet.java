package ClothesShop.servlet;

import ClothesShop.dao.Database;
import ClothesShop.dao.UserDao;
import ClothesShop.domain.Users;
import ClothesShop.exception.UserNotFoundException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Optional;

@WebServlet ("/login")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Database database = new Database();
        UserDao userDao = new UserDao(database.getConnection());
        try {
            Optional<Users> users = userDao.getUser(username, password);
            if (users.isPresent()) {
                HttpSession session = request.getSession(true);
                session.setAttribute("currentUser", users.get());
                out.println("<div class='alert alert-success' role='alert'>Successful login</div>");
            } else {
                out.println("<div class='alert alert-danger' role='alert'>Check the login details</div>");
            }
        } catch (SQLException sqle) {
            out.println("<div class='alert alert-danger' role='alert'>An error happened during the search</div>");
            sqle.printStackTrace();
        }catch (UserNotFoundException unfe) {
            out.println("<div class='alert alert-danger' role='alert'>User not found in the database</div>");
        }
    }
}


