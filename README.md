# ToDoList — Spring Boot and Angular

An early application maintained as a compact full-stack portfolio example. Java 21, Spring Boot, JPA/MySQL, Angular and Docker Compose.

## Local Docker demo

1. Copy `.env.example` to `.env`, then set unique strong `DB_PASSWORD` and `MYSQL_ROOT_PASSWORD`.
2. Run `docker compose config` and `docker compose up --build`.
3. Visit http://localhost:4200. Nginx forwards `/api/` requests directly to the Spring Boot service on the internal Compose network.
4. To run backend tests: `cd todoList-spring-boot && ./gradlew test` if using the supplied wrapper (or installed Gradle).

The database has no host-published port. The application is a local-development demonstration, not a hardened multi-user production service. Hibernate `ddl-auto=update` remains for demo convenience; production use would require a reviewed migration strategy. A clean Compose boot and live browser smoke test have not yet been independently verified.
