<%@ page import="ClothesShop.domain.Users" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="ClothesShop.exception.UserNotFoundException" %>
<%
    Users currentUser = (Users) session.getAttribute("currentUser");
    String textButton = "";
    String textTitle = "";
    if (currentUser != null) {
        textButton = "Modify";
        textTitle = "New data of the user";
    } else {
        textButton = "Register";
        textTitle = "Insert the data of the new user";
    }
%>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
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
<script type="text/javascript">
    $(document).ready(function() {
        $("form").on("submit", function(event) {
            event.preventDefault();
            var formValue = $(this).serialize();
            $.post("add-modify-user", formValue, function(data) {
                $("#result").html(data);
            });
        });
    });
</script>
<div>
    <h2><%= textTitle %></h2>
    <form>
        <div class="mb-2">
            <label for="username" class="form-label">UserName</label>
            <input name="username" style="margin: auto" type="text" class="form-control w-25" id="username" value="<% if(currentUser!=null){out.print(currentUser.getUsername());}%>">
        </div>
        <div class="mb-2">
            <label for="password" class="form-label">Password</label>
            <input name="password"style="margin: auto" type="password" class="form-control w-25" id="password" value="<%if(currentUser!=null){ out.print(currentUser.getPassword());}%>">
        </div>
        <div class="mb-3">
            <label for="adress" class="form-label">Adress</label>
            <input name="adress" style="margin: auto" type="text" class="form-control w-25" id="adress" value="<% if(currentUser!=null){out.print(currentUser.getAdress());}%>">
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input name="email" style="margin: auto" type="text" class="form-control w-25" id="email" value="<% if(currentUser!=null){out.print(currentUser.getEmail());}%>">
        </div>
        <div class="mb-3">
            <label for="telephone" class="form-label">Telephone</label>
            <input name="telephone" style="margin: auto" type="number" class="form-control w-25" id="telephone" value="<%if(currentUser!=null){ out.print(currentUser.getTelephone());}%>">
        </div>

        <input type="hidden" name="action" value="<% if (currentUser != null) out.print("modify"); else out.print("register"); %>">
        <input type="hidden" name="UserName" value="<% if (currentUser != null) out.print(currentUser.getUsername()); %>">
        <button type="submit" class="btn btn-primary"><%= textButton %></button>
    </form>
    <div id="result"></div>
    <p><a href="index.jsp">Go back to index</a></p>
</div>
<script src="js/index.js"></script>
</body>
</html>
