/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aliebay.service;

import aliebay.dao.ListacompradorFacade;
import aliebay.dao.MarketingFacade;
import aliebay.dao.MensajeFacade;
import aliebay.dto.ListacompradorDTO;
import aliebay.dto.MensajeDTO;
import aliebay.entity.Listacomprador;
import aliebay.entity.Mensaje;
import aliebay.entity.MensajePK;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Enrique Cañadas Cobo
 */
@Stateless
public class MensajeService {
    @EJB MensajeFacade mf;
    @EJB ListacompradorFacade lcf;
    @EJB MarketingFacade marf;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public List<MensajeDTO> listarMensaje(){
        List<Mensaje> mensajes = mf.findAll();
        
        return this.listaEntityADTO(mensajes);
    }
    
    private List<MensajeDTO> listaEntityADTO (List<Mensaje> lista) {
        List<MensajeDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Mensaje m : lista) {
                listaDTO.add(m.toDTO());
            }
        }
        return listaDTO;
    }
    
    public MensajeDTO buscarMensaje(int mensaje){
        Mensaje m = mf.find(mensaje);
        return m.toDTO();
    }
    
    public void borrarMensaje(int mensaje) {
        Mensaje m = this.mf.find(mensaje);

        this.mf.remove(m);        
    }

    public List<MensajeDTO> mensajesListaComprador(int idLista) {
        return listaEntityADTO(this.mf.mensajesListaComprador(idLista));
    }

    public List<MensajeDTO> getMensajesLista(Integer idLista) {
        Listacomprador lc = lcf.find(idLista);
        return this.listaEntityADTO(lc.getMensajeList());
    }

    public void eliminarMensaje(int mensaje) {
        Mensaje m = mf.find(mensaje);
        
        Listacomprador lc = lcf.find(m.getListacomprador().getIdLista());
        List<Mensaje> mensajes = lc.getMensajeList();
        mensajes.remove(m);
        lc.setMensajeList(mensajes);
        lcf.edit(lc);
        
        mf.remove(m);
    }



    public void crearMensaje(String descripcion, Date fecha, ListacompradorDTO lc, Integer idUsuario) {
        MensajePK mpk = new MensajePK();
        mpk.setIdListaComprador(lc.getIdLista());
        mpk.setIdMarketing(idUsuario);
        Mensaje m = new Mensaje(mpk);
        Listacomprador lista = lcf.find(lc.getIdLista());
        m.setDescripcion(descripcion);
        m.setFecha(fecha);
        m.setListacomprador(lcf.find(lc.getIdLista()));
        m.setMarketing(marf.find(idUsuario));
        
        List<Mensaje> ms = lista.getMensajeList();
        ms.add(m);
        lista.setMensajeList(ms);
        lcf.edit(lista);
        
        mf.create(m);
    }

    public void editarMensaje(int id, String descripcion, Date fecha, ListacompradorDTO lc, Integer idUsuario) {
        Mensaje m = mf.findById(id);
        Listacomprador lista = lcf.find(lc.getIdLista());
        m.setDescripcion(descripcion);
        m.setFecha(fecha);
        m.setListacomprador(lista);
        m.setMarketing(marf.find(idUsuario));
        
        List<Mensaje> ms = lista.getMensajeList();
        ms.add(m);
        lista.setMensajeList(ms);
        lcf.edit(lista);
        
        mf.edit(m);
    }
}
