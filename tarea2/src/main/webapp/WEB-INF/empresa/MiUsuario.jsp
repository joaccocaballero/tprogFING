<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.List" %>
<%@ page import="servidor.publicar.DtOferta" %>
<%@ page import="servidor.publicar.DtPublicacion" %>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="java.lang.reflect.Type"%>
<%@ page import="com.google.gson.reflect.TypeToken"%>
<%@ page import="com.google.gson.GsonBuilder"%>
<%@ page import="java.time.LocalDateTime"%>
<%@ page import="com.google.gson.reflect.TypeToken"%>
<%@ page import="java.time.LocalDate"%>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import=" utils.LocalDateSerializer"%>
<%@ page import="utils.LocalDateTimeAdapter"%>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <jsp:include page="/WEB-INF/template/head.jsp"/>
      <%   String imgPerfilJSON = (String) request.getAttribute("imgPerfil");
		 %>
		 <%  
         String nicknameJSON = (String) request.getAttribute("nickname");
         String nombreJSON = (String) request.getAttribute("nombre");
         String apellidoJSON = (String) request.getAttribute("apellido");
         String correoJSON = (String) request.getAttribute("correo");
         String nombreEmpresa = (String) request.getAttribute("nombreEmpresa"); 
         String descripcion = (String) request.getAttribute("descripcion"); 
         String linkWeb = (String) request.getAttribute("link_web"); 
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
        <jsp:include page="/WEB-INF/template/NavBarEmpresa.jsp"/>
        <div class="d-flex flex-column p-5">
            <div class="d-flex flex-column gap-5">
                <div class="d-flex justify-content-center">
                    <img width="250" height="250" src="<%=imgPerfilJSON%>" alt="">
                </div>
                <div class="d-flex gap-5">
                    <div>
                        <h2>Datos Personales:</h2>
                        <p class="m-0"><span class="fw-bold">Nickname: </span><%=nicknameJSON%></p>
                        <p class="m-0"><span class="fw-bold">Nombre: </span><%=nombreJSON%></p>
                        <p class="m-0"><span class="fw-bold">Apellido: </span><%=apellidoJSON%></p>
                        <p class="m-0"><span class="fw-bold">Email: </span><%=correoJSON%></p>
                        <p class="m-0"><span class="fw-bold">Nombre de Empresa: </span><%=nombreEmpresa%></p>
                        <p class="m-0"><span class="fw-bold">Dsecripción: </span><%=descripcion%></p>
                        <% 
						   if (!linkWeb.equals("")) {
						%>
						   <p class="m-0"><span class="fw-bold">Link Web:</span> <%= linkWeb %></p>
						<% 
						   }
						%>
                    </div>
                </div>
			</div>
            <div class=" mt-4">
    	<div>
        <h2>Ofertas Laborales:</h2>
        <table class="table table-hover">
            <thead>
                <tr>
                    <th scope="col">Nombre:</th>
                    <th scope="col">Tipo de Publicacion:</th>
                    <th scope="col">Remuneración:</th>
                    <th scope="col">Horario:</th>
                    <th scope="col">Fecha de Alta:</th>
                    <th scope="col">Estado:</th>
                </tr>
            </thead>
            <tbody>
    <%
            List<DtPublicacion> publicaciones = (List<DtPublicacion>) request.getAttribute("publicaciones");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            for(DtPublicacion publicacion : publicaciones) {
                //LocalDate fechaAlta = publicacion.getDtOferta().getFechaAlta();
                //String formattedDate = (fechaAlta != null) ? fechaAlta.format(formatter) : "N/A";
    %>
            <tr> 
           		<td class="fw-bold"> 
					<a class="text-dark" href="consultaOferta?nombreOferta=<%= URLEncoder.encode(publicacion.getDtOferta().getNombre(), "UTF-8") %>">
				        <%= publicacion.getDtOferta().getNombre() %>
				    </a>
				</td>
                <td>
                  	<a class="text-dark" href="mostrarTipo?nombre=<%= URLEncoder.encode(publicacion.getDtTipo().getNombre(), "UTF-8") %>">
				        <%= publicacion.getDtTipo().getNombre() %>
				    </a>
				</td>
                <td><%= publicacion.getDtOferta().getRemuneracion() %></td>
                <td><%= publicacion.getDtOferta().getHorario() %></td>
                <td><%= publicacion.getFechaAlta() %></td>
                <td><%= publicacion.getDtOferta().getEstado().name() %></td>
            </tr>
    <% 
        }
    %>
</tbody>
        </table>
    </div>
</div>
        </div>
             <div class="mt-4 mb-5 text-center">
            	<a onclick="window.history.back();" class="btn btn-dark">Volver atrás</a>
        	</div>
  
    </main>
</body>

</html>
