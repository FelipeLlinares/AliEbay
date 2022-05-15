<%-- 
    Document   : editarCrearListaComprador
    Created on : 26 abr. 2022, 18:03:08
    Author     : Cate
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="aliebay.dto.UsuarioDTO"%>
<%@page import="aliebay.dto.CompradorDTO"%>
<%@page import="java.util.List"%>
<%@page import="aliebay.dto.ListacompradorDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar-Crear Lista Comprador</title>
    </head>
       <%
        ListacompradorDTO listaComprador = (ListacompradorDTO) request.getAttribute("listaComprador");
        List<UsuarioDTO> compradoresListaComprador = (List)request.getAttribute("compradoresListaComprador");
        List<UsuarioDTO> compradores = (List) request.getAttribute("compradores");
        %>
    <body>
            <jsp:include page="/WEB-INF/jsp/cabeceraMarketing.jsp" />

        <h1>Listas de compradores</h1>
        <form action="ListaCompradorGuardarServlet" method="POST">        
        <input type="hidden" name="id" value="<%= listaComprador == null ? "" : listaComprador.getIdLista()%>"/>
        Nombre: <input type="text" name="nombre" value="<%= listaComprador == null ? "" : listaComprador.getNombre()%>" /> </br>
        Compradores: <br/>
        <%
        for (UsuarioDTO comprador : compradores){ 
            String checked = "";
            if (compradoresListaComprador != null && compradoresListaComprador.contains(comprador)){
                checked = "checked";
            }
        %>
        <input type="checkbox" name="<%= comprador.getIdUsuario() %>" value="<%= comprador.getIdUsuario() %>" <%=checked%> /><%= comprador.getNombre() %><br/>    
        <%
            }
        %>
        
        <input type="submit" value="Confirmar" />
        </form>
    </body>
</html>
