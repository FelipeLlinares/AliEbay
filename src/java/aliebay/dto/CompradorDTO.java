/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aliebay.dto;

import aliebay.entity.Listacomprador;
import aliebay.entity.Producto;
import aliebay.entity.Puja;
import aliebay.entity.Usuario;
import aliebay.entity.Venta;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.List;

/**
 *
 * @author felip
 */
public class CompradorDTO {
    private Integer idUsuario;
    private List<ProductoDTO> productoList;
    private List<ListacompradorDTO> listacompradorList;
    private List<VentaDTO> ventaList;
    private List<PujaDTO> pujaList;
    private UsuarioDTO usuario;
}
