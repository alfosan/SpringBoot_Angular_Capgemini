# Ludoteca de Capgemini

Este proyecto es una aplicación web desarrollada con Spring Boot para el backend y Angular para el frontend. La aplicación permite gestionar una ludoteca, incluyendo la gestión de usuarios, juegos y reservas.

## Arquitectura del Proyecto

El proyecto está dividido en dos partes principales:

1. **Backend**: Implementado con Spring Boot.
2. **Frontend**: Implementado con Angular.

### Backend

El backend está desarrollado con Spring Boot y proporciona una API RESTful para interactuar con la base de datos y realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre los recursos de la ludoteca.

#### Servicios

- **Gestión de Usuarios**: Servicios para registrar, autenticar y gestionar usuarios.
- **Gestión de Juegos**: Servicios para añadir, actualizar, eliminar y listar juegos.
- **Gestión de Reservas**: Servicios para crear, actualizar y cancelar reservas de juegos.

### Frontend

El frontend está desarrollado con Angular y proporciona una interfaz de usuario interactiva para que los usuarios puedan interactuar con la ludoteca.

#### Componentes

- **Registro y Autenticación**: Formularios para el registro y autenticación de usuarios.
- **Listado de Juegos**: Vista para listar todos los juegos disponibles en la ludoteca.
- **Gestión de Juegos**: Formularios para añadir, actualizar y eliminar juegos.
- **Reservas**: Vista y formularios para gestionar las reservas de juegos.

## Instalación y Ejecución

### Backend

1. Clonar el repositorio.
2. Navegar al directorio del backend.
3. Ejecutar `mvn clean install` para construir el proyecto.
4. Ejecutar `mvn spring-boot:run` para iniciar el servidor.

### Frontend

1. Clonar el repositorio.
2. Navegar al directorio del frontend.
3. Ejecutar `npm install` para instalar las dependencias.
4. Ejecutar `ng serve` para iniciar el servidor de desarrollo.

## Contribuciones

Las contribuciones son bienvenidas. Por favor, abre un issue o envía un pull request para discutir cualquier cambio que desees realizar.

## Licencia

Este proyecto está licenciado bajo la Licencia MIT. Consulta el archivo LICENSE para más detalles.
