<%-- 
    Document   : vendedor
    Created on : 14 may 2022, 18:25:07
    Author     : alvar
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="aliebay.dto.ProductoDTO"%>
<%@page import="aliebay.dto.UsuarioDTO"%>
<%@page import="aliebay.dto.VendedorDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Productos</title>
    </head>
    <body>
        <jsp:include page="/WEB-INF/jsp/cabeceraVendedor.jsp" />
        <%
            UsuarioDTO user = (UsuarioDTO) session.getAttribute("usuario");
            VendedorDTO v = user.getVendedor();
         %>
        <a href="EditarNuevoProductoServlet?id=<%=v.getIdUsuario()%>">Añadir nuevo producto</a><br>
        
        <%
            List<ProductoDTO> productosVendidos = (List)request.getAttribute("productosVendidos");
            List<ProductoDTO> productosNoVendidos = (List)request.getAttribute("productosNoVendidos");
            List<ProductoDTO> productosNoVendidosTerminados = (List)request.getAttribute("productosNoVendidosTerminados");
            

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            
            if(productosVendidos.isEmpty() && productosNoVendidos.isEmpty() && productosNoVendidosTerminados.isEmpty()){
        %>
                <h2> No existen productos para usted:</h2>
        <%
            }else{
                if(!productosNoVendidos.isEmpty()){
            %>
            
            <h3>Productos en venta:</h3>
            <table border="1" width="80%" style="text-align:center">
            <tr>
                <th>Titulo</th>
                <th>Descripcion</th>
                <th>Categoria</th>
                <th>Precio de salida</th>
                <th>Foto del producto</th>
                <th>Fecha de salida</th>
                <th>Fecha de fin:</th>
                <th></th><!-- Editar -->
                <th></th><!-- Borrar -->
            </tr>
        <%
                for (ProductoDTO p: productosNoVendidos) {
        %>
        <tr>
            <td><%= p.getTitulo()%></td>
            <td><%= p.getDescripcion() %></td>
            <td><%= p.getCategoria().getIdCategoria() %></td>
            <td><%= p.getPrecioSalida() %></td>
            <td><a href="verFotoServlet?url=<%= p.getuRLFoto() %>">Ver Foto</a></td>
            <td><%=  sdf.format(p.getFechaSalida()) %></td>
            <td><%= sdf.format(p.getFechaFin()) %></td>
            <td><a href="EditarNuevoProductoServlet?id=<%=v.getIdUsuario()%>&idprod=<%=p.getIdProducto()%>">Editar</a></td>
            <td><a href="BorrarProductoServlet?idprod=<%=p.getIdProducto()%>">Borrar</a></td>
        </tr>
        <%
                    }
            %> 
             </table>
        <%
                } 

            %>
            
        <%
                if(!productosVendidos.isEmpty()){
        %>
               
        <h3>Productos vendidos:</h3>
            <table border="1" width="80%" style="text-align:center">
            <tr>
                <th>Titulo</th>
                <th>Descripcion</th>
                <th>Categoria</th>
                <th>Precio compra</th>
                <th>Comprador</th>
                <th>Fecha compra</th>
                <th></th><!-- Editar -->
                <th></th><!-- Borrar -->
            </tr>
        <%
                for (ProductoDTO p: productosVendidos) {
        %>
        <tr>
            <td><%= p.getTitulo()%></td>
            <td><%= p.getDescripcion() %></td>
            <td><%= p.getCategoria().getIdCategoria() %></td>
            <td><%= p.getVenta().getPrecioVenta() %></td>
            <td><%= p.getVenta().getComprador().getUsuario().getUserName() %></td>
            <td><%= sdf.format(p.getVenta().getFecha()) %></td>
            <td><a href="EditarNuevoProductoServlet?id=<%=v.getIdUsuario()%>&idprod=<%=p.getIdProducto()%>">Editar</a></td>
            <td><a href="BorrarProductoServlet?idprod=<%=p.getIdProducto()%>">Borrar</a></td>
        </tr>
        
        <%
                }
            %>
        </table>
        <%
            }
        if(!productosNoVendidosTerminados.isEmpty()){
            %>
            
            <h3>Pujas terminadas sin comprador:</h3>
            <table border="1" width="80%" style="text-align:center">
            <tr>
                <th>Titulo</th>
                <th>Descripcion</th>
                <th>Categoria</th>
                <th>Precio de salida</th>
                <th>Foto del producto</th>
                <th>Fecha de salida</th>
                <th>Fecha de fin:</th>
                <th></th><!-- Editar -->
                <th></th><!-- Borrar -->
            </tr>
        <%
                for (ProductoDTO p: productosNoVendidosTerminados) {
        %>
        <tr>
            <td><%= p.getTitulo()%></td>
            <td><%= p.getDescripcion() %></td>
            <td><%= p.getCategoria().getIdCategoria() %></td>
            <td><%= p.getPrecioSalida() %></td>
            <td><a href="verFotoServlet?url=<%= p.getuRLFoto() %>">Ver Foto</a></td>
            <td><%=  sdf.format(p.getFechaSalida()) %></td>
            <td><%= sdf.format(p.getFechaFin()) %></td>
            <td><a href="EditarNuevoProductoServlet?id=<%=v.getIdUsuario()%>&idprod=<%=p.getIdProducto()%>">Editar</a></td>
            <td><a href="BorrarProductoServlet?idprod=<%=p.getIdProducto()%>">Borrar</a></td>
        </tr>
       
        <%
                    } 
        %>
            </table>
        <%
                }
            }
        %>
         
        
    </body>
</html>
