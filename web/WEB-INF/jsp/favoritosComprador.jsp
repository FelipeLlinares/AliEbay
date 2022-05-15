<%-- 
    Document   : favoritosComprador
    Created on : 13 may. 2022, 19:43:30
    Author     : Enrique Cañadas Cobo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="aliebay.dto.ProductoDTO"%>
<%@page import="aliebay.dto.PujaDTO"%>
<%@page import="aliebay.dto.CategoriaDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Favoritos</title>
    </head>
    <body>
        
        <%
        String strError = (String)request.getAttribute("error");
        if (strError == null) strError = "";
    %>  
    <body>
        <jsp:include page="/WEB-INF/jsp/cabeceraFavoritos.jsp" />
        <h1>Favoritos</h1>
        <%= strError%><br/>
        
        
        <%
            List<ProductoDTO> productosFavoritos = (List)request.getAttribute("productos");
            if(productosFavoritos == null || productosFavoritos.isEmpty()) {
                
        %>
            <h2>No tienes ningún favorito</h2>
        <%
            } else {
        %>
        
        <h2>Productos cuya puja es tuya</h2>
        <%
                List<ProductoDTO> productos = (List)request.getAttribute("productosPujadosPorComprador");
                List<String> vendedores = (List) request.getAttribute("nombresVendedoresPujados");
            
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
                <td><a href="CompradorBorrarFavoritoServlet?producto=<%= pc.getIdProducto()%>"> Quitar favorito </td>
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
                <td><a href="CompradorBorrarFavoritoServlet?producto=<%= pc.getIdProducto()%>"> Quitar favorito </td>
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
            }
        %>
    </body>
</html>
