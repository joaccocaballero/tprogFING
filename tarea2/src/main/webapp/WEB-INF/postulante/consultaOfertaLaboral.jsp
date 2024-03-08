<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="servidor.publicar.DtOferta" %>
<%@ page import="servidor.publicar.DtPublicacion" %>
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
<%@page import="java.time.format.DateTimeFormatter"%>

<!DOCTYPE html>
<html lang="en">

<head>
     <jsp:include page="/WEB-INF/template/head.jsp"/>
         <%   String imgPerfilJSON = (String) request.getAttribute("imgPerfil");
		 %>
</head>

<body>
    <header>
         <nav class="navbar p-3">
            <div class=" d-flex justify-content-between align-items-center w-100" style="height: 8vh;">
                <div class="d-flex ms-5">
                      <jsp:include page="/WEB-INF/template/Logo.jsp"/>
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
         <jsp:include page="/WEB-INF/template/NavBarPostulante.jsp"/>
          <%
		            DtPublicacion publicacion = (DtPublicacion) request.getAttribute("publicacion");
		            if(publicacion != null){
		        %>
        <div class="p-3 mt-5 d-flex flex-column">
            <div class="d-flex  justify-content-center">
                        <div class="row d-flex text-center align-items-center justify-content-center">
                            <div>
                                <img class="w-75" src="<%= publicacion.getDtOferta().getUrlImagen() %>" alt="">
                            </div>
                        
                        </div>
                        <div>
                            <div class="row d-flex  mt-3">
                                <div class="text-start align-items-center justify-content-center">
                                    <h3 class="fw-bold"><%= publicacion.getDtOferta().getNombre() %></h3>
                                </div>
                                <p><span class="fw-bold">Descripción: </span><%= publicacion.getDtOferta().getDescripcion() %></p>
                            </div>
                            <div class="row d-flex ">
                                <p class="m-0"><span class="fw-bold">Remuneración: </span><%= publicacion.getDtOferta().getRemuneracion() %> pesos uruguayos </p>
                                <p class="m-0"><span class="fw-bold">Horario: </span> <%= publicacion.getDtOferta().getHorario() %> </p>
                                <p class="m-0"><span class="fw-bold">Departamento: </span><%= publicacion.getDtOferta().getDepartamento() %></p>
                                <p class="m-0"><span class="fw-bold">Ciudad: </span><%= publicacion.getDtOferta().getCiudad() %> </p>

                                <p class="m-0"><span class="fw-bold">Tipo de Publicacion:</span>  <%= publicacion.getDtTipo().getNombre()%> : <a class="fw-bold text-decoration-none text-black" href="mostrarTipo?nombre=<%= publicacion.getDtTipo().getNombre()%>">Ver más ></a></p>
                               
                            </div>
                            <div class="row d-flex  mt-3">
                                <p class="m-0"><span class="fw-bold">Fecha de alta:</span>
				                     <%= publicacion.getFechaAlta().toString()%></p>
                            </div>
                        
                            <div class="row d-flex  mt-3">
                                <p class="m-0"><span class="fw-bold"> Keywords:</span>  <% for(String keyword : publicacion.getDtOferta().getKeywords()){ %>
		                            <span class="badge text-bg-dark">#<%= keyword %></span>
		                        <% } %>
                        
                            </div>
                        </div>
            	</div>
            </div>
              <% 
			    DtPostulacion postulacion = (DtPostulacion) request.getAttribute("postulacion");
			    if(postulacion != null){
			    	String fechaHoraStr = postulacion.getFecha();
               		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");                 	
               		LocalDate fecha = LocalDate.parse(fechaHoraStr, formatter);
			%>
			    <div class="container mt-5 mb-5">
			        <div class="row justify-content-center">
			            <div class="col-8">
			                <div class="card">
			                    <div class="card-header">
			                        <h3 class="fw-bold">Mi Postulación:</h3>
			                    </div>
			                    <div class="card-body">
			                        <h4>CV:</h4>
			                        <p><%=postulacion.getCvReducido()%></p>
			                        <h4>Motivación:</h4>
			                        <p><%=postulacion.getMotivacion()%></p>
			                        <h4>Fecha:</h4>
			                        <p><%=fecha%></p> 
			                    </div>
			                </div>
			            </div>
			        </div>
			    </div>
			<%
			    } else {
			%>
			       
			        <div class="row d-flex justify-content-center my-5">
			            <a class="btn btn-success" href="confirmarPostulacion?NombreOferta=<%= URLEncoder.encode(publicacion.getDtOferta().getNombre(), "UTF-8") %>">
			                Postularse
			            </a>
			        </div>
			<%
			    }
			%>
         
         
         
         <%
		            } else {
		                %>
		                    <div class="alert alert-danger" role="alert">
		                        Lo sentimos, no se pudo encontrar la oferta solicitada.
		                    </div>
		                <%  
		                    }
		                %>
		<div class="my-4 container d-flex justify-content-center">
    <a class="btn btn-dark" onclick="window.history.back();" >Volver atrás</a>
</div>          
    </main>
</body>

</html>

