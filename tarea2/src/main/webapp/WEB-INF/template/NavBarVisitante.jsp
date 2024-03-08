<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<style>
    .nav-item {
        border-right: 1px solid white;
        padding-right: 10px; /* Añade espacio entre el texto y el borde */
        margin-right: 10px;  /* Añade espacio entre el borde y el siguiente item */
    }
    .nav-item:last-child {
        border-right: none;  /* Remueve el borde del último item */
    }
</style>

<nav class="navbar navbar-expand-lg bg-body-tertiary bg-navbar">
    <div class="container-fluid justify-content-center">
        <div class="collapse navbar-collapse justify-content-center">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link active text-white" aria-current="page" href="/tarea2/visitante">Inicio</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active text-white" aria-current="page" href="/tarea2/consultaUsuario">Consulta de Usuario</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active text-white" href="/tarea2/consultaTipos">Consultar Tipos de Publicación</a>
                </li>
            </ul>
        </div>
    </div>
</nav>