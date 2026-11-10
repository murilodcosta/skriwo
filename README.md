# Skriwo

> **Status: Work in Progress (MVP / Phase 1)**  
> An open-source, real-time online notepad platform inspired by [Dontpad](https://dontpad.com), built with Java 21 and Spring Boot.

---

## About the Project

**Skriwo** is an open-source collaborative notepad platform inspired by [Dontpad](https://dontpad.com), designed to provide fast, anonymous, URL-based scratchpads. Navigate to any slug, view the note, and edit it freely without mandatory registration.

### Vision & Philosophy

- **Current & Initial Focus:** The current stage is strictly dedicated to **in-depth study, research, and learning** within the modern Java 21 and Spring Boot ecosystem — mastering clean architecture, concurrency, database migrations, and real-time streaming.
- **Ultimate Goal:** The final ambition is to deploy Skriwo (`skriwo.com`) as a **publicly available and fully hosted service**.

> [!NOTE]  
> This repository contains the backend API (`skriwo`). The project is actively under development, currently in its **MVP phase**. More features, infrastructure, and a dedicated web frontend will be introduced across subsequent roadmap milestones.

---

## Current MVP Scope (Phase 1)

The current milestone focuses on core note lifecycle, optimistic concurrency, and real-time streaming:

- **Slug Normalization & Validation**: Dynamic note resolution with URL-friendly slugs (lowercase, trimmed, hyphens for spaces, reserved word protection).
- **Optimistic Locking**: Versioned updates (`@Version`) returning `409 Conflict` when concurrent edits collide.
- **Real-Time Synchronization (SSE)**: Clients connected to a note's stream receive immediate `pad-updated` push notifications with echo suppression (`clientId`) and periodic `:ping` heartbeats.
- **Content Size Limits & Health Check**: Enforces a 200 KB payload limit (`413 Payload Too Large`) and exposes `/actuator/health`.
- **Database Migrations**: Automatic schema versioning with Flyway and PostgreSQL.

### MVP Endpoints

| HTTP Method | Endpoint | Description |
|---|---|---|
| `GET` | `/api/pads/{slug}` | Retrieves the current note content and version (or returns 404 if not created). |
| `PUT` | `/api/pads/{slug}` | Creates or updates note content with optimistic locking and broadcasts via SSE. |
| `GET` | `/api/pads/{slug}/stream` | Subscribes to Server-Sent Events (SSE) for real-time updates on `{slug}`. |
| `GET` | `/actuator/health` | Returns application health status (`{"status":"UP"}`). |

---

## Tech Stack

- **Language:** Java 21
- **Framework:** Spring Boot 4.x
- **Web Layer:** Spring WebMvc (`SseEmitter` for streaming)
- **Data Persistence:** Spring Data JPA / Hibernate
- **Database & Migrations:** PostgreSQL & Flyway
- **Observability:** Spring Boot Actuator
- **Testing:** JUnit 5, MockMvc, and Testcontainers *(planned)*
- **Build Tool:** Apache Maven (via Maven Wrapper)

---

## Getting Started

### Prerequisites

- **Java Development Kit (JDK):** Version 21 or higher
- **PostgreSQL:** Local instance or running via Docker (`docker compose up -d`)

### 1. Configure the Database

Configure your PostgreSQL connection in `src/main/resources/application.properties` (or via environment variables):

```properties
spring.application.name=skriwo

# Database Configuration
spring.datasource.url=${DB_URL:jdbc:postgresql://localhost:5436/skriwo_db}
spring.datasource.username=${DB_USERNAME:postgres}
spring.datasource.password=${DB_PASSWORD:postgres_password}
spring.jpa.hibernate.ddl-auto=validate

# Flyway Migrations
spring.flyway.enabled=true
```

### 2. Run Database Migrations & Start the Application

Execute using Maven:

```bash
.\mvnw.cmd spring-boot:run
```

The API will be available at `http://localhost:8080`.

---

## License

This project is and will always remain open-source, distributed under the [MIT License](LICENSE).
