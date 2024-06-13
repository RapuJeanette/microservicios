#!/bin/bash

# Navegar al directorio del frontend
cd src/main/java/authservice/auth_service/fronted

# Instalar dependencias y construir el frontend
npm install
npm run build

# Volver a la raíz del proyecto
cd ../../../../../../

# Construir el proyecto con Maven
mvn clean install

# Ejecutar la aplicación Spring Boot
mvn spring-boot:run
