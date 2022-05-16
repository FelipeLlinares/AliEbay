/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aliebay.dao;

import aliebay.entity.Administrador;
import aliebay.entity.Comprador;
import aliebay.entity.Marketing;
import aliebay.entity.Usuario;
import aliebay.entity.Vendedor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.List;

/**
 *
 * @author felip
 */
@jakarta.ejb.Stateless
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
    
    public String getTipoUsuario (Usuario user){
        int idUsuario = user.getIdUsuario();
        Query q;
        String result="";
        boolean encontrado = false;
        
        q = this.getEntityManager().createQuery("Select a From Administrador a where a.idUsuario = :idUsuario");
        q.setParameter("idUsuario", idUsuario);
        List<Administrador> admin = q.getResultList();
        if (admin != null && !admin.isEmpty()){
            encontrado = true;
            result = "Admin";
        }
        
        if(!encontrado){
       
            q = this.getEntityManager().createQuery("Select m From Marketing m where m.idUsuario = :idUsuario");
            q.setParameter("idUsuario", idUsuario);
            List<Marketing> marketing = q.getResultList();
            if (marketing != null && !marketing.isEmpty()){
                encontrado = true;
                result = "Marketing";
            }
            
            if (!encontrado){
                q = this.getEntityManager().createQuery("Select c From Comprador c where c.idUsuario = :idUsuario");
                q.setParameter("idUsuario", idUsuario);
                List<Comprador> comprador = q.getResultList();
                if (comprador != null && !comprador.isEmpty()){
                    result = "Comprador";
                    encontrado = true;
                }
                
                if (!encontrado){
                    q = this.getEntityManager().createQuery("Select v From Vendedor v where v.idUsuario = :idUsuario");
                    q.setParameter("idUsuario", idUsuario);
                    List<Vendedor> vendedor = q.getResultList();
                    if (vendedor != null && !vendedor.isEmpty()){
                        result = "Vendedor";
                    }
                }
            }
        }
        return result;
    }
    
    public Usuario getUsuarioPorUserName(String username){
        Query q;
        q = this.getEntityManager().createNamedQuery("Usuario.findByUserName");
        q.setParameter("userName", username);
        
        return (Usuario)q.getResultList().get(0);
    }
    
}
