<%@page import="servidor.publicar.DtTipoPublicacion"%>
<%@page import="java.time.format.DateTimeFormatter"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <jsp:include page="/WEB-INF/template/head.jsp" />
      <%   String imgPerfilJSON = (String) request.getAttribute("imgPerfil");
		 %>
</head>
<body>
    <header>
        <nav class="navbar p-3">
            <div class="d-flex justify-content-between align-items-center w-100" style="height: 8vh;">
                <div class="d-flex">
                    <div class="ms-5">
                        <a class="navbar-brand" href="/tarea2/visitante"><img class="img-fluid w-50" src="media/img/trabajo_logo.png" alt=""></a>
                    </div>
                  
                </div>
                 <div class="d-flex me-5">
                 
				 <div class="d-flex me-5">
					 <div class="d-flex">
					    <img src="<%=imgPerfilJSON%>" class="rounded-circle" alt="Foto de perfil" style="width:40px; height:40px; border: 2px solid black;">
					  </div>
				     <div class="d-flex border-end px-2 mt-2">
				         <a href="/tarea2/miUsuario" class="text-decoration-none text-black fw-bold "><img src="">Mi Usuario</a>
				     </div>
				     <div class="d-flex px-2 mt-2">
				         <a class="text-decoration-none text-black fw-bold " href="/tarea2/cerrar-sesion">Cerrar Sesión<i class="ms-2 fas fa-sign-out-alt"></i></a>
				     </div>
 </div>
 				</div>
            </div>
        </nav>
    </header>
    <main>
     <jsp:include page="/WEB-INF/template/NavBarPostulante.jsp"/>
       <div class="container my-5">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <%
                DtTipoPublicacion tipo = (DtTipoPublicacion) request.getAttribute("tipoSeleccionado");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
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
                        <p class="m-0"><span class="fw-bold">Fecha de Alta: </span><%= tipo.getFechaAlta()%></p>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="row mt-5">
            <div class="col d-flex justify-content-center">
                <button type="button" class="btn btn-dark btn-lg" onclick="window.history.back();">Volver Atrás</button>
            </div>
        </div>
    </div>
    </main>
</body>
</html>