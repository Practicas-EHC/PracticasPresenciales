package ClothesShop.servlet;

import ClothesShop.dao.ClothesDao;
import ClothesShop.dao.Database;
import ClothesShop.domain.Clothes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet ("/SearchClothes")
public class SearchClothesServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String searchText = request.getParameter("text");

        Database database = new Database();
        ClothesDao clothesDao = new ClothesDao(database.getConnection());

        try {
            ArrayList<Clothes> clotheS = clothesDao.findByText(searchText);
            StringBuilder result = new StringBuilder("<ul class='list-group'>");
            for (Clothes clothes : clotheS) {
                result.append("<li class='list-group-item'> <a target='_blank' href='cloth.jsp?name="+clothes.getName()+ "'>")  .append  ("</li>");
            }
            result.append("</ul>");
            out.println(result);
        } catch (SQLException sqle) {
            out.println("<div class='alert alert-danger' role='alert'>SEARCH ERROR</div>");

        }
    }
}




