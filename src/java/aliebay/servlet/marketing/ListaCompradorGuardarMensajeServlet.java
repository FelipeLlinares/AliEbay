/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package aliebay.servlet.marketing;

import aliebay.dao.CompradorFacade;
import aliebay.dao.ListacompradorFacade;
import aliebay.dao.MarketingFacade;
import aliebay.dao.MensajeFacade;
import aliebay.entity.Comprador;
import aliebay.entity.Listacomprador;
import aliebay.entity.Marketing;
import aliebay.entity.Mensaje;
import aliebay.entity.Usuario;
import aliebay.servlet.AliEbaySessionServlet;
import jakarta.ejb.EJB;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cate
 */
@WebServlet(name = "ListaCompradorGuardarMensajeServlet", urlPatterns = {"/ListaCompradorGuardarMensajeServlet"})
public class ListaCompradorGuardarMensajeServlet extends AliEbaySessionServlet {
    @EJB ListacompradorFacade lcf;
    @EJB MensajeFacade mensajef;
    @EJB MarketingFacade marketingf;
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
       
     if (super.comprobarSesion(request,response) && super.comprobarMarketing(request,response)){
        
        String strIdLista,strID,str;
        Mensaje mensaje;

        
        strID = request.getParameter("id");
        
        if (strID == null || strID.isEmpty()){
            mensaje = new Mensaje();
        } else{
            mensaje = this.mensajef.find(Integer.parseInt(strID));
        }
       
        str = request.getParameter("description");
        mensaje.setDescripcion(str);
        
        strIdLista = request.getParameter("idLista");
        mensaje.setListacomprador(lcf.find(Integer.parseInt(strIdLista)));
        
        HttpSession session = request.getSession();
        Usuario user = (Usuario) session.getAttribute("usuario");
        Marketing marketing = marketingf.find(user.getIdUsuario());
        mensaje.setMarketing(marketing);
        
        
        if (strID == null || strID.isEmpty()){
            mensajef.create(mensaje);
        } else {
            mensajef.edit(mensaje);
        }
        
        response.sendRedirect(request.getContextPath() + "/ListaCompradorMensajeServlet");
        }
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
