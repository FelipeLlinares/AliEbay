/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package aliebay.servlet;

import aliebay.dto.CompradorDTO;
import aliebay.dto.ProductoDTO;
import aliebay.dto.PujaDTO;
import aliebay.dto.UsuarioDTO;
import aliebay.dto.VentaDTO;
import aliebay.service.CompradorService;
import aliebay.service.ProductoService;
import aliebay.service.UsuarioService;
import aliebay.service.VentaService;
import jakarta.ejb.EJB;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author Jose Maria Tapia Catena
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends AliEbaySessionServlet {
    @EJB ProductoService ps;
    @EJB VentaService vs;
    @EJB CompradorService cs;
    @EJB UsuarioService userS;
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
      
        String usuario = request.getParameter("usuario");
        String clave = request.getParameter("clave");
        
        List<ProductoDTO> productosVendidos = ps.getProductosVendidos();
        
        for(ProductoDTO pr : productosVendidos) {
            List<PujaDTO> pujas = ps.getPujaList(pr);
            
            if(pujas != null && !pujas.isEmpty()) {
                PujaDTO puja = pujas.get(pujas.size() - 1);
                
                this.vs.crearVenta(puja.getComprador().getIdUsuario(),
                                    puja.getProducto().getIdProducto(),
                                    puja.getComprador(),
                                    pr.getFechaFin(),
                                    puja.getPuja()
                                    );
            }
        }
        
        
        UsuarioDTO user = this.userS.comprobarUsuario(usuario, clave);
        //admin es o una lista vacia o un Object[]
        if (user == null){
            String strError = "El usuario o la clave son incorrectos";
            request.setAttribute("error", strError);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else{
            HttpSession session = request.getSession();
            session.setAttribute("usuario", user);
            
            String tipoUser = userS.getTipoUsuario(user);
            session.setAttribute("tipoUsuario",tipoUser);
            response.sendRedirect(request.getContextPath() + "/" + tipoUser + "Servlet");
                    
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
