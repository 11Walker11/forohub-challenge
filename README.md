# Forohub Challenge

[![Java](https://img.shields.io/badge/Java-17-blue.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.6.3-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](LICENSE)

## Índice
- [Descripción del Proyecto](#descripción-del-proyecto)
- [Estado del Proyecto](#estado-del-proyecto)
- [Demostración de Funciones y Aplicaciones](#demostración-de-funciones-y-aplicaciones)
- [Acceso al Proyecto](#acceso-al-proyecto)
- [Tecnologías Utilizadas](#tecnologías-utilizadas)
- [Autores del Proyecto](#autores-del-proyecto)

## :file_folder: Descripción del Proyecto
Este es nuestro desafío, llamado ForoHub: en él, vamos a replicar este proceso a nivel de back end y, para eso, crearemos una API REST usando Spring.
Esta ofrece funcionalidades CRUD para la gestión de tópicos, cursos y usuarios. Permite a los usuarios autenticarse, registrar, listar, actualizar y eliminar registros en la base de datos, proporcionando un acceso controlado mediante tokens JWT.

En resumen, nuestro objetivo con este challenge es implementar una API REST con las siguientes funcionalidades:

- API con rutas implementadas siguiendo las mejores prácticas del modelo REST;

- Validaciones realizadas según las reglas de negocio;

- Implementación de una base de datos relacional para la persistencia de la información;

- Servicio de autenticación/autorización para restringir el acceso a la información.

## Estado del Proyecto
![Status](https://img.shields.io/badge/status-en%20desarrollo-yellow.svg)

El proyecto se encuentra en desarrollo, implementando y afinando las funcionalidades principales.

## Funcionalidades
Nuestra API se centrará específicamente en los tópicos, cursos, y debe permitir a los usuarios, lo siguiente:


## Demostración de Funciones y Aplicaciones
### Autenticación
- **Endpoint:** `/login`
- **Descripción:** Obtiene el token para el usuario asignado que da acceso al resto de endpoints.

### Gestión de Cursos
- **Registrar Curso:** 
  - **Endpoint:** `POST /cursos`
  - **Descripción:** Registra un nuevo curso en la base de datos.

- **Listar Cursos:** 
  - **Endpoint:** `GET /cursos`
  - **Descripción:** Obtiene el listado de cursos.

- **Actualizar Curso:** 
  - **Endpoint:** `PUT /cursos/{id}`
  - **Descripción:** Actualiza los datos de un curso existente.

- **Eliminar Curso:** 
  - **Endpoint:** `DELETE /cursos/{id}`
  - **Descripción:** Elimina un curso registrado.

### Gestión de Tópicos
- **Registrar Tópico:** 
  - **Endpoint:** `POST /topicos`
  - **Descripción:** Registra los tópicos en la base de datos.

- **Listar Tópicos:** 
  - **Endpoint:** `GET /topicos`
  - **Descripción:** Obtiene el listado de los tópicos.

- **Actualizar Tópico:** 
  - **Endpoint:** `PUT /topicos/{id}`
  - **Descripción:** Actualiza los datos de un tópico existente.

- **Eliminar Tópico:** 
  - **Endpoint:** `DELETE /topicos/{id}`
  - **Descripción:** Elimina un tópico registrado.

### Gestión de Usuarios
- **Registrar Usuario:** 
  - **Endpoint:** `POST /usuarios`
  - **Descripción:** Registra los usuarios en la base de datos.

- **Listar Usuarios:** 
  - **Endpoint:** `GET /usuarios`
  - **Descripción:** Obtiene el listado de los usuarios.

- **Actualizar Usuario:** 
  - **Endpoint:** `PUT /usuarios/{id}`
  - **Descripción:** Actualiza los datos de un usuario existente.

- **Eliminar Usuario:** 
  - **Endpoint:** `DELETE /usuarios/{id}`
  - **Descripción:** Elimina un usuario registrado.

## :computer:Tecnologías Utilizadas
- Java JDK 17: Lenguaje de programación utilizado.
- Spring Boot 2.6.3: Framework para la creación de aplicaciones web.
- Maven: Herramienta de gestión de proyectos y dependencias.
- JWT: Biblioteca para la autenticación basada en tokens JSON Web Tokens.
- H2 Database: Base de datos en memoria utilizada para pruebas y desarrollo.

## :stars: Autores del Proyecto
- *ALAN AMASTAL FABIAN* - [11Walker11](https://github.com/11walker11)

## Acceso al Proyecto
Para clonar y ejecutar este proyecto, necesitarás Git y Java 11 instalados en tu máquina. Desde tu terminal, ejecuta:
```bash
# Clonar el repositorio
git clone https://github.com/tuusuario/forohub_challenge.git

# Ir al directorio del proyecto
cd forohub_challenge

# Ejecutar el proyecto con Maven
./mvnw spring-boot:run


