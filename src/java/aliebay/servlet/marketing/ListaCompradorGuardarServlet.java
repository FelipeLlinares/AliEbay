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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jose Maria Tapia Catena
 */
@WebServlet(name = "ListaCompradorGuardarServlet", urlPatterns = {"/ListaCompradorGuardarServlet"})
public class ListaCompradorGuardarServlet extends AliEbaySessionServlet {
    @EJB ListacompradorService lcS;
    @EJB CompradorService compradorS;
       
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (super.comprobarSesion(request,response) && super.comprobarMarketing(request,response)){
        
        String strId,str;
        ListacompradorDTO lComprador;

        strId = request.getParameter("id");
        
        if (strId == null || strId.isEmpty()){
            lComprador = new ListacompradorDTO();
        } else{
            lComprador = this.lcS.buscarListacomprador(Integer.parseInt(strId));
        }

        String nombre = request.getParameter("nombre");
        
        if (strId == null || strId.isEmpty()){
            lcS.crear(nombre);
        } else {
            lcS.editar(lComprador.getIdLista(), nombre);
        }

        List <CompradorDTO> c = new ArrayList();
        List <ListacompradorDTO> a = new ArrayList();
        for (CompradorDTO comprador : compradorS.listarComprador()){
            int compradorID = comprador.getIdUsuario(); 
            str = request.getParameter(Integer.toString(compradorID));
            if (str != null){ //en el caso que no esten seleccionados el checkbox no devuelve nada por lo que str es null
                c.add(comprador);
                
                //actualizar la referencia de comprador
                if (!a.contains(lComprador)){
                    compradorS.añadirLista(lComprador, comprador.getIdUsuario());
                }
            } else {
                if (a.contains(lComprador)){
                    compradorS.eliminarLista(lComprador, comprador.getIdUsuario());
                }
            }
        }
               
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
