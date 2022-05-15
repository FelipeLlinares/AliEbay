/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aliebay.service;

import aliebay.dao.CategoriaFacade;
import aliebay.dao.CompradorFacade;
import aliebay.dto.CategoriaDTO;
import aliebay.dto.CompradorDTO;
import aliebay.dto.VentaDTO;
import aliebay.entity.Categoria;
import aliebay.entity.Comprador;
import aliebay.entity.Venta;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Enrique Cañadas Cobo
 */
@Stateless
public class CompradorService {
    @EJB CompradorFacade cf; 
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public List<CompradorDTO> listarComprador(){
        List<Comprador> compradores = cf.findAll();
        
        return this.listaEntityADTO(compradores);
    }
    
    private List<CompradorDTO> listaEntityADTO (List<Comprador> lista) {
        List<CompradorDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Comprador c : lista) {
                listaDTO.add(c.toDTO());
            }
        }
        return listaDTO;
    }
    
    public CompradorDTO buscarComprador(String comprador){
        Comprador c = cf.find(comprador);
        return c.toDTO();
    }
    
    public void borrarComprador(String comprador) {
        Comprador c = this.cf.find(comprador);

        this.cf.remove(c);        
    }
    
    public void crearComprador(int nueva) {
        Comprador c = new Comprador();
        c.setIdUsuario(nueva);

        this.cf.create(c);
    }
}
