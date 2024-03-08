<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ page import="java.util.List" %>
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
<!DOCTYPE html>
<html lang="en">
<head>
     <jsp:include page="/WEB-INF/template/head.jsp" />
     <% Gson gson = new GsonBuilder()
     		    .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
     		    .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
     		    .create(); 
        %>
        <%   String imgPerfilJSON = (String) request.getAttribute("imgPerfil");
        	System.out.println(imgPerfilJSON);
		 %>
</head>
<body>
    <header>
        <nav class="navbar p-3  mb-4 border-bottom border-black">
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
    <div class="container">
   			  <div class="row">
             <div class="col-md-2 mx-auto mt-3 col-sm-12">
            <div class="bg-light p-2 rounded">
                <h5 class="text-center mb-3">Menú</h5>
                 <div class="list-group">
			        <a href="/tarea2/altaOfertaLaboral" class="list-group-item list-group-item-action d-flex align-items-center">
			            <i class="fas fa-plus-circle me-3"></i> Alta de Oferta Laboral
			        </a>
			        <a href="/tarea2/consultaUsuario" class="list-group-item list-group-item-action d-flex align-items-center">
			            <i class="fas fa-user me-3"></i> Consulta de Usuario
			        </a>
			        <a href="/tarea2/consultaTipos" class="list-group-item list-group-item-action d-flex align-items-center">
			            <i class="fas fa-file-alt me-3"></i> Consulta de Tipo de Publicación
			        </a>
			        <a href="/tarea2/consultaPostulacionAOferta" class="list-group-item list-group-item-action d-flex align-items-center">
			            <i class="fas fa-briefcase me-3"></i> Consulta de Postulación a Oferta Laboral
			        </a>
			        <a href="/tarea2/mostrarSeleccionarOferta" class="list-group-item list-group-item-action d-flex align-items-center">
			           <i class="fas fa-house"></i>
			           <svg xmlns="http://www.w3.org/2000/svg"  height="35" width="35" viewBox="0 0 448 512">
			           <path d="M64 80c-8.8 0-16 7.2-16 16V416c0 8.8 7.2 16 16 16H384c8.8 0 16-7.2 16-16V96c0-8.8-7.2-16-16-16H64zM0 96C0 60.7 28.7 32 64 32H384c35.3 0 64 28.7 64 64V416c0 35.3-28.7
			            64-64 64H64c-35.3 0-64-28.7-64-64V96zM337 209L209 337c-9.4 9.4-24.6 9.4-33.9 0l-64-64c-9.4-9.4-9.4-24.6 0-33.9s24.6-9.4 33.9 0l47 47L303 175c9.4-9.4 24.6-9.4 33.9 0s9.4 24.6 0 33.9z"/></svg>
			           <span class="m-2">Seleccionar Postulación a Oferta Laboral</span>
			        </a>
			        <a href="/tarea2/modificarDatosUsuario" class="list-group-item list-group-item-action d-flex align-items-center">
			          <svg xmlns="http://www.w3.org/2000/svg" height="25" width="25" viewBox="0 0 512 512">
			          <path d="M471.6 21.7c-21.9-21.9-57.3-21.9-79.2 0L362.3 51.7l97.9 97.9 30.1-30.1c21.9-21.9 21.9-57.3 0-79.2L471.6 21.7zm-299.2 220c-6.1 
			          6.1-10.8 13.6-13.5 21.9l-29.6 88.8c-2.9 8.6-.6 18.1 5.8 24.6s15.9 8.7 24.6 5.8l88.8-29.6c8.2-2.7 15.7-7.4 21.9-13.5L437.7 172.3 339.7 
			          74.3 172.4 241.7zM96 64C43 64 0 107 0 160V416c0 53 43 96 96 96H352c53 0 96-43 96-96V320c0-17.7-14.3-32-32-32s-32 14.3-32 32v96c0 17.7-14.3
			           32-32 32H96c-17.7 0-32-14.3-32-32V160c0-17.7 14.3-32 32-32h96c17.7 0 32-14.3 32-32s-14.3-32-32-32H96z"/></svg>
                       <span class="m-2">Modificar Datos Personales</span>
                    </a>
			    </div>
            </div>
        </div>

       <div class="col-md-8 mx-auto" id="mainOfertas">
    <div class="row">
    
    	<form class="d-flex col-12 my-3" action="empresa" method="get">
                    <input class="form-control square-corners me-2" name="busqueda" type="search" placeholder="Búsqueda de ofertas laborales" aria-label="Search">
                    <button class="btn btn-outline-light bg-dark square-corners" type="submit"><i class="fas fa-search"></i> Buscar</button>
        </form>
    
    
    </div>
    <div class="col-md-12 col-sm-12 mx-auto">
					    <% 
					        List<DtPublicacion> publicaciones = (List<DtPublicacion>) request.getAttribute("publicaciones");
					        for(DtPublicacion publicacion : publicaciones) {
					    %>
					        <div class="d-flex p-2  border border-dark align-items-center mb-3">
					            <div style="width: 25%;">
					                <img class="w-75" src="<%= publicacion.getDtOferta().getUrlImagen()%>" alt="">
					            </div>
					            <div class="w-75">
					                <div class="d-flex flex-column">
					                    <h3><%= publicacion.getDtOferta().getNombre() %></h3>
					                    <div>
					                        <p>
					                            <%= publicacion.getDtOferta().getDescripcion() %>
					                        </p>
					                    </div>
					                    <div class="d-flex flex-column mb-2">
					                        <b>Departamento: <%= publicacion.getDtOferta().getDepartamento() %></b>
					                        <b>Ciudad: <%= publicacion.getDtOferta().getCiudad() %></b>
					                    </div>
					                    <div class="d-flex gap-1 justify-content-start">
					                   <%   
									        List<String> keywordsDeOferta = publicacion.getDtOferta().getKeywords();
									       
									        for(String keyword : keywordsDeOferta) {
									    %>
									     	 <span class="keyword"><%= keyword %></span>
									    <% 
									        }
									    %>
					                    </div>
					                    <div class="d-flex justify-content-end">
					                       <span class="badge bg-dark"><a href="consultaOferta?nombreOferta=<%= URLEncoder.encode(publicacion.getDtOferta().getNombre(), "UTF-8") %>" class="text-white text-decoration-none">Ver más</a></span>
					                        
					                    </div>
					                </div>
					            </div>
					        </div>
					    <% 
					        }
					    %>
					</div>
					</div>
		    		  <div class="col-md-2  mt-3 col-sm-12">
			               <div class=" container-fluid p-3 mx-auto border border-dark text-center" id="keywordList">
			                <h4>Filtrar Publicaciones:</h4>
			                <h5>Keywords</h5>
			                <br>
			                 <form action="empresa" method="get">
						        <%
						            List<String> keywordsList = (List<String>) request.getAttribute("keywords");
						        %>
						        <% for(String keyword : keywordsList) { %>
						            <div class="form-check">
						                <input class="form-check-input" name="keywords" type="checkbox" value="<%=keyword %>" id="flexCheckChecked<%=keyword %>">
						                <label class="form-check-label" for="flexCheckChecked<%=keyword %>">
						                    <%= keyword %>
						                </label>
						            </div>
						        <% } %>
						        <button type="submit" class="btn btn-warning w-75 m-3">Filtrar</button>
						    </form>     
			              </div>
			            </div>
     </div>
     </div>
       
    </main>
</body>
	<script src="media/js/index.js"></script>
</html>