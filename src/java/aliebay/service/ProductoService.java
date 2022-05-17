/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aliebay.service;

import aliebay.dao.CategoriaFacade;
import aliebay.dao.CompradorFacade;
import aliebay.dao.ProductoFacade;
import aliebay.dao.VendedorFacade;
import aliebay.dao.VentaFacade;
import aliebay.dto.CategoriaDTO;
import aliebay.dto.ProductoDTO;
import aliebay.dto.PujaDTO;
import aliebay.entity.Categoria;
import aliebay.entity.Comprador;
import aliebay.entity.Producto;
import aliebay.entity.Venta;
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
public class ProductoService {
    @EJB ProductoFacade pf;
    @EJB CategoriaFacade cf;
    @EJB PujaService ps;
    @EJB CompradorFacade comf;
    @EJB VendedorFacade venf;
    @EJB VentaFacade vf;
    
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
    
    public ProductoDTO buscarProducto (Integer id) {
        Producto p = this.pf.find(id);
        if ( p != null ){
            return p.toDTO();
        }
        return null;
    }
    
    public void borrarProducto (Integer id) {
        Producto p = this.pf.find(id);
        
        if(p.getVenta() != null){
            Comprador c = p.getVenta().getComprador();
            List<Venta> ventas = c.getVentaList();
            ventas.remove(p.getVenta());
            c.setVentaList(ventas);
            this.comf.edit(c);
            
            Venta v = p.getVenta();
            this.vf.remove(v);
            p.setVenta(null);  
            
        }
        
        for(Comprador c: p.getCompradorList()){
            List<Producto> prods = c.getProductoList();
            prods.remove(p);
            c.setProductoList(prods);
            this.comf.edit(c);
        }

            
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

    public List<ProductoDTO> getProductosPorCategoria(String categ) {
        Categoria categoria = cf.find(categ);
        return this.listaEntityADTO(pf.getProductosPorCategoria(categoria));
    }

    public List<ProductoDTO> getTodosProductosPorCategoria(String categ) {
        Categoria categoria = cf.find(categ);
        return this.listaEntityADTO(pf.getTodosProductosPorCategoria(categoria));
    }
    
    public List<ProductoDTO> getProductosDisponibles() {
        return this.listaEntityADTO(pf.getProductosDisponibles());
    }

    public List<PujaDTO> getPujaList(ProductoDTO pr) {
        Producto p = pf.find(pr.getIdProducto());
        return ps.listaEntityADTO(p.getPujaList());
    }

    public List<ProductoDTO> getProductosVendidos() {
        return this.listaEntityADTO(pf.getProductosVendidos());
    }
    
    public List<ProductoDTO> getProductosVendedor(int id){
        return this.listaEntityADTO(pf.getProductos(id));
    }

    public void crearProducto(String titulo, String descripcion, Float precioinicio, String urlFoto, Date fechaSalida, Date fechafinal, String categoria,String vendedor) {
        Producto p = new Producto();
        p.setCategoria(cf.find(categoria));
        p.setDescripcion(descripcion);
        p.setFechaFin(fechafinal);
        p.setFechaSalida(fechaSalida);
        p.setURLFoto(urlFoto);
        p.setTitulo(titulo);
        p.setPrecioSalida(precioinicio);
        p.setIdVendedor(Integer.parseInt(vendedor));
        pf.create(p);
        
        Categoria cat = cf.find(categoria);
        List<Producto> prods = cat.getProductoList();
        prods.add(p);
        cat.setProductoList(prods);

        cf.edit(cat);
        
    }
    
    public List<ProductoDTO> getProductosFavoritos(int id) {
        Comprador c = comf.find(id);
        return this.listaEntityADTO(c.getProductoList());
    }

    public void editarProducto(int idproducto, String titulo, String descripcion, Float precioinicio, String urlFoto, Date fechaSalida, Date fechafinal, String categoria, String vendedor) {
        Producto p = pf.find(idproducto);
        p.setCategoria(cf.find(categoria));
        p.setDescripcion(descripcion);
        p.setFechaFin(fechafinal);
        p.setFechaSalida(fechaSalida);
        p.setURLFoto(urlFoto);
        p.setTitulo(titulo);
        p.setPrecioSalida(precioinicio);
        p.setIdVendedor(Integer.parseInt(vendedor));
        pf.edit(p);
        
        Categoria cat = cf.find(categoria);
        List<Producto> prods = cat.getProductoList();
        prods.add(p);
        cat.setProductoList(prods);

        cf.edit(cat);
    }

    public ProductoDTO getVenta(ProductoDTO p) {
        Producto producto = pf.find(p.getIdProducto());
        if(producto.getVenta() != null)
            p.setVenta(producto.getVenta().toDTO());
            
        return p;
        
    }
}