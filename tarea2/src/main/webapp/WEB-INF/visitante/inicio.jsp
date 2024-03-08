<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<%@ page import="net.java.dev.jaxb.array.StringArray" %>


<!DOCTYPE html>
<html>
	<head>
    <jsp:include page="/WEB-INF/template/head.jsp"/>
	   <script>
	        <% if (request.getAttribute("sessionExpired") != null) { %>
	            alert("La sesión ha expirado. Por favor, inicie sesión nuevamente.");
	        <% } %>
	    </script>   
	    <% Gson gson = new GsonBuilder()
     		    .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
     		    .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
     		    .create(); 
        %>
	</head>
	<body>
		<jsp:include page="/WEB-INF/template/headerVisitante.jsp" />
		
		<% 
		String errorMessage = (String)session.getAttribute("errorMessage");
		session.removeAttribute("errorMessage"); 
		%>
		<% if(errorMessage != null && !errorMessage.isEmpty()) { %>
		     <div class="alert alert-danger error-message" role="alert">
		        <%= errorMessage %>
		    </div>
		<% } %>
		<script type="text/javascript">
		    $(document).ready(function() {
		        setTimeout(function() {
		            $(".error-message").fadeOut("slow");
		        }, 3000);
		    });
		</script>
		
		<%
		    String mensaje = request.getParameter("mensaje");
		    if (mensaje != null) {
			%>
			<div id="mensajeDiv" class="alert alert-success">
			    <%= mensaje %>
			</div>
			<script>
			    setTimeout(function() {
			        var mensajeDiv = document.getElementById('mensajeDiv');
			        if (mensajeDiv) {
			            mensajeDiv.style.display = 'none';
			        }
			    }, 3000); // 3000 milisegundos (3 segundos)
			</script>
			<%
			    }
		%>

		<div class="modal fade" id="loginModal" tabindex="-1" aria-labelledby="loginModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="loginModalLabel">Iniciar Sesión</h5>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      <div class="modal-body">
		        <form action="iniciar-sesion" method="post">
			          <div class="form-group mb-2">
			            <label class="mb-3" for="login">Email</label>
			            <input type="text" class="form-control" id="login" name="login" placeholder="Ingrese su email">
			          </div>
			          <div class="form-group mb-2">
			            <label class="mb-3" for="password">Contraseña</label>
			            <input type="password" class="form-control" id="password" name="password" placeholder="Ingrese su contraseña">
			          </div>
			      
			      <div class="modal-footer">
			        <button type="button" class="btn btn-secondary btn-dark" data-bs-dismiss="modal">Cerrar</button>
			        <button type="submit" class="btn btn-warning">Iniciar sesión</button>
			      </div>
		      </form>
		      </div>
		    </div>
		  </div>
		</div>
		<jsp:include page="/WEB-INF/template/NavBarVisitante.jsp" />
		<main>	
			   <div class="container-fluid mt-3">
			        <div class="row">
			            <div class="col-md-2  mt-2 col-sm-12">
			               <div class=" container-fluid p-3 mx-auto border border-dark text-center" id="keywordList">
			                <h4>Filtrar Publicaciones:</h4>
			                <h5>Keywords</h5>
			                <br>
			                 <form action="visitante" method="get">
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
			            
			           <div class="col-md-9 p-2 col-sm-12 mx-auto" id="mainContent">
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
			      </div>
		</main>
	    <script src="media/js/login.js"></script>
	    <script src="media/js/index.js"></script>
	</body>
</html>