/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aliebay.service;

import aliebay.dao.UsuarioFacade;
import aliebay.dao.VendedorFacade;
import aliebay.dto.UsuarioDTO;
import aliebay.dto.VendedorDTO;
import aliebay.entity.Usuario;
import aliebay.entity.Vendedor;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Enrique Cañadas Cobo
 */
@Stateless
public class VendedorService {
    @EJB VendedorFacade vf; 
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public List<VendedorDTO> listarVendedor(){
        List<Vendedor> vendedores = vf.findAll();
        
        return this.listaEntityADTO(vendedores);
    }
    
    private List<VendedorDTO> listaEntityADTO (List<Vendedor> lista) {
        List<VendedorDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Vendedor v : lista) {
                listaDTO.add(v.toDTO());
            }
        }
        return listaDTO;
    }
    
    public VendedorDTO buscarVendedor(int vendedor){
        Vendedor v = vf.find(vendedor);
        return v.toDTO();
    }
    
    public void borrarVendedor(int vendedor) {
        Vendedor v = this.vf.find(vendedor);

        this.vf.remove(v);        
    }
    
    public void crearVendedor(int nueva) {
        Vendedor v = new Vendedor();
        v.setIdUsuario(nueva);

        this.vf.create(v);
    }
}
