<%@page import="servidor.publicar.DtPostulante"%>
<%@page import="servidor.publicar.DtPostulacion"%>
<%@page import="java.util.List"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDate"%>
<%@ page import="java.net.URLEncoder" %>

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
        <jsp:include page="/WEB-INF/template/NavBarPostulante.jsp"/>
        <div class="container d-flex flex-column justify-content-center my-4">
            <div class="d-flex mt-4 gap-3 flex-column">
                <div class="d-flex gap-5">
                    <div>
                        <h2>Mis Postulaciones:</h2>
                        <% 
                        DtPostulante usuario = (DtPostulante) request.getAttribute("usuario");
                    	List<DtPostulacion> posts = (List<DtPostulacion>) usuario.getPostulaciones();
                    	for (DtPostulacion post: posts) {
                    		String fechaHoraStr = post.getFecha();
	                   		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");                 	
	                   		LocalDate fecha = LocalDate.parse(fechaHoraStr, formatter);
                    %>                    
				      
				      <div class="card mb-2 my-3">
				      <div class="card-header">
    						<span class="fw-bold"><%=post.getNombreOfertaLaboral()%> </span>
				      	</div>
				      <div class="card-body">
				      	
				      	<p class="m-0 card-text"><span class="fw-bold">Motivacion: </span><%=post.getMotivacion() %></p>
                   		<p class="m-0 card-text"><span class="fw-bold">Cv reducido: </span><%=post.getCvReducido() %></p>
                   		<p class="m-0 card-text"><span class="fw-bold">Fecha de postulacion: </span><%= fecha %></p>
                   		<%
                   			if(!post.getUrlVideo().equals("")){
                   		%>
                  		<div class="d-flex mb-2 my-3 justify-content-center">
							<iframe width="420" height="315"
								src=<%=post.getUrlVideo()%>>
							</iframe>				      	
				      	</div>
				      	<% } %>
				      </div>
				      <div class="card-footer">
				      <div >
	                        <a href="consultaOferta?nombreOferta=<%=URLEncoder.encode(post.getNombreOfertaLaboral(), "UTF-8") %>" class="fw-bold text-black text-decoration-none">Ver Oferta ></a>
	                        
	                    </div>
				      </div>
				      				     
				      </div>
				      
					<% } %>
                    </div>
              		
                </div>
            </div>
			<div class="mt-4">
	            <a onclick="window.history.back();" class="btn btn-dark">Volver atrás</a>
	        </div>
		</div>
       
    </main>
</body>

</html>
