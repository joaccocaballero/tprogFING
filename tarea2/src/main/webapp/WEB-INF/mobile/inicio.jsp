<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="servidor.publicar.DtOferta" %>
<%@ page import="servidor.publicar.DtPublicacion" %>
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
        </div>
    </nav>
    <div id="banner-container">
        <img src="media/img/mobile/banner_postulante_mobile2.png" alt="Banner" style="width:100%; height:auto;">
    </div>
    <div class="d-flex justify-content-center align-items-center my-3">
    <a class="btn btn-primary mx-2 fw-bold" style="width: 200px;" href="ver-ofertas">Ver Ofertas</a>
    <a class="btn btn-primary mx-2 fw-bold" style="width: 200px;" href="ver-postulaciones">Ver Postulaciones</a>
</div>

<div class="d-flex flex-column align-items-center my-3">

    <a class="btn btn-secondary my-1" href="cerrar-sesion">Salir</a>
</div>

    <script src="media/js/login.js"></script>
	<script src="media/js/index.js"></script>
	 <script src="media/js/jquery-3.7.1.min.js?v=<?php echo time(); ?>"></script>
	<script src="media/js/bootstrap.min.js?v=<?php echo time(); ?>"></script>
</body>	
	
</html>