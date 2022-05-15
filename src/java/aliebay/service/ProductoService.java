/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aliebay.service;

import aliebay.dao.CategoriaFacade;
import aliebay.dao.ProductoFacade;
import aliebay.dto.CategoriaDTO;
import aliebay.dto.ProductoDTO;
import aliebay.dto.PujaDTO;
import aliebay.entity.Categoria;
import aliebay.entity.Producto;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author felip
 */

@Stateless
public class ProductoService {
    @EJB ProductoFacade pf;
    @EJB CategoriaFacade cf;
    @EJB PujaService ps;
    
    private List<ProductoDTO> listaEntityADTO (List<Producto> lista) {
        List<ProductoDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Producto p:lista) {
                listaDTO.add(p.toDTO());
            }
        }
        return listaDTO;
    }
    
    public List<ProductoDTO> listarProductos () {
        List<Producto> productos = this.pf.findAll();        

        return this.listaEntityADTO(productos);                
    } 
    
    public ProductoDTO buscarCliente (Integer id) {
        Producto p = this.pf.find(id);
        return p.toDTO();
    }
    
    public void borrarCliente (Integer id) {
        Producto p = this.pf.find(id);

        this.pf.remove(p);        
    }

    public List<ProductoDTO> getProductosPorNombreYCategoria(String nombreFiltro, CategoriaDTO categ) {
        Categoria categoria = cf.find(categ.getIdCategoria());
        List<Producto> lista = pf.getProductosPorNombreYCategoria(nombreFiltro, categoria);
        
        return this.listaEntityADTO(lista);
    }

    public List<ProductoDTO> getProductosDisponiblesPorNombre(String nombreFiltro) {
        return this.listaEntityADTO(pf.getProductosDisponiblesPorNombre(nombreFiltro));
    }

    public List<ProductoDTO> getProductosPorCategoria(CategoriaDTO categ) {
        Categoria categoria = cf.find(categ.getIdCategoria());
        return this.listaEntityADTO(pf.getProductosPorCategoria(categoria));
    }

    public List<ProductoDTO> getProductosDisponibles() {
        return this.listaEntityADTO(pf.getProductosDisponibles());
    }

    public List<PujaDTO> getPujaList(ProductoDTO pr) {
        Producto p = pf.find(pr.getIdProducto());
        return ps.listaEntityADTO(p.getPujaList());
    }
}
