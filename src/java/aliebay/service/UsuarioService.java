/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aliebay.service;

import aliebay.dao.CompradorFacade;
import aliebay.dao.UsuarioFacade;
import aliebay.dto.CompradorDTO;
import aliebay.dto.UsuarioDTO;
import aliebay.entity.Comprador;
import aliebay.entity.Usuario;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Enrique Cañadas Cobo
 */
@Stateless
public class UsuarioService {
    @EJB UsuarioFacade uf; 
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public List<UsuarioDTO> listarUsuario(){
        List<Usuario> usuarios = uf.findAll();
        
        return this.listaEntityADTO(usuarios);
    }
    
    private List<UsuarioDTO> listaEntityADTO (List<Usuario> lista) {
        List<UsuarioDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Usuario u : lista) {
                listaDTO.add(u.toDTO());
            }
        }
        return listaDTO;
    }
    
    public UsuarioDTO buscarUsuario(String usuario){
        Usuario u = uf.find(usuario);
        return u.toDTO();
    }
    
    public void borrarUsuario(String usuario) {
        Usuario u = this.uf.find(usuario);

        this.uf.remove(u);        
    }
    
    public void crearUsuario(int nueva) {
        Usuario u = new Usuario();
        u.setIdUsuario(nueva);

        this.uf.create(u);
    }
}
