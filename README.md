# topico-service

## Tech Stack

- Spring Boot 3.1.3
- Java 20
- Mybatis
- MySQL 8
- Redis 6
- Docker

## Setup

- Make sure you have the environment mentioned above installed.
- Open the project in IntelliJ Idea.
- Open `pom.xml` and install the maven packages.
- Note: Make sure to stop __MySQL__ and __Redis__ service from your local machine
  i.e., no port bind on __3306__ and __6379__.
- Run: __MySQL__ and __Redis__ services from the `docker-compose.yml` file in the __root__ directory.
- Run: `CREATE_DB.sql` from the `/src/resources` directory. Configure the datasource if prompted. The default username
  is `root` and password is `123456`.
- Run the `main` method in `TopicoServiceApplication.java`.

## Terms

### Dto

Dtos are the data sent from frontend to backend.

### Model

Models are data stored in the database.

### Vo

VOs are data sent from backend to frontend.

### Pojo

Pojos are plain old java objects.
