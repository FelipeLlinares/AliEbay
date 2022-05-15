/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aliebay.service;

import aliebay.dao.CompradorFacade;
import aliebay.dao.ProductoFacade;
import aliebay.dao.PujaFacade;
import aliebay.dto.CompradorDTO;
import aliebay.dto.ProductoDTO;
import aliebay.dto.PujaDTO;
import aliebay.entity.Comprador;
import aliebay.entity.Producto;
import aliebay.entity.Puja;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author felip
 */
@Stateless
public class PujaService {

    @EJB
    CompradorFacade cf;
    @EJB
    ProductoFacade pf;
    @EJB
    PujaFacade pjf;

    public List<PujaDTO> listaEntityADTO(List<Puja> lista) {
        List<PujaDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Puja p : lista) {
                listaDTO.add(p.toDTO());
            }
        }
        return listaDTO;
    }

    public PujaDTO findPuja(int idProducto, int idComprador) {
        return pjf.findPuja(idProducto, idComprador).toDTO();
    }

    public void anyadirPuja(Integer idProducto, Integer idUsuario, float nuevoValorPuja, ProductoDTO prod, CompradorDTO comprador, Date date) {
        Comprador c = this.cf.find(idUsuario);
        Producto p = this.pf.find(idProducto);

        Puja nuevaPuja = pjf.findPuja(prod.getIdProducto(), comprador.getIdUsuario());

        if (nuevaPuja == null) {
            nuevaPuja = new Puja(prod.getIdProducto(), comprador.getIdUsuario());

        }

        nuevaPuja.setPuja(nuevoValorPuja);
        nuevaPuja.setProducto(p);
        nuevaPuja.setComprador(c);
        nuevaPuja.setFecha(date);

        List<Puja> pujasComprador = c.getPujaList();
        pujasComprador.add(nuevaPuja);
        c.setPujaList(pujasComprador);
        cf.edit(c);

        List<Puja> pujasProducto = p.getPujaList();
        pujasProducto.add(nuevaPuja);
        p.setPujaList(pujasProducto);
        pf.edit(p);
    }

}
