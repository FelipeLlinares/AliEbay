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
import aliebay.entity.Marketing;
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
public class UsuarioService {
    @EJB UsuarioFacade uf; 
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public List<UsuarioDTO> listarUsuario(){
        List<Usuario> usuarios = uf.findAll();
        
        return this.listaEntityADTO(usuarios);
    }
    
    public List<UsuarioDTO> listaEntityADTO (List<Usuario> lista) {
        List<UsuarioDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Usuario u : lista) {
                listaDTO.add(u.toDTO());
            }
        }
        return listaDTO;
    }
    
    public UsuarioDTO buscarUsuario(int usuario){
        Usuario u = uf.find(usuario);
        return u.toDTO();
    }
    
    public void borrarUsuario(int usuario) {
        Usuario u = this.uf.find(usuario);

        this.uf.remove(u);        
    }
    
    public void crearUsuario(int nueva) {
        Usuario u = new Usuario();
        u.setIdUsuario(nueva);

        this.uf.create(u);
    }

    public UsuarioDTO comprobarUsuario(String usuario, String clave) {
        Usuario u = uf.comprobarUsuario(usuario, clave);
        return u==null?null:u.toDTO();
    }

    public String getTipoUsuario(UsuarioDTO user) {
        return uf.getTipoUsuario(uf.find(user.getIdUsuario()));
    }

    public UsuarioDTO crearUsuario(String tipoUsuario,String nombre, String apellidos, String domicilio, String ciudad, int edad, String sexo, String user, String password) {
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellidos(apellidos);
        usuario.setDomicilio(domicilio);
        usuario.setCiudadResidencia(ciudad);
        usuario.setEdad(edad);
        usuario.setSexo(sexo);
        usuario.setUserName(user);
        usuario.setPassword(password);
        
        uf.create(usuario);
        
        if(tipoUsuario.equals("comprador")){
                this.añadirComprador(usuario.getUserName());
                //comFacade.create(comprador);
            }else if(tipoUsuario.equals("vendedor")){
                this.añadirVendedor(usuario.getUserName());
                //venFacade.create(vendedor);
            }else if(tipoUsuario.equals("marketing")){
                this.añadirMarketing(usuario.getUserName());
                //marFacade.create(marketing);
            }
 
        return usuario.toDTO();
    }
    
    public void añadirComprador(String userName){
        Usuario user = uf.getUsuarioPorUserName(userName);
        Comprador comprador = new Comprador(user.getIdUsuario());
        comprador.setUsuario(user);
        user.setComprador(comprador);
        uf.edit(user);
    }
    
    public void añadirVendedor(String userName){
        Usuario user = uf.getUsuarioPorUserName(userName);
        Vendedor vendedor = new Vendedor(user.getIdUsuario());
        vendedor.setUsuario(user);
        user.setVendedor(vendedor);
        uf.edit(user);
    }
    
    public void añadirMarketing(String userName){
        Usuario user = uf.getUsuarioPorUserName(userName);
        Marketing marketing = new Marketing(user.getIdUsuario());
        marketing.setUsuario(user);
        user.setMarketing(marketing);
        uf.edit(user);
    }

    public void modificarUsuario(Integer idUsuario, String nombre, String apellidos, String domicilio, String ciudad, int edad, String sexo, String user, String password) {
        Usuario usuario = uf.find(idUsuario);
        usuario.setNombre(nombre);
        usuario.setApellidos(apellidos);
        usuario.setDomicilio(domicilio);
        usuario.setCiudadResidencia(ciudad);
        usuario.setEdad(edad);
        usuario.setSexo(sexo);
        usuario.setUserName(user);
        usuario.setPassword(password);
        
        uf.edit(usuario);
    }
}
