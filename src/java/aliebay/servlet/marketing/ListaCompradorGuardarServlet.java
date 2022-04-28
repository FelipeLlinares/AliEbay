/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package aliebay.servlet.marketing;

import aliebay.dao.CompradorFacade;
import aliebay.dao.ListacompradorFacade;
import aliebay.entity.Comprador;
import aliebay.entity.Listacomprador;
import aliebay.entity.Vendedor;
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
 * @author Cate
 */
@WebServlet(name = "ListaCompradorGuardarServlet", urlPatterns = {"/ListaCompradorGuardarServlet"})
public class ListaCompradorGuardarServlet extends AliEbaySessionServlet {
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
        
        String strId,str;
        Listacomprador lComprador;
        
        strId = request.getParameter("id");
        
        if (strId == null || strId.isEmpty()){
            lComprador = new Listacomprador();
        } else{
            lComprador = this.lcf.find(Integer.parseInt(strId));
        }
        
        str = request.getParameter("nombre");
        lComprador.setNombre(str);
        
        List <Comprador> c = new ArrayList();
        for (Comprador comprador : compradorf.findAll()){
            int compradorID = comprador.getIdUsuario(); 
            str = request.getParameter(Integer.toString(compradorID));
            if (str != null){ //en el caso que no esten seleccionados el checkbox no devuelve nada por lo que str es null
                Comprador compradorEncontrado = compradorf.find(Integer.parseInt(str));
                c.add(compradorEncontrado);
                
                //actualizar la referencia de comprador
                List<Listacomprador> a = compradorEncontrado.getListacompradorList();
                a.add(lComprador);
                compradorEncontrado.setListacompradorList(a);
            }
        }
        lComprador.setCompradorList(c);
        
        if (strId == null || strId.isEmpty()){
            lcf.create(lComprador);
        } else {
            lcf.edit(lComprador);
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
