<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="servidor.publicar.DtOferta" %>
<%@ page import="servidor.publicar.DtPublicacion" %>
<%@ page import="servidor.publicar.DtPostulacion" %>
<%@ page import="servidor.publicar.DtEmpresa" %>
<%@ page import="java.time.LocalDateTime"%>
<%@ page import="java.time.LocalDate"%>
<%@ page import=" utils.LocalDateSerializer"%>
<%@ page import="utils.LocalDateTimeAdapter"%>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="net.java.dev.jaxb.array.StringArray" %>
<%@page import="java.time.format.DateTimeFormatter"%>
<!DOCTYPE html>
<html>
	<head>
    <jsp:include page="/WEB-INF/mobile/headMobile.jsp"/>
	   <script>
	        <% if (request.getAttribute("sessionExpired") != null) { %>
	            alert("La sesión ha expirado. Por favor, inicie sesión nuevamente.");
	        <% } %>
	    </script>   
	</head>
<body>
	<%   String imgPerfilJSON = (String) request.getAttribute("imgPerfil");
			String name = (String) request.getAttribute("nombre");
			String genero = (String) request.getAttribute("posibleGenero");
		 %>
    <nav class="navbar  navbar-light bg-light shadow mt-3">
        <div class="container-fluid mx-lg-3">
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
             <% if (genero.equals("Femenino")) { %>

            <a class="navbar-brand fw-bold text-uppercase" href="visitante">Bienvenida <%=name%></a>
                <div id="profile_img_container">
					    <img id="profile_img" src="<%=imgPerfilJSON%>" class="rounded-circle" alt="Foto de perfil" style="width:40px; height:40px; border: 2px solid black;">
                </div>
             <% } else { %>
           		<a class="navbar-brand fw-bold text-uppercase" href="visitante">Bienvenido <%=name%></a>
                <div id="profile_img_container">
					    <img id="profile_img" src="<%=imgPerfilJSON%>" class="rounded-circle" alt="Foto de perfil" style="width:40px; height:40px; border: 2px solid black;">
                </div>
             
             <% } %>
            <div class="collapse navbar-collapse my-3" id="navbarNav">
                <ul class="navbar-nav ml-auto">
                    <hr class="d-lg-none">
                    <li class="nav-item">
                        <a class="nav-link fw-bold" href="ver-ofertas">Ver Ofertas Laborales</a>
                    </li>
                    <hr class="d-lg-none">
                    <li class="nav-item">
                        <a class="nav-link fw-bold" href="ver-postulaciones">Ver Postulaciones</a>
                    </li>
                    <hr class="d-lg-none">
                    <li class="nav-item">
                        <a class="nav-link fw-bold" href="cerrar-sesion">Salir</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <div class="container my-3">
        <div class="row">
            <div class="col-12 align-items-center d-flex ms-2">
                <div>
    <button class="badge rounded-pill" onclick="window.history.back()">
        <span class="badge rounded-pill p-2">
            <i class="text-black bi bi-caret-left-fill me-2"></i>
            <span class="text-dark fw-bold">Volver Atrás</span>
        </span>
    </button>
</div>
            </div>

        </div>
    </div>
    				<%
		            DtPublicacion publicacion = (DtPublicacion) request.getAttribute("publicacion");
		            if(publicacion != null){
		        %>
    <div class="container mt-3">
        <div class="row">
            <div class="col-12 mb-4 mx-auto">
                <div class="card shadow">
                    <div class="card-header justify-content-center align-items-center d-flex">
                        <h5 class="card-title"><%= publicacion.getDtOferta().getNombre() %></h5>  
                    </div>
                    <div class="card-body">
                        <div class="container">
                            <p><%= publicacion.getDtOferta().getDescripcion() %></p>
                        </div>
                        <hr>
                        <div class="my-3">
                            <img src="<%= publicacion.getDtOferta().getUrlImagen() %>" class="card-img-top shadow" alt="Nombre oferta 1">
                        </div>
                        <hr>
                        <div class="mb-2">
                            <strong>Remuneración:</strong> <%= publicacion.getDtOferta().getRemuneracion() %> $U
                        </div>
                        <hr>
                        <div class="mb-2">
                            <strong>Horario:</strong> <%= publicacion.getDtOferta().getHorario() %>
                        </div>
                        <hr>
                        <div class="mb-2">
                            <strong>Ciudad:</strong> <%= publicacion.getDtOferta().getCiudad() %> 
                        </div>
                        <hr>
                        <div class="mb-2">
                            <strong>Departamento:</strong> <%= publicacion.getDtOferta().getDepartamento() %>
                        </div>
                        <hr>
                        <div class="mb-2">
                            <strong>Fecha alta:</strong> <%= publicacion.getFechaAlta().toString()%>
                        </div>
                        <hr>
                        <div class="mb-2">
                            <strong>Tipo Publicación:</strong> <%= publicacion.getDtTipo().getNombre()%> 
                        </div>
                        <hr>
                        <div class="mb-2">
                            <strong>Costo:</strong> <%= publicacion.getCostoAsociado()%>
                        </div>
                        <hr>
                        <div class="row d-flex  mt-3">
                                <p class="m-0"><span class="fw-bold"> Keywords:</span>  
                                <% for(String keyword : publicacion.getDtOferta().getKeywords()){ %>
		                            <span class="badge text-bg-dark">#<%= keyword %></span>
		                       	 <% } %>
                        
                        </div>
                    </div>
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
    
    <div class="container mt-4 mb-5">
        <div class="row">
            <div class="col-12 mb-4 mx-auto">
                <div class="card shadow">
                    <div class="card-header justify-content-center align-items-center d-flex">
                        <h5 class="card-title text-u">Postulacion: </h5>  
                    </div>
                    <div class="card-body">
                        <div class="container">
                            <h6>CV Reducido:</h6>
                            <p><%=postulacion.getCvReducido()%></p>
                        </div>
                        <hr>
                        <div class="container">
                            <h6>Motivación:</h6>
                            <p><%=postulacion.getMotivacion()%></p>
                        </div>
                        <hr>
                        <div class="container">
                            <h6><span>Fecha postulación: </span> <%=fecha%></h6>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <%
			    } else {
			%>
			       
			        <div class="container mb-5">
        <div class="row">
            <div class="col-12 d-flex mx-auto align-items-center justify-content-center">
                <a class="btn btn-success text-uppercase" href="postular-oferta-mobile?NombreOferta=<%= URLEncoder.encode(publicacion.getDtOferta().getNombre(), "UTF-8") %>">
                    Postularme
                </a>
            </div>
            
        </div>
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
    
    
    <script src="media/js/login.js"></script>
	<script src="media/js/index.js"></script>
	 <script src="media/js/jquery-3.7.1.min.js?v=<?php echo time(); ?>"></script>
	<script src="media/js/bootstrap.bundle.min.js?v=<?php echo time(); ?>"></script>
</body>	
	
</html>