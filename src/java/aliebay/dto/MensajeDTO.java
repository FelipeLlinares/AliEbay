/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aliebay.dto;

import aliebay.entity.Listacomprador;
import aliebay.entity.Marketing;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;

/**
 *
 * @author felip
 */
public class MensajeDTO {
    private String descripcion;
    private Date fecha;
    private ListacompradorDTO listacomprador;
    private MarketingDTO marketing;

    public MensajeDTO() {
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public ListacompradorDTO getListacomprador() {
        return listacomprador;
    }

    public void setListacomprador(ListacompradorDTO listacomprador) {
        this.listacomprador = listacomprador;
    }

    public MarketingDTO getMarketing() {
        return marketing;
    }

    public void setMarketing(MarketingDTO marketing) {
        this.marketing = marketing;
    }
    
    
}
