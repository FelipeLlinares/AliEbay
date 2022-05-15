/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aliebay.dto;

import aliebay.entity.Administrador;
import aliebay.entity.Comprador;
import aliebay.entity.Marketing;
import aliebay.entity.Vendedor;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;

/**
 *
 * @author felip
 */
public class UsuarioDTO {

    private Integer idUsuario;
    private String nombre;
    private String apellidos;
    private String domicilio;
    private String ciudadResidencia;
    private Integer edad;
    private String sexo;
    private String userName;
    private String password;
    private MarketingDTO marketing;
    private AdministradorDTO administrador;
    private VendedorDTO vendedor;
    private CompradorDTO comprador;
}
