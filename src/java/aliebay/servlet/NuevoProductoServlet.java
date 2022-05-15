/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package aliebay.servlet;

import aliebay.dao.CategoriaFacade;
import aliebay.dao.ProductoFacade;
import aliebay.entity.Categoria;
import aliebay.entity.Producto;
import aliebay.entity.Usuario;
import jakarta.ejb.EJB;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    CategoriaFacade cf;
    @EJB ProductoFacade pf;

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

            Producto producto = new Producto();
            String str;

            HttpSession session = request.getSession();
            Usuario user = (Usuario) session.getAttribute("usuario");

            List<Categoria> categorias = cf.findAll();
            request.setAttribute("categorias", categorias);

            str = request.getParameter("titulo");
            producto.setTitulo(str);

            str = request.getParameter("descripcion");
            producto.setDescripcion(str);

            str = request.getParameter("precioSalida");
            Float precioinicio = Float.parseFloat(str);
            producto.setPrecioSalida(precioinicio);

            str = request.getParameter("urlFoto");
            producto.setURLFoto(str);

            str = request.getParameter("fechaSalida");
            SimpleDateFormat sdt = new SimpleDateFormat("dd-MM-YYYY");
            Date fecha = sdt.parse(str);
            producto.setFechaSalida(fecha);

            str = request.getParameter("fechaFin");
            SimpleDateFormat sdt2 = new SimpleDateFormat("dd-MM-YYYY");
            Date fechafinal = sdt2.parse(str);
            producto.setFechaFin(fechafinal);

            str = request.getParameter("categorias");
            Categoria categoria = cf.find(str);
            producto.setCategoria(categoria);
            List<Producto> productos = categoria.getProductoList();
            productos.add(producto);
            categoria.setProductoList(productos);
            
            
            str = request.getParameter("vendedor");
            producto.setIdVendedor(Integer.parseInt(str));
            
            cf.edit(categoria);
            pf.create(producto);

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
