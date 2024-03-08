<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.List" %>
<%@ page import="servidor.publicar.DtOferta" %>
<%@ page import="servidor.publicar.DtPostulante" %>
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
<html>
	<head>
		<jsp:include page="/WEB-INF/template/head.jsp"/> 
		<% Gson gson = new GsonBuilder()
     		    .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
     		    .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
     		    .create(); 
        %>
        <%   String imgPerfilJSON = (String) request.getAttribute("imgPerfil");
		 %>

		 
	</head>
	<body>
    <header>
        <nav class="navbar p-3  mb-4 border-bottom border-black">
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
       <div class="container">
   			  <div class="row">
             <div class="col-md-2 mx-auto mt-3 col-sm-12">
            <div class="bg-light p-2 rounded">
                <h5 class="text-center mb-3">Menú</h5>
                <div class="list-group">
                    <a href="/tarea2/consultaUsuario" class="list-group-item list-group-item-action d-flex align-items-center">
                        <i class="fas fa-user me-3"></i> Consulta de Usuario
                    </a>
                    <a href="/tarea2/consultaTipos" class="list-group-item list-group-item-action d-flex align-items-center">
                        <i class="fas fa-file-alt me-3"></i> Consulta de Tipo de Publicación
                    </a>
                    <a href="/tarea2/consultaPostulacionAOferta" class="list-group-item list-group-item-action d-flex align-items-center">
                        <i class="fas fa-briefcase me-3"></i> Consulta de Postulación a Oferta Laboral
                    </a>
                    <a href="/tarea2/postularAOferta" class="list-group-item list-group-item-action d-flex align-items-center">
                        <i class="fas fa-paper-plane me-3"></i> Postular a Oferta Laboral
                    </a>
                    <a href="/tarea2/modificarDatosUsuario" class="list-group-item list-group-item-action d-flex align-items-center">
                        <i class="fas fa-paper-plane me-3"></i> Modificar Datos Personales
                    </a>         
                </div>
            </div>
        </div>

       <div class="col-md-8 mx-auto" id="mainOfertas">
    <div class="row">
    
    	<form class="d-flex col-12 my-3" action="postulante" method="get">
                    <input class="form-control square-corners me-2" name="busqueda" type="search" placeholder="Búsqueda de ofertas laborales" aria-label="Search">
                    <button class="btn btn-outline-light bg-dark square-corners" type="submit"><i class="fas fa-search"></i> Buscar</button>
        </form>
    
    
    </div>
    <div class="col-md-12 col-sm-12 mx-auto">
					    <% 
					    	String postu = (String) request.getAttribute("postulante");
					        List<DtPublicacion> publicaciones = (List<DtPublicacion>) request.getAttribute("publicaciones");
					        for(DtPublicacion publicacion : publicaciones) {
					    %>
					        <div class="d-flex p-2  border border-dark align-items-center mb-3">			    
					            <div style="width: 25%;">
					                <img class="w-75" src="<%= publicacion.getDtOferta().getUrlImagen()%>" alt="">
					                <%
						                boolean encontrado = false;
						        		for (DtPostulante post : publicacion.getDtOferta().getFaveados()) {
						        		    if (post.getNickname().equals(postu)) {
						        		        encontrado = true;
						        		        break;
						        		    }
						        		}
					                		
					                	if (encontrado == true) {
					                %>
					                <form action="postulante" method="post">
					                
						                <input type="hidden" name="action" value="desmarcarFavorito">
						                <input type="hidden" name="nickname" value="<%= postu %>">
	    								<input type="hidden" name="nombreOferta" value="<%= publicacion.getDtOferta().getNombre() %>">
						                <button type="submit" class="w-75 mt-4 btn btn-outline-danger"
						                	id="favorito_<%= publicacion.getDtOferta().getNombre() %>">
						                	Desmarcar <span class="badge rounded-pill bg-dark"><%= publicacion.getDtOferta().getFaveados().size()%></span>
						                </button>
						            </form>
						                <% } else {%>
									<form action="postulante" method="post">
						                
						                <input type="hidden" name="action" value="marcarFavorito">
        	    						<input type="hidden" name="nickname" value="<%= postu %>">
						                
	    								<input type="hidden" name="nombreOferta" value="<%= publicacion.getDtOferta().getNombre() %>">
						                <button type="submit" class="w-75 mt-4 btn btn-outline-success"
						                	id="favorito_<%= publicacion.getDtOferta().getNombre() %>">
						                	Marcar <span class="badge rounded-pill bg-dark"><%= publicacion.getDtOferta().getFaveados().size()%></span>
						                </button>
					                </form>
					                <%} %>
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
			                 <form action="postulante" method="get">
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

</html>