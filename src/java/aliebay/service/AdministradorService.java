/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aliebay.service;

import aliebay.dao.AdministradorFacade;
import aliebay.dao.CompradorFacade;
import aliebay.dto.AdministradorDTO;
import aliebay.dto.CompradorDTO;
import aliebay.entity.Administrador;
import aliebay.entity.Comprador;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Enrique Cañadas Cobo
 */
@Stateless
public class AdministradorService {
    @EJB AdministradorFacade af; 
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public List<AdministradorDTO> listarAdministrador(){
        List<Administrador> administradores = af.findAll();
        
        return this.listaEntityADTO(administradores);
    }
    
    private List<AdministradorDTO> listaEntityADTO (List<Administrador> lista) {
        List<AdministradorDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Administrador a : lista) {
                listaDTO.add(a.toDTO());
            }
        }
        return listaDTO;
    }
    
    public AdministradorDTO buscarAdministrador(String administrador){
        Administrador a = af.find(administrador);
        return a.toDTO();
    }
    
    public void borrarAdministrador(String administrador) {
        Administrador c = this.af.find(administrador);

        this.af.remove(c);        
    }
    
    public void crearComprador(int nueva) {
        Administrador a = new Administrador();
        a.setIdUsuario(nueva);

        this.af.create(a);
    }
}
