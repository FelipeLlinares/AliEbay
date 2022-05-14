/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package aliebay.servlet.comprador;

import aliebay.dao.CompradorFacade;
import aliebay.dao.ProductoFacade;
import aliebay.entity.Comprador;
import aliebay.entity.Producto;
import aliebay.entity.Usuario;
import aliebay.servlet.AliEbaySessionServlet;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
 * @author encac
 */
@WebServlet(name = "CompradorBorrarFavoritoServlet", urlPatterns = {"/CompradorBorrarFavoritoServlet"})
public class CompradorBorrarFavoritoServlet extends AliEbaySessionServlet {
    @EJB ProductoFacade pf;
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
        if (super.comprobarSesion(request, response)) {

            HttpSession session = request.getSession();
            Usuario user = (Usuario) session.getAttribute("usuario");

            Comprador comprador = user.getComprador();

            String producto = request.getParameter("producto");

            if (producto != null) {
                Producto prod = pf.find(Integer.valueOf(producto));

                List<Producto> productos = comprador.getProductoList();
                productos.remove(prod);
                comprador.setProductoList(productos);
                cf.edit(comprador);

                List<Comprador> compradores = prod.getCompradorList();
                compradores.remove(comprador);
                prod.setCompradorList(compradores);
                pf.edit(prod);
                
                response.sendRedirect(request.getContextPath() + "/" + "FavoritoServlet");
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
