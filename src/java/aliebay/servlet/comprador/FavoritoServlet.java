/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package aliebay.servlet.comprador;

import aliebay.dto.CompradorDTO;
import aliebay.dto.ProductoDTO;
import aliebay.dto.PujaDTO;
import aliebay.dto.UsuarioDTO;
import aliebay.service.CompradorService;
import aliebay.service.ProductoService;
import aliebay.service.UsuarioService;
import aliebay.service.VendedorService;
import aliebay.servlet.AliEbaySessionServlet;
import jakarta.ejb.EJB;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Enrique Cañadas Cobo
 */
@WebServlet(name = "FavoritoServlet", urlPatterns = {"/FavoritoServlet"})
public class FavoritoServlet extends AliEbaySessionServlet {
    @EJB VendedorService vs;
    @EJB ProductoService ps;
    @EJB CompradorService cs;
    @EJB
    UsuarioService us;

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

            if (comprador != null) {

                List<ProductoDTO> productos = ps.getProductosFavoritos(comprador.getIdUsuario());
                
                List<ProductoDTO> productosPujadosPorComprador = new ArrayList<>();
                List<String> nombresVendedoresPujados = new ArrayList<>();
                List<ProductoDTO> productosNoPujadosPorComprador = new ArrayList<>();
                List<String> nombresVendedoresNoPujados = new ArrayList<>();
                List<Float> mayoresPujasVendidos = new ArrayList<>();
                List<Float> mayoresPujasNoVendidos = new ArrayList<>();

                for (ProductoDTO prod : productos) {
                    Date date = new Date();
                    if(date.before(prod.getFechaFin())){
                        ProductoDTO pr = ps.buscarProducto(prod.getIdProducto());
                        List<PujaDTO> pujas = ps.getPujaList(pr);
                    
                        UsuarioDTO usuario = us.buscarUsuario(pr.getIdVendedor());
                        String vendedorNombre = usuario.getUserName();
                    
                        if (pujas != null && !pujas.isEmpty()) {
                            PujaDTO puja = pujas.get(pujas.size() - 1);

                            if (Objects.equals(puja.getComprador().getIdUsuario(), comprador.getIdUsuario())) {
                              productosPujadosPorComprador.add(pr);
                                nombresVendedoresPujados.add(vendedorNombre);
                                mayoresPujasVendidos.add(puja.getPuja());
                            } else {
                                productosNoPujadosPorComprador.add(pr);
                                nombresVendedoresNoPujados.add(vendedorNombre);
                                mayoresPujasNoVendidos.add(puja.getPuja());
                            }

                        } else {
                            productosNoPujadosPorComprador.add(pr);
                             nombresVendedoresNoPujados.add(vendedorNombre);
                            mayoresPujasNoVendidos.add(0f);
                        }
                    }     
                }

                request.setAttribute("productos", productos);
                request.setAttribute("productosPujadosPorComprador", productosPujadosPorComprador);
                request.setAttribute("nombresVendedoresPujados", nombresVendedoresPujados);
                request.setAttribute("productosNoPujadosPorComprador", productosNoPujadosPorComprador);
                request.setAttribute("nombresVendedoresNoPujados", nombresVendedoresNoPujados);
                request.setAttribute("mayoresPujasVendidos", mayoresPujasVendidos);
                request.setAttribute("mayoresPujasNoVendidos", mayoresPujasNoVendidos);

                request.getRequestDispatcher("/WEB-INF/jsp/favoritosComprador.jsp").forward(request, response);
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
