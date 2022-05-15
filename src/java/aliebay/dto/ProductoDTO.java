/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aliebay.dto;

import aliebay.entity.Categoria;
import aliebay.entity.Comprador;
import aliebay.entity.Puja;
import aliebay.entity.Venta;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import java.util.List;

/**
 *
 * @author felip
 */
public class ProductoDTO {
    private Integer idProducto;
    private int idVendedor;
    private String titulo;
    private String descripcion;
    private Float precioSalida;
    private String uRLFoto;
    private Date fechaSalida;
    private Date fechaFin;
    private List<CompradorDTO> compradorList;
    private VentaDTO venta;
    private List<PujaDTO> pujaList;
    private CategoriaDTO categoria;

    public ProductoDTO() {
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Float getPrecioSalida() {
        return precioSalida;
    }

    public void setPrecioSalida(Float precioSalida) {
        this.precioSalida = precioSalida;
    }

    public String getuRLFoto() {
        return uRLFoto;
    }

    public void setuRLFoto(String uRLFoto) {
        this.uRLFoto = uRLFoto;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List<CompradorDTO> getCompradorList() {
        return compradorList;
    }

    public void setCompradorList(List<CompradorDTO> compradorList) {
        this.compradorList = compradorList;
    }

    public VentaDTO getVenta() {
        return venta;
    }

    public void setVenta(VentaDTO venta) {
        this.venta = venta;
    }

    public List<PujaDTO> getPujaList() {
        return pujaList;
    }

    public void setPujaList(List<PujaDTO> pujaList) {
        this.pujaList = pujaList;
    }

    public CategoriaDTO getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaDTO categoria) {
        this.categoria = categoria;
    }
    
    
}
