# 🛒 Proyecto E-commerce Backend – Gestión de Productos y Pedidos en Java con Spring Boot

Este proyecto es una API RESTful desarrollada en Java 21 usando Spring Boot. Permite gestionar productos y pedidos para un sistema de comercio electrónico, aplicando fundamentos de programación orientada a objetos (POO), validaciones, manejo de excepciones personalizadas y arquitectura en capas.

---

## 🚀 Funcionalidades principales

- CRUD completo para productos.
- Creación de pedidos con múltiples productos.
- Control automático de stock al crear pedidos.
- Gestión del estado de los pedidos (PENDIENTE, CONFIRMADO, ENVIADO, ENTREGADO, CANCELADO).
- Búsqueda de pedidos por estado.
- Validaciones con Jakarta Validation.
- Manejo de excepciones personalizadas:
    - Producto no encontrado
    - Stock insuficiente
    - Pedido no encontrado
- Endpoints REST accesibles desde Postman, navegador o clientes HTTP.
- Arquitectura en capas: Controller, Service, Repository.

---

## 🧠 Tecnologías y conceptos aplicados

- Java 21 (JDK 21.0.6)
- Spring Boot
- Spring Data JPA
- Base de datos en memoria H2
- Maven
- Jakarta Validation
- Lombok
- Programación orientada a objetos (POO)

---

## 📌 Endpoints principales

### Productos
- `GET /api/productos` – Listar todos los productos
- `GET /api/productos/{id}` – Obtener producto por ID
- `POST /api/productos` – Crear un nuevo producto
- `PUT /api/productos/{id}` – Actualizar producto existente
- `DELETE /api/productos/{id}` – Eliminar producto
- `GET /api/productos/buscar?nombre={nombre}` – Buscar producto por nombre
- `GET /api/productos/buscar?categoria={categoria}` – Buscar producto por categoría

### Pedidos
- `GET /api/pedidos` – Listar todos los pedidos
- `GET /api/pedidos/filtrar?estado={ESTADO}` – Listar pedidos por estado (Ej: PENDIENTE)
- `POST /api/pedidos` – Crear un pedido con múltiples productos
- `PUT /api/pedidos/{id}/estado?estado={ESTADO}` – Cambiar el estado de un pedido

---

## ⚙️ Cómo ejecutar

1. Clonar el repositorio.
2. Abrir el proyecto en un IDE compatible con Spring Boot (IntelliJ IDEA, Eclipse, VSCode).
3. Ejecutar la aplicación con:

```bash
mvn spring-boot:run
```
4. Acceder a la API desde: http://localhost:8080
5. Probar los endpoints usando Postman, Insomnia u otra herramienta de pruebas.

---

## 📚 Extras

- Uso de base de datos en memoria H2 para facilitar las pruebas sin configuración adicional.
- Posibilidad de integrar un frontend en el futuro (React, Angular, etc).
- ✅ Mejora futura: reemplazar H2 por MySQL u otro motor de base de datos relacional para ambientes productivos.