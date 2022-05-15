/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 *
 * Creado por Enrique Cañadas Cobo
 */
package aliebay.servlet.comprador;

import aliebay.dto.CategoriaDTO;
import aliebay.dto.CompradorDTO;
import aliebay.dto.ProductoDTO;
import aliebay.dto.PujaDTO;
import aliebay.dto.UsuarioDTO;
import aliebay.dto.VendedorDTO;
import aliebay.service.CategoriaService;
import aliebay.service.ProductoService;
import aliebay.service.VendedorService;
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
    @EJB VendedorService vs;
    @EJB
    ProductoService ps;
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
            UsuarioDTO user = (UsuarioDTO) session.getAttribute("usuario");

            CompradorDTO comprador = user.getComprador();

            String nombreFiltro = request.getParameter("filtro");
            String categoria = request.getParameter("categorias");
            
            List<ProductoDTO> productos;
            
            if(nombreFiltro != null && categoria != null && !nombreFiltro.isEmpty() && !categoria.isEmpty()) {
                CategoriaDTO categ = cs.buscarCategoria(categoria);
                productos = ps.getProductosPorNombreYCategoria(nombreFiltro, categ);
                request.setAttribute("filtrado", "por '" + nombreFiltro + "' y por '" + categ.getIdCategoria() + "'");
            } else if(nombreFiltro != null && !nombreFiltro.isEmpty()) {
                productos = ps.getProductosDisponiblesPorNombre(nombreFiltro);
                request.setAttribute("filtrado", "por '" + nombreFiltro + "'");
            } else if(categoria != null && !categoria.isEmpty()){
                CategoriaDTO categ = cs.buscarCategoria(categoria);
                productos = ps.getProductosPorCategoria(categ);
                request.setAttribute("filtrado", "por '" + categ.getIdCategoria() + "'");
            } else {
                productos = ps.getProductosDisponibles();
            }

            List<ProductoDTO> productosPujadosPorComprador = new ArrayList<>();
            List<String> nombresVendedoresPujados = new ArrayList<>();
            List<ProductoDTO> productosNoPujadosPorComprador = new ArrayList<>();
            List<String> nombresVendedoresNoPujados = new ArrayList<>();
            List<Float> mayoresPujasVendidos = new ArrayList<>();
            List<Float> mayoresPujasNoVendidos = new ArrayList<>();
            
            List<ProductoDTO> productosFavoritos = ps.getProductosFavoritos(comprador.getIdUsuario());

            for (ProductoDTO pr : productos) {
                List<PujaDTO> pujas = ps.getPujaList(pr);
                if (pujas != null && !pujas.isEmpty()) {
                    PujaDTO puja = pujas.get(pujas.size() - 1);

                    if (Objects.equals(puja.getComprador().getIdUsuario(), comprador.getIdUsuario())) {
                        productosPujadosPorComprador.add(pr);

                        VendedorDTO vendedor = vs.buscarVendedor(pr.getIdVendedor());
                        String vendedorNombre = vendedor.getUsuario().getUserName();

                        nombresVendedoresPujados.add(vendedorNombre);
                    } else {
                        productosNoPujadosPorComprador.add(pr);
                        VendedorDTO vendedor = vs.buscarVendedor(pr.getIdVendedor());
                        String vendedorNombre = vendedor.getUsuario().getUserName();

                        nombresVendedoresNoPujados.add(vendedorNombre);
                    }
                    
                    mayoresPujasVendidos.add(puja.getPuja());

                } else {
                    productosNoPujadosPorComprador.add(pr);
                    VendedorDTO vendedor = vs.buscarVendedor(pr.getIdVendedor());
                    String vendedorNombre = vendedor.getUsuario().getUserName();

                    nombresVendedoresNoPujados.add(vendedorNombre);
                    
                    mayoresPujasNoVendidos.add(0f);
                }
            }

            List<CategoriaDTO> categorias = cs.listarCategorias();

            request.setAttribute("categorias", categorias);
            request.setAttribute("productosPujadosPorComprador", productosPujadosPorComprador);
            request.setAttribute("nombresVendedoresPujados", nombresVendedoresPujados);
            request.setAttribute("productosNoPujadosPorComprador", productosNoPujadosPorComprador);
            request.setAttribute("nombresVendedoresNoPujados", nombresVendedoresNoPujados);
            request.setAttribute("mayoresPujasVendidos", mayoresPujasVendidos);
            request.setAttribute("mayoresPujasNoVendidos", mayoresPujasNoVendidos);
            request.setAttribute("favoritos", mayoresPujasNoVendidos);

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
