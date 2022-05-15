/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aliebay.service;

import aliebay.dao.CompradorFacade;
import aliebay.dao.ListacompradorFacade;
import aliebay.dto.ListacompradorDTO;
import aliebay.entity.Comprador;
import aliebay.entity.Listacomprador;
import jakarta.ejb.EJB;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jose Maria Tapia Catena
 */
public class ListacompradorService {
    
    @EJB ListacompradorFacade listacompradorf;
    @EJB CompradorFacade cf;
    
    public List<ListacompradorDTO> listarListaComprador(){
        List<Listacomprador> listaCompradores = listacompradorf.findAll();
        
        return this.listaEntityADTO(listaCompradores);
    }
    
    public List<ListacompradorDTO> listaEntityADTO (List<Listacomprador> lista) {
        List<ListacompradorDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Listacomprador m : lista) {
                listaDTO.add(m.toDTO());
            }
        }
        return listaDTO;
    }
    
    public ListacompradorDTO buscarListacomprador(int listaComprador){
        Listacomprador lc = this.listacompradorf.find(listaComprador);
        return lc.toDTO();
    }
    
    public void borrarListacomprador(int listaComprador) {
        Listacomprador lc = this.listacompradorf.find(listaComprador);

        this.listacompradorf.remove(lc);        
    }

    public void crear(String nombre) {
        Listacomprador lc = new Listacomprador();
        lc.setNombre(nombre);
        this.listacompradorf.create(lc);
    }

    public void editar(Integer idLista, String nombre) {
        Listacomprador lc = this.listacompradorf.find(idLista);
        lc.setNombre(nombre);
        this.listacompradorf.edit(lc);
    }
    
    public List<ListacompradorDTO> getListListaComprador(int idComprador) {
        Comprador c = cf.find(idComprador);
        return this.listaEntityADTO(c.getListacompradorList());
    }
    
}
