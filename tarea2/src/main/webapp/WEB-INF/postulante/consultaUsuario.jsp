<%@page import="java.util.List"%>
<%@page import="servidor.publicar.DtUsuario"%>
<%@page import="servidor.publicar.DtPostulante"%>
<%@page import="servidor.publicar.DtEmpresa"%>

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
         <div class="container my-5">
        <div class="row">

            <div class="col-md-6">
                <div class="card">
                    <div class="card-header">
                        <h1 class="card-title">Lista de Usuarios</h1>
                    </div>
                    <div class="card-body">
                        <ul class="list-group list-group-flush">
                            <% List<DtUsuario> usuarios = (List<DtUsuario>) request.getAttribute("usuarios");
                               for (DtUsuario usuario : usuarios) { %>
                            <li class="list-group-item">
                                <a class="text-decoration-none text-black fw-bold" href="mostrarUsuario?nickname=<%= usuario.getNickname()%>"><%= usuario.getNickname()%></a>        
                                 <% if(usuario instanceof DtPostulante){  %>
                                     <span class="badge rounded-pill text-bg-success">Postulante</span>
                                 
                                <%
                                     }else{%>
                                    <span class="badge rounded-pill text-bg-info">Empresa</span>
                                         <%
                                     }
                                %>
                            </li>
                            <% } %>
                        </ul>
                    </div>
                </div>
            </div>

           <div class="col-md-6">
    <h2 class="mb-4">Buscar Usuario:</h2>
    <form action="consultaUsuario" method="get" class="mb-4">
        <div class="input-group">
            <input type="text" name="nickname" class="form-control" placeholder="Buscar por nickname" required>
            <button class="btn btn-dark" type="submit">Buscar</button>
        </div>
    </form>

    <%
        List<DtUsuario> usuariosFiltrados = (List<DtUsuario>) request.getAttribute("usuariosFiltrados");
        if (usuariosFiltrados != null && !usuariosFiltrados.isEmpty()) {
    %>
        <h3 class="mb-4">Resultados de la búsqueda:</h3>
        <div class="list-group">
            <% for (DtUsuario usuario : usuariosFiltrados) { %>
                <div class="list-group-item">
                    <div class="row align-items-center">
                        <div class="col-md-2">
                            <img src="<%=usuario.getUrlImagen()%>" alt="Imagen de perfil" class="img-thumbnail">
                        </div>
                        <div class="col-md-6">
                            <strong><%=usuario.getNickname()%></strong>
                        </div>
                        <div class="col-md-4 text-end">
                            <a href="mostrarUsuario?nickname=<%= usuario.getNickname()%>" class="btn btn-dark">Ver Perfil</a>
                        </div>
                    </div>
                </div>
            <% } %>
        </div>
    <%
        }
    %>
</div>

<div class="mt-4">
    <a onclick="window.history.back();"  class="btn btn-dark">Volver atrás</a>
</div>
    </div>
		</div>
	</main>
    

</body>
</html>
