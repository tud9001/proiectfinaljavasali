
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: tud
  Date: 13/05/2020
  Time: 15:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log in</title>
</head>
<body>
<h3 align="center">Salut! Introdu username-ul pentru a te loga</h3>
<form align="center" method="post" action="/login">
    <input type="text" name="username"><br>
    <input type="text" name="password"><br>
    <input type="submit" value="Log in!">
</form>


<p align="center">
    <%
        out.print("Username "+ request.getAttribute("username"));
        out.print("<br>");
        out.print( request.getAttribute("loginError"));
%></p>
</body>
</html>