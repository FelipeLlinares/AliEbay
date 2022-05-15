/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aliebay.dto;

import aliebay.entity.Producto;
import jakarta.persistence.OneToMany;
import java.util.List;

/**
 *
 * @author felip
 */
public class CategoriaDTO {
    private String idCategoria; 
    private List<ProductoDTO> productoList;
}
