/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aliebay.service;

import aliebay.dao.CompradorFacade;
import aliebay.dao.MarketingFacade;
import aliebay.dto.CompradorDTO;
import aliebay.dto.MarketingDTO;
import aliebay.entity.Comprador;
import aliebay.entity.Marketing;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Enrique Cañadas Cobo
 */
@Stateless
public class MarketingService {
    @EJB MarketingFacade mf; 
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public List<MarketingDTO> listarMarketing(){
        List<Marketing> marketings = mf.findAll();
        
        return this.listaEntityADTO(marketings);
    }
    
    private List<MarketingDTO> listaEntityADTO (List<Marketing> lista) {
        List<MarketingDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Marketing m : lista) {
                listaDTO.add(m.toDTO());
            }
        }
        return listaDTO;
    }
    
    public MarketingDTO buscarMarketing(int marketing){
        Marketing m = mf.find(marketing);
        return m.toDTO();
    }
    
    public void borrarMarketing(int marketing) {
        Marketing c = this.mf.find(marketing);

        this.mf.remove(c);        
    }
    
    public void crearMarketing(int nueva) {
        Marketing c = new Marketing();
        c.setIdUsuario(nueva);

        this.mf.create(c);
    }
}
