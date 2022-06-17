<%@ page import="java.sql.SQLException" %>
<%@ page import="ClothesShop.dao.Database" %>
<%@ page import="ClothesShop.dao.OrderDao" %>
<%@ page import="ClothesShop.domain.Users" %>
<%@ page import="ClothesShop.domain.Orders" %>
<%@ page import="java.util.ArrayList" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Users currentUser = (Users) session.getAttribute("currentUser");
    if (currentUser == null) {
        response.sendRedirect("login.jsp");
    }
%>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="css/index.css" rel="stylesheet">
</head>
<body style="text-align: center">
<header>
    <div class="container">
        <nav>
            <a href="#" id="icono" class="icono"><img class="me" src="images/img-menu.svg" alt=""></a>
            <div class="enlaces uno" id="enlaces">
                <a href="clothes.jsp">Clothes</a>

                <%
                    if (currentUser != null) {
                %>
                <a href="orders.jsp">My Orders</a>
                <%
                    }
                %>
            </div>
        </nav>
        <h2><a href="index.jsp">Clothes Store</a></h2>
        <div class="alert alert-success" role="alert">
            Welcome <% if (currentUser != null) out.print(currentUser.getUsername()); %>
        </div>
        <ul>
            <li><a href="login.jsp">Login</a></li>
            <%

                if (currentUser == null) {
            %>
            <li><a href="signUp.jsp">Sign Up</a></li>
            <%
                }if (currentUser != null) {
            %>
            <li><a href="logout">Log Out</a></li>
            <li><a href="signUp.jsp">Modify User</a></li>
            <li><a href="deleteUser.jsp">Delete User</a></li>
            <%
                }%>
        </ul>
    </div>
</header>
<div class="">
    <h2>Orders list: <%if(currentUser!=null){ out.print(currentUser.getUsername());} %></h2>
    <ul class="list-group">
        <%
            Database database = new Database();
            OrderDao orderDao = new OrderDao(database.getConnection());
            try {
                ArrayList<Orders> orders = orderDao.getUserOrder(currentUser.getUsername());
                for (Orders order : orders) {
        %>
        <li class="list-group-item">
            <a target="_blank" href="order.jsp?id=<%=order.getId()%>"><%= order.getId() %></a><p><%= order.getDate() %></p>
        </li>
        <%
            }
        } catch (SQLException sqle) {
                sqle.printStackTrace();
        %>
        <div class="alert alert-danger" role="alert">
            Error connecting database
        </div>
        <%
            }
        %>
    </ul>
</div>
<p><a href="index.jsp">Go back to index</a></p>
<script src="js/index.js"></script>
</body>
</html>
