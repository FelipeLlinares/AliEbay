/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package aliebay.servlet.marketing;

import aliebay.dao.CompradorFacade;
import aliebay.dao.ListacompradorFacade;
import aliebay.entity.Comprador;
import aliebay.entity.Listacomprador;
import aliebay.servlet.AliEbaySessionServlet;
import jakarta.ejb.EJB;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author Jose Maria Tapia Catena
 */
@WebServlet(name = "ListaCompradorBorrarServlet", urlPatterns = {"/ListaCompradorBorrarServlet"})
public class ListaCompradorBorrarServlet extends AliEbaySessionServlet {

    @EJB ListacompradorFacade lcf;
    @EJB CompradorFacade compradorf;
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
        
            Listacomprador listacompra = this.lcf.find(Integer.parseInt(str));
            
            List<Comprador> compradores = listacompra.getCompradorList();
            for (Comprador c : compradores){
                c.getListacompradorList().remove(listacompra);
                this.compradorf.edit(c);
            }
            
            this.lcf.remove(listacompra);
        
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
