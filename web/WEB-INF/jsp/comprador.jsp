<%-- 
    Document   : comprador
    Created on : 25 abr 2022, 11:09:26
    Author     : Enrique Cañadas Cobo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="aliebay.dto.ProductoDTO"%>
<%@page import="aliebay.dto.PujaDTO"%>
<%@page import="aliebay.dto.CategoriaDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="aliebay.dto.UsuarioDTO"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Comprador</title>
    </head>
    <%
        String strError = (String)request.getAttribute("error");
        if (strError == null) strError = "";
    %>  
    <body>
        <jsp:include page="/WEB-INF/jsp/cabeceraComprador.jsp" />
        <h1>Productos disponibles</h1>
        <%= strError%><br/>

        <%
            List<CategoriaDTO> categorias = (List) request.getAttribute("categorias");
            
        %>
        
        <form action="CompradorServlet" method="POST">
            <table>
                <tr>
                    <td>Buscar por nombre: <input type="text" id="filtro" name="filtro"></td>
                    <%
                        if(categorias != null && !categorias.isEmpty()) {
                    %>
                    <td>
                        <label for="categorias">Categoría:</label>
                        <select name="categorias" id="categorias">
                            <option value="" selected disabled hidden></option>
                        <%
                            for(CategoriaDTO c : categorias) {
                        %>
                                <option value="<%= c.getIdCategoria()%>"><%= c.getIdCategoria()%></option>
                        <%
                            }
                        %>
                        </select>
                    </td>
                    <%
                        }
                    %>
                    <td><input type="submit" value="Aplicar filtros" /></td>
                </tr>
            </table>
        </form>
        </br>

        <%
            String filtradoPor = (String) request.getAttribute("filtrado");
            if(filtradoPor != null && !filtradoPor.isEmpty()) {
        %>
        <h4>Filtrado <%= filtradoPor%></h4>
        <%
            }
        %>
        <h2>Productos cuya puja es tuya</h2>
        <%
                List<ProductoDTO> productos = (List)request.getAttribute("productosPujadosPorComprador");
                List<String> vendedores = (List) request.getAttribute("nombresVendedoresPujados");
            
                UsuarioDTO user = (UsuarioDTO) session.getAttribute("usuario");
                List<ProductoDTO> productosFavoritos = (List) request.getAttribute("favoritos");
            
                if(productos != null && !productos.isEmpty()) {
        %>
        
        <table border="1" width="90%" style="text-align:center">
            <tr>
                <th>Foto</th>
                <th>Título</th>
                <th>Descripción</th>
                <th>Vendedor</th>
                <th>Categoría</th>
                <th>Fecha</th>
                <th>Precio salida</th>
                <th>Puja actual</th>
                <th></th><!--- Nueva puja --->
                <th></th><!--- Favoritos --->
            </tr>
            <%
                
                List<Float> pujas = (List) request.getAttribute("mayoresPujasVendidos");
                for (int i=0; i < productos.size(); i++) {
                    ProductoDTO pc = productos.get(i);
                
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    String fechaTotal = "Desde " + sdf.format(pc.getFechaSalida()) + " hasta " + sdf.format(pc.getFechaFin());
                
                    String pujaUltima = "No hay pujas";
                    if(pujas.get(i) != 0) {
                        pujaUltima = String.valueOf(pujas.get(i)) + " €";
                    }
            %>  
            <tr>
                <td><a href="verFotoServlet?url=<%= pc.getuRLFoto() %>">Ver Foto</a></td>
                <td><%= pc.getTitulo()%></td>
                <td><%= pc.getDescripcion()%></td>
                <td><%= vendedores.get(i)%></td>
                <td><%= pc.getCategoria().getIdCategoria()%></td>
                <td><%= fechaTotal%></td>
                <td><%= pc.getPrecioSalida()%> €</td>
                <td><%= pujaUltima%></td>
                <td><a href="PujaServlet?producto=<%= pc.getIdProducto()%>">  Pujar  </td>
                <%
                    if(productosFavoritos.contains(pc)) {
                %>
                <td>Ya es favorito</td>
                <%
                    } else {
                %>
                <td><a href="CompradorGuardarFavorito?producto=<%= pc.getIdProducto()%>"> Añadir favorito </td>
                <%
                    }
                %>
            </tr>
            <%
                }
            %>            
        </table>
        
        <%
            } else {
        %>
        <h3>No tienes ninguna puja activa</h3>
        <%
            }
        %>
        <h2>Productos no pujados por ti</h2>
        
        <%
                productos = (List)request.getAttribute("productosNoPujadosPorComprador");
                vendedores = (List) request.getAttribute("nombresVendedoresNoPujados");
            
                if(productos != null && !productos.isEmpty()) {
        %>
        
        <table border="1" width="90%" style="text-align:center">
            <tr>
                <th>Foto</th>
                <th>Título</th>
                <th>Descripción</th>
                <th>Vendedor</th>
                <th>Categoría</th>
                <th>Fecha</th>
                <th>Precio salida</th>
                <th>Puja actual</th>
                <th></th><!--- Nueva puja --->
                <th></th><!--- Favoritos --->
            </tr>
            <%
                productos = (List)request.getAttribute("productosNoPujadosPorComprador");
                vendedores = (List) request.getAttribute("nombresVendedoresNoPujados");
                List<Float> pujas = (List) request.getAttribute("mayoresPujasNoVendidos");
                for (int i=0; i < productos.size(); i++) {
                    ProductoDTO pc = productos.get(i);
                
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    String fechaTotal = "Desde " + sdf.format(pc.getFechaSalida()) + " hasta " + sdf.format(pc.getFechaFin());
                
                    String pujaUltima = "No hay pujas";
                    if(pujas.get(i) != 0) {
                        pujaUltima = String.valueOf(pujas.get(i)) + " €";
                    }
            %>  
            <tr>
                <td><a href="verFotoServlet?url=<%= pc.getuRLFoto() %>">Ver Foto</a></td>
                <td><%= pc.getTitulo()%></td>
                <td><%= pc.getDescripcion()%></td>
                <td><%= vendedores.get(i)%></td>
                <td><%= pc.getCategoria().getIdCategoria()%></td>
                <td><%= fechaTotal%></td>
                <td><%= pc.getPrecioSalida()%> €</td>
                <td><%= pujaUltima%></td>
                <td><a href="PujaServlet?producto=<%= pc.getIdProducto()%>">  Pujar  </td>
                <%
                    if(productosFavoritos.contains(pc)) {
                %>
                <td>Ya es favorito</td>
                <%
                    } else {
                %>
                <td><a href="CompradorGuardarFavorito?producto=<%= pc.getIdProducto()%>"> Añadir favorito </td>
                <%
                    }
                %>
            </tr>
            <%
                }
            %>            
        </table>
        
        <%
            } else {
        %>
        <h3>No hay productos disponibles</h3>
        <%
            }
        %>

    </body>
</html>
