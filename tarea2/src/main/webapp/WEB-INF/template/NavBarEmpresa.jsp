<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
    .navbar-nav .nav-item:not(:last-child) {
        border-right: 1px solid white;
    }
    .nav-item {
        padding-right: 10px; /* Añade espacio entre el texto y el borde */
        margin-right: 10px;  /* Añade espacio entre el borde y el siguiente item */
    }
</style>

<nav class="navbar navbar-expand-lg bg-body-tertiary bg-navbar">
    <div class="container-fluid justify-content-center">
        <div class="collapse navbar-collapse justify-content-center">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link active text-white" aria-current="page" href="/tarea2/empresa">Inicio</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active text-white" aria-current="page" href="/tarea2/altaOfertaLaboral">Alta de Oferta Laboral</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active text-white" aria-current="page" href="/tarea2/consultaUsuario">Consulta de Usuario</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-white" href="/tarea2/consultaTipos">Consulta de Tipo De Publicación</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-white" href="/tarea2/consultaPostulacionAOferta">Consulta de Postulación a Oferta Laboral</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-white" href="/tarea2/mostrarSeleccionarOferta">Seleccionar Postulaciones</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-white" href="/tarea2/modificarDatosUsuario">Modificar Datos Personales</a>
                </li>
            </ul>
        </div>
    </div>
</nav>