/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aliebay.dao;

import aliebay.entity.Puja;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.List;
/**
 *
 * @author Enrique Cañadas Cobo
 */
@jakarta.ejb.Stateless
public class PujaFacade extends AbstractFacade<Puja> {

    @PersistenceContext(unitName = "AliEbayPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PujaFacade() {
        super(Puja.class);
    }
    
    public Puja findPuja(int idProducto, int idComprador) {
        Query q;
        
        q = this.getEntityManager().createQuery("SELECT p FROM Puja p WHERE p.producto.idProducto = :idP AND p.comprador.idUsuario = :idC ");
        q.setParameter("idP", idProducto);
        q.setParameter("idC", idComprador);
        
        List<Puja> pujas = q.getResultList();
        
        if(pujas == null || pujas.isEmpty()) {
            return null;
        } else {
            return pujas.get(0);
        }
    }
}
