<%-- 
    Document   : favoritosComprador
    Created on : 13 may. 2022, 19:43:30
    Author     : Enrique Cañadas Cobo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="aliebay.entity.Producto"%>
<%@page import="aliebay.entity.Puja"%>
<%@page import="aliebay.entity.Categoria"%>
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
            List<Producto> productosFavoritos = (List)request.getAttribute("productos");
            if(productosFavoritos == null || productosFavoritos.isEmpty()) {
                
        %>
            <h2>No tienes ningún favorito</h2>
        <%
            } else {
        %>
        
        <h2>Productos cuya puja es tuya</h2>
        <%
                List<Producto> productos = (List)request.getAttribute("productosPujadosPorComprador");
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
                
                for (int i=0; i < productos.size(); i++) {
                    Producto pc = productos.get(i);
                
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    String fechaTotal = "Desde " + sdf.format(pc.getFechaSalida()) + " hasta " + sdf.format(pc.getFechaFin());
                
                    String pujaUltima = "No hay pujas";
                    List<Puja> pujas = pc.getPujaList();
                    if(pujas != null && !pujas.isEmpty()) {
                        pujaUltima = String.valueOf(pujas.get(pujas.size() - 1).getPuja()) + " €";
                    }
            %>  
            <tr>
                <td><img src="<%= pc.getURLFoto()%>" width="50px" height="50px"></td>
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
                for (int i=0; i < productos.size(); i++) {
                    Producto pc = productos.get(i);
                
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    String fechaTotal = "Desde " + sdf.format(pc.getFechaSalida()) + " hasta " + sdf.format(pc.getFechaFin());
                
                    String pujaUltima = "No hay pujas";
                    List<Puja> pujas = pc.getPujaList();
                    if(pujas != null && !pujas.isEmpty()) {
                        pujaUltima = String.valueOf(pujas.get(pujas.size() - 1).getPuja()) + " €";
                    }
            %>  
            <tr>
                <td><img src="<%= pc.getURLFoto()%>" width="50px" height="50px"></td>
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
