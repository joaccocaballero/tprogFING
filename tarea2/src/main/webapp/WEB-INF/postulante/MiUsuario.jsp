<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="servidor.publicar.DtPostulacion" %>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="java.lang.reflect.Type"%>
<%@ page import="com.google.gson.reflect.TypeToken"%>
<%@ page import="com.google.gson.GsonBuilder"%>
<%@ page import="java.time.LocalDateTime"%>
<%@ page import="com.google.gson.reflect.TypeToken"%>
<%@ page import="java.time.LocalDate"%>
<%@ page import=" utils.LocalDateSerializer"%>
<%@ page import="utils.LocalDateTimeAdapter"%>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <jsp:include page="/WEB-INF/template/head.jsp" />
     <% Gson gson = new GsonBuilder()
     		    .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
     		    .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
     		    .create(); 
        %>
         <%   String imgPerfilJSON = (String) request.getAttribute("imgPerfil");
         String nicknameJSON = (String) request.getAttribute("nickname");
         String nombreJSON = (String) request.getAttribute("nombre");
         String apellidoJSON = (String) request.getAttribute("apellido");
         String correoJSON = (String) request.getAttribute("correo");
         String nacionalidad = (String) request.getAttribute("nacionalidad");
         String fechaNacimiento = (String) request.getAttribute("fechaNacimiento");
		 %>
</head>
<body>
    <header>
        <nav class="navbar p-3">
            <div class=" d-flex justify-content-between align-items-center w-100" style="height: 8vh;">
                <div class="d-flex" >
                    <div>
                        <a class="navbar-brand" href="/tarea2/postulante"><img width="160" src="media/img/trabajo_logo.png"
                                alt=""></a>
                    </div>
                    <h3 class="m-0 d-flex align-items-center">Mi Usuario</h3>
                </div>
				<div class="d-flex me-5">
					 <div class="d-flex">
					    <img src="<%=imgPerfilJSON%>" class="rounded-circle" alt="Foto de perfil" style="width:40px; height:40px; border: 2px solid black;">
					  </div>
				     <div class="d-flex border-end px-2 mt-2">
				         <a href="/tarea2/miUsuario" class="text-decoration-none text-black fw-bold ">Mi Usuario</a>
				     </div>
				     <div class="d-flex px-2 mt-2">
				         <a class="text-decoration-none text-black fw-bold " href="/tarea2/cerrar-sesion">Cerrar Sesión<i class="ms-2 fas fa-sign-out-alt"></i></a>
				     </div>
				 </div>
            </div>
        </nav>
    </header>
    <main>
    <jsp:include page="/WEB-INF/template/NavBarPostulante.jsp" />
    <div class="container mt-5">
        <div class="row">

            <div class="col-md-3 text-center">
                <img class="img-fluid rounded-circle mb-3" src="<%=imgPerfilJSON%>" alt="" style="max-width: 250px; max-height: 250px;">
            </div>

            <div class="col-md-9">
                <div class="card mb-4">
                    <div class="card-header">
                        <h2>Datos Personales</h2>
                    </div>
                    <div class="card-body">
                        <p class="m-0"><span class="fw-bold">Nickname: </span><%=nicknameJSON%></p>
                        <p class="m-0"><span class="fw-bold">Nombre: </span><%=nombreJSON%></p>
                        <p class="m-0"><span class="fw-bold">Apellido: </span><%=apellidoJSON%></p>
                        <p class="m-0"><span class="fw-bold">Email: </span><%=correoJSON%></p>
                         <p class="m-0"><span class="fw-bold">Nacionalidad: </span><%=nacionalidad%></p>
                          <p class="m-0"><span class="fw-bold">Fecha de Nacimiento: </span><%=fechaNacimiento%></p>
                    </div>
                </div>

   
                <div class="card mt-4 mt-3 mb-5">
				    <div class="card-header">
				        <h2>Mis Postulaciones</h2>
				    </div>
				    <div class="card-body ">
				        <%  
				            List<DtPostulacion> postulaciones =  (List<DtPostulacion>) request.getAttribute("postulaciones");
				            if(postulaciones != null && !postulaciones.isEmpty()){
				        %>
				        <ul class="list-group">
				           <strong>Nombre de Oferta Laboral: </strong>
				            <% for(DtPostulacion postulacion : postulaciones){ %>
				            <li class="list-group-item mt-2 p-3">
				                <a class="fw-bold text-black text-decoration-none ms-2" href="consultaOferta?nombreOferta=<%= URLEncoder.encode(postulacion.getNombreOfertaLaboral(), "UTF-8") %>">
				                    <%= postulacion.getNombreOfertaLaboral() %> >
				                </a>
				            </li>
				            <% } %>
				        </ul>
				        <%  
				            } else {
				        %>
				        <p>No hay postulaciones disponibles.</p>
				        <%
				            }
				        %>
				    </div>
				</div>
            </div>
        </div>
    </div>
    <div class="my-4 container d-flex justify-content-center">
    <a onclick="window.history.back();"  class="btn btn-dark">Volver atrás</a>
</div>     
</main>
</body>

</html>