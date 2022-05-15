/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package aliebay.servlet.marketing;

import aliebay.dto.CompradorDTO;
import aliebay.dto.ListacompradorDTO;
import aliebay.dto.UsuarioDTO;
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
            lComprador = lcS.crear(nombre);
        } else {
            lcS.editar(lComprador.getIdLista(), nombre);
        }
        
        List <CompradorDTO> c = new ArrayList();  
        for (UsuarioDTO comprador : compradorS.listarComprador()){
            int compradorID = comprador.getIdUsuario(); 
            List <ListacompradorDTO> a = lcS.getListListaComprador(compradorID);
            str = request.getParameter(Integer.toString(compradorID));
            if (str != null){ //Aqui entro si he seleccionado el comprador
                //actualizar la referencia de comprador
                c.add(comprador.getComprador());
                if (!a.contains(lComprador)){
                    compradorS.añadirLista(lComprador, compradorID);
                    //this.lcS.añadirComprador(lComprador, compradorID);
                }
            } else {
                if (a.contains(lComprador)){
                    compradorS.eliminarLista(lComprador, compradorID);
                }
            }
        }
        
        if (!c.isEmpty()) this.lcS.editar(lComprador.getIdLista(), nombre, c);
               
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
