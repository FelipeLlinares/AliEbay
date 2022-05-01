/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package aliebay.servlet.marketing;

import aliebay.dao.ListacompradorFacade;
import aliebay.dao.MensajeFacade;
import aliebay.entity.Listacomprador;
import aliebay.entity.Marketing;
import aliebay.entity.Mensaje;
import aliebay.entity.MensajePK;
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

/**
 *
 * @author Cate
 */
@WebServlet(name = "ListaCompradorBorrarMensajeServlet", urlPatterns = {"/ListaCompradorBorrarMensajeServlet"})
public class ListaCompradorBorrarMensajeServlet extends AliEbaySessionServlet {
    @EJB MensajeFacade mensajef;
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
            String str = request.getParameter("id");
            String strIdLista = request.getParameter("idLista");
            HttpSession session = request.getSession();
            Usuario user = (Usuario) session.getAttribute("usuario");
            
            int idMensaje = Integer.parseInt(str);
            int idLista = Integer.parseInt(strIdLista);     
            
            MensajePK mensajePK = new MensajePK(idMensaje, idLista, user.getIdUsuario());
           
            Mensaje mensaje = this.mensajef.find(mensajePK);
            
            this.mensajef.remove(mensaje);
        
            response.sendRedirect(request.getContextPath() + "/MarketingServlet");
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
