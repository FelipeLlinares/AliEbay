/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package aliebay.servlet.comprador;

import aliebay.dto.CompradorDTO;
import aliebay.dto.UsuarioDTO;
import aliebay.dto.VentaDTO;
import aliebay.service.CompradorService;
import aliebay.service.ProductoService;
import aliebay.service.UsuarioService;
import aliebay.service.VendedorService;
import aliebay.service.VentaService;
import aliebay.servlet.AliEbaySessionServlet;
import jakarta.ejb.EJB;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Enrique Cañadas Cobo
 */
@WebServlet(name = "ProductosCompradorServlet", urlPatterns = {"/ProductosCompradorServlet"})
public class ProductosCompradorServlet extends AliEbaySessionServlet {
    @EJB VendedorService vs;
    @EJB CompradorService cs;
    @EJB VentaService vts;
    @EJB UsuarioService us;
    @EJB ProductoService ps;
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
            
            String usuario = request.getParameter("id");
            
            if(usuario != null) {
                
                List<VentaDTO> ventas = vts.getVentaList(Integer.parseInt(usuario));
                
                request.setAttribute("ventas", ventas);
                
                List<String> nombresVendedores = new ArrayList<>();
                
                for(VentaDTO ve : ventas) {
                    UsuarioDTO user = us.buscarUsuario(ve.getProducto().getIdVendedor());
                    String vendedorNombre = user.getUserName();

                    nombresVendedores.add(vendedorNombre);
                }

                request.setAttribute("vendedores", nombresVendedores);
                
                HttpSession session = request.getSession();
                String tipo = (String) session.getAttribute("tipoUsuario");
                if(tipo.equals("Admin")) {
                    request.setAttribute("admin", true);
                }
                
                request.getRequestDispatcher("/WEB-INF/jsp/productosComprador.jsp").forward(request, response);
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
