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

    public CompradorDTO() {
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<ProductoDTO> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<ProductoDTO> productoList) {
        this.productoList = productoList;
    }

    public List<ListacompradorDTO> getListacompradorList() {
        return listacompradorList;
    }

    public void setListacompradorList(List<ListacompradorDTO> listacompradorList) {
        this.listacompradorList = listacompradorList;
    }

    public List<VentaDTO> getVentaList() {
        return ventaList;
    }

    public void setVentaList(List<VentaDTO> ventaList) {
        this.ventaList = ventaList;
    }

    public List<PujaDTO> getPujaList() {
        return pujaList;
    }

    public void setPujaList(List<PujaDTO> pujaList) {
        this.pujaList = pujaList;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }
    
    
}
