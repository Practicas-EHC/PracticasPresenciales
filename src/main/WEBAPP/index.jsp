<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         import="ClothesShop.domain.Users"
%>
<%
    Users currentUser = (Users) session.getAttribute("currentUser");
%>
<html>
<head>
    <title>ClothesShop</title>
    <link href="css/index.css" rel="stylesheet">
</head>
<body>

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
    <h2>Clothes Store</h2>
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
<div id="carrusel-contenido">
    <div id="carrusel-caja">
        <div class="carrusel-elemento">
            <img class="imagenes" src="images/vestido.jpg" >
        </div>
        <div class="carrusel-elemento">
            <img class="imagenes" src="images/traje.jpg">
        </div>
        <div class="carrusel-elemento">
            <img class="imagenes" src="images/zapatillas.jpg">
        </div>
    </div>
</div>
<script src="js/index.js"></script>
</body>
</html>
