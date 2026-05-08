# Sistema de Administración de Propiedades (SAP)

Sistema de gestión inmobiliaria de escritorio desarrollado en Java, con arquitectura **MVC completa**, interfaz gráfica con Swing y persistencia en base de datos **Oracle**.

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=flat&logo=openjdk&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.x-C71A36?style=flat&logo=apachemaven&logoColor=white)
![Oracle](https://img.shields.io/badge/Oracle-DB-F80000?style=flat&logo=oracle&logoColor=white)

---

## Descripción

El SAP permite gestionar los procesos clave de una agencia inmobiliaria: registro de propiedades, clientes, inquilinos, solicitudes de renta, visitas, pagos, comisiones e impuestos. El sistema implementa autenticación de usuarios con roles diferenciados (propietario, inquilino, empleado) y un módulo de reportes integrado.

---

## Funcionalidades principales

- **Autenticación** — login con roles diferenciados por tipo de usuario
- **Gestión de propiedades** — registro, consulta y administración de inmuebles por ubicación
- **Clientes e inquilinos** — CRUD completo de personas vinculadas a la agencia
- **Solicitudes de renta** — flujo completo desde solicitud hasta aprobación
- **Visitas** — agendamiento y seguimiento de visitas a propiedades
- **Pagos y comisiones** — registro y control de transacciones financieras
- **Impuestos y servicios extra** — gestión de obligaciones y servicios adicionales
- **Módulo de reportes** — generación de reportes del sistema

---

## Arquitectura

El proyecto sigue el patrón **MVC (Model - View - Controller)** con una capa DAO para el acceso a datos:

```
src/main/java/com/mycompany/
├── model/          # Entidades del dominio
│   ├── Propiedad.java
│   ├── Cliente.java
│   ├── Inquilino.java
│   ├── Empleado.java
│   ├── Agencia.java
│   ├── Visita.java
│   ├── SolicitudRenta.java
│   ├── Pago.java
│   ├── Comision.java
│   └── ...
├── dao/            # Acceso a datos (Oracle JDBC)
│   ├── PropiedadDAO.java
│   ├── ClienteDAO.java
│   ├── UsuarioDAO.java
│   └── ...
├── controller/     # Lógica de negocio
│   ├── PropiedadController.java
│   ├── ClienteController.java
│   ├── RentaSimpleController.java
│   └── ...
├── gui/            # Interfaz gráfica (Swing)
│   ├── LoginGUI.java
│   ├── PropiedadGUI.java
│   ├── ModuloReportesGUI.java
│   └── ...
└── util/           # Configuración y utilidades
    └── OracleDBConection.java
```

---

## Tecnologías

| Tecnología | Uso |
|---|---|
| Java 21 | Lenguaje principal |
| Apache Maven | Gestión de dependencias y build |
| Oracle DB (JDBC) | Persistencia de datos |
| Java Swing | Interfaz gráfica de escritorio |
| dotenv-java | Gestión segura de credenciales |

---

## Configuración y ejecución

### Prerrequisitos

- Java 21+
- Apache Maven 3.x
- Acceso a una base de datos Oracle

### 1. Clonar el repositorio

```bash
git clone https://github.com/Sebasve0810/sistema-administracion-propiedades.git
cd sistema-administracion-propiedades
```

### 2. Configurar variables de entorno

```bash
cp .env.example .env
```

Edita el archivo `.env` con tus credenciales:

```
DB_URL=jdbc:oracle:thin:@tu-servidor:1521/tu-bd
DB_USER=tu_usuario
DB_PASSWORD=tu_contraseña
```

### 3. Compilar y ejecutar

```bash
mvn compile
mvn exec:java
```

---

## Autor

**Sebastián Velasquez**
Systems Engineering @ Pontificia Universidad Javeriana

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=flat&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/sebastian-velasquez-73662721a)
[![GitHub](https://img.shields.io/badge/GitHub-100000?style=flat&logo=github&logoColor=white)](https://github.com/Sebasve0810)

---

*Proyecto académico — Bases de Datos | Ingeniería de Sistemas, Javeriana 2025*
