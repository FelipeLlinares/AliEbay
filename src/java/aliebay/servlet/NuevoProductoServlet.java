/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package aliebay.servlet;

import aliebay.dto.CategoriaDTO;
import aliebay.dto.ProductoDTO;
import aliebay.dto.UsuarioDTO;
import aliebay.service.CategoriaService;
import aliebay.service.ProductoService;
import jakarta.ejb.EJB;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alvar
 */
@WebServlet(name = "NuevoProductoServlet", urlPatterns = {"/NuevoProductoServlet"})
public class NuevoProductoServlet extends AliEbaySessionServlet {

    @EJB
    CategoriaService cs;
    @EJB
    ProductoService ps;

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
            throws ServletException, IOException, ParseException {
        if (super.comprobarSesion(request, response)) {
            
            SimpleDateFormat sdt = new SimpleDateFormat("EE MM d HH:mm:ss zz YYYY");
            ProductoDTO producto = new ProductoDTO();
            String str;

            HttpSession session = request.getSession();
            UsuarioDTO user = (UsuarioDTO) session.getAttribute("usuario");

            List<CategoriaDTO> categorias = cs.listarCategorias();
            request.setAttribute("categorias", categorias);

            String titulo = request.getParameter("titulo");
            String descripcion = request.getParameter("descripcion");

            str = request.getParameter("precioSalida");
            Float precioinicio = Float.parseFloat(str);

            String urlFoto = request.getParameter("urlFoto");

            str = request.getParameter("fechaSalida");
            Date fechaSalida = sdt.parse(str);

            str = request.getParameter("fechaFin");
            Date fechafinal = sdt.parse(str);

            String categoria = request.getParameter("categorias");
            String vendedor = request.getParameter("vendedor");

            ps.crearProducto(titulo, descripcion, precioinicio, urlFoto, fechaSalida, fechafinal, categoria, vendedor);
            response.sendRedirect(request.getContextPath() + "/VendedorServlet");
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(NuevoProductoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(NuevoProductoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
