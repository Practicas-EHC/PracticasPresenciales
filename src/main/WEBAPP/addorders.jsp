<%@ page import="ClothesShop.domain.Users" %>
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
            $.post("add-order", formValue, function(data) {
                $("#result").html(data);
            });
        });
    });
</script>
<div>
    <h2>New order</h2>
    <form>
        <div class="mb-2">
            <label for="cloth" class="form-label">Cloth name</label>
            <input name="cloth" style="margin: auto" type="text" class="form-control w-25" id="cloth"  value="<% out.print(name);%>">
        </div>
        <div class="mb-2">
            <label for="size" class="form-label">Select your size</label>
            <input name="size" style="margin: auto" type="text" class="form-control w-25" id="size"  value="">
        </div>
        <div class="mb-2">
            <label for="quantity" class="form-label">How many items do you want to buy</label>
            <input name="quantity" style="margin: auto" type="text" class="form-control w-25" id="quantity"  value="">
        </div>
        <button type="submit" class="btn btn-primary">Make order</button>
    </form>
    <div id="result"></div>
    <p><a href="index.jsp">Go back to index</a></p>
</div>
<script src="js/index.js"></script>
</body>
</html>