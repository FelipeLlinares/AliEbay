<%-- 
    Document   : editarCrearListaComprador
    Created on : 26 abr. 2022, 18:03:08
    Author     : Cate
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="aliebay.entity.Usuario"%>
<%@page import="aliebay.entity.Comprador"%>
<%@page import="java.util.List"%>
<%@page import="aliebay.entity.Listacomprador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar-Crear Lista Comprador</title>
    </head>
       <%
        Listacomprador listaComprador = (Listacomprador) request.getAttribute("listaComprador");
        List<Comprador> compradoresListaComprador = (List)request.getAttribute("compradoresListaComprador");
        List<Comprador> compradores = (List) request.getAttribute("compradores");
        %>
    <body>
        <h1>Listas de compradores</h1>
        <form action="ListaCompradorGuardarServlet" method="POST">        
        <input type="hidden" name="id" value="<%= listaComprador == null ? "" : listaComprador.getIdLista()%>"/>
        Nombre: <input type="text" name="nombre" value="<%= listaComprador == null ? "" : listaComprador.getNombre()%>" /> </br>
        Compradores: <br/>
        <%
        for (Comprador comprador : compradores){ 
            String checked = "";
            if (compradoresListaComprador != null && compradoresListaComprador.contains(comprador)){
                checked = "checked";
            }
        %>
        <input type="checkbox" name="<%= comprador.getIdUsuario() %>" value="<%= comprador.getIdUsuario() %>" <%=checked%> /><%= comprador.getUsuario().getNombre() %><br/>    
        <%
            }
        %>
        
        <input type="submit" value="Confirmar" />
        </form>
    </body>
</html>
