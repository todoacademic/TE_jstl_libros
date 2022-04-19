
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.emergentes.modelo.Categoria"%>
<%@page import="com.emergentes.modelo.GestorCategorias"%>
<%
    if(session.getAttribute("agenda") == null){
        GestorCategorias objeto1 = new GestorCategorias();
        
        objeto1.insertarCategoria(new Categoria(1, "Novela"));
        objeto1.insertarCategoria(new Categoria(2, "Historia"));
        objeto1.insertarCategoria(new Categoria(3, "Cuento"));
        
        session.setAttribute("agenda", objeto1);
    }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Categorias</h1>
        <a href="ControllerCat?op=nuevo">Nuevo</a><br><br>
        <table border="1">
            <tr>
                <th>Id</th>
                <th>Categoria</th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach var="item" items="${sessionScope.agenda.getLista()}">
            <tr>
                <td>${item.id}</td> 
                <td>${item.categoria}</td> 
                <td><a href="ControllerCat?op=modificar&id=${item.id}">Modificar</a></td> 
                <td><a href="ControllerCat?op=eliminar&id=${item.id}">Eliminar</a></td> 
            </tr>
            </c:forEach>
        </table>
    </body>
</html>
