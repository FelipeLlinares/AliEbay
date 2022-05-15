/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aliebay.service;

import aliebay.dao.CompradorFacade;
import aliebay.dao.VentaFacade;
import aliebay.dto.CompradorDTO;
import aliebay.dto.VentaDTO;
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
public class VentaService {
    @EJB VentaFacade vf; 
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public List<VentaDTO> listarVenta(){
        List<Venta> ventas = vf.findAll();
        
        return this.listaEntityADTO(ventas);
    }
    
    private List<VentaDTO> listaEntityADTO (List<Venta> lista) {
        List<VentaDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Venta v : lista) {
                listaDTO.add(v.toDTO());
            }
        }
        return listaDTO;
    }
    
    public VentaDTO buscarVenta(String venta){
        Venta c = vf.find(venta);
        return c.toDTO();
    }
    
    public void borrarVenta(String venta) {
        Venta c = this.vf.find(venta);

        this.vf.remove(c);        
    }
    
    public void crearVenta(int nueva) {
        Venta c = new Venta();

        this.vf.create(c);
    }
}
