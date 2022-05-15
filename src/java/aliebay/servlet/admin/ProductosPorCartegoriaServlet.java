/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package aliebay.servlet.admin;

import aliebay.dao.ProductoFacade;
import aliebay.dto.ProductoDTO;
import aliebay.entity.Producto;
import aliebay.service.CategoriaService;
import aliebay.service.ProductoService;
import jakarta.ejb.EJB;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 *
 * @author felip
 */
@WebServlet(name = "ProductosPorCartegoriaServlet", urlPatterns = {"/ProductosPorCartegoriaServlet"})
public class ProductosPorCartegoriaServlet extends HttpServlet {
    @EJB ProductoService ps;
    @EJB CategoriaService cs;
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
        String categoria = (String) request.getParameter("id");
        List<ProductoDTO> productos = ps.getProductosPorCategoria(cs.buscarCategoria(categoria));
        
        List<ProductoDTO> productosVendidos = new ArrayList<>();
        List<ProductoDTO> productosNoVendidos  = new ArrayList<>();
        
        for(ProductoDTO p:productos){
            if(p.getVenta() == null){
                productosNoVendidos.add(p);
            }else{
                productosVendidos.add(p);
            }
        }
        request.setAttribute("productosVendidos", productosVendidos);
        request.setAttribute("productosNoVendidos", productosNoVendidos);
        request.setAttribute("categoria",categoria);
        
        request.getRequestDispatcher("/WEB-INF/jsp/productos.jsp").forward(request, response);
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
