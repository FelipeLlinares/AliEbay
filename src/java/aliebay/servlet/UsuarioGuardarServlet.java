/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package aliebay.servlet;

import aliebay.dao.CompradorFacade;
import aliebay.dao.UsuarioFacade;
import aliebay.dao.VendedorFacade;
import aliebay.entity.Comprador;
import aliebay.entity.Usuario;
import aliebay.entity.Vendedor;
import jakarta.ejb.EJB;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Cate
 */
@WebServlet(name = "UsuarioGuardarServlet", urlPatterns = {"/UsuarioGuardarServlet"})
public class UsuarioGuardarServlet extends AliEbaySessionServlet {

    @EJB UsuarioFacade userFacade;
    @EJB CompradorFacade comFacade;
    @EJB VendedorFacade venFacade;
    
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

        String strId,str;
        Usuario usuario;
        
        strId = request.getParameter("id");
        
        if (strId == null ||strId.isEmpty()){
            usuario = new Usuario();
        } else{
            usuario = this.userFacade.find(Integer.parseInt(strId));
        }
        
        str = request.getParameter("nombre");
        usuario.setNombre(str);
        
        str = request.getParameter("apellidos");
        usuario.setApellidos(str);
        
        str = request.getParameter("domicilio");
        usuario.setDomicilio(str);
        
        str = request.getParameter("ciudad");
        usuario.setCiudadResidencia(str);
        
        str = request.getParameter("edad");
        usuario.setEdad(Integer.parseInt(str));
        
        str = request.getParameter("sexo");
        usuario.setSexo(str);
        
        str = request.getParameter("usuario");
        usuario.setUserName(str);
        
        str = request.getParameter("password");
        usuario.setPassword(str);
        
        str = request.getParameter("tipoUsuario");
        
        if (strId == null ||strId.isEmpty()){
            userFacade.create(usuario);
            if(str.equals("Comprador")){
                Comprador comprador = new Comprador(usuario.getIdUsuario());
                comFacade.create(comprador);
            }else if(str.equals("Vendedor")){
                Vendedor vendedor = new Vendedor(usuario.getIdUsuario());
                venFacade.create(vendedor);
            }
        } else {
            userFacade.edit(usuario);
        }
        
        response.sendRedirect(request.getContextPath() + "/NuevoServlet");
        
        
        
        
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
