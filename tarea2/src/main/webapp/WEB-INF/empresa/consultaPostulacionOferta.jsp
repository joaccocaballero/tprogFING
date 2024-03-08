<%@ page import="servidor.publicar.DtOferta"%>
<%@ page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <jsp:include page="/WEB-INF/template/head.jsp"/>
    <%   String imgPerfilJSON = (String) request.getAttribute("imgPerfil");
		 %>
</head>

<body>
    <header>
        <nav class="navbar p-3">
            <div class="d-flex justify-content-between align-items-center w-100" style="height: 8vh;">
                <div class="d-flex">
                    <div class="ms-5">
                        <a class="navbar-brand" href="/tarea2/visitante"><img class="img-fluid w-50" src="media/img/trabajo_logo.png" alt=""></a>
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
         <jsp:include page="/WEB-INF/template/NavBarEmpresa.jsp"/>
  <div class="container my-4 p-4">
    <h2 class="text-center mb-4">Seleccione una Oferta:</h2>
    <div class="list-group">
        <% List<DtOferta> ofertas = (List<DtOferta>) request.getAttribute("ofertas");
           for (DtOferta oferta : ofertas) { %>
            <a  href="mostrarPostulacion?nombre=<%= oferta.getNombre()%>" class="list-group-item list-group-item-action fw-bold">
                <%= oferta.getNombre()%> >
            </a>
        <% } %>
    </div>
</div>
 <div class="mt-4 text-center">
            <a onclick="window.history.back();" class="btn btn-dark">Volver atrás</a>
        </div>
    </div>
    </main>
</body>

</html>