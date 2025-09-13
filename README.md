Mesero 🗂️

Mesero es una aplicación backend tipo gestor de tareas/pedidos para restaurantes y bares. Permite a usuarios autenticados crear, visualizar y gestionar tareas/pedidos, con un panel de administración exclusivo para usuarios con rol ADMIN.

✨ Características
	•	Registro y login con autenticación JWT
	•	Roles de usuario (USER y ADMIN)
	•	Crear, listar y eliminar tareas/pedidos
	•	Panel de administración para ver todas las tareas
	•	Integración con MySQL como base de datos
	•	Validación de datos con Spring Validation

🚀 Tecnologías

Backend
	•	Java 17
	•	Spring Boot 3.5.4
	•	Spring Security + JWT
	•	JPA + Hibernate
	•	MySQL
	•	Lombok

Frontend (opcional)
	•	React
	•	Tailwind CSS
	•	React Router

🔧 Estructura del Proyecto

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

🔐 Usuario de prueba (Demo)

Puedes acceder con:

Email: demo@example.com
Contraseña: demo1234

📦 Instalación y ejecución

Backend
	1.	Clona el repositorio
	2.	Configura la base de datos en application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/mesero_db
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

jwt.secret=una_clave_segura_y_larga_min32
jwt.expiration=3600000

	3.	Ejecuta la aplicación con tu IDE o mvn spring-boot:run

Frontend (opcional)
	1.	Ve al directorio del frontend
	2.	Instala las dependencias:

npm install

	3.	Ejecuta la aplicación:

npm run dev

📌 Endpoints principales

Método	Endpoint	Descripción	Roles Permitidos
POST	/login	Autenticación y obtención de token	PUBLIC
POST	/tareas/crear	Crear una tarea	USER, ADMIN
GET	/tareas/mostrar	Mostrar tareas del usuario	USER, ADMIN
GET	/tareas/mostrar-todas	Mostrar todas las tareas	ADMIN
GET	/tareas/{id}	Obtener tarea por ID	ADMIN
DELETE	/tareas/{id}	Eliminar tarea por ID	ADMIN

🛠️ Notas de desarrollo
	•	La aplicación crea automáticamente el usuario demo si no existe.
	•	JWT se almacena en localStorage para mantener la sesión.
	•	Puedes eliminar y reiniciar la base de datos manualmente desde tu cliente MySQL.
	•	Asegúrate de usar un jwt.secret con mínimo 32 caracteres para que HS256 funcione correctamente.

📄 Licencia

Proyecto con fines educativos - Roberto Frutos Jiménez
