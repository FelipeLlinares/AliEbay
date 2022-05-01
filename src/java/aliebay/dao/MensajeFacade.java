/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aliebay.dao;

import aliebay.entity.Mensaje;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.List;

/**
 *
 * @author felip
 */
@jakarta.ejb.Stateless
public class MensajeFacade extends AbstractFacade<Mensaje> {

    @PersistenceContext(unitName = "AliEbayPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MensajeFacade() {
        super(Mensaje.class);
    }
    
    public List<Mensaje> mensajesListaComprador (int idLista){
        Query q;
        
        q = this.getEntityManager().createQuery("SELECT m FROM "
                                            + "Mensaje m WHERE m.mensajePK.idListaComprador = :idListaComprador");
        
      q.setParameter("idListaComprador", idLista);
      return q.getResultList();
                
    }
    
}
