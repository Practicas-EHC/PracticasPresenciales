package ClothesShop.servlet;

import ClothesShop.dao.ClothesDao;
import ClothesShop.dao.Database;
import ClothesShop.dao.OrderDao;
import ClothesShop.domain.Clothes;
import ClothesShop.domain.Users;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


@WebServlet("/add-order")
public class AddOrderServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Users currentUser = (Users) request.getSession().getAttribute("currentUser");
        String name = request.getParameter("cloth");
        String size = request.getParameter("size");
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        Database database = new Database();
        ClothesDao clothesDao = new ClothesDao(database.getConnection());
        Clothes cloth = null;

        try {
            cloth = clothesDao.findByName(name);
        } catch (SQLException e) {
            out.println("<div class='alert alert-danger' role='alert'>Se ha producido un error al registrar el pedido</div>");
        }
        if (cloth != null)
            try {
                OrderDao orderDao = new OrderDao(database.getConnection());
                orderDao.addOrder(currentUser, cloth, size, quantity);
                out.println("<div class='alert alert-success' role='alert'>ORDER SUCCESS</div>");
            } catch (SQLException e) {
                out.println("<div class='alert alert-danger' role='alert'>ORDER ERROR</div>");
                e.printStackTrace();
            } finally {
                database.close();
            }
    }
}

