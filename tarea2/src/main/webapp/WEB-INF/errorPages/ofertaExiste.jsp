<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }

        .error-container {
            text-align: center;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
        }

        h1 {
            color: #e74c3c;
        }

        p {
            color: #333;
        }

        button {
            padding: 10px 20px;
            background-color: rgb(255,193,7);
            color: black;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <div class="error-container">
        <h1>Error</h1>
         <% if (request.getAttribute("error") != null) { %>
		    <p>
		        ${requestScope.error}
		    </p>
		<% } else {%>
	        <p>Ha ocurrido un error.</p>
		<% } %>
        <button onclick="goBack()">Volver Atrás</button>
    </div>

    <script>
        function goBack() {
            window.history.back();
        }
    </script>
</body>
</html>
    