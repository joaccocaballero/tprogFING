<%@page import="servidor.publicar.DtOferta"%>
<%@page import="servidor.publicar.DtPostulacion"%>
<%@page import="java.util.List"%>
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
                        <a class="navbar-brand" href="/tarea2/postulante"><img class="img-fluid w-50" src="media/img/trabajo_logo.png" alt=""></a>
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
    <div class="container my-4">
        <%
            DtOferta oferta = (DtOferta) request.getAttribute("oferta");
            List<DtPostulacion> posts = (List<DtPostulacion>) oferta.getPostulaciones();
            if (posts == null || posts.isEmpty()) {
            
        %>
         <h2>No se han encontrado postulaciones.</h2>
        <%
		    } else {
		%>
        <h2 class="mb-4">Postulaciones a <%= oferta.getNombre() %> :</h2>
	        <% 
	            for (DtPostulacion post: posts) {
	                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	                //String formattedDate = post.getFecha().format(formatter);
	        %>
	        <div class="card mb-4">
	        <div class="card-header">
	        	 <h5 class="card-title"><%= post.getNicknamePostulante() %></h5>
	        </div>
	            <div class="card-body">
	               
	                <p class="m-0"><span class="fw-bold">Motivacion: </span><%= post.getMotivacion() %></p>
	                <p class="m-0"><span class="fw-bold">Cv reducido: </span><%= post.getCvReducido() %></p>
	                <p class="m-0"><span class="fw-bold">Fecha de postulacion: </span><%= post.getFecha() %></p>
	                <p class="m-0"><span class="fw-bold">Resultado: </span><%= post.getResultado() %></p>
	                <%
	                	if(!post.getUrlVideo().equals("")){
	               	%>
	             	<% if (post.getUrlVideo() != null && post.getUrlVideo().length() > 5) { %>
					    <div class="d-flex mb-2 my-3 justify-content-center">
					        <iframe width="420" height="315" src="<%= post.getUrlVideo() %>"></iframe>
					    </div>
					<% } %>
	
			      	<% } %>
	            </div>
	            
	        </div>
        	<% } %>
        <% } %>
    </div>
     <div class="mt-4 mb-5 text-center">
            <button type="button" class="btn btn-dark" onclick="window.history.back();">Volver atrás</button>
        </div>
    </div>
</main>

</body>

</html>
