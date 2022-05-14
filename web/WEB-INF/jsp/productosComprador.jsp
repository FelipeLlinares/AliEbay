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
        <%
            if (request.getAttribute("admin") != null){
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
            List<Venta> ventas = (List)request.getAttribute("ventas");
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
                <td><a href="verFotoServlet?url=<%= pc.getURLFoto() %>">Ver Foto</a></td>
                <td><%= pc.getTitulo()%></td>
                <td><%= pc.getDescripcion()%></td>
                <td><%= vendedores.get(i)%></td>
                <td><%= pc.getCategoria().getIdCategoria()%></td>
                <td><%= fechaTotal%></td>
                <td><%= pc.getPrecioSalida()%> €</td>
                <td><%= ve.getPrecioVenta()%> €</td>
                <td><a href="ProductosBorrarServlet?producto=<%= pc.getIdProducto()%>"> Borrar </a></td>
                <td><a href="ProductosEditarServlet?producto=<%= pc.getIdProducto()%>"> Editar </a></td>
            </tr>
            <%
                    }
                }
            %>
    </body>
</html>
