<%@ page import="ClothesShop.dao.Database" %>
<%@ page import="ClothesShop.dao.ClothesDao" %>
<%@ page import="ClothesShop.domain.Clothes" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="ClothesShop.domain.Users" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Users currentUser = (Users) session.getAttribute("currentUser");
    if (currentUser == null) {
        response.sendRedirect("login.jsp");
    }
    String name = request.getParameter("name");
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
<%
    String clothesId = request.getParameter("id");
    Database database = new Database();
    ClothesDao clothesDao = new ClothesDao(database.getConnection());
    try {
        Clothes clothes = clothesDao.findById(Integer.parseInt(clothesId));

%>
<div class="">
    <div class="card text-center">
        <div class="card-header">
            Clothes details
        </div>
        <div class="card-body">
            <img class="mb-4" src="images/image<%=clothes.getId()%>.jpg" width="600" height="500" style="margin: auto">
            <h5 class="card-title"><%= clothes.getName() %></h5>
            <p class="card-text">Style <strong><%= clothes.getStyles() %></strong></p>
            <p class="card-text">Collection <strong><%= clothes.getCollection() %></strong></p>
            <p class="card-text">Price <strong><%= clothes.getPrice() %>€</strong></p>
            <a href="addorders.jsp?name=<%=clothes.getName() %>" class="btn btn-primary">Buy</a>
        </div>
    </div>
</div>
<%
} catch (SQLException sqle) {
%>
<div class='alert alert-danger' role='alert'>There was an error loading the clothes list</div>
<%
    }
%>
<p><a href="index.jsp">Go back to index</a></p>
<script src="js/index.js"></script>
</body>
</html>
