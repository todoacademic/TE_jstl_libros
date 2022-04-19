
package com.emergentes.controlador;

import com.emergentes.modelo.Categoria;
import com.emergentes.modelo.GestorCategorias;
import com.emergentes.modelo.GestorLibros;
import com.emergentes.modelo.Libro;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Controller", urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        Libro objLibro = new Libro();
        int id;
        int pos;
        String opcion = request.getParameter("op");
        String op = (opcion != null) ? request.getParameter("op"):"view";
        
        if(op.equals("nuevo")){
            HttpSession ses = request.getSession();
            GestorLibros agenda = (GestorLibros) ses.getAttribute("agenda");
            objLibro.setId(agenda.obtieneId());
            request.setAttribute("op", op);
            request.setAttribute("miLibro", objLibro);
            request.getRequestDispatcher("editarLibro.jsp").forward(request, response);
        }
        
        if(op.equals("modificar")){
            id = Integer.parseInt(request.getParameter("id"));
            HttpSession ses = request.getSession();
            GestorLibros agenda = (GestorLibros) ses.getAttribute("agenda");
            pos = agenda.ubicarLibro(id);
            objLibro = agenda.getLista().get(pos);
            
            request.setAttribute("op", op);
            request.setAttribute("miLibro", objLibro);
            request.getRequestDispatcher("editarLibro.jsp").forward(request, response);
        }
        
        if(op.equals("eliminar")){
            id = Integer.parseInt(request.getParameter("id"));
            HttpSession ses = request.getSession();
            GestorLibros agenda = (GestorLibros) ses.getAttribute("agenda");
            pos = agenda.ubicarLibro(id);
            agenda.eliminarLibro(pos);
            ses.setAttribute("agenda", agenda);
            response.sendRedirect("libros.jsp");
        }
        
        if(op.equals("view")){
            response.sendRedirect("index.jsp");
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        Libro objLibro = new Libro();
        int pos;
        String op = request.getParameter("op");
        
        if(op.equals("grabar")){
            // Recuperar valores del formulario
            // Verificar si es nuevo o es una modificación
            objLibro.setId(Integer.parseInt(request.getParameter("id")));
            objLibro.setTitulo(request.getParameter("titulo"));
            objLibro.setAutor(request.getParameter("autor"));
            objLibro.setDisponible(request.getParameter("disponible"));
            objLibro.setCategoria(request.getParameter("categoria"));
            
            HttpSession ses = request.getSession();
            GestorLibros agenda = (GestorLibros) ses.getAttribute("agenda");
            
            String opg = request.getParameter("opg");
            if(opg.equals("nuevo")){
                agenda.insertarLibro(objLibro);
            }else{
                pos = agenda.ubicarLibro(objLibro.getId());
                agenda.modificarLibro(pos, objLibro);
            }
            ses.setAttribute("agenda", agenda);
            response.sendRedirect("index.jsp");
        }
    }

}
