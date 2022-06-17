package ClothesShop.servlet;

import ClothesShop.dao.Database;
import ClothesShop.dao.UserDao;
import ClothesShop.exception.UserNotFoundException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/delete-user")
public class DeleteUserServlet extends HttpServlet{

        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            String username = request.getParameter("name");

            Database database = new Database();
            UserDao userDao = new UserDao(database.getConnection());

            try {
                userDao.delete(username);
                out.println("<div class='alert alert-success' role='alert'>The user has been deleted successfullu</div>");
            } catch (SQLException sqle) {
                out.println("<div class='alert alert-danger' role='alert'>An error happened while deleting the user, please check if the user has any active orders</div>");
                sqle.printStackTrace();
            } catch (UserNotFoundException UNFE) {
                UNFE.printStackTrace();
                out.println("<div class='alert alert-danger' role='alert'>User not found in the database</div>");
            }
        }
    }

