/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aliebay.dao;

import aliebay.entity.Categoria;
import aliebay.entity.Producto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Enrique Cañadas Cobo
 */
@jakarta.ejb.Stateless
public class ProductoFacade extends AbstractFacade<Producto> {

    @PersistenceContext(unitName = "AliEbayPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductoFacade() {
        super(Producto.class);
    }

    public List<Producto> getProductos(int id){
        Query q;
        q = this.getEntityManager().createNamedQuery("Producto.findByIdVendedor");
        q.setParameter("idVendedor",id);

        return q.getResultList();
    }

    public List<Producto> getProductosDisponibles() {
        Query q;
        
        Date date = new Date();
        
        q = this.getEntityManager().createQuery("SELECT p FROM Producto p WHERE p.fechaFin >= :fechaAhora ORDER BY p.fechaFin");
        q.setParameter("fechaAhora", date);
        
        return q.getResultList();
    }
    
    public List<Producto> getProductosDisponiblesPorNombre(String nombre) {
        Query q;
        
        Date date = new Date();

        
        q = this.getEntityManager().createQuery("SELECT p FROM Producto p WHERE p.fechaFin >= :fechaAhora AND p.titulo LIKE '%" + nombre + "%' ORDER BY p.fechaFin ");
        q.setParameter("fechaAhora", date);
        
        return q.getResultList();
    }
    
    public List<Producto> getProductosPorCategoria(Categoria categoria){
        Query q;
        
        Date date = new Date();
        
        q = this.getEntityManager().createQuery("SELECT p FROM Producto p WHERE p.fechaFin >= :fechaAhora AND p.categoria = :categoria");
        q.setParameter("categoria", categoria);
        q.setParameter("fechaAhora", date);
        
        return q.getResultList();
    }
    
    public List<Producto> getTodosProductosPorCategoria(Categoria categoria){
        Query q;

        q = this.getEntityManager().createQuery("SELECT p FROM Producto p WHERE p.categoria = :categoria");
        q.setParameter("categoria", categoria);
        
        return q.getResultList();
    }
    
    public List<Producto> getProductosPorNombreYCategoria(String nombreFiltro, Categoria categ) {
        Query q;
        
        Date date = new Date();
        
        q = this.getEntityManager().createQuery("SELECT p FROM Producto p WHERE p.fechaFin >= :fechaAhora AND p.titulo LIKE '%" + nombreFiltro + "%' AND p.categoria = :categoria");
        q.setParameter("categoria", categ);
        q.setParameter("fechaAhora", date);
        
        return q.getResultList();    }

    public List<Producto> getProductosVendidos() {
        Query q;
        
        Date date = new Date();
        
        q = this.getEntityManager().createQuery("SELECT p FROM Producto p WHERE p.fechaFin < :fechaAhora AND p.venta NOT IN (SELECT v FROM Venta v)");
        q.setParameter("fechaAhora", date);
        
        return q.getResultList();
    }
}
