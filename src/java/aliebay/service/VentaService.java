/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aliebay.service;

import aliebay.dao.CompradorFacade;
import aliebay.dao.ProductoFacade;
import aliebay.dao.VentaFacade;
import aliebay.dto.CompradorDTO;
import aliebay.dto.ProductoDTO;
import aliebay.dto.VentaDTO;
import aliebay.entity.Comprador;
import aliebay.entity.Producto;
import aliebay.entity.Venta;
import aliebay.entity.VentaPK;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Enrique Cañadas Cobo
 */
@Stateless
public class VentaService {
    @EJB VentaFacade vf; 
    @EJB CompradorFacade cf;
    @EJB ProductoFacade pf;
    @EJB VentaService vs;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public List<VentaDTO> listarVenta(){
        List<Venta> ventas = vf.findAll();
        
        return this.listaEntityADTO(ventas);
    }
    
    private List<VentaDTO> listaEntityADTO (List<Venta> lista) {
        List<VentaDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Venta v : lista) {
                listaDTO.add(v.toDTO());
            }
        }
        return listaDTO;
    }
    
    public VentaDTO buscarVenta(int venta){
        Venta c = vf.find(venta);
        return c.toDTO();
    }
    
    public void borrarVenta(int venta) {
        Venta c = this.vf.find(venta);

        this.vf.remove(c);        
    }
    
    public void crearVenta(Integer idUsuario, Integer idProducto, CompradorDTO comprador, Date fechaFin, Float puja) {
        VentaPK ventaPK = new VentaPK();
        ventaPK.setIdComprador(idUsuario);
        ventaPK.setIdProducto(idProducto);
        Venta v = new Venta();
        v.setVentaPK(ventaPK);
        v.setComprador(cf.find(idUsuario));
        v.setProducto(pf.find(idProducto));
        v.setFecha(fechaFin);
        v.setPrecioVenta(puja);
        
        this.vf.create(v);
        
        Comprador c = cf.find(comprador.getIdUsuario());
        List<Venta> ventas = c.getVentaList();
        ventas.add(v);
        c.setVentaList(ventas);
        cf.edit(c);
        
        Producto p = pf.find(idProducto);
        p.setVenta(v);
        pf.edit(p);
    }

    public List<VentaDTO> getVentaList(int usuario) {
        Comprador c = cf.find(usuario);
        return vs.listaEntityADTO(c.getVentaList()); 
    }
    
    
}
