/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package aliebay.servlet.comprador;

import aliebay.dao.CategoriaFacade;
import aliebay.dao.CompradorFacade;
import aliebay.dao.ProductoFacade;
import aliebay.dao.UsuarioFacade;
import aliebay.dao.VendedorFacade;
import aliebay.entity.Categoria;
import aliebay.entity.Comprador;
import aliebay.entity.Producto;
import aliebay.entity.Usuario;
import aliebay.entity.Vendedor;
import aliebay.servlet.AliEbaySessionServlet;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import java.util.ArrayList;

/**
 *
 * @author encac
 */
@WebServlet(name = "AplicarFiltrosServlet", urlPatterns = {"/AplicarFiltrosServlet"})
public class AplicarFiltrosServlet extends AliEbaySessionServlet {
    @EJB UsuarioFacade uf;
    @EJB VendedorFacade vf;
    @EJB ProductoFacade pf;
    @EJB CategoriaFacade cf;
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

            String nombreFiltro = request.getParameter("filtro");
            String categoria = request.getParameter("categorias");
            
            List<Producto> productos;
            if(nombreFiltro != null && !nombreFiltro.isEmpty()) {
                productos = pf.getProductosDisponiblesPorNombre(nombreFiltro);
            } else if(categoria != null && !categoria.isEmpty()){
                Categoria categ = cf.find(categoria);
                productos = pf.getProductosPorCategoria(categ);
            } else {
                productos = pf.getProductosDisponibles();
            }
            
            List<String> nombresVendedores = new ArrayList<>();
            
            for(Producto pr : productos) {
                Vendedor vendedor = vf.find(pr.getIdVendedor());
                String vendedorNombre = vendedor.getUsuario().getNombre() + " " + vendedor.getUsuario().getApellidos();

                nombresVendedores.add(vendedorNombre);
            }
            
            List<Categoria> categorias = cf.findAll();
            
            request.setAttribute("categorias", categorias);
            request.setAttribute("productos", productos);
            request.setAttribute("vendedores", nombresVendedores);
            
            request.getRequestDispatcher("/WEB-INF/jsp/comprador.jsp").forward(request, response);
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
