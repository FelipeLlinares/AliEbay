/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package aliebay.servlet;

import aliebay.dao.CompradorFacade;
import aliebay.dao.ProductoFacade;
import aliebay.dao.UsuarioFacade;
import aliebay.dao.VentaFacade;
import aliebay.entity.Comprador;
import aliebay.entity.Producto;
import aliebay.entity.Puja;
import aliebay.entity.Usuario;
import aliebay.entity.Venta;
import aliebay.entity.VentaPK;
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
 * @author Cate
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends AliEbaySessionServlet {
    @EJB ProductoFacade pf;
    @EJB VentaFacade vf;
    @EJB CompradorFacade cf;
    @EJB UsuarioFacade userfac;
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
        
        List<Producto> productosVendidos = pf.getProductosVendidos();
        
        for(Producto pr : productosVendidos) {
            List<Puja> pujas = pr.getPujaList();
            
            if(pujas != null && !pujas.isEmpty()) {
                Puja puja = pujas.get(pujas.size() - 1);
                
                VentaPK ventaPK = new VentaPK();
                ventaPK.setIdComprador(puja.getComprador().getIdUsuario());
                ventaPK.setIdProducto(puja.getProducto().getIdProducto());
                
                Venta venta = new Venta(ventaPK);
                venta.setComprador(puja.getComprador());
                venta.setFecha(pr.getFechaFin());
                venta.setPrecioVenta(puja.getPuja());
                venta.setProducto(pr);
                vf.create(venta);
                
                Comprador comprador = puja.getComprador();
                List<Venta> ventasComprador = comprador.getVentaList();
                ventasComprador.add(venta);
                comprador.setVentaList(ventasComprador);
                cf.edit(comprador);
                
                pr.setVenta(venta);
                pf.edit(pr);
            } else {
                Venta venta = new Venta();
                venta.setFecha(pr.getFechaFin());
                venta.setProducto(pr);
                vf.create(venta);
                
                pr.setVenta(venta);
                pf.edit(pr);
            }   
        }
        
        
        Usuario user = this.userfac.comprobarUsuario(usuario, clave);
        //admin es o una lista vacia o un Object[]
        if (user == null){
            String strError = "El usuario o la clave son incorrectos";
            request.setAttribute("error", strError);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else{
            HttpSession session = request.getSession();
            session.setAttribute("usuario", user);
            
            String tipoUser = userfac.getTipoUsuario(user);
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
