/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package aliebay.servlet.marketing;

import aliebay.dto.CompradorDTO;
import aliebay.dto.ListacompradorDTO;
import aliebay.service.CompradorService;
import aliebay.service.ListacompradorService;
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
@WebServlet(name = "ListaCompradorNuevoEditarServlet", urlPatterns = {"/ListaCompradorNuevoEditarServlet"})
public class ListaCompradorNuevoEditarServlet extends AliEbaySessionServlet {
     @EJB ListacompradorService lcS;
     @EJB CompradorService compradorS;
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
            if (str != null){
                ListacompradorDTO listacomprador = lcS.buscarListacomprador(Integer.parseInt(str));
                request.setAttribute("listaComprador", listacomprador);
                
                List<CompradorDTO> compradoresListaComprador = compradorS.getCompradoresListaComprador(Integer.parseInt(str));
                request.setAttribute("compradoresListaComprador", compradoresListaComprador);
                
            }
            
            List<CompradorDTO> compradores = this.compradorS.listarComprador();
            request.setAttribute("compradores", compradores);
        
            request.getRequestDispatcher("/WEB-INF/jsp/editarCrearListaComprador.jsp").forward(request,response);
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
