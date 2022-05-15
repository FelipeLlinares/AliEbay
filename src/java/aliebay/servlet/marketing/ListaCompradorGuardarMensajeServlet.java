/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package aliebay.servlet.marketing;

import aliebay.dto.ListacompradorDTO;
import aliebay.dto.MarketingDTO;
import aliebay.dto.MensajeDTO;
import aliebay.dto.UsuarioDTO;
import aliebay.service.ListacompradorService;
import aliebay.service.MarketingService;
import aliebay.service.MensajeService;
import aliebay.servlet.AliEbaySessionServlet;
import jakarta.ejb.EJB;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.StringJoiner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jose Maria Tapia Catena
 */
@WebServlet(name = "ListaCompradorGuardarMensajeServlet", urlPatterns = {"/ListaCompradorGuardarMensajeServlet"})
public class ListaCompradorGuardarMensajeServlet extends AliEbaySessionServlet {
    @EJB ListacompradorService lcs;
    @EJB MensajeService mensajes;
    @EJB MarketingService marketings;
    
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
       
     if (super.comprobarSesion(request,response) && super.comprobarMarketing(request,response)){
        
        String strIdLista,strID,str;
        strID = request.getParameter("id");
        
        MensajeDTO mensaje = new MensajeDTO();
        
        StringJoiner sj = new StringJoiner(";");
        str = request.getParameter("asunto");
        sj.add(str);
        
        str = request.getParameter("productos");
        sj.add(str);
        
        str = request.getParameter("description");
        sj.add(str);
        String descripcion = sj.toString();
        
        //Fecha
            LocalDateTime myDateObj = LocalDateTime.now();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String formattedDate = myDateObj.format(myFormatObj);
        
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date fecha = new Date();
         try {
             fecha = format.parse(formattedDate);
         } catch (ParseException ex) {
             Logger.getLogger(ListaCompradorGuardarMensajeServlet.class.getName()).log(Level.SEVERE, null, ex);
         }
         
        strIdLista = request.getParameter("idLista");
        ListacompradorDTO lc = lcs.buscarListacomprador(Integer.parseInt(strIdLista));
        
        HttpSession session = request.getSession();
        UsuarioDTO user = (UsuarioDTO) session.getAttribute("usuario");
        MarketingDTO marketing = marketings.buscarMarketing(user.getIdUsuario());
        
        if (strID == null || strID.isEmpty()){
            mensajes.crearMensaje(descripcion,fecha,lc,marketing.getIdUsuario());
        } else{
            mensajes.editarMensaje(Integer.parseInt(strID),descripcion,fecha,lc,marketing.getIdUsuario());
        }
        
        
       
        
        response.sendRedirect(request.getContextPath() + "/ListaCompradorMensajeServlet?id=" + mensaje.getListacomprador().getIdLista());
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
