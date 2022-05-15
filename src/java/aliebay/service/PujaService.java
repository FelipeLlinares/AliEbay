/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aliebay.service;

import aliebay.dao.PujaFacade;
import aliebay.dto.ProductoDTO;
import aliebay.dto.PujaDTO;
import aliebay.entity.Producto;
import aliebay.entity.Puja;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author felip
 */

@Stateless
public class PujaService {
    @EJB PujaFacade pf;
    
    public List<PujaDTO> listaEntityADTO(List<Puja> lista) {
        List<PujaDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Puja p:lista) {
                listaDTO.add(p.toDTO());
            }
        }
        return listaDTO;
    }
    
    public PujaDTO findPuja(int idProducto,int idComprador){
        return pf.findPuja(idProducto, idComprador).toDTO();
    }
    
    
    
}
