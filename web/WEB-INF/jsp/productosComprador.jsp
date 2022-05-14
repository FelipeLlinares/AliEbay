<%-- 
    Document   : productosComprador
    Created on : 13 may. 2022, 20:28:20
    Author     : Enrique Cañadas Cobo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="aliebay.entity.Venta"%>
<%@page import="aliebay.entity.Producto"%>
<%@page import="aliebay.entity.Puja"%>
<%@page import="aliebay.entity.Categoria"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Productos comprados</title>
    </head>
    <body>
        <jsp:include page="/WEB-INF/jsp/cabeceraFavoritos.jsp" />
        <h1>Mis productos</h1>
        
        <%
            List<Venta> ventas = (List)request.getAttribute("ventas");
            List<String> vendedores = (List)request.getAttribute("vendedores");
            if(ventas == null || ventas.isEmpty()) {
                
        %>
            <h2>No tienes ningún producto comprado</h2>
        <%
            } else {
        %>
        
        <table border="1" width="90%">
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
                <th></th><!--- Editar --->
            </tr>
        <%
            
            for (int i=0; i < ventas.size(); i++) {
                Venta ve = ventas.get(i);
                Producto pc = ve.getProducto();
                
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                String fechaTotal = sdf.format(ve.getFecha());
                
                String pujaUltima = "No hay pujas";
                List<Puja> pujas = pc.getPujaList();
                if(pujas != null && !pujas.isEmpty()) {
                    pujaUltima = String.valueOf(pujas.get(pujas.size() - 1).getPuja());
                }
        %>
        <tr>
            <td style="text-align:center"><img src="<%= pc.getURLFoto()%>" width="50px" height="50px"></td>
            <td style="text-align:center"><%= pc.getTitulo()%></td>
            <td style="text-align:center"><%= pc.getDescripcion()%></td>
            <td style="text-align:center"><%= vendedores.get(i)%></td>
            <td style="text-align:center"><%= pc.getCategoria().getIdCategoria()%></td>
            <td style="text-align:center"><%= fechaTotal%></td>
            <td style="text-align:center"><%= pc.getPrecioSalida()%> €</td>
            <td style="text-align:center"><%= ve.getPrecioVenta()%> €</td>
            <td style="text-align:center"><a href="ProductosBorrarServlet?producto=<%= pc.getIdProducto()%>"> Borrar </a></td>
            <td style="text-align:center"><a href="ProductosEditarServlet?producto=<%= pc.getIdProducto()%>"> Editar </a></td>
        </tr>
        <%
                }
            }
        %>
    </body>
</html>
