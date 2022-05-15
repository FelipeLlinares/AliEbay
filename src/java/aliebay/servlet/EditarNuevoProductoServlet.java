/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package aliebay.servlet;

import aliebay.dao.CategoriaFacade;
import aliebay.dao.ProductoFacade;
import aliebay.dto.CategoriaDTO;
import aliebay.dto.ProductoDTO;
import aliebay.entity.Categoria;
import aliebay.entity.Comprador;
import aliebay.entity.Marketing;
import aliebay.entity.Producto;
import aliebay.entity.Vendedor;
import aliebay.service.CategoriaService;
import aliebay.service.ProductoService;
import jakarta.ejb.EJB;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author alvar
 */
@WebServlet(name = "EditarNuevoProductoServlet", urlPatterns = {"/EditarNuevoProductoServlet"})
public class EditarNuevoProductoServlet extends AliEbaySessionServlet {
    @EJB ProductoService ps;
    @EJB CategoriaService cs;

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
        if (super.comprobarSesion(request,response)){
          
            String str = request.getParameter("idprod");
            List<CategoriaDTO> categorias = cs.listarCategorias();
            
            request.setAttribute("categorias",categorias);
            
            if (str != null){
                ProductoDTO producto = ps.buscarProducto(Integer.parseInt(str));
                request.setAttribute("idprod", producto);
            }else{
                String vendedor = request.getParameter("id");
                request.setAttribute("vendedor", vendedor);
            }
            request.getRequestDispatcher("/WEB-INF/jsp/nuevoProducto.jsp").forward(request, response);
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
