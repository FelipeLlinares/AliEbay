/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aliebay.dao;

import aliebay.entity.Listacomprador;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 *
 * @author felip
 */
@Stateless
public class ListacompradorFacade extends AbstractFacade<Listacomprador> {

    @PersistenceContext(unitName = "AliEbayPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ListacompradorFacade() {
        super(Listacomprador.class);
    }
    
}
