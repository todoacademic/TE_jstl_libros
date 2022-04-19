<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>
            <c:if test="${requestScope.op == 'nuevo'}" var="res" scope="request">
                Registro de
            </c:if>
            <c:if test="${requestScope.op == 'modificar'}" var="res" scope="request">
                Modificar
            </c:if>
            Libro
        </h1>
        <jsp:useBean id="miLibro" scope="request" class="com.emergentes.modelo.Libro" />

        <form action="Controller" method="post">
            <table>
                <tr>
                    <td>Id</td>
                    <td><input type="text" name="id" 
                               value="<jsp:getProperty name="miLibro" property="id" />">
                    </td>
                </tr>
                <tr>
                    <td>Titulo</td>
                    <td><input type="text" name="titulo" 
                               value="<jsp:getProperty name="miLibro" property="titulo" />">
                    </td>
                </tr>
                <tr>
                    <td>Autor</td>
                    <td><input type="text" name="autor" 
                               value="<jsp:getProperty name="miLibro" property="autor" />">
                    </td>
                </tr>
                <tr>
                    <td>Disponible</td>
                    <td><input type="text" name="disponible" 
                               value="<jsp:getProperty name="miLibro" property="disponible" />">
                    </td>
                </tr>
                <tr>
                    <td>Categoria</td>
                    <td>
                        <select name="categoria">
                            <option value="Novela"
                                    <c:if test="${miLibro.categoria == 'Novela'}" 
                                    var="res" scope="request">
                                selected
                                </c:if>
                                >Novela</option>
                            <option value="Historia"
                                    <c:if test="${miLibro.categoria == 'Historia'}" 
                                    var="res" scope="request">
                                selected
                                </c:if>
                                >Historia</option>
                            <option value="Cuento"
                                    <c:if test="${miLibro.categoria == 'Cuento'}" 
                                    var="res" scope="request">
                                selected
                                </c:if>
                                >Cuento</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="hidden" name="opg" value="${requestScope.op}" />
                        <input type="hidden" name="op" value="grabar" />
                    </td>
                    <td> <input type="submit" value="Enviar"> </td>
                </tr>
            </table>
        </form>

    </body>
</html>
