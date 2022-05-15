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
}
