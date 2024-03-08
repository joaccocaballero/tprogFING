<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.google.gson.Gson" %>
<%@ page import="com.google.gson.GsonBuilder" %>
<%@ page import="com.google.gson.reflect.TypeToken" %>
<%@ page import="java.lang.reflect.Type" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.util.List" %>
<%@ page import="servidor.publicar.DtTipoPublicacion" %> 
<%@ page import="utils.LocalDateSerializer" %> 
<%@ page import="utils.LocalDateTimeAdapter" %> 

<!DOCTYPE html>
<html lang="en">
    <head>
        <jsp:include page="/WEB-INF/template/head.jsp"/>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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
        <% if (request.getAttribute("error") != null) { %>
		    <div id="errorDiv" class="alert alert-danger">
		        ${requestScope.error}
		    </div>
		<% } %>
           <jsp:include page="/WEB-INF/template/NavBarEmpresa.jsp"/>
            <div class="d-flex flex-column justify-content-start">
                <div class="col-12">
                    <form class="d-flex justify-content-between gap-3" action= "altaOfertaLaboral" method = "post">
                        <div class="col-6 p-5">
                            <div class="mb-3">
                                <label class="form-label">Seleccione un Tipo de Publicación:</label>
                                <select class="form-select" id="floatingSelect" name="tipoPublicacion">
								    <%
								    	List<DtTipoPublicacion> tiposPub = (List<DtTipoPublicacion>) request.getAttribute("tiposPub");
								        if(tiposPub!=null){
								    	for(DtTipoPublicacion tipo : tiposPub) {
								    %>
								        <option value="<%= tipo.getNombre() %>"><%= tipo.getNombre() %></option>
								    <%
								        }}
								    %>
								</select>
                            </div>
                            <div class="mb-3 d-flex gap-5 justify-content-between">
                                <div class="col-5">
                                    <label class="form-label">Nombre:</label>
                                    <input type="text" class="form-control" placeholder="Ingrese Nombre" name ="nombre">
                                </div>
                                <div class="col-5">
                                    <label for="exampleInputPassword1" class="form-label">Remuneración:</label>
                                    <input type="number" class="form-control" placeholder="Ingrese Remuneración" name = "remuneracion">
                                </div>
                            </div>
                            <div class="mb-3 d-flex gap-5 justify-content-between">
                                <div class="col-5">
                                    <label for="exampleInputPassword1" class="form-label">Horario:</label>
                                    <input type="text" class="form-control" placeholder="Ingrese un horario" name = "horario">
                                </div>
                                <div class="col-5">
                                    <label for="exampleInputPassword1" class="form-label">Departamento:</label>
                                    <select class="form-select" id="floatingSelect" aria-label="Floating label select example" name= "departamento">
                                        <option value="artigas">Artigas</option>
                                        <option value="canelones">Canelones</option>
                                        <option value="cerro_largo">Cerro Largo</option>
                                        <option value="colonia">Colonia</option>
                                        <option value="durazno">Durazno</option>
                                        <option value="flores">Flores</option>
                                        <option value="florida">Florida</option>
                                        <option value="lavalleja">Lavalleja</option>
                                        <option value="maldonado">Maldonado</option>
                                        <option value="montevideo">Montevideo</option>
                                        <option value="paysandu">Paysandú</option>
                                        <option value="rio_negro">Río Negro</option>
                                        <option value="rivera">Rivera</option>
                                        <option value="rocha">Rocha</option>
                                        <option value="salto">Salto</option>
                                        <option value="san_jose">San José</option>
                                        <option value="soriano">Soriano</option>
                                        <option value="tacuarembo">Tacuarembó</option>
                                        <option value="treinta_y_tres">Treinta y Tres</option>
                                    </select>
                                </div>
                            </div>
                            <div class="mb-3 d-flex gap-5 justify-content-between">
                                <div class="col-12">
                                    <label class="form-label">Ciudad:</label>
                                    <input type="text" class="form-control" placeholder="Ingrese Ciudad" name = "ciudad">
                                    <div class="mt-3">
                                        <label class="form-label">URL imagen:</label>
                                        <input type="text" class="form-control" placeholder="Ingrese URL de imagen (opcional)" name= "urlImagen">
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-6 p-5">
                            <div class="mb-3">
                                <label for="floatingTextarea2">Descripción:</label>
                                <textarea class="form-control" placeholder="Ingrese una descripción" name= "descripcion"
                                    style="height: 120px;"></textarea>
                            </div>
                            <div class="mb-3">
                                <label for="floatingTextarea2">Seleccione Keywords:</label>
                                <select class="custom-select form-control" multiple name = "keywords">
								    <%
								    	List<String> keywords = (List<String>) request.getAttribute("keywords");
								        if(keywords !=null){
								    	for(String keyword : keywords) {
								    %>
								        <option value="<%= keyword %>"><%= keyword %></option>
								    <%
								        }}
								    %>
								</select>
                            </div>
                            <div class="mb-3">
                                <label>Forma de Pago de Oferta:</label>
                               <div class="mt-2">
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" name="formaPago" value="General">
                                        <label class="form-check-label" for="inlineRadio1">General</label>
                                    </div>
                                    <div class="form-check mb-3 form-check-inline">
                                        <input class="form-check-input" type="radio" name="formaPago" value="Paquete">
                                        <label class="form-check-label" for="inlineRadio2">Paquete adquirido previamente</label>
                                    </div>
                                    <div>
                                        <label class="form-check-label" for="inlineRadio2">Seleccione Paquete:</label>
                                        <select class="form-select" id="paqueteSelect" name="paqueteSeleccionado">
                                            <option selected value="Premium">Premium</option>
    										<option value="Destacado">Destacado</option>
   								 			<option value="Express">Express</option>
    										<option value="Básica">Básica</option>
                                        </select>
                                    </div>
                               </div>
                               
                            </div>
                    <div class="d-flex justify-content-end mt-2">
                        <button type="submit" class="btn btn-dark">Confirmar</button>
                    </div>
                        </div>
              
                    </form>
					</div>
             <div class="mt-4 mb-5 text-center">
            <a onclick="window.history.back();" class="btn btn-dark">Volver atrás</a>
        </div>
                </div>
        </main>
        <script>
        $(document).ready(function() {
            
            $("input[name='formaPago']").change(function() {
                if ($("input[name='formaPago']:checked").val() == "Paquete") {
                    $("#paqueteSelect").prop("disabled", false);
                } else {
                    $("#paqueteSelect").prop("disabled", true);
                }
            });
            $("#paqueteSelect").prop("disabled", true);
        });
</script>
    </body>
    <script>
    /*
    $('#multiple-select-field').select2({
            theme: "bootstrap-5",
            width: $(this).data('width') ? $(this).data('width') : $(this).hasClass('w-100') ? '100%' : 'style',
            placeholder: $(this).data('placeholder'),
            closeOnSelect: false,
        });
    */
    </script>

</html>
