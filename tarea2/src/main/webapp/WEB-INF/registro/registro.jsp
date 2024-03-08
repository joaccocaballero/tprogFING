<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="media/styles/bootstrap.min.css?v=<?php echo time(); ?>">
   <script src="media/js/bootstrap.min.js?v=<?php echo time(); ?>"></script>
   <script src="media/js/jquery-3.7.1.min.js?v=<?php echo time(); ?>"></script>
    <title>Trabajo.uy</title>
</head>
<script type="text/javascript">
	function verificarDisponibilidadEmail(esEmpresa) {
		let email;
		if (esEmpresa) {
			email = document.getElementById("email").value;
		} else {
			email = document.getElementById("emailPost").value;
		}	    
		if (email.trim() !== "") {
	        $.post("./verificarEmail", { email: email })
	            .done(function (respuesta) {
					var disponible = respuesta == true;
					if (disponible){
						esEmpresa ? 
		                	mostrarMensajeDisponibilidadEmailEmpresa("Email disponible")
		                	: mostrarMensajeDisponibilidadEmailPost("Email disponible");
					} else {
						esEmpresa ? 
		                	mostrarMensajeDisponibilidadEmailEmpresa("Email no disponible")
		                	: mostrarMensajeDisponibilidadEmailPost("Email no disponible");
					}
	            })
	            .fail(function () {
	                mostrarMensajeDisponibilidad("Email no disponible");
	            });
	    } else {
	    	mostrarMensajeDisponibilidad("");
	    }
	}
	
	function verificarDisponibilidadNickname(esEmpresa) {
		let nickname;
		if (esEmpresa) {
			nickname = document.getElementById("nickname").value;
		} else {
			nickname = document.getElementById("nicknamePost").value;
		}
	     
	    if (nickname.trim() !== "") {
	        $.post("./verificarNickname", { nickname: nickname})
	            .done(function (respuesta) {
	            	var disponible = respuesta == true;
					if (disponible){
						esEmpresa ? 
		                	mostrarMensajeDisponibilidadNicknameEmpresa("Nickname disponible")
		                	: mostrarMensajeDisponibilidadNicknamePost("Nickname disponible");
					} else {
		            	esEmpresa ?
	            			mostrarMensajeDisponibilidadNicknameEmpresa("Nickname no disponible")
	            			: mostrarMensajeDisponibilidadNicknamePost("Nickname no disponible");
					}	            })
	            .fail(function () {
	                mostrarMensajeDisponibilidad("Email no disponible");
	            });
	    } else {
	        mostrarMensajeDisponibilidad("holi");
	    }
	}
	
	function mostrarMensajeDisponibilidadEmailPost(mensaje) {
	    var mensajeElemento = document.getElementById("emailPost");
	    mensajeElemento.innerHTML = mensaje;
	}
	function mostrarMensajeDisponibilidadEmailEmpresa(mensaje) {
	    var mensajeElemento = document.getElementById("emailEmpresa");
	    mensajeElemento.innerHTML = mensaje;
	}
	function mostrarMensajeDisponibilidadNicknamePost(mensaje) {
	    var mensajeElemento = document.getElementById("nickPost");
	    mensajeElemento.innerHTML = mensaje;
	}
	function mostrarMensajeDisponibilidadNicknameEmpresa(mensaje) {
	    var mensajeElemento = document.getElementById("nickEmpresa");
	    mensajeElemento.innerHTML = mensaje;
	}
</script>
<body> 
    <main>
		<% if (request.getAttribute("error") != null) { %>
		    <div id="errorDiv" class="alert alert-danger">
		        ${requestScope.error}
		    </div>
		<% } %>
		<script>
		setTimeout(function() {
		    var errorDiv = document.getElementById("errorDiv");
		    if (errorDiv) {
		        errorDiv.style.display = "none";
		    }
		}, 3000);
		</script>
        <section class="h-auto w-auto bg-light">
            <div class="container h-100">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col-lg-8 col-md-10">
                        <div class="card card-registration my-1">
                            <div class="row g-0">
                                <div class="col-xl-9">
                                    <div class="card-body p-md-5 text-black">
                                        <h3>Registrarse como:</h3>
                                        <select id="registerAs" class="form-control mb-4">
                                            <option value="postulante">Postulante</option>
                                            <option value="empresa">Empresa</option>
                                        </select>

                                        <form id="postulanteForm" action="registro" method="post">
                                            <input type="hidden" name="action" value="altaPostulante">
                                            <div class="form-group mb-2">
                                                <label for="nickname">Nickname:</label>
                                                <input type="text" id="nicknamePost" name="nickname" class="form-control" oninput="verificarDisponibilidadNickname(false)" required>
                                                <span id="nickPost"></span>
                                                
                                            </div>
                                            <div class="form-group mb-2">
					                              <label for="nombre">Nombre:</label>
					                              <input type="text" id="nombre" name="nombre" class="form-control" required>
					                          </div>
					                          <div class="form-group mb-2">
					                              <label for="apellido">Apellido:</label>
					                              <input type="text" id="apellido" name="apellido" class="form-control" required>
					                          </div>
					                          <div class="form-group mb-2">
					                              <label for="email">Email:</label>
					                              <input type="email" id="emailPost" name="email" class="form-control" oninput="verificarDisponibilidadEmail(false)" required>
					                          	  <span id="emailPost"></span>
					                          </div>
					                          <div class="form-group mb-2">
					                              <label for="fechaNacimiento">Fecha de Nacimiento:</label>
					                              <input type="date" id="fechaNacimiento" name="fechaNacimiento" class="form-control" required>
					                          </div>
					                          <div class="form-group mb-4">
					                              <label for="nacionalidad">Nacionalidad:</label>
					                              <select id="nacionalidad" name="nacionalidad" class="form-control">
					                                  <option value="Uruguayo">Uruguayo</option>
					                                  <option value="Argentino">Argentino</option>
					                                  <option value="Brasileño">Brasileño</option>
					                              </select>
					                          </div>
					                           <div class="form-group mb-2">
			                                        <label for="password">Contraseña:</label>
			                                        <input type="password" id="password" name="password" autocomplete="off" class="form-control" required>
			                                   		 <small>La contraseña debe tener al menos 6 caracteres.</small>
			                                    </div>
			                                <div class="form-group mb-3">
												    <label for="confirmar_contrasena">Confirmar Contraseña:</label>
											        <input type="password" id="confirmar_contrasena" name="confirmar_contrasena" autocomplete="off" class="form-control" required pattern=".{6,}">
										    </div>
					                          
                                            <button type="button" class="btn btn-dark btn-lg" onclick="window.location.href='visitante';">Cancelar</button>
                                            <button type="submit" id="submitButtonPostulante" class="btn btn-warning btn-lg">Enviar</button>
                                        </form>

                                        <form id="empresaForm" action="registro" method="post"  style="display: none;">
                                           <input type="hidden" name="action" value="altaEmpresa">
                                           <div class="form-group mb-2">
					                            <label for="nickname">Nickname:</label>
					                            <input class="form-control" type="text" id="nickname" name="nickname" oninput="verificarDisponibilidadNickname(true)" required>
					                            <span id="nickEmpresa"></span>
					                        </div>
					                
					                        
					                        <div class="form-group mb-2">
					                            <label for="nombre">Nombre:</label>
					                            <input class="form-control" type="text" id="nombre" name="nombre" required>
					                        </div>
					                
					                        
					                        <div class="form-group mb-2">
					                            <label for="apellido">Apellido:</label>
					                            <input class="form-control" type="text" id="apellido" name="apellido" required>
					                        </div>
					                
					                       
					                        <div class="form-group mb-2">
					                            <label for="email">Email:</label>
					                            <input class="form-control" type="email" id="email" name="email" oninput="verificarDisponibilidadEmail(true)" required>
					                            <span id="emailEmpresa"></span>
					                        </div>
					                
					                        
					                        <div class="form-group mb-2">
					                            <label for="nomEmpresa">Nombre de la Empresa:</label>
					                            <input class="form-control" type="text" id="nomEmpresa" name="nomEmpresa" required>
					                        </div>
					                
					                        
					                        <div class="form-group mb-2">
					                            <label for="desc">Descripción:</label>
					                            <textarea class="form-control" id="desc" name="desc" rows="4" cols="42" required></textarea>
					                        </div>
					                
					                       
					                        <div class="form-group mb-2">
					                            <label for="linkWeb">Sitio Web:</label>
					                            <input class="form-control" type="url" id="linkWeb" name="linkWeb">
					                        </div>
					                        
					                        <div class="form-group mb-2">
										        <label for="password">Contraseña:</label>
										        <input type="password" id="password" name="password" autocomplete="off" class="form-control" required>
										        <small>La contraseña debe tener al menos 6 caracteres.</small>
										    </div>
										    <div class="form-group mb-3">
												    <label for="confirmar_contrasena">Confirmar Contraseña:</label>
											        <input type="password" id="confirmar_contrasena" name="confirmar_contrasena" autocomplete="off" class="form-control" required pattern=".{6,}">
										    </div>
                                            
                                            <div class="mt-3">
	                                            <button type="button" class="btn btn-dark btn-lg" onclick="window.location.href='visitante';">Cancelar</button>
	                                            <button type="submit"  id="submitButtonEmpresa" class="btn btn-warning btn-lg">Enviar</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </main>
    <script src="media/js/register.js"></script>
	<script src="media/js/index.js"></script>
</body>
</html>
