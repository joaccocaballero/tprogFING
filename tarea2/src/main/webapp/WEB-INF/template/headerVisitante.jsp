<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<header>
      <nav class="navbar bg-body-tertiary border-bottom border-black">
          <div class="container d-flex py-1 " >
            <div style="width: 20%;">
 				<a class="navbar-brand" href="visitante"><img class="w-50"  src="media/img/trabajo_logo.png" alt=""></a>
            </div>
            <div  style="width: 50%;">
                <form class="d-flex " action="visitante" method="get">
                    <input class="form-control square-corners me-2" name="busqueda" type="search" placeholder="Búsqueda de ofertas laborales" aria-label="Search">
                    <button class="btn btn-outline-light bg-dark square-corners" type="submit"><i class="fas fa-search"></i> Buscar</button>
                 </form>
            </div>
            <div class="d-flex me-5">
                <div class="d-flex border-end px-2">
                    <a href="#" data-bs-toggle="modal" data-bs-target="#loginModal" class="text-decoration-none text-black fw-bold " >Ingresar</a>
                </div>
                <div class="d-flex px-2">
                   <a class="text-decoration-none text-black fw-bold" href="registro">Registrarse</a>
                </div>
            </div>               
          </div>
      </nav>
</header>