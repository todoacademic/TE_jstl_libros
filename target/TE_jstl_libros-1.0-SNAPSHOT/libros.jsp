
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.emergentes.modelo.Libro"%>
<%@page import="com.emergentes.modelo.GestorLibros"%>
<%
    if (session.getAttribute("agenda") == null) {
        GestorLibros objeto1 = new GestorLibros();

        objeto1.insertarLibro(new Libro(1, "El principito", "Antonie de Sain", "Si", "Cuento"));
        objeto1.insertarLibro(new Libro(2, "La conspiracion", "Dan Brown", "No", "Novela"));
        objeto1.insertarLibro(new Libro(3, "Historia de Bolivia", "Historia", "Si", "Historia"));

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
        <h1>Libros</h1>
        <a href="Controller?op=nuevo">Nuevo</a><br><br>
        <table border="1">
            <tr>
                <th>Id</th>
                <th>Titulo</th>
                <th>Autor</th>
                <th>Disponible</th>
                <th>Categoria</th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach var="item" items="${sessionScope.agenda.getLista()}">
                <tr>
                    <td>${item.id}</td> 
                    <td>${item.titulo}</td>
                    <td>${item.autor}</td> 
                    <td>${item.disponible}</td> 
                    <td>${item.categoria}</td> 
                    <td><a href="Controller?op=modificar&id=${item.id}">Modificar</a></td> 
                    <td><a href="Controller?op=eliminar&id=${item.id}">Eliminar</a></td> 
                </tr>
            </c:forEach>
        </table>

    </body>
</html>
