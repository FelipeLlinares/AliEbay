/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aliebay.entity;

import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author felip
 */
@Embeddable
public class MensajePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idListaComprador")
    private int idListaComprador;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idMarketing")
    private int idMarketing;

    public MensajePK() {
    }

    public MensajePK(int id, int idListaComprador, int idMarketing) {
        this.id = id;
        this.idListaComprador = idListaComprador;
        this.idMarketing = idMarketing;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdListaComprador() {
        return idListaComprador;
    }

    public void setIdListaComprador(int idListaComprador) {
        this.idListaComprador = idListaComprador;
    }

    public int getIdMarketing() {
        return idMarketing;
    }

    public void setIdMarketing(int idMarketing) {
        this.idMarketing = idMarketing;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) idListaComprador;
        hash += (int) idMarketing;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MensajePK)) {
            return false;
        }
        MensajePK other = (MensajePK) object;
        if (this.id != other.id) {
            return false;
        }
        if (this.idListaComprador != other.idListaComprador) {
            return false;
        }
        if (this.idMarketing != other.idMarketing) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "aliebay.entity.MensajePK[ id=" + id + ", idListaComprador=" + idListaComprador + ", idMarketing=" + idMarketing + " ]";
    }
    
}
