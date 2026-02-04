# API Documentation - Prueba Técnica Ada

Esta documentación detalla los endpoints disponibles en la API de Ada.

## Base URL
`http://localhost:8080/api`

---

## Companies (Empresas)

Endpoints para gestionar empresas.

### Obtener todas las empresas
- **URL**: `/companies`
- **Método**: `GET`
- **Respuesta**: `200 OK` con lista de `CompanyDTO`.

### Obtener empresa por ID
- **URL**: `/companies/{id}`
- **Método**: `GET`
- **Respuesta**: `200 OK` con `CompanyDTO`.

### Crear empresa
- **URL**: `/companies`
- **Método**: `POST`
- **Cuerpo**: `CreateCompanyRequest`
- **Respuesta**: `200 OK` con `CompanyDTO`.

### Actualizar empresa
- **URL**: `/companies/{id}`
- **Método**: `PUT`
- **Cuerpo**: `CreateCompanyRequest`
- **Respuesta**: `200 OK` con `CompanyDTO`.

### Eliminar empresa
- **URL**: `/companies/{id}`
- **Método**: `DELETE`
- **Respuesta**: `204 No Content`.

---

## Applications (Aplicaciones)

Endpoints para gestionar aplicaciones.

### Obtener todas las aplicaciones
- **URL**: `/applications`
- **Método**: `GET`
- **Respuesta**: `200 OK` con lista de `ApplicationDTO`.

---

## Versions (Versiones)

Endpoints para gestionar versiones de aplicaciones.

### Obtener todas las versiones
- **URL**: `/versions`
- **Método**: `GET`
- **Respuesta**: `200 OK` con lista de `VersionDTO`.

---

## VersionCompany (Relación Versión-Empresa)

Endpoints para gestionar la asignación de versiones a empresas.

### Obtener todas las relaciones
- **URL**: `/versioncompanies`
- **Método**: `GET`
- **Respuesta**: `200 OK` con lista de `VersionCompanyDTO`.

---

## Documentación Interactiva (Swagger)

Si la aplicación está en ejecución, puedes acceder al Swagger UI para probar los endpoints interactivamente:

- **Swagger UI**: `http://localhost:8080/swagger-ui/index.html`
- **OpenAPI Spec (JSON)**: `http://localhost:8080/v3/api-docs`
