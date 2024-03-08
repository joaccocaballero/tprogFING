<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Set"%>
<%@page import="servidor.publicar.DtOferta"%>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="java.lang.reflect.Type"%>
<%@ page import="com.google.gson.reflect.TypeToken"%>
<%@ page import="com.google.gson.GsonBuilder"%>
<%@ page import="java.time.LocalDateTime"%>
<%@ page import="java.time.LocalDate"%>
<%@ page import=" utils.LocalDateSerializer"%>
<%@ page import="utils.LocalDateTimeAdapter"%>
<%@ page import="java.net.URLEncoder" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		 <jsp:include page="/WEB-INF/template/head.jsp"/>
		 <%   String imgPerfilJSON = (String) request.getAttribute("imgPerfil");
		 %>
		 <% Gson gson = new GsonBuilder()
     		    .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
     		    .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
     		    .create(); 
        %>
	</head>
	<body>
		    <header>
		      
		        <nav class="navbar p-3">
            <div class="d-flex justify-content-between align-items-center w-100" style="height: 8vh;">
                <div class="d-flex">
                    <div class="ms-5">
                        <a class="navbar-brand" href="/tarea2/empresa"><img class="img-fluid w-50" src="media/img/trabajo_logo.png" alt=""></a>
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
			    <jsp:include page="/WEB-INF/template/NavBarEmpresa.jsp" />
			    <div class="container my-5">
			        <div class="row">
			            <div class="col-md-6">
			                <div class="card">
			                    <div class="card-header">
			                        <h3 class="card-title">Seleccione una Oferta:</h3>
			                    </div>
			                    <div class="card-body">
			                        <ul class="list-group list-group-flush">
			                            <%
			                            	String ofertasJSON = (String) request.getAttribute("ofertasVigentes");
			                            	Type listType = new TypeToken<Set<DtOferta>>() {}.getType();	
			                            	Set<DtOferta> ofertas = gson.fromJson(ofertasJSON, listType);
			                               for (DtOferta oferta : ofertas) { %>
			                            <li class="list-group-item">
			                                <a class="text-decoration-none text-black fw-bold" href="seleccionarPostulaciones?NombreOferta=<%= oferta.getNombre()%>"><%= oferta.getNombre()%></a>                             
			                            </li>
			                            <% } %>
			                        </ul>
			                    </div>
			                </div>
			            </div>
					</div>
					<div class="mt-4">
					    <a onclick="window.history.back();"  class="btn btn-dark">Volver atrás</a>
					</div>
				</div>
			</main>
	</body>
</html>
