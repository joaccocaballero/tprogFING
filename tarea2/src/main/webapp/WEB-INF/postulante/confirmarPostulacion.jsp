<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="servidor.publicar.DtOferta"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		 <jsp:include page="/WEB-INF/template/head.jsp"/>
		 <%   String imgPerfilJSON = (String) request.getAttribute("imgPerfil");
		 %>
	</head>
	<script>
		setTimeout(function() {
		    var errorDiv = document.getElementById("errorDiv");
		    if (errorDiv) {
		        errorDiv.style.display = "none";
		    }
		}, 5000);
	</script>
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
    <jsp:include page="/WEB-INF/template/NavBarPostulante.jsp" />
    <div class="container my-5">
        <% if (request.getAttribute("error") != null) { %>
            <div id="errorDiv" class="alert alert-danger">
                ${requestScope.error}
            </div>
        <% } %>

        <% DtOferta oferta = (DtOferta) request.getAttribute("ofertaSeleccionada"); %>
        <div class="card mb-4">
            <div class="row g-0">
                <div class="col-md-4">
                    <img src="<%= oferta.getUrlImagen() %>" class="img-fluid rounded-start" alt="">
                </div>
                <div class="col-md-8">
                    <div class="card-body">
                        <h3 class="card-title"><%= oferta.getNombre() %></h3>
                        <p class="card-text"><%= oferta.getDescripcion() %></p>
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item"><b>Departamento: </b><%= oferta.getDepartamento() %></li>
                            <li class="list-group-item"><b>Ciudad: </b><%= oferta.getCiudad() %></li>
                            <li class="list-group-item"><b>Horario: </b><%= oferta.getHorario() %></li>
                            <li class="list-group-item"><b>Remuneración: </b><%= oferta.getRemuneracion() %></li>
                        </ul>
                        <div class="mt-3">
                            <%
                            List<String> keywordsDeOferta = oferta.getKeywords();
                            for (String keyword : keywordsDeOferta) {
                            %>
                                <span class="badge bg-dark">#<%= keyword %></span>
                            <%
                            }
                            %>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="card">
            <div class="card-body">
                <form action="confirmarPostulacion?NombreOferta=<%= oferta.getNombre() %>" method="post">
                    <div class="row mb-3">
                        <div class="col">
                            <textarea class="form-control" id="cv" name="cv" placeholder="Ingrese CV reducido" rows="10" required></textarea>
                        </div>
                        <div class="col">
                            <textarea class="form-control" id="motivacion" name="motivacion" placeholder="Ingrese Motivación:" rows="10" required></textarea>
                        </div>
                    </div>
                    <div class="row mb-3 justify-content-center">
                    	<div>
                    		<input class="form-control" id="urlVideo" name="urlVideo" placeholder="Ingrese Url de su video">
                    	</div>
                    	
                    </div>
                    <div class="d-flex justify-content-between">
                        <button onClick="retroceder()" type="button" class="btn btn-dark">Volver atrás</button>
                        <button type="submit" class="btn btn-dark">Confirmar</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</main>
	</body>
	<script>
    function retroceder() {
        window.history.back();
    }
	</script>
</html>