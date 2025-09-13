# Mesero

**Mesero** es una aplicación backend desarrollada en Java con Spring Boot, diseñada para la gestión eficiente de pedidos en restaurantes y bares. Permite la toma de órdenes, autenticación de usuarios, gestión de roles y comunicación segura mediante JWT.

---

## Tabla de Contenidos

- [Características](#características)
- [Tecnologías](#tecnologías)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Requisitos](#requisitos)
- [Instalación](#instalación)
- [Configuración de Base de Datos](#configuración-de-base-de-datos)
- [Uso](#uso)
- [Endpoints Principales](#endpoints-principales)
- [Contribuciones](#contribuciones)
- [Licencia](#licencia)

---

## Características

- Gestión de pedidos de clientes.
- Autenticación y autorización mediante JWT.
- Gestión de usuarios con roles (`USER`, `ADMIN`).
- Validación de datos usando Spring Validation.
- Integración con MySQL como base de datos persistente.
- Fácil despliegue y configuración mediante `application.properties`.

---

## Tecnologías

- **Backend:** Java 17, Spring Boot 3.5.4  
- **Seguridad:** Spring Security, JWT  
- **Base de datos:** MySQL 8.0  
- **ORM:** Spring Data JPA  
- **Dependencias adicionales:**
  - Lombok
  - JJWT (Java JWT)
  - Spring Boot Starter Validation

---

## Estructura del Proyecto
Mesero/
├─ src/main/java/com/rdfj/mesero/
│  ├─ controller/         # Endpoints REST
│  ├─ security/           # JWT y filtros de seguridad
│  ├─ service/            # Lógica de negocio
│  ├─ model/              # Entidades JPA
│  └─ repository/         # Repositorios JPA
├─ src/main/resources/
│  ├─ application.properties
│  └─ data.sql            # Datos iniciales (opcional)
└─ pom.xml

---

## Requisitos

- JDK 17 o superior
- Maven 3.8.6 o superior
- MySQL 8.0 o superior

---

## Instalación

1. Clona el repositorio:

```bash
git clone https://github.com/robertodfj/Mesero.git
cd Mesero


2. Configura la base de datos en src/main/resources/application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/mesero_db
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

jwt.secret=una_clave_segura_y_larga_min32
jwt.expiration=3600000

3.	Compila e inicia la aplicación:
mvn clean install
mvn spring-boot:run

4.	La aplicación estará disponible en http://localhost:8080.


Endpoints Principales
Método
Endpoint
Descripción
Roles Permitidos
POST
/login
Autenticación y obtención de token
PUBLIC
POST
/tareas/crear
Crear una tarea
USER, ADMIN
GET
/tareas/mostrar
Mostrar tareas del usuario
USER, ADMIN
GET
/tareas/mostrar-todas
Mostrar todas las tareas
ADMIN
GET
/tareas/{id}
Obtener tarea por ID
ADMIN
DELETE
/tareas/{id}
Eliminar tarea por ID

Licencia

Este proyecto está bajo la Licencia MIT. Consulta el archivo LICENSE para más detalles.