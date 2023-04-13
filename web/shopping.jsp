
<%@page import="sample.user.UserDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping | Mi Watch Store</title>
        <link rel="stylesheet" href="https://unpkg.com/boxicons@latest/css/boxicons.min.css" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <style>
            .w3-sidebar a {
                font-family: "Roboto", sans-serif
            }

            body,
            h1,
            h2,
            h3,
            h4,
            h5,
            h6,
            .w3-wide {
                font-family: "Montserrat", sans-serif;
            }
        </style>
    </head>
    <body>

        <jsp:include page="menu.jsp"></jsp:include>
        <c:if test = "${!requestScope.FirstTime}">
            <c:if test = "${empty requestScope.Watch}">
                <br><p>Please search products that you'd like to buy</p>
                <p> Note: Type "all" if you want to display all Watches.</p>
            </c:if>

            <c:if test = "${requestScope.Success_Purchase}">
                <p style="color: red;"> Purchase successfully. Please check your Cart for more details.</p>
            </c:if>
            <c:if test = "${not empty requestScope.Watch}">
                <%
                    String error = (String) request.getAttribute("ERROR");
                    if (error == null) {
                        error = "";
                    }

                %>
                <p style="color: red; ">
                    <%= error%>
                </p>

                <% //ADMIN ROLES%>
                <%
                    UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
                    if ("AD".equals(loginUser.getRoleID())) {
                %>
                <table border="1">
                    <thead>
                        <tr>
                            <th>No. </th>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Image</th>
                            <th>Category ID</th>
                            <th>Update</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var = "Watch" items = "${requestScope.Watch}" varStatus = "status">
                        <form action="MainController">
                            <tr>
                                <td>${status.count}</td>
                                <td>${Watch.getId()}</td>

                                <td>
                                    <input type="text" name="name" value="${Watch.getName()}" />
                                </td>

                                <td>
                                    <input type="number" min="1000" max="10000000000" name="price" value="${Watch.getPrice()}" />
                                </td>

                                <td>
                                    <input type="number" min="1" name="quantity" value="${Watch.getQuantity()}" />
                                </td>

                                <td>
                                    <input type="text" name="image" value="${Watch.getImage()}" />
                                    <img src="${Watch.getImage()}" alt="Watch" width="280" height="250" />
                                </td>

                                <td>
                                    <input type="number" min="1" max="3" name="category" value="${Watch.getCategoryID()}" />
                                </td>

                                <td>
                                    <input type="submit" name="action" value="UpdateProduct" />
                                    <input type="hidden" name="pid" value="${Watch.getId()}" />
                                    <input type="hidden" name="lastSearchValue" value="<%= request.getParameter("txtSearch")%>" />
                                </td>

                                <td>
                                    <a href="MainController?action=RemoveWatch&id=${Watch.getId()}&search=${requestScope.SearchValue}">Delete</a>
                                </td>
                            </tr>
                        </form>
                    </c:forEach>

                </tbody>

            </table>

            <% //USER ROLES%>
            <%
            } else if ("US".equals(loginUser.getRoleID())) {
            %>
            <form action = "AddController" method = "GET" id = "Add" > </form> 
            <table border="1">
                <thead>
                    <tr>
                        <th>No. </th>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Image</th>
                        <th>Category ID</th>
                        <th>Purchase Quantity</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var = "Watch" items = "${requestScope.Watch}" varStatus = "status">
                        <tr>
                            <td>${status.count}</td>
                            <td>${Watch.getId()}</td>
                            <td>${Watch.getName()}</td>
                            <td>${Watch.getPrice()}</td>
                            <td>${Watch.getQuantity()}</td>
                            <td><img src="${Watch.getImage()}" alt="Watch" width="280" height="250" /></td>
                            <td>${Watch.getCategoryID()}</td>

                            <td>
                                <input type = "number" min="1" max="${Watch.getQuantity()}" name = "${Watch.getId()}_Quantity" value = "${requestScope.QuantityValues.get(Watch)}" form = "Add">    
                            </td>
                        </tr>
                    </c:forEach>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td>
                    <input type = "HIDDEN" name = "SearchValue" value = "${requestScope.SearchValue}" form = "Add">
                    <input type = "SUBMIT" name = "watchStore" value = "Purchase" form = "Add">    

                </td>
            </tbody>

        </table>
        <% }%>
    </c:if>
</c:if>
<%
    String message = (String) request.getAttribute("MESSAGE");
    if (message == null) {
        message = "";
    }

%>
<p style="color: red; ">
    <%= message%>
</p>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</body>
</html>
