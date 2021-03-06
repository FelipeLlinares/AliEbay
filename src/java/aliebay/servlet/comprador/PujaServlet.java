/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this templateç
 *
 * Creado por Enrique Cañadas Cobo
 */
package aliebay.servlet.comprador;

import aliebay.dto.ProductoDTO;
import aliebay.dto.PujaDTO;
import aliebay.dto.UsuarioDTO;
import aliebay.dto.VendedorDTO;
import aliebay.service.ProductoService;
import aliebay.service.UsuarioService;
import aliebay.service.VendedorService;
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
 * @author Enrique Cañadas Cobo
 */
@WebServlet(name = "PujaServlet", urlPatterns = {"/PujaServlet"})
public class PujaServlet extends AliEbaySessionServlet {
    @EJB ProductoService ps;
    @EJB VendedorService vs;
    @EJB UsuarioService us;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (super.comprobarSesion(request,response)){
            
            String producto = (String) request.getParameter("producto");
            
            if(producto != null) {
                ProductoDTO prod = ps.buscarProducto(Integer.valueOf(producto));
                List<PujaDTO> pujas = ps.getPujaList(prod);
                request.setAttribute("producto", prod);
                request.setAttribute("pujas",pujas);
                UsuarioDTO user = us.buscarUsuario(prod.getIdVendedor());
                String vendedorNombre = user.getUserName();
                request.setAttribute("vendedor", vendedorNombre);
                
                String error = (String) request.getAttribute("error");
                request.setAttribute("error", error);
                request.getRequestDispatcher("/WEB-INF/jsp/pujar.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/WEB-INF/jsp/comprador.jsp").forward(request, response);
            }
            
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
