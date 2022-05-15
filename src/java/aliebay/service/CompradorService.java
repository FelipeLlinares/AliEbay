/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aliebay.service;

import aliebay.dao.CompradorFacade;
import aliebay.dao.ListacompradorFacade;
import aliebay.dao.ProductoFacade;
import aliebay.dao.VentaFacade;
import aliebay.dto.CategoriaDTO;
import aliebay.dto.CompradorDTO;
import aliebay.dto.ListacompradorDTO;
import aliebay.dto.ProductoDTO;
import aliebay.dto.VentaDTO;
import aliebay.entity.Categoria;
import aliebay.entity.Comprador;
import aliebay.entity.Listacomprador;
import aliebay.entity.Producto;
import aliebay.entity.Venta;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Enrique Cañadas Cobo
 */
@Stateless
public class CompradorService {
    @EJB CompradorFacade cf; 
    @EJB ListacompradorFacade lcf;
    @EJB ProductoFacade pf;
    @EJB VentaFacade vf;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public List<CompradorDTO> listarComprador() {
        List<Comprador> compradores = cf.findAll();

        return this.listaEntityADTO(compradores);
    }

    private List<CompradorDTO> listaEntityADTO(List<Comprador> lista) {
        List<CompradorDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Comprador c : lista) {
                listaDTO.add(c.toDTO());
            }
        }
        return listaDTO;
    }

    public CompradorDTO buscarComprador(int comprador) {
        Comprador c = cf.find(comprador);
        return c.toDTO();
    }

    public void borrarComprador(int comprador) {
        Comprador c = this.cf.find(comprador);

        this.cf.remove(c);
    }

    public void crearComprador(int nueva) {
        Comprador c = new Comprador();
        c.setIdUsuario(nueva);

        this.cf.create(c);
    }

    public List<CompradorDTO> getCompradoresListaComprador(int idLista) {
        return this.listaEntityADTO(this.cf.getCompradoresListaComprador(idLista));
    }
    
    public void añadirLista(ListacompradorDTO lComprador, int id) {
        Comprador c = this.cf.find(id);
        List<Listacomprador> listasComprador = c.getListacompradorList();
        Listacomprador lc = lcf.find(lComprador.getIdLista());
        listasComprador.add(lc);
        c.setListacompradorList(listasComprador);
        this.cf.edit(c);
    }

    public void eliminarLista(ListacompradorDTO lComprador, Integer idUsuario) {
        Comprador c = this.cf.find(idUsuario);
        List<Listacomprador> listasComprador = c.getListacompradorList();
        Listacomprador lc = lcf.find(lComprador.getIdLista());
        listasComprador.remove(lc);
        c.setListacompradorList(listasComprador);
        this.cf.edit(c);
    }
   
    public void anyadirFavorito(Integer idUsuario, Integer producto) {
        Comprador c = this.cf.find(idUsuario);
        Producto p = this.pf.find(producto);

        List<Producto> productos = c.getProductoList();
        productos.add(p);
        c.setProductoList(productos);

        List<Comprador> compradores = p.getCompradorList();
        compradores.add(c);
        p.setCompradorList(compradores);
        pf.edit(p);

        this.cf.edit(c);
    }

    public void quitarFavorito(Integer idUsuario, Integer producto) {
        Comprador c = this.cf.find(idUsuario);
        Producto p = this.pf.find(producto);

        List<Producto> productos = c.getProductoList();
        productos.remove(p);
        c.setProductoList(productos);

        List<Comprador> compradores = p.getCompradorList();
        compradores.remove(c);
        p.setCompradorList(compradores);
        pf.edit(p);

        this.cf.edit(c);
    }
    
    public void quitarProducto(Integer idUsuario, Integer producto) {
        Comprador c = this.cf.find(idUsuario);
        Producto p = this.pf.find(producto);
        
        Venta venta = p.getVenta();
        
        List<Venta> ventas = c.getVentaList();
        ventas.remove(venta);
        c.setVentaList(ventas);
        cf.edit(c);
        
        vf.remove(venta);
        
        p.setPujaList(null);
        p.setVenta(null);
        p.setCompradorList(null);
        pf.edit(p);
    }
}
