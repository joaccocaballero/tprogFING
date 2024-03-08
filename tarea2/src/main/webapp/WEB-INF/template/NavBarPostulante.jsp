<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
    .nav-item {
        border-right: 1px solid white;
        padding-right: 10px; 
        margin-right: 10px; 
    }
    .nav-item:last-child {
        border-right: none;
    }
</style>

<nav class="navbar navbar-expand-lg bg-body-tertiary bg-navbar">
    <div class="container-fluid justify-content-center">
        <div class="collapse navbar-collapse justify-content-center">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link active text-white" aria-current="page"
                       href="/tarea2/postulante">Inicio</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active text-white" aria-current="page" href="/tarea2/consultaUsuario">Consulta de Usuario</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active text-white" aria-current="page" href="/tarea2/consultaTipos">Consulta de Tipo de
                        Publicación</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-white" href="/tarea2/consultaPostulacionAOferta">Consulta Postulacion a Oferta Laboral</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-white" href="/tarea2/postularAOferta">Postular a Oferta Laboral</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-white" href="/tarea2/modificarDatosUsuario">Modificar Datos Personales</a>
                </li>
            </ul>
        </div>
    </div>
</nav>