/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package aliebay.servlet;

import aliebay.entity.Administrador;
import aliebay.entity.Usuario;
import jakarta.persistence.Query;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author Cate
 */
@WebServlet(name = "AliEbaySessionServlet", urlPatterns = {"/AliEbaySessionServlet"})
public abstract class AliEbaySessionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    
    protected boolean comprobarSesion (HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException{
        
        HttpSession session = request.getSession();
        
        Usuario user = (Usuario) session.getAttribute("usuario");
        
        if (user == null){
            response.sendRedirect(request.getContextPath());
            return false;
        } else {
            return true;
        }
    }
    
    protected boolean comprobarAdmin (HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException{
        HttpSession session = request.getSession();
        
        String tipoUsuario = (String) session.getAttribute("tipoUsuario");
        
        if (!tipoUsuario.equals("Admin")){
            String strError = "No tienes permisos de administrador";
            request.setAttribute("error", strError);
            request.getRequestDispatcher("/" + tipoUsuario + "Servlet").forward(request,response);
            return false;
        } else {
            return true;
        }
    }

    protected boolean comprobarMarketing(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
       HttpSession session = request.getSession();
        
        String tipoUsuario = (String) session.getAttribute("tipoUsuario");
        
        if (!tipoUsuario.equals("Marketing")){
            String strError = "No tienes permisos de marketing";
            request.setAttribute("error", strError);
            request.getRequestDispatcher("/" + tipoUsuario + "Servlet").forward(request,response);
            return false;
        } else {
            return true;
        }
    }
    
    protected boolean comprobarVendedor(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
       HttpSession session = request.getSession();
        
        String tipoUsuario = (String) session.getAttribute("tipoUsuario");
        
        if (!tipoUsuario.equals("Vendedor")){
            String strError = "No tienes permisos de vendedor";
            request.setAttribute("error", strError);
            request.getRequestDispatcher("/" + tipoUsuario + "Servlet").forward(request,response);
            return false;
        } else {
            return true;
        }
    }
}
