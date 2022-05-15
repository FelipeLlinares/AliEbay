/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aliebay.service;

import aliebay.dao.CategoriaFacade;
import aliebay.dto.CategoriaDTO;
import aliebay.entity.Categoria;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author felip
 */

@Stateless
public class CategoriaService {
    @EJB CategoriaFacade cf; 
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public List<CategoriaDTO> listarCategorias(){
        List<Categoria> categoria = cf.findAll();
        
        return this.listaEntityADTO(categoria);
    }
    
    private List<CategoriaDTO> listaEntityADTO (List<Categoria> lista) {
        List<CategoriaDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Categoria c:lista) {
                listaDTO.add(c.toDTO());
            }
        }
        return listaDTO;
    }
    
    public CategoriaDTO buscarCategoria(String categoria){
        Categoria cat = cf.find(categoria);
        return cat.toDTO();
    }
    
    public void borrarCategoria (String categoria) {
        Categoria cat = this.cf.find(categoria);

        this.cf.remove(cat);        
    }
    
    public void crearCategoria(String nueva) {
        Categoria cat = new Categoria();
        cat.setIdCategoria(nueva);

        this.cf.create(cat);
    }
    /*
    public List<CustomerDTO> listarClientes (String filtroNombre) {
        List<Customer> clientes = null;

        if (filtroNombre == null || filtroNombre.isEmpty()) {
            clientes = this.cf.findAll();        
        } else {
            clientes = this.cf.findByNombre(filtroNombre);
        }
        
        return this.listaEntityADTO(clientes);                
    } 
    
    public CustomerDTO buscarCliente (Integer id) {
        Customer cliente = this.cf.find(id);
        return cliente.toDTO();
    }
    
    public void borrarCliente (Integer id) {
        Customer customer = this.cf.find(id);

        this.cf.remove(customer);        
    }
    
    
    private void rellenarCliente (Customer cliente,
                              String nombre, String domicilio1, String domicilio2, String email, 
                              String ciudad, String estado, String telefono, String fax, 
                              Integer credito, String supermercadoCP, String descuento) {
        
        cliente.setName(nombre);

        cliente.setAddressline1(domicilio1);
        cliente.setAddressline2(domicilio2);
        cliente.setEmail(email);
        cliente.setCity(ciudad);
        cliente.setState(estado);
        cliente.setPhone(telefono);
        cliente.setFax(fax);
        cliente.setCreditLimit(credito);

        MicroMarket mm = this.mmf.find(supermercadoCP);
        cliente.setZip(mm);

        DiscountCode dc = this.dcf.find(descuento);
        cliente.setDiscountCode(dc);                
    }
    
    public void crearCliente (String nombre, String domicilio1, String domicilio2, String email, 
                              String ciudad, String estado, String telefono, String fax, 
                              Integer credito, String supermercadoCP, String descuento) {
        Customer cliente = new Customer();

        this.rellenarCliente(cliente, nombre, domicilio1, domicilio2, email, ciudad, 
                             estado, telefono, fax, credito, supermercadoCP, descuento);

        this.cf.create(cliente);
    }

    public void modificarCliente (Integer id,
                              String nombre, String domicilio1, String domicilio2, String email, 
                              String ciudad, String estado, String telefono, String fax, 
                              Integer credito, String supermercadoCP, String descuento) {
        
        Customer cliente = this.cf.find(id);

        this.rellenarCliente(cliente, nombre, domicilio1, domicilio2, email, ciudad, 
                             estado, telefono, fax, credito, supermercadoCP, descuento);

        this.cf.edit(cliente);
    }
    
   */

    
}
