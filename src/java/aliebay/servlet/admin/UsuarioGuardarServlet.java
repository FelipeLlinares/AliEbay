/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package aliebay.servlet.admin;

import aliebay.dao.CompradorFacade;
import aliebay.dao.MarketingFacade;
import aliebay.dao.UsuarioFacade;
import aliebay.dao.VendedorFacade;
import aliebay.entity.Comprador;
import aliebay.entity.Marketing;
import aliebay.entity.Usuario;
import aliebay.entity.Vendedor;
import aliebay.servlet.AliEbaySessionServlet;
import jakarta.ejb.EJB;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Jose Maria Tapia Catena
 */
@WebServlet(name = "UsuarioGuardarServlet", urlPatterns = {"/UsuarioGuardarServlet"})
public class UsuarioGuardarServlet extends AliEbaySessionServlet {

    @EJB UsuarioFacade userFacade;
    @EJB CompradorFacade comFacade;
    @EJB VendedorFacade venFacade;
    @EJB MarketingFacade marFacade;
    
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
        
        String servlet = request.getParameter("admin");
        servlet = servlet != null?"/AdminServlet":"/LoginServlet";
        
        if (strId == null || strId.isEmpty()){
            userFacade.create(usuario);
            if(str.equals("comprador")){
                Comprador comprador = new Comprador(usuario.getIdUsuario());
                comprador.setUsuario(usuario);
                comFacade.create(comprador);
            }else if(str.equals("vendedor")){
                Vendedor vendedor = new Vendedor(usuario.getIdUsuario());
                vendedor.setUsuario(usuario);
                venFacade.create(vendedor);
            }else if(str.equals("marketing")){
                Marketing marketing = new Marketing(usuario.getIdUsuario());
                marketing.setUsuario(usuario);
                marFacade.create(marketing);
            }
        } else {
            userFacade.edit(usuario);
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
