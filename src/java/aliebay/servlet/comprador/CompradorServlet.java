/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 *
 * Creado por Enrique Cañadas Cobo
 */
package aliebay.servlet.comprador;

import aliebay.dao.CategoriaFacade;
import aliebay.dao.ProductoFacade;
import aliebay.dao.UsuarioFacade;
import aliebay.dao.VendedorFacade;
import aliebay.dto.CategoriaDTO;
import aliebay.entity.Categoria;
import aliebay.entity.Comprador;
import aliebay.entity.Producto;
import aliebay.entity.Puja;
import aliebay.entity.Usuario;
import aliebay.entity.Vendedor;
import aliebay.service.CategoriaService;
import aliebay.servlet.AliEbaySessionServlet;
import jakarta.ejb.EJB;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Enrique Cañadas Cobo
 */
@WebServlet(name = "CompradorServlet", urlPatterns = {"/CompradorServlet"})
public class CompradorServlet extends AliEbaySessionServlet {

    @EJB
    UsuarioFacade uf;
    @EJB
    VendedorFacade vf;
    @EJB
    ProductoFacade pf;
    @EJB
    CategoriaService cs;

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

            String nombreFiltro = request.getParameter("filtro");
            String categoria = request.getParameter("categorias");
            
            List<Producto> productos;
            
            if(nombreFiltro != null && categoria != null && !nombreFiltro.isEmpty() && !categoria.isEmpty()) {
                CategoriaDTO categ = cs.buscarCategoria(categoria);
                productos = pf.getProductosPorNombreYCategoria(nombreFiltro, categ);
                request.setAttribute("filtrado", "por '" + nombreFiltro + "' y por '" + categ.getIdCategoria() + "'");
            } else if(nombreFiltro != null && !nombreFiltro.isEmpty()) {
                productos = pf.getProductosDisponiblesPorNombre(nombreFiltro);
                request.setAttribute("filtrado", "por '" + nombreFiltro + "'");
            } else if(categoria != null && !categoria.isEmpty()){
                CategoriaDTO categ = cs.buscarCategoria(categoria);
                productos = pf.getProductosPorCategoria(categ);
                request.setAttribute("filtrado", "por '" + categ.getIdCategoria() + "'");
            } else {
                productos = pf.getProductosDisponibles();
            }

            List<Producto> productosPujadosPorComprador = new ArrayList<>();
            List<String> nombresVendedoresPujados = new ArrayList<>();
            List<Producto> productosNoPujadosPorComprador = new ArrayList<>();
            List<String> nombresVendedoresNoPujados = new ArrayList<>();

            for (Producto pr : productos) {
                List<Puja> pujas = pr.getPujaList();
                if (pujas != null && !pujas.isEmpty()) {
                    Puja puja = pr.getPujaList().get(pr.getPujaList().size() - 1);

                    if (Objects.equals(puja.getComprador().getIdUsuario(), comprador.getIdUsuario())) {
                        productosPujadosPorComprador.add(pr);

                        Vendedor vendedor = vf.find(pr.getIdVendedor());
                        String vendedorNombre = vendedor.getUsuario().getUserName();

                        nombresVendedoresPujados.add(vendedorNombre);
                    } else {
                        productosNoPujadosPorComprador.add(pr);
                        Vendedor vendedor = vf.find(pr.getIdVendedor());
                        String vendedorNombre = vendedor.getUsuario().getUserName();

                        nombresVendedoresNoPujados.add(vendedorNombre);
                    }

                } else {
                    productosNoPujadosPorComprador.add(pr);
                    Vendedor vendedor = vf.find(pr.getIdVendedor());
                    String vendedorNombre = vendedor.getUsuario().getUserName();

                    nombresVendedoresNoPujados.add(vendedorNombre);
                }
            }

            List<CategoriaDTO> categorias = cs.listarCategorias();

            request.setAttribute("categorias", categorias);
            request.setAttribute("productosPujadosPorComprador", productosPujadosPorComprador);
            request.setAttribute("nombresVendedoresPujados", nombresVendedoresPujados);
            request.setAttribute("productosNoPujadosPorComprador", productosNoPujadosPorComprador);
            request.setAttribute("nombresVendedoresNoPujados", nombresVendedoresNoPujados);

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
