/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aliebay.entity;

import aliebay.dto.PujaDTO;
import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author felip
 */
@Entity
@Table(name = "puja")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Puja.findAll", query = "SELECT p FROM Puja p"),
    @NamedQuery(name = "Puja.findByIdProducto", query = "SELECT p FROM Puja p WHERE p.pujaPK.idProducto = :idProducto"),
    @NamedQuery(name = "Puja.findByIdComprador", query = "SELECT p FROM Puja p WHERE p.pujaPK.idComprador = :idComprador"),
    @NamedQuery(name = "Puja.findByPuja", query = "SELECT p FROM Puja p WHERE p.puja = :puja"),
    @NamedQuery(name = "Puja.findByFecha", query = "SELECT p FROM Puja p WHERE p.fecha = :fecha")})
public class Puja implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PujaPK pujaPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "puja")
    private Float puja;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "idComprador", referencedColumnName = "idUsuario", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Comprador comprador;
    @JoinColumn(name = "idProducto", referencedColumnName = "idProducto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Producto producto;

    public Puja() {
    }

    public Puja(PujaPK pujaPK) {
        this.pujaPK = pujaPK;
    }

    public Puja(int idProducto, int idComprador) {
        this.pujaPK = new PujaPK(idProducto, idComprador);
    }

    public PujaPK getPujaPK() {
        return pujaPK;
    }

    public void setPujaPK(PujaPK pujaPK) {
        this.pujaPK = pujaPK;
    }

    public Float getPuja() {
        return puja;
    }

    public void setPuja(Float puja) {
        this.puja = puja;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pujaPK != null ? pujaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Puja)) {
            return false;
        }
        Puja other = (Puja) object;
        if ((this.pujaPK == null && other.pujaPK != null) || (this.pujaPK != null && !this.pujaPK.equals(other.pujaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "aliebay.entity.Puja[ pujaPK=" + pujaPK + " ]";
    }
    
    public PujaDTO toDTO(){
        PujaDTO dto = new PujaDTO();
        dto.setComprador(comprador.toDTO());
        dto.setFecha(fecha);
        dto.setProducto(producto.toDTO());
        dto.setPuja(puja);
        return dto;
    }
}
