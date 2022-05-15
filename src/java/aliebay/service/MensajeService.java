/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aliebay.service;

import aliebay.dao.MensajeFacade;
import aliebay.dto.MensajeDTO;
import aliebay.entity.Mensaje;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Enrique Cañadas Cobo
 */
@Stateless
public class MensajeService {
    @EJB MensajeFacade mf; 
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
    
    public MensajeDTO buscarMensaje(String comprador){
        Mensaje m = mf.find(comprador);
        return m.toDTO();
    }
    
    public void borrarMensaje(String comprador) {
        Mensaje m = this.mf.find(comprador);

        this.mf.remove(m);        
    }
    
    public void crearMensaje(int nueva) {
        Mensaje m = new Mensaje();
        this.mf.create(m);
    }
}
