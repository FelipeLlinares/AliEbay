/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aliebay.entity;

import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 *
 * @author felip
 */
@Embeddable
public class VentaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idVenta")
    private int idVenta;
    @Basic(optional = false)
    @Column(name = "idProducto")
    private int idProducto;
    @Basic(optional = false)
    @Column(name = "idComprador")
    private int idComprador;

    public VentaPK() {
    }

    public VentaPK(int idVenta, int idProducto, int idComprador) {
        this.idVenta = idVenta;
        this.idProducto = idProducto;
        this.idComprador = idComprador;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdComprador() {
        return idComprador;
    }

    public void setIdComprador(int idComprador) {
        this.idComprador = idComprador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idVenta;
        hash += (int) idProducto;
        hash += (int) idComprador;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VentaPK)) {
            return false;
        }
        VentaPK other = (VentaPK) object;
        if (this.idVenta != other.idVenta) {
            return false;
        }
        if (this.idProducto != other.idProducto) {
            return false;
        }
        if (this.idComprador != other.idComprador) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "aliebay.entity.VentaPK[ idVenta=" + idVenta + ", idProducto=" + idProducto + ", idComprador=" + idComprador + " ]";
    }
    
}
