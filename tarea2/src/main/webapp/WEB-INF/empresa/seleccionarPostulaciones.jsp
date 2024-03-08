<%@page import="servidor.publicar.DtOferta"%>
<%@page import="servidor.publicar.DtPostulacion"%>
<%@page import="java.util.List"%>
<%@page import="java.time.format.DateTimeFormatter"%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <jsp:include page="/WEB-INF/template/head.jsp"/>
    <%   String imgPerfilJSON = (String) request.getAttribute("imgPerfil");
		 %>
</head>
<script>
        function validarFormulario() {
            var inputs = document.getElementsByName("posiciones[]");
            var valores = [];

            for (var i = 0; i < inputs.length; i++) {
                var valor = inputs[i].value;

                // Verificar si el valor es un número entero
                if (isNaN(valor) || parseInt(valor) !== parseFloat(valor) || valor < 0) {
                    alert("Ingrese posiciones validas.");
                    return false;
                }

                // Almacenar el valor para verificar duplicados
                valores.push(valor);
            }

            // Verificar duplicados
            var uniqueValores = valores.filter(function (value, index, self) {
                return self.indexOf(value) === index;
            });

            if (valores.length !== uniqueValores.length) {
                alert("Ingrese posiciones distintas para cada postulacion");
                return false;
            }

            return true;
        }
    </script>
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
		        <form action="./seleccionarPostulaciones" method="post" onsubmit="return validarFormulario();">
		  
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
			    		int index = 1;
			        	int listaSize = posts.size();
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
			                <%
			                	if(!post.getUrlVideo().equals("")){
			               	%>
			             	<div class="d-flex mb-2 my-3 justify-content-center">
								<iframe width="420" height="315"
									src=<%=post.getUrlVideo()%>>
								</iframe>				      	
					      	<% } %>
			            </div>
			            	<p class="m-0"><span class="fw-bold">Posición deseada: </span>
			            		<input type="number" name="posiciones[]" value="<%= index++ %>" min=1  max="<%= listaSize %>" style="width: 50px;" />
			        		</p>
			        </div>
					<div class="mt-4 text-center">	
						<button type="submit" class="btn ml-4 btn-dark">Enviar Posiciones</button>
					</div>
			</div>
						<% } %>
			<% } %>
				
		</form>
		 <div class="mt-4 text-center">
            <a onclick="window.history.back();" class="btn btn-dark">Volver atrás</a>
			   
        </div>
    </div>
    </main>
</body>

</html>