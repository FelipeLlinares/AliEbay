/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aliebay.dao;

import aliebay.entity.Usuario;
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
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "AliEbayPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    public Usuario comprobarUsuario(String strusuario, String strpassword){
        Query q;
        
        q = this.getEntityManager().createQuery("SELECT u FROM Usuario u where u.userName = :usuario and u.password = :password");
        
        q.setParameter("usuario",strusuario);
        q.setParameter("password",strpassword);
        
        List<Usuario> usuarios = q.getResultList();
        
        if(usuarios == null || usuarios.isEmpty()){
            return null;
        }else{
            return usuarios.get(0);
        }
    }
}
