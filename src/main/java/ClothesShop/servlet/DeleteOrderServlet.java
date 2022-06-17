
package ClothesShop.servlet;


import ClothesShop.dao.Database;
import ClothesShop.dao.OrderDao;
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

@WebServlet("/delete-order")
public class DeleteOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        int id = Integer.parseInt(request.getParameter("id"));

        Database database = new Database();
        OrderDao orderDao = new OrderDao(database.getConnection());

        try {
            orderDao.delete(id);
            out.println("<div class='alert alert-success' role='alert'>The order has been deleted succesfully</div>");
        } catch (SQLException sqle) {
            out.println("<div class='alert alert-danger' role='alert'>An error happened while deleting the order</div>");
            sqle.printStackTrace();
        }
    }
}











