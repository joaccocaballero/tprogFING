<%@page import="servidor.publicar.DtTipoPublicacion"%>
<%@page import="java.time.format.DateTimeFormatter"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <jsp:include page="/WEB-INF/template/head.jsp" />
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
        <div class="row justify-content-center">
            <div class="col-md-8">
                <%
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                DtTipoPublicacion tipo = (DtTipoPublicacion) request.getAttribute("tipoSeleccionado");
                %>
                <div class="card">
                    <div class="card-header">
                        <h2 class="m-0"><%= tipo.getNombre() %></h2>
                    </div>
                    <div class="card-body">
                        <p class="m-0"><span class="fw-bold">Descripción: </span><%= tipo.getDescripcion() %></p>
                        <p class="m-0"><span class="fw-bold">Exposición: </span><%= tipo.getExposicion() %></p>
                        <p class="m-0"><span class="fw-bold">Duración: </span><%= tipo.getDuracionPublicacion() %> Días</p>
                        <p class="m-0"><span class="fw-bold">Costo: </span><%= tipo.getCosto() %></p>
                        <p class="m-0"><span class="fw-bold">Fecha de Alta: </span><%= tipo.getFechaAlta() %></p>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="row mt-5">
            <div class="col d-flex justify-content-center">
                <button type="button" class="btn btn-dark btn-lg" onclick="window.history.back();" >Volver Atrás</button>
            </div>
        </div>
    </div>
</main>
</body>

</html>
