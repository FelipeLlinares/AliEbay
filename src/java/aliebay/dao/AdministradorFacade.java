/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aliebay.dao;

import aliebay.entity.Administrador;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 *
 * @author felip
 */
@jakarta.ejb.Stateless
public class AdministradorFacade extends AbstractFacade<Administrador> {

    @PersistenceContext(unitName = "AliEbayPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdministradorFacade() {
        super(Administrador.class);
    }
    
}
