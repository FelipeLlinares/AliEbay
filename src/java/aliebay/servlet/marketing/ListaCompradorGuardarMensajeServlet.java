/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package aliebay.servlet.marketing;

import aliebay.dao.ListacompradorFacade;
import aliebay.dao.MarketingFacade;
import aliebay.dao.MensajeFacade;
import aliebay.entity.Listacomprador;
import aliebay.entity.Marketing;
import aliebay.entity.Mensaje;
import aliebay.entity.MensajePK;
import aliebay.entity.Usuario;
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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cate
 */
@WebServlet(name = "ListaCompradorGuardarMensajeServlet", urlPatterns = {"/ListaCompradorGuardarMensajeServlet"})
public class ListaCompradorGuardarMensajeServlet extends AliEbaySessionServlet {
    @EJB ListacompradorFacade lcf;
    @EJB MensajeFacade mensajef;
    @EJB MarketingFacade marketingf;
    
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
        Mensaje mensaje;
        MensajePK mPK;

        strID = request.getParameter("id");
        
        if (strID == null || strID.isEmpty()){
            mensaje = new Mensaje();
            mPK = new MensajePK();
        } else{
            mensaje = this.mensajef.find(Integer.parseInt(strID));
            mPK = mensaje.getMensajePK();
        }
       
        str = request.getParameter("description");
        mensaje.setDescripcion(str);
        
        strIdLista = request.getParameter("idLista");
        Listacomprador lc = lcf.find(Integer.parseInt(strIdLista));
        mensaje.setListacomprador(lc);
        
        //Fecha
            LocalDateTime myDateObj = LocalDateTime.now();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String formattedDate = myDateObj.format(myFormatObj);
        
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
         try {
             mensaje.setFecha(format.parse(formattedDate));
         } catch (ParseException ex) {
             Logger.getLogger(ListaCompradorGuardarMensajeServlet.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        
        HttpSession session = request.getSession();
        Usuario user = (Usuario) session.getAttribute("usuario");
        Marketing marketing = marketingf.find(user.getIdUsuario());
        mensaje.setMarketing(marketing);
        
        mPK.setIdListaComprador(lc.getIdLista());
        mPK.setIdMarketing(marketing.getIdUsuario());
        
        mensaje.setMensajePK(mPK);
        
        if (strID == null || strID.isEmpty()){
            mensajef.create(mensaje);
        } else {
            mensajef.edit(mensaje);
        }
        
        response.sendRedirect(request.getContextPath() + "/MarketingServlet");
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
