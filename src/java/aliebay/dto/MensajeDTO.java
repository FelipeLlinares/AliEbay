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
}
