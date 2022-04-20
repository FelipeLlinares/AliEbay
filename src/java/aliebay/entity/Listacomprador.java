/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aliebay.entity;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

/**
 *
 * @author felip
 */
@Entity
@Table(name = "listacomprador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Listacomprador.findAll", query = "SELECT l FROM Listacomprador l"),
    @NamedQuery(name = "Listacomprador.findByIdLista", query = "SELECT l FROM Listacomprador l WHERE l.idLista = :idLista"),
    @NamedQuery(name = "Listacomprador.findByNombre", query = "SELECT l FROM Listacomprador l WHERE l.nombre = :nombre")})
public class Listacomprador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idLista")
    private Integer idLista;
    @Size(max = 45)
    @Column(name = "nombre")
    private String nombre;
    @JoinTable(name = "pertenencialista", joinColumns = {
        @JoinColumn(name = "idLista", referencedColumnName = "idLista")}, inverseJoinColumns = {
        @JoinColumn(name = "idComprador", referencedColumnName = "idUsuario")})
    @ManyToMany
    private List<Comprador> compradorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "listacomprador")
    private List<Mensaje> mensajeList;

    public Listacomprador() {
    }

    public Listacomprador(Integer idLista) {
        this.idLista = idLista;
    }

    public Integer getIdLista() {
        return idLista;
    }

    public void setIdLista(Integer idLista) {
        this.idLista = idLista;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<Comprador> getCompradorList() {
        return compradorList;
    }

    public void setCompradorList(List<Comprador> compradorList) {
        this.compradorList = compradorList;
    }

    @XmlTransient
    public List<Mensaje> getMensajeList() {
        return mensajeList;
    }

    public void setMensajeList(List<Mensaje> mensajeList) {
        this.mensajeList = mensajeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLista != null ? idLista.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Listacomprador)) {
            return false;
        }
        Listacomprador other = (Listacomprador) object;
        if ((this.idLista == null && other.idLista != null) || (this.idLista != null && !this.idLista.equals(other.idLista))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "aliebay.entity.Listacomprador[ idLista=" + idLista + " ]";
    }
    
}
