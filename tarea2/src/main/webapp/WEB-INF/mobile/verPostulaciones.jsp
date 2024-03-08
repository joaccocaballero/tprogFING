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
    <button class="badge rounded-pill" onclick="location.href='visitante'">
        <span class="badge rounded-pill p-2">
            <i class="text-black bi bi-caret-left-fill me-2"></i>
            <span class="text-dark fw-bold">Volver Atrás</span>
        </span>
    </button>
</div>
            </div>

        </div>
    </div>
   <div class="d-flex justify-content-center align-items-center bg-secondary p-2 text-light">
        <h1>Ver mis Postulaciones</h1>
    </div>
    <div class="container my-3">
   
        <div class="row">
            <div class="col-md-9 mx-auto">
                <div class="card">
                    <div class="card-header">
                        <h3 class="card-title">Seleccione una Oferta:</h3>
                    </div>
                    <div class="card-body">
                    <%  
				         List<DtPostulacion> postulaciones =  (List<DtPostulacion>) request.getAttribute("postulaciones");
				         if(postulaciones != null && !postulaciones.isEmpty()){
				     %>
                        <ul class="list-group list-group-flush">
                        <% for(DtPostulacion postulacion : postulaciones){ %>
                            <li class="list-group-item">
                                <a class="text-decoration-none text-black fw-bold" href="ver-oferta?nombreOferta=<%= URLEncoder.encode(postulacion.getNombreOfertaLaboral(), "UTF-8") %>"> <%= postulacion.getNombreOfertaLaboral() %> ></a>                             
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
    <script src="media/js/login.js"></script>
	<script src="media/js/index.js"></script>
	 <script src="media/js/jquery-3.7.1.min.js?v=<?php echo time(); ?>"></script>
	<script src="media/js/bootstrap.bundle.min.js?v=<?php echo time(); ?>"></script>
</body>	
	
</html>