<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <jsp:include page="/WEB-INF/mobile/headMobile.jsp"/>
</head>
<body class="bg-light d-flex justify-content-center align-items-center vh-100">
    <div class="col-10 col-md-4">
        <div class="card shadow">
            <div class="card-header bg-secondary text-white text-center">
                <h4>Iniciar Sesión</h4>
            </div>
            <div class="card-body">
                <form action="iniciar-sesion" method="post">
                    <div class="mb-3">
                        <label for="loginMobile" class="form-label">Correo Electrónico</label>
                        <input type="text" class="form-control" id="loginMobile" name="login" placeholder="Ingrese su email" required>
                    </div>
                    <div class="mb-3">
                        <label for="passwordMobile" class="form-label">Contraseña</label>
                        <input type="password" class="form-control" id="passwordMobile" name="password" placeholder="Ingrese su contraseña" required autocomplete="current-password">
                    </div>
                    <div class="d-grid">
                        <button type="submit" class="btn btn-warning btn-lg">Iniciar sesión</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <jsp:include page="/WEB-INF/mobile/footerMobile.jsp"/>
</body>
</html>
