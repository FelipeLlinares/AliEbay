/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aliebay.dao;

import aliebay.entity.Comprador;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.List;

/**
 *
 * @author felip
 */
@jakarta.ejb.Stateless
public class CompradorFacade extends AbstractFacade<Comprador> {

    @PersistenceContext(unitName = "AliEbayPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CompradorFacade() {
        super(Comprador.class);
    }
    
    
    public List<Comprador> getCompradoresListaComprador(int idLista) {
      Query q;

      q = this.getEntityManager().createQuery("select c from Comprador c join c.listacompradorList lc "
                                                + "where lc.idLista= :id");

      q.setParameter("id", idLista);
      return q.getResultList();
    }
}
