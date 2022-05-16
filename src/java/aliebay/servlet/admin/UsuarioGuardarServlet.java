/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package aliebay.servlet.admin;

import aliebay.dto.UsuarioDTO;
import aliebay.service.CompradorService;
import aliebay.service.MarketingService;
import aliebay.service.UsuarioService;
import aliebay.service.VendedorService;
import aliebay.servlet.AliEbaySessionServlet;
import jakarta.ejb.EJB;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author felip
 */
@WebServlet(name = "UsuarioGuardarServlet", urlPatterns = {"/UsuarioGuardarServlet"})
public class UsuarioGuardarServlet extends AliEbaySessionServlet {

    @EJB UsuarioService userService;
    @EJB CompradorService comService;
    @EJB VendedorService venService;
    @EJB MarketingService marService;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String strId;
        UsuarioDTO usuario;
        
        strId = request.getParameter("id");
        
        if (strId == null ||strId.isEmpty()){
            usuario = new UsuarioDTO();
        } else{
            usuario = this.userService.buscarUsuario(Integer.parseInt(strId));
        }
        
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String domicilio = request.getParameter("domicilio");
        String ciudad = request.getParameter("ciudad");
        int edad = Integer.parseInt(request.getParameter("edad"));
        String sexo = request.getParameter("sexo");
        String user = request.getParameter("usuario");
        String password = request.getParameter("password");
        String tipoUsuario = request.getParameter("tipoUsuario");
        
        String servlet = request.getParameter("admin");
        servlet = servlet != null?"/AdminServlet":"/LoginServlet";
        
        if (strId == null || strId.isEmpty()){
            UsuarioDTO u = userService.crearUsuario(tipoUsuario,nombre,apellidos,domicilio,ciudad,edad,sexo,user,password);
        } else {
            userService.modificarUsuario(usuario.getIdUsuario(),nombre,apellidos,domicilio,ciudad,edad,sexo,user,password);
            servlet = "/AdminServlet";
        }
        
        
        //request.getRequestDispatcher(request.getContextPath() + servlet).forward(request,response);
        response.sendRedirect(request.getContextPath() + servlet);   
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
