/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aliebay.service;

import aliebay.dao.CompradorFacade;
import aliebay.dao.ListacompradorFacade;
import aliebay.dto.CompradorDTO;
import aliebay.dto.ListacompradorDTO;
import aliebay.entity.Comprador;
import aliebay.entity.Listacomprador;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jose Maria Tapia Catena
 */
@Stateless
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
        List<Comprador> compradores = lc.getCompradorList();
            for (Comprador c : compradores){
                List<Listacomprador> lista = c.getListacompradorList();
                lista.remove(lc);
                c.setListacompradorList(lista);
                cf.edit(c);
            }
            
        this.listacompradorf.remove(lc);        
    }

    public ListacompradorDTO crear(String nombre) {
        Listacomprador lc = new Listacomprador();
        lc.setNombre(nombre);
        lc.setCompradorList(new ArrayList());
        lc.setMensajeList(new ArrayList());
        this.listacompradorf.create(lc);
        
        this.listacompradorf.count();
        
        return lc.toDTO();
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

    public void añadirComprador(ListacompradorDTO ldto, int compradorID) {
        Listacomprador lc = listacompradorf.find(ldto.getIdLista());
        Comprador c = cf.find(compradorID);
        List<Comprador> compradores = lc.getCompradorList();
        compradores.add(c);
        lc.setCompradorList(compradores);
       
        listacompradorf.edit(lc);
    }

    public void editar(Integer idLista, String nombre, List<CompradorDTO> c) {
        Listacomprador lc = listacompradorf.find(idLista);
        List<Comprador> compradoresParaLista = new ArrayList();
        
        for (CompradorDTO comprador : c){
            Comprador comp = this.cf.find(comprador.getIdUsuario());
            compradoresParaLista.add(comp);
        }
        lc.setCompradorList(compradoresParaLista);
        this.listacompradorf.edit(lc);
    }
    
}
