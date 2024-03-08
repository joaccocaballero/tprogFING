<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="servidor.publicar.DtUsuario"%>
<%@page import="servidor.publicar.DtPostulante"%>
<%@page import="servidor.publicar.DtEmpresa"%>
<!DOCTYPE html>
<html>
	<head>
	<jsp:include page="/WEB-INF/template/head.jsp"/>
	<%   String imgPerfilJSON = (String) request.getAttribute("imgPerfil");
	%> 
	<%   DtPostulante usuario = (DtPostulante) request.getAttribute("usuario");
	%>
	</head>
	<body>
	<header>
		<nav class="navbar p-3 border-bottom border-black">
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
	 	<div class="p-2 d-flex justify-content-center mt-3">	
		<form class="col-6" method="post" action="modificarDatosUsuario">
			  <div class="form-group">
			    <label>Nombre</label>
			    <input type="text" class="form-control" name="nombre" id="NombreField" value="<%= usuario.getNombre() %>">
			  </div>
			  <div class="form-group">
			    <label>Apellido</label>
			    <input type="text" class="form-control" name="apellido" id="ApellidoField"  value="<%= usuario.getApellido() %>">
			  </div>
			  <div class="form-group mb-4">
                  <label for="nacionalidad">Nacionalidad:</label>
                  <select id="nacionalidad" name="nacionalidad" class="form-control" value="<%= usuario.getNacionalidad() %>">
                      <option value="Uruguayo">Uruguayo</option>
                      <option value="Argentino">Argentino</option>
                      <option value="Brasileño">Brasileño</option>
                  </select>
              </div>
			  <div class="form-group">
			    <label>URL Imagen</label>
			    <input type="text" class="form-control" name="url_imagen" id="URLImagenField" value="<%= usuario.getUrlImagen() %>">
			  </div>
			  <div class="form-group mb-2">
                  <label for="fechaNacimiento">Fecha de Nacimiento:</label>
                  <input type="date" id="fechaNacimiento" name="fechaNacimiento" class="form-control" value="<%= usuario.getFechaNacimiento() %>" required>
              </div>
              <div class="mt-4">
			  	<button type="submit" class="btn btn-dark">Confirmar</button>
              </div>
		</form>
	 	</div>
	
	</main>
	
	</body>
</html>