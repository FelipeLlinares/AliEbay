/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package aliebay.servlet;

import aliebay.dao.AdministradorFacade;
import aliebay.dao.CompradorFacade;
import aliebay.dao.UsuarioFacade;
import aliebay.dao.VendedorFacade;
import aliebay.entity.Comprador;
import aliebay.entity.Usuario;
import aliebay.entity.Vendedor;
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

/**
 *
 * @author Cate
 */
@WebServlet(name = "AdminServlet", urlPatterns = {"/AdminServlet"})
public class AdminServlet extends AliEbaySessionServlet {

    @EJB UsuarioFacade uf;
    @EJB CompradorFacade cf;
    @EJB VendedorFacade vf;
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
        
        if (super.comprobarSesion(request,response) && super.comprobarAdmin(request,response)){
        List<Comprador> compradores = cf.findAll();
        List<Vendedor> vendedores = vf.findAll();
        
        List<Usuario> usuariosC = new ArrayList<>();
        List<Usuario> usuariosV = new ArrayList<>();
        
        for(Comprador c: compradores){
            usuariosC.add(uf.find(c.getIdUsuario()));
        }
        
        for(Vendedor v: vendedores){
            usuariosV.add(uf.find(v.getIdUsuario()));;
        }
        
        request.setAttribute("usuariosC", usuariosC);
        request.setAttribute("usuariosV", usuariosV);
        
        request.getRequestDispatcher("/WEB-INF/jsp/lista.jsp").forward(request, response);
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
