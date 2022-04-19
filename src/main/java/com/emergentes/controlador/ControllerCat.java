
package com.emergentes.controlador;

import com.emergentes.modelo.Categoria;
import com.emergentes.modelo.GestorCategorias;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ControllerCat", urlPatterns = {"/ControllerCat"})
public class ControllerCat extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        Categoria objCat = new Categoria();
        int id;
        int pos;
        String opcion = request.getParameter("op");
        String op = (opcion != null) ? request.getParameter("op"):"view";
        
        if(op.equals("nuevo")){
            HttpSession ses = request.getSession();
            GestorCategorias agenda = (GestorCategorias) ses.getAttribute("agenda");
            objCat.setId(agenda.obtieneId());
            request.setAttribute("op", op);
            request.setAttribute("miCategoria", objCat);
            request.getRequestDispatcher("editarCategoria.jsp").forward(request, response);
        }
        
        if(op.equals("modificar")){
            id = Integer.parseInt(request.getParameter("id"));
            HttpSession ses = request.getSession();
            GestorCategorias agenda = (GestorCategorias) ses.getAttribute("agenda");
            pos = agenda.ubicarCategoria(id);
            objCat = agenda.getLista().get(pos);
            
            request.setAttribute("op", op);
            request.setAttribute("miCategoria", objCat);
            request.getRequestDispatcher("editarCategoria.jsp").forward(request, response);
        }
        
        if(op.equals("eliminar")){
            id = Integer.parseInt(request.getParameter("id"));
            HttpSession ses = request.getSession();
            GestorCategorias agenda = (GestorCategorias) ses.getAttribute("agenda");
            pos = agenda.ubicarCategoria(id);
            agenda.eliminarCategoria(pos);
            ses.setAttribute("agenda", agenda);
            response.sendRedirect("categorias.jsp");
        }
        
        if(op.equals("view")){
            response.sendRedirect("index.jsp");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        Categoria objCat = new Categoria();
        int pos;
        String op = request.getParameter("op");
        
        if(op.equals("grabar")){
            // Recuperar valores del formulario
            // Verificar si es nuevo o es una modificación
            objCat.setId(Integer.parseInt(request.getParameter("id")));
            objCat.setCategoria(request.getParameter("categoria"));

            
            HttpSession ses = request.getSession();
            GestorCategorias agenda = (GestorCategorias) ses.getAttribute("agenda");
            
            String opg = request.getParameter("opg");
            if(opg.equals("nuevo")){
                agenda.insertarCategoria(objCat);
            }else{
                pos = agenda.ubicarCategoria(objCat.getId());
                agenda.modificarCategoria(pos, objCat);
            }
            ses.setAttribute("agenda", agenda);
            response.sendRedirect("index.jsp");
        }
    }

}
