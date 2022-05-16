/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aliebay.dto;

import java.util.Date;

/**
 *
 * @author felip
 */
public class VentaDTO {
    private Date fecha;
    private Float precioVenta;
    private CompradorDTO comprador;
    private ProductoDTO producto;

    public VentaDTO() {
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Float getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Float precioVenta) {
        this.precioVenta = precioVenta;
    }

    public CompradorDTO getComprador() {
        return comprador;
    }

    public void setComprador(CompradorDTO comprador) {
        this.comprador = comprador;
    }

    public ProductoDTO getProducto() {
        return producto;
    }

    public void setProducto(ProductoDTO producto) {
        this.producto = producto;
    }
    
    
    
}
