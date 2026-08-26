# School/College Chat

School Chat is a school-focused social platform for students and members of a school community. Visitors can search for schools in German federal states, select a school, create an account, and join conversations with multiple people from their school community. It supports social interaction as well as communication and collaboration for school work.

The application is implemented as a Spring Boot monolith with server-rendered Thymeleaf pages, JavaScript enhancements, and PostgreSQL persistence.

## Contents

- [School/College Chat](#schoolcollege-chat)
	- [Contents](#contents)
	- [Product Overview](#product-overview)
	- [Main User Flows](#main-user-flows)
	- [Technology Stack](#technology-stack)
	- [Database](#database)
		- [PostgreSQL Tables](#postgresql-tables)
		- [Relationships](#relationships)
		- [School Data](#school-data)
		- [Inspect the Database](#inspect-the-database)
	- [Project Structure](#project-structure)
	- [**Deployment Scripts**](#deployment-scripts)
		- [**Dockerfile**](#dockerfile)
		- [**Docker Compose**](#docker-compose)
		- [**Automated Deployment**](#automated-deployment)
	- [Prerequisites](#prerequisites)
	- [Run with Docker Compose](#run-with-docker-compose)
		- [Compose Database Settings](#compose-database-settings)
	- [Run Locally with Maven](#run-locally-with-maven)
	- [Configuration](#configuration)
	- [Verify the Deployment](#verify-the-deployment)
	- [Screenshots](#screenshots)
		- [School Discovery and Account Setup](#school-discovery-and-account-setup)
		- [Community Chat and Collaboration](#community-chat-and-collaboration)
		- [Profiles](#profiles)
	- [Troubleshooting](#troubleshooting)
		- [Port 8080 is already in use](#port-8080-is-already-in-use)
		- [The application cannot connect to PostgreSQL](#the-application-cannot-connect-to-postgresql)
		- [The Docker build cannot find a JAR](#the-docker-build-cannot-find-a-jar)
		- [The page redirects or fails after login](#the-page-redirects-or-fails-after-login)
	- [Production Checklist](#production-checklist)
	- [License](#license)

## Product Overview

School Chat combines school discovery with a community feed:

- Search schools by German region and school name.
- Select a school from the search results.
- Register an account associated with the selected school.
- Log in with a nickname and password.
- View the school feed and publish text posts.
- Add images to posts where supported by the interface.
- Comment on posts and interact with likes.
- Chat and exchange ideas with multiple people in the school community.
- Communicate about school work and collaborate with other students.
- View a personal profile and manage profile content.
- Update profile biography, avatar, and background image.
- Browse school data sourced from JSON files in `src/main/resources/all_shools_list`.

The public entry point is `http://localhost:8080/` after the application starts.

## Main User Flows

1. **Find a school**: open the home page, choose a region, and search by school name.
2. **Join a school community**: select a result and continue to registration.
3. **Create an account**: provide a nickname, email address, and password. The registration page checks nickname availability.
4. **Use the school feed**: log in to view the selected school's posts, communicate with multiple people, and publish new content.
5. **Work together**: use the community conversations to discuss assignments, school work, and shared ideas.
6. **Manage a profile**: open `/profile` to view user posts and update profile information.

## Technology Stack

| Area        | Technology                                               |
| ----------- | -------------------------------------------------------- |
| Backend     | Java 17, Spring Boot 3.3.3, Spring MVC                   |
| Persistence | Spring Data JPA, Hibernate, PostgreSQL                   |
| Views       | Thymeleaf and FreeMarker dependencies                    |
| Frontend    | HTML, CSS, JavaScript, jQuery, Bootstrap                 |
| Security    | Spring Security filter chain and BCrypt password hashing |
| Build       | Maven Wrapper and Maven                                  |
| Deployment  | Docker, Docker Compose, Eclipse Temurin 17               |

## Database

School Chat uses PostgreSQL through Spring Data JPA and Hibernate. The database schema is created or updated automatically when the application starts because `spring.jpa.hibernate.ddl-auto=update` is configured.

### PostgreSQL Tables

| Table                | Purpose                                       | Main columns                                                                                 |
| -------------------- | --------------------------------------------- | -------------------------------------------------------------------------------------------- |
| `users_table`        | Registered accounts and their selected school | `id`, `login`, `password`, `email`, `school_id`                                              |
| `profile_table`      | User profile information                      | `profile_id`, `fk_user_id`, `profile_image`, `profile_background`, `profile_biography`       |
| `posts_table`        | Posts published by users                      | `post_id`, `fk_school_id`, `fk_user_id`, `meassage`, `send_time`, `post_image`, `like_count` |
| `school_posts_table` | Associates posts with a school feed           | `school_post_id`, `school_id`, `fk_post_id`, `fk_user_id`                                    |
| `comments_table`     | Comments written on posts                     | `comment_id`, `post_id`, `fk_user_id`, `fk_profile_id`, `comment_message`, `comment_time`    |
| `likes_table`        | User likes on posts                           | `like_id`, `post_id`, `user_id`                                                              |

### Relationships

- A user can create posts, comments, and likes.
- A user has a selected school stored as the `school_id` value in `users_table`.
- A post references its author through `posts_table.fk_user_id` and its school through `posts_table.fk_school_id`.
- `school_posts_table` maps posts into a school-specific feed.
- Profiles reference users through `profile_table.fk_user_id`.
- Comments reference a post identifier and can reference both a user and a profile.
- Likes store the post and user identifiers used to determine whether a user has liked a post.

### School Data

Schools are not stored in a dedicated database table. Search data is loaded from regional JSON files in `src/main/resources/all_shools_list`, including files for German federal states such as Bavaria, Berlin, Hesse, and Saxony. The selected school's identifier is copied into user and post records.

### Inspect the Database

When using Docker Compose, connect to PostgreSQL with:

```bash
docker compose exec db psql -U postgres -d postgres
```

Inside `psql`, useful commands are:

```sql
\dt
\d users_table
\d posts_table
SELECT id, login, email, school_id FROM users_table;
SELECT post_id, fk_school_id, fk_user_id, send_time FROM posts_table;
```

The Compose volume named `pgdata` persists the database between container restarts. Use `docker compose down -v` only when you intentionally want to delete the stored database data.

The current `src/main/resources/database.sql` file is not a complete schema migration; it contains a users-table query. For production, use a versioned migration tool such as Flyway or Liquibase and replace `ddl-auto=update` with a controlled schema strategy.

## Project Structure

```text
src/main/java/com/schoolchat/school/chat/
├── controller/              MVC controllers for pages and user flows
├── controller/restController/ JSON-style endpoints used by the frontend
├── model/                   JPA and request models
├── repository/              Spring Data repositories
├── service/                 Application and business services
├── schoolService/           School JSON data loading
└── security/                Security and password hashing configuration

src/main/resources/
├── templates/               Thymeleaf pages
├── static/                  CSS, JavaScript, and image assets
├── all_shools_list/         Regional school JSON data
└── application.properties  Default application configuration
```

## **Deployment Scripts**

The project includes Docker configuration and a deployment script for building and starting the application.

### **Dockerfile**

The `Dockerfile` runs the packaged Spring Boot JAR with Eclipse Temurin Java 17 and exposes port `8080`.

### **Docker Compose**

`docker-compose.yml` starts the application and PostgreSQL together:

```bash
./mvnw clean package -DskipTests
docker compose up --build -d
```

### **Automated Deployment**

`deploy.sh` automates the server deployment process:

```bash
cd ~/app/School-Chat
chmod +x deploy.sh
./deploy.sh
```

The script:

- **Stops** the running Docker Compose services.
- **Pulls** the latest code with `git pull`.
- **Builds** the application with Maven.
- **Creates** a new Docker image.
- **Starts** the application and database in detached mode.

Before using the script on a server:

- Update the hard-coded project path if the checkout is elsewhere.
- Confirm that `mvn` is installed, or change the script to use `./mvnw`.
- Review the database credentials in `docker-compose.yml`.
- Configure a reverse proxy and HTTPS for public access.
- Decide whether skipping tests with `-DskipTests` is appropriate for the release process.

## Prerequisites

For Docker deployment:

- Docker Engine with the Compose plugin
- Git, if deploying from a Git checkout

For local Maven development:

- JDK 17
- PostgreSQL 15 or a compatible PostgreSQL server
- Bash, or an equivalent shell for the Maven Wrapper

## Run with Docker Compose

This is the recommended way to start the complete application locally. Compose starts both the Spring Boot application and PostgreSQL, and stores database data in the named `pgdata` volume.

```bash
cd ~/app/School-Chat
./mvnw clean package -DskipTests
docker compose up --build -d
```

On Linux, use `./mvnw`. If necessary:

```bash
chmod +x mvnw
```

Open the application at [http://localhost:8080/](http://localhost:8080/).

Useful Compose commands:

```bash
docker compose ps
docker compose logs -f app
docker compose logs -f db
docker compose down
```

`docker compose down` keeps the database volume. To remove the stored PostgreSQL data as well, use `docker compose down -v`.

### Compose Database Settings

The committed Compose configuration uses these development credentials:

| Setting                   | Value      |
| ------------------------- | ---------- |
| Database                  | `postgres` |
| Username                  | `postgres` |
| Password                  | `admin`    |
| Internal application host | `db`       |
| PostgreSQL port           | `5432`     |
| Application port          | `8080`     |

These values are suitable for local development only. Change them before exposing the service to the internet.

## Run Locally with Maven

Start PostgreSQL separately, then make sure the database and credentials match `application.properties`:

```bash
cd ~/app/School-Chat
./mvnw spring-boot:run
```

Alternatively, build and run the packaged JAR:

```bash
./mvnw clean package
java -jar target/School-Chat-2.0.0.jar
```

The default local JDBC URL is `jdbc:postgresql://localhost:5432/postgres`. The application uses `spring.jpa.hibernate.ddl-auto=update`, so Hibernate updates the schema when the application starts. The checked-in `database.sql` file currently contains a basic users-table query rather than a complete schema migration.

## Configuration

Spring Boot environment variables can override the defaults without changing source files. For example:

```bash
SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/postgres \
SPRING_DATASOURCE_USERNAME=postgres \
SPRING_DATASOURCE_PASSWORD=change-me \
SPRING_JPA_HIBERNATE_DDL_AUTO=update \
./mvnw spring-boot:run
```

Important configuration notes:

- `server.port` is not explicitly changed, so the application listens on port `8080` by default.
- The default datasource points to `localhost`; the Compose service overrides it to use the hostname `db`.
- `app.api.url` is currently set to `https://localhost:8080`, while the application does not configure TLS certificates itself. Use HTTP locally unless a reverse proxy terminates HTTPS, or update this setting for your environment.
- Google and GitHub OAuth settings are present as commented examples. OAuth login is not enabled by the current active configuration.
- Do not commit production database passwords, OAuth secrets, or TLS private keys.

## Verify the Deployment

Check that the containers are running:

```bash
docker compose ps
```

Check application logs for a successful Spring Boot startup:

```bash
docker compose logs --tail=100 app
```

Then open `/` in a browser and verify this sequence:

1. The school search page loads.
2. A region and school search return results.
3. A school can be selected and registration opens.
4. A newly registered user can log in.
5. `/home` loads the school feed.

Spring Boot Actuator is included as a dependency, but no actuator exposure policy is defined in the current properties file. Configure and protect health endpoints before using them for external monitoring.

## Screenshots

The following screenshots show the main user flows and community features:

### School Discovery and Account Setup

![School Chat home page](app-screenshots/home-page.png)
![School search](app-screenshots/school-search.png)
![Search bar](app-screenshots/search-bar.png)
![Login page](app-screenshots/login.png)
![Registration page](app-screenshots/register.png)

### Community Chat and Collaboration

![Main page](app-screenshots/main-page.png)
![Home page with a post](app-screenshots/home-page-with-post.png)
![Post comment](app-screenshots/post-comment.png)
![Posted comment](app-screenshots/posted-comment-in-post.png)
![Changed post data](app-screenshots/change-post-data.png)
![Sidebar](app-screenshots/side-bar.png)

### Profiles

![Profile page](app-screenshots/profile-page.png)
![Changed profile](app-screenshots/changed-profile.png)
![Profile settings](app-screenshots/profile-settings.png)
![Delete or edit a post from the profile page](app-screenshots/delete-edit-post-in-profile-page.png)

## Troubleshooting

### Port 8080 is already in use

Find the process using the port or change the host-side Compose mapping, for example `8081:8080`, then open `http://localhost:8081/`.

### The application cannot connect to PostgreSQL

- With Compose, the JDBC hostname must be `db`, not `localhost`.
- With Maven, PostgreSQL must be reachable at `localhost:5432`.
- Confirm the username, password, database name, and container status.

### The Docker build cannot find a JAR

Run the Maven package command before `docker compose up --build`. The Dockerfile copies a JAR from `target/` into the image.

### The page redirects or fails after login

Confirm that the selected school data is present in the regional JSON files and inspect the application logs. User and school context is stored in the HTTP session, so clearing cookies or changing hosts can start a new session.

## Production Checklist

- Replace development PostgreSQL credentials with secrets managed by the hosting platform.
- Put the application behind an HTTPS reverse proxy.
- Restrict database network access to the application.
- Review the current CSRF and authorization configuration before public release.
- Configure backups for PostgreSQL and test restoration.
- Add a complete schema migration process instead of relying only on `ddl-auto=update`.
- Enable protected health checks and centralized log collection.
- Run the full test suite as part of the deployment pipeline.
- Remove placeholder links and verify all public navigation routes.

## License

This project is licensed under the [MIT License](LICENSE). Copyright (c) 2026 kartabas.



