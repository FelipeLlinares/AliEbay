/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package aliebay.servlet.marketing;

import aliebay.dto.CompradorDTO;
import aliebay.dto.ListacompradorDTO;
import aliebay.dto.MensajeDTO;
import aliebay.service.ListacompradorService;
import aliebay.service.MensajeService;
import aliebay.service.CompradorService;
import jakarta.ejb.EJB;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jose Maria Tapia Catena
 */
@WebServlet(name = "CompradorMensajesServlet", urlPatterns = {"/CompradorMensajesServlet"})
public class CompradorMensajesServlet extends HttpServlet {

    @EJB CompradorService compradors;
    @EJB ListacompradorService lcs;
    @EJB MensajeService ms;
    

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
            
        String id = request.getParameter("id");
        

        CompradorDTO comprador = this.compradors.buscarComprador(Integer.parseInt(id));
        List<ListacompradorDTO> listas  = lcs.getListListaComprador(comprador.getIdUsuario());
        List<List<MensajeDTO>> mensajes = new ArrayList<>();
        
        for(ListacompradorDTO lc: listas){
            mensajes.add(ms.getMensajesLista(lc.getIdLista()));
        }

        request.setAttribute("comprador", comprador);
        request.setAttribute("listas",listas);
        request.setAttribute("mensajes",mensajes);
        
        request.getRequestDispatcher("/WEB-INF/jsp/mensajesComprador.jsp").forward(request,response);
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
