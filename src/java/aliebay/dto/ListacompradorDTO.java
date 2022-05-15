/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aliebay.dto;

import aliebay.entity.Comprador;
import aliebay.entity.Mensaje;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import java.util.List;

/**
 *
 * @author felip
 */
public class ListacompradorDTO {
    private Integer idLista;
    private String nombre;
    private List<CompradorDTO> compradorList;
    private List<MensajeDTO> mensajeList;
}
