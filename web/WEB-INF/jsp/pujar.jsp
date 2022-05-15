<%-- 
    Document   : pujar
    Created on : 13 may. 2022, 17:09:55
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
        <jsp:include page="/WEB-INF/jsp/cabeceraFavoritos.jsp" />
        <title>Pujar</title>
    </head>
    <body>
        <h1>Realizar puja</h1>
        <%
            
                ProductoDTO pc = (ProductoDTO) request.getAttribute("producto");
            
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                String fechaTotal = "Desde " + sdf.format(pc.getFechaSalida()) + " hasta " + sdf.format(pc.getFechaFin());
                
                String pujaUltima = "No hay pujas";
                List<PujaDTO> pujas = (List) request.getAttribute("pujas");
                if(pujas != null && !pujas.isEmpty()) {
                    pujaUltima = String.valueOf(pujas.get(pujas.size() - 1).getPuja()) + " €";
                }
                
                String vendedor = (String) request.getAttribute("vendedor");
        %>
        <form action="CompradorGuardarPujaServlet" method="POST">
        <table>
            <tr><td><img src="<%= pc.getuRLFoto()%>" width="50px" height="50px"></td></tr>
            <tr><td>Titulo: <%= pc.getTitulo()%></td></tr>
            <tr><td>Descripción: <%= pc.getDescripcion()%></td></tr>
            <tr><td>Vendedor: <%= vendedor%></td></tr>
            <tr><td>Categoria: <%= pc.getCategoria().getIdCategoria()%></td></tr>
            <tr><td><%= fechaTotal%></td></tr>
            <tr><td>Precio de salida: <%= pc.getPrecioSalida()%> €</td></tr>
            <tr><td>Última puja: <%= pujaUltima%></td></tr>
            <tr><td>Nueva puja: <input type="text" id="puja" name="puja" pattern="[0-9]+"></td></tr>
            
            <%
            String strError = (String)request.getAttribute("error");
            if (strError != null){
            %>  
                <tr><td><h3 style="color:red"><%= strError%></h3></td></tr><br/>
            <%
                }
            %>
            
            <input type="hidden" name="id" value="<%= pc.getIdProducto() %>"/>
            <tr><td><input type="submit" value="Confirmar puja" /></td></tr>
        </table>
        </form>
    </body>
</html>
