<%-- 
    Document   : productosComprador
    Created on : 13 may. 2022, 20:28:20
    Author     : Enrique Cañadas Cobo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="aliebay.dto.VentaDTO"%>
<%@page import="aliebay.dto.ProductoDTO"%>
<%@page import="aliebay.dto.PujaDTO"%>
<%@page import="aliebay.dto.CategoriaDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Productos comprados</title>
    </head>
    <body>
        <%
            if (request.getAttribute("admin") == null){
        %>
        <jsp:include page="/WEB-INF/jsp/cabeceraFavoritos.jsp" />
        <h1>Mis productos</h1>
        
        <%
            } else {
        %>
        <jsp:include page="/WEB-INF/jsp/cabecera.jsp" />
        <h1>Productos del comprador</h1>
        <%
            }
        %>
        


        <%
            List<VentaDTO> ventas = (List)request.getAttribute("ventas");
            List<String> vendedores = (List)request.getAttribute("vendedores");
            if(ventas == null || ventas.isEmpty()) {
                
        %>
        <h2>No tiene ningún producto comprado</h2>
        <%
            } else {
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
                <th>Precio compra</th>
                <th></th><!--- Borrar --->
            </tr>
            <%
            
                for (int i=0; i < ventas.size(); i++) {
                    VentaDTO ve = ventas.get(i);
                    ProductoDTO pc = ve.getProducto();
                
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    String fechaTotal = sdf.format(ve.getFecha());
            %>
            <tr>
                <td><a href="verFotoServlet?url=<%= pc.getuRLFoto() %>">Ver Foto</a></td>
                <td><%= pc.getTitulo()%></td>
                <td><%= pc.getDescripcion()%></td>
                <td><%= vendedores.get(i)%></td>
                <td><%= pc.getCategoria().getIdCategoria()%></td>
                <td><%= fechaTotal%></td>
                <td><%= pc.getPrecioSalida()%> €</td>
                <td><%= ve.getPrecioVenta()%> €</td>
                <td><a href="ProductosBorrarServlet?producto=<%= pc.getIdProducto()%>&comprador=<%=ve.getComprador().getIdUsuario()%>"> Borrar </a></td>
            </tr>
            <%
                    }
                }
            %>
    </body>
</html>
