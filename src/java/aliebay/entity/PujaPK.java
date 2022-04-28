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
public class PujaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idProducto")
    private int idProducto;
    @Basic(optional = false)
    @Column(name = "idComprador")
    private int idComprador;

    public PujaPK() {
    }

    public PujaPK(int idProducto, int idComprador) {
        this.idProducto = idProducto;
        this.idComprador = idComprador;
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
        hash += (int) idProducto;
        hash += (int) idComprador;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PujaPK)) {
            return false;
        }
        PujaPK other = (PujaPK) object;
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
        return "aliebay.entity.PujaPK[ idProducto=" + idProducto + ", idComprador=" + idComprador + " ]";
    }
    
}
