#!/bin/bash

# Navegar al directorio del proyecto si es necesario
# cd /ruta/al/directorio/del/proyecto

# Ejecutar Maven clean install
mvn clean install

# Verificar si Maven construyó con éxito
if [ $? -eq 0 ]; then
    echo "Maven build fue exitoso. Continuando con el script..."

    # Copiar la carpeta datscsv a target
    cp -r DatosCSV target/

    # Ejecutar el archivo JAR
    java -jar target/tarea1-jar-with-dependencies.jar
else
    echo "Maven build falló. Abortando el script."
    exit 1
fi

