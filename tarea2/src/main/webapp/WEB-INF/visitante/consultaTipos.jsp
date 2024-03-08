<%@page import="java.util.List"%>
<%@page import="servidor.publicar.DtTipoPublicacion"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
     <jsp:include page="/WEB-INF/template/head.jsp"/>
</head>
<body>
    <header>
        <nav class="navbar p-3">
            <div class=" d-flex justify-content-between align-items-center w-100" style="height: 8vh;">
                <div class="d-flex" style="width: 80vw;">
                    <jsp:include page="/WEB-INF/template/Logo.jsp" />
                    <h3 class="m-0 d-flex align-items-center">Consultar Tipo de Publicación</h3>
                </div>
            </div>
        </nav>
    </header>
   <main>
    <jsp:include page="/WEB-INF/template/NavBarVisitante.jsp" />
    <div class="container my-5">
        <h2 class="mb-4 text-center">Seleccione un Tipo de Publicación:</h2>
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="list-group">
                    <%
                        List<DtTipoPublicacion> tiposPublicacion = (List<DtTipoPublicacion>) request.getAttribute("tiposPublicacion");
                        for (DtTipoPublicacion tipo : tiposPublicacion) {
                    %>
                    <a href="mostrarTipo?nombre=<%= tipo.getNombre()%>" class="list-group-item list-group-item-action text-center">
                        <%= tipo.getNombre()%>
                    </a>
                    <% } %>
                </div>
            </div>
        </div>
        <div class="mt-4 text-center">
            <a onclick="window.history.back();"  class="btn btn-dark">Volver atrás</a>
        </div>
    </div>
</main>
</body>
</html>
