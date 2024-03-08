<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="servidor.publicar.DtOferta" %>
<%@ page import="servidor.publicar.DtPublicacion" %>
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
    </head>
    <body>
        <header>
              <nav class="navbar p-3">
		            <div class=" d-flex justify-content-between align-items-center w-100" style="height: 8vh;">
		                <div class="d-flex" style="width: 80vw;">
		                    <jsp:include page="/WEB-INF/template/Logo.jsp" />
		                    <h3 class="m-0 d-flex align-items-center">Consulta Oferta Laboral</h3>
		                </div>
		            </div>
		        </nav>
        </header>
       <main>
		    <jsp:include page="/WEB-INF/template/NavBarVisitante.jsp" />
		    <div class="container mt-3">
		        <% 
		            DtPublicacion publicacion = (DtPublicacion) request.getAttribute("publicacion");
		            if(publicacion != null){
		        %>
		            <div class="card mb-3">
		                <div class="card-header">
		                    <h3 class="fw-bold m-3"><%= publicacion.getDtOferta().getNombre() %></h3>
		                    <div class="col-md-6">

			                    <img src="<%= publicacion.getDtOferta().getUrlImagen() %>" class="img-fluid w-50 m-2" alt="Imagen de la oferta laboral">

			                </div>
		                </div>
		                <div class="card-body">
		                    <p><span class="fw-bold">Descripción:</span> <%= publicacion.getDtOferta().getDescripcion() %></p>
		                    <p class="m-0"><span class="fw-bold">Remuneración:</span> <%= publicacion.getDtOferta().getRemuneracion() %> pesos uruguayos </p>
		                    <p class="m-0"><span class="fw-bold">Horario:</span> <%= publicacion.getDtOferta().getHorario() %> </p>
		                    <p class="m-0"><span class="fw-bold">Departamento:</span> <%= publicacion.getDtOferta().getDepartamento() %> </p>
		                    <p class="m-0"><span class="fw-bold">Ciudad:</span> <%= publicacion.getDtOferta().getCiudad() %> </p>
		                    <p class="m-0"><span class="fw-bold">Fecha de alta:</span> 
				                     <%=publicacion.getFechaAlta().toString()%></p>
		                </div>
		                <div class="card-footer">
		                    
		                    <p class="m-0"><span class="fw-bold"> Keywords:</span>
		                        <% for(String keyword : publicacion.getDtOferta().getKeywords()){ %>
		                        <span class="badge rounded-pill text-bg-secondary"><%= keyword %></span>
		                           
		                        <% } %>
		                    </p>
		                    
		                </div>
		            </div>
		            <div class="mt-2 mb-4">
					            <a onclick="window.history.back();"  class="btn btn-dark">Volver atrás</a>
					</div>
		             
		        <%
		            } else {
		                %>
		                    <div class="alert alert-danger" role="alert">
		                        Lo sentimos, no se pudo encontrar la oferta solicitada.
		                    </div>
		                <%  
		                    }
		                %>
		   </div>
		</main>
    </body>
</html>
