/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aliebay.dao;

import aliebay.entity.Producto;
import aliebay.entity.Usuario;
import aliebay.entity.Vendedor;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.List;

/**
 *
 * @author felip
 */
@Stateless
public class VendedorFacade extends AbstractFacade<Vendedor> {

    @PersistenceContext(unitName = "AliEbayPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VendedorFacade() {
        super(Vendedor.class);
    }
    
    public List<Producto> getProductos(Vendedor vendedor) {
        Query q;
        
        q = this.getEntityManager().createQuery("SELECT p FROM Producto p where p.idVendedor = :idVendedor");
        
        q.setParameter("idVendedor",vendedor);
      
        
        List<Producto> productos = q.getResultList();
        
        if(productos == null || productos.isEmpty()){
            return null;
        }else{
            return productos;
        }
    }
}
