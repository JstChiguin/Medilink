# Cambios realizados - Actividad 3.1 Swagger y HATEOAS

## Objetivo

Implementar documentación automática de la API mediante Swagger/OpenAPI e incorporar HATEOAS para enriquecer las respuestas REST con enlaces de navegación.

---

## Implementación de Swagger/OpenAPI

Se agregó la dependencia:

```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.8.9</version>
</dependency>
```

Se creó la clase `SwaggerConfig.java` para personalizar la documentación de la API.

Se configuró la documentación del microservicio indicando:

* Título de la API.
* Descripción.
* Versión.
* Información de los endpoints disponibles.

Posteriormente se verificó el funcionamiento mediante:

```text
http://localhost:3006/v3/api-docs
```

y

```text
http://localhost:3006/doc/swagger-ui/index.html
```

confirmando que los endpoints quedaron documentados y disponibles para pruebas.

---

## Implementación de HATEOAS

Se agregó la dependencia:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-hateoas</artifactId>
</dependency>
```

Se creó la clase:

```text
CitaModelAssembler
```

encargada de construir los enlaces de navegación de cada recurso.

También se creó:

```text
CitaControllerV2
```

para exponer una nueva versión de la API utilizando HATEOAS.

Se implementaron:

* EntityModel
* CollectionModel
* Links Self
* Links hacia la colección de citas

Ejemplo de respuesta obtenida:

```json
"_links": {
  "self": {
    "href": "http://localhost:3006/api/v2/citas/1"
  },
  "citas": {
    "href": "http://localhost:3006/api/v2/citas"
  }
}
```

La implementación fue validada mediante:

```text
http://localhost:3006/api/v2/citas
```

confirmando la presencia de enlaces HATEOAS en las respuestas.

---

## Problemas encontrados durante la implementación

### Conflicto entre Swagger y HATEOAS

Al incorporar HATEOAS la aplicación dejó de iniciar correctamente.

Luego de revisar los errores se identificó una incompatibilidad entre:

* Spring Boot 3.5.4
* Spring HATEOAS
* SpringDoc OpenAPI 2.7.0

El problema se resolvió actualizando SpringDoc a una versión compatible.

Versión inicial:

```xml
<version>2.7.0</version>
```

Versión final utilizada:

```xml
<version>2.8.9</version>
```

Después de la actualización Swagger y HATEOAS funcionaron correctamente de manera conjunta.

---

### Corrección de dependencia Flyway

Durante la compilación Maven detectó un error relacionado con:

```xml
spring-boot-starter-flyway
```

Se reemplazó por las dependencias oficiales:

```xml
<dependency>
    <groupId>org.flywaydb</groupId>
    <artifactId>flyway-core</artifactId>
</dependency>

<dependency>
    <groupId>org.flywaydb</groupId>
    <artifactId>flyway-mysql</artifactId>
</dependency>
```

Con esta modificación las migraciones volvieron a ejecutarse correctamente.

---

## Validaciones realizadas

Se verificó correctamente:

* Levantamiento de la aplicación Spring Boot.
* Conexión con MySQL.
* Ejecución de migraciones Flyway.
* Generación de documentación OpenAPI.
* Visualización de Swagger UI.
* Funcionamiento de HATEOAS.
* Exposición de endpoints V1 y V2.
* Respuestas con enlaces `_links`.

---

## Resultado

La actividad fue completada exitosamente integrando Swagger/OpenAPI y HATEOAS al microservicio de citas, documentando los endpoints y enriqueciendo las respuestas REST con enlaces de navegación entre recursos.
