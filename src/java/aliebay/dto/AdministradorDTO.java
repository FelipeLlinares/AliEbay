/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aliebay.dto;

import aliebay.entity.Usuario;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

/**
 *
 * @author felip
 */
public class AdministradorDTO {
    

    public AdministradorDTO() {
    }
    private UsuarioDTO usuario;
    private Integer idUsuario;

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    
}
