/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aliebay.entity;

import aliebay.dto.ProductoDTO;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

/**
 *
 * @author felip
 */
@Entity
@Table(name = "producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p"),
    @NamedQuery(name = "Producto.findByIdProducto", query = "SELECT p FROM Producto p WHERE p.idProducto = :idProducto"),
    @NamedQuery(name = "Producto.findByIdVendedor", query = "SELECT p FROM Producto p WHERE p.idVendedor = :idVendedor"),
    @NamedQuery(name = "Producto.findByTitulo", query = "SELECT p FROM Producto p WHERE p.titulo = :titulo"),
    @NamedQuery(name = "Producto.findByDescripcion", query = "SELECT p FROM Producto p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "Producto.findByPrecioSalida", query = "SELECT p FROM Producto p WHERE p.precioSalida = :precioSalida"),
    @NamedQuery(name = "Producto.findByURLFoto", query = "SELECT p FROM Producto p WHERE p.uRLFoto = :uRLFoto"),
    @NamedQuery(name = "Producto.findByFechaSalida", query = "SELECT p FROM Producto p WHERE p.fechaSalida = :fechaSalida"),
    @NamedQuery(name = "Producto.findByFechaFin", query = "SELECT p FROM Producto p WHERE p.fechaFin = :fechaFin")})
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idProducto")
    private Integer idProducto;
    @Basic(optional = false)
    @Column(name = "idVendedor")
    private int idVendedor;
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "descripcion")
    private String descripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precioSalida")
    private Float precioSalida;
    @Column(name = "URLFoto")
    private String uRLFoto;
    @Column(name = "fechaSalida")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSalida;
    @Column(name = "fechaFin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @ManyToMany(mappedBy = "productoList")
    private List<Comprador> compradorList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "producto")
    private Venta venta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producto")
    private List<Puja> pujaList;
    @JoinColumn(name = "categoria", referencedColumnName = "idCategoria")
    @ManyToOne
    private Categoria categoria;

    public Producto() {
    }

    public Producto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Producto(Integer idProducto, int idVendedor) {
        this.idProducto = idProducto;
        this.idVendedor = idVendedor;
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

    public String getURLFoto() {
        return uRLFoto;
    }

    public void setURLFoto(String uRLFoto) {
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

    @XmlTransient
    public List<Comprador> getCompradorList() {
        return compradorList;
    }

    public void setCompradorList(List<Comprador> compradorList) {
        this.compradorList = compradorList;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    @XmlTransient
    public List<Puja> getPujaList() {
        return pujaList;
    }

    public void setPujaList(List<Puja> pujaList) {
        this.pujaList = pujaList;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProducto != null ? idProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.idProducto == null && other.idProducto != null) || (this.idProducto != null && !this.idProducto.equals(other.idProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "aliebay.entity.Producto[ idProducto=" + idProducto + " ]";
    }
    
    public ProductoDTO toDTO(){
        ProductoDTO dto = new ProductoDTO();
        dto.setCategoria(categoria.toDTO());
        dto.setDescripcion(descripcion);
        dto.setFechaFin(fechaFin);
        dto.setFechaSalida(fechaSalida);
        dto.setIdProducto(idProducto);
        dto.setIdVendedor(idVendedor);
        dto.setPrecioSalida(precioSalida);
        dto.setTitulo(titulo);
        dto.setuRLFoto(uRLFoto);
        if(getVenta() != null){
            dto.setVenta(venta.toDTO());
        }
        return dto;
    }
    
}
