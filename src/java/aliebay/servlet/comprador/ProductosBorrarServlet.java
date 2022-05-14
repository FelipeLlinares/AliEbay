/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package aliebay.servlet.comprador;

import aliebay.dao.CompradorFacade;
import aliebay.dao.ProductoFacade;
import aliebay.dao.UsuarioFacade;
import aliebay.dao.VentaFacade;
import aliebay.entity.Comprador;
import aliebay.entity.Producto;
import aliebay.entity.Usuario;
import aliebay.entity.Venta;
import aliebay.servlet.AliEbaySessionServlet;
import jakarta.ejb.EJB;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Enrique Cañadas Cobo
 */
@WebServlet(name = "ProductosBorrarServlet", urlPatterns = {"/ProductosBorrarServlet"})
public class ProductosBorrarServlet extends AliEbaySessionServlet {
    @EJB ProductoFacade pf;
    @EJB VentaFacade vf;
    @EJB CompradorFacade cf;
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
        HttpSession session = request.getSession();
        Usuario user = (Usuario) session.getAttribute("usuario");

        Comprador comprador = user.getComprador();
        
        String producto = request.getParameter("producto");
        Producto prod = pf.find(Integer.parseInt(producto));
        Venta venta = prod.getVenta();
        
        List<Venta> ventas = comprador.getVentaList();
        ventas.remove(ventas);
        comprador.setVentaList(ventas);
        cf.edit(comprador);
        
        vf.remove(venta);
        
        response.sendRedirect(request.getContextPath() + "/ProductosCompradorServlet");
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
