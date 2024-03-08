#!/bin/bash

# Define la ruta al archivo .properties
PROPERTIES_FILE="$HOME/trabajoUy/.properties"

# Leer las URLs de los servicios del archivo .properties
SERVICE_OFERTAS_URL=$(grep "serviceOfertas" $PROPERTIES_FILE | cut -d'=' -f2)
SERVICE_USUARIOS_URL=$(grep "serviceUsuarios" $PROPERTIES_FILE | cut -d'=' -f2)

# Función para ejecutar wsimport
execute_wsimport() {
    local service_url=$1
    echo "Ejecutando wsimport para $service_url"
    lib/jaxws-ri/bin/wsimport.sh -keep -s src/main/java "${service_url}?wsdl"

    if [ $? -ne 0 ]; then
        echo "wsimport falló para $service_url"
        exit 1
    fi
}

# Verificar si las URLs de los servicios fueron encontradas
if [ -z "$SERVICE_OFERTAS_URL" ] || [ -z "$SERVICE_USUARIOS_URL" ]; then
    echo "Una o más URLs de servicios no fueron encontradas en el archivo .properties"
    exit 1
fi
cd /lib/jaxws-ri/bin/
chmod 777 *.sh
cd -

# Ejecutar wsimport para cada servicio
execute_wsimport $SERVICE_OFERTAS_URL
execute_wsimport $SERVICE_USUARIOS_URL

echo "wsimport completado exitosamente para ambos servicios. Procediendo con Maven clean install..."

# Ejecutar Maven clean install
mvn clean install

if [ $? -eq 0 ]; then
    echo "Maven build exitoso."
	    # Ruta del archivo .war que quieres mover
	archivo_war="target/tarea2.war"

	# Directorio destino
	destino="$HOME/Desktop/apache-tomcat/webapps"

	# Mover el archivo
	mv "$archivo_war" "$destino"
else
    echo "Maven build falló."
    exit 1
fi

