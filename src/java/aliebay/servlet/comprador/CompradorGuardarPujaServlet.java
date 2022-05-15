/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 *
 * Creado por Enrique Cañadas Cobo
 */
package aliebay.servlet.comprador;

import aliebay.dao.CompradorFacade;
import aliebay.dao.ProductoFacade;
import aliebay.dao.PujaFacade;
import aliebay.dto.CompradorDTO;
import aliebay.dto.ProductoDTO;
import aliebay.dto.PujaDTO;
import aliebay.dto.UsuarioDTO;
import aliebay.entity.Comprador;
import aliebay.entity.Producto;
import aliebay.entity.Puja;
import aliebay.entity.Usuario;
import aliebay.service.CompradorService;
import aliebay.service.ProductoService;
import aliebay.service.PujaService;
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
import java.util.Date;
import java.util.List;

/**
 *
 * @author Enrique Cañadas Cobo
 */
@WebServlet(name = "CompradorGuardarPujaServlet", urlPatterns = {"/CompradorGuardarPujaServlet"})
public class CompradorGuardarPujaServlet extends AliEbaySessionServlet {

    @EJB
    ProductoService ps;
    @EJB
    PujaService pjs;
    @EJB
    CompradorService cs;

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

            String producto = request.getParameter("id");
            String puja = request.getParameter("puja");

            ProductoDTO prod = ps.buscarProducto(Integer.valueOf(producto));
            request.setAttribute("producto", prod);

            if (puja != null && !puja.isEmpty()) {

                List<PujaDTO> pujas = ps.getPujaList(prod);
                float pujaUltima = 0;
                if (pujas != null && !pujas.isEmpty()) {
                    pujaUltima = pujas.get(pujas.size() - 1).getPuja();
                }

                float nuevoValorPuja = Float.parseFloat(puja);

                if (prod.getPrecioSalida() <= nuevoValorPuja) {
                    if (pujaUltima < nuevoValorPuja) {
                        PujaDTO nuevaPuja = pjs.findPuja(prod.getIdProducto(), comprador.getIdUsuario());
                        if (nuevaPuja == null) {
                            nuevaPuja = new PujaDTO(prod.getIdProducto(), comprador.getIdUsuario());
                            
                        }
                        nuevaPuja.setPuja(nuevoValorPuja);
                        nuevaPuja.setProducto(prod);
                        nuevaPuja.setComprador(comprador);
                        Date date = new Date();
                        nuevaPuja.setFecha(date);

                        pjs.editarPuja(nuevaPuja);

                        List<Puja> pujasComprador = comprador.getPujaList();
                        pujasComprador.add(nuevaPuja);
                        comprador.setPujaList(pujas);
                        cf.edit(comprador);

                        pujas.add(nuevaPuja);
                        prod.setPujaList(pujas);
                        pf.edit(prod);
                        response.sendRedirect(request.getContextPath() + "/" + "CompradorServlet");
                    } else {

                        String strError = "El valor de la puja tiene que ser superior a la puja anterior";
                        request.setAttribute("error", strError);
                        request.getRequestDispatcher("/WEB-INF/jsp/pujar.jsp").forward(request, response);
                    }
                } else {
                    String strError = "El valor de la puja tiene que ser superior al precio de salida";
                    request.setAttribute("error", strError);
                    request.getRequestDispatcher("/WEB-INF/jsp/pujar.jsp").forward(request, response);
                }
            } else {
                String strError = "Tienes que introducir el valor de la puja";
                request.setAttribute("error", strError);
                request.getRequestDispatcher("/WEB-INF/jsp/pujar.jsp").forward(request, response);
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
