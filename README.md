# topico-service

## Test account: ADMIN

- email: admin@qq.com
- password: 123456

## Test account: USER

- email: test1@qq.com, test2@qq.com, ... , test100@qq.com
- password: 123456

## Tech Stack

- Spring Boot 3.1.3
- Java 20
- Mybatis
- MySQL 8
- Redis 6
- Docker

## Preparation

- Make sure you have **JDK20**, **Intellij Idea** and **Docker** installed.
- Open the project in IntelliJ Idea.
- Open `pom.xml` and install the maven packages.
- Make sure you have Docker desktop app or Docker service up and running.

## Run in Development mode

- Note: Make sure to stop __MySQL__ and __Redis__ service from your local machine
  i.e., no port bind on __3306__ and __6379__.

- Run: __MySQL__ and __Redis__ services from the `docker-compose.yml` file in the __root__ directory.
- Run: `CREATE_DB.sql` from the `/src/resources` directory. Configure the datasource if prompted. The default username
  is `root` and password is `123456`.
- Run the `main` method in `TopicoServiceApplication.java`.

## Run in Production mode

- _Optional: In the maven tools window, run `mvn clean` to clean the target directory between each build._
- In the maven tools window, run `package` under `Lifecycle` to generate the jar file.
- Run `docker-compose.yml` from the __root__ directory.
- First time: Run `CREATE_DB.sql` from the `/src/resources` directory. Configure the datasource if prompted. The default
  username
  is `root` and password is `123456`.

## Initialize data

**After the server is up and running,**

run `mock_main.py` in `src/main/python` to initialize data.

Command:

```shell
cd ./src/main/python
python ./mock_main.py
```

## API Documentation powered by Swagger

- http://localhost:8080/swagger-ui.html

## Terms

### Dto

Dtos are the data sent from frontend to backend.

### Model

Models are data stored in the database.

### Vo

VOs are data sent from backend to frontend.

### Pojo

Pojos are plain old java objects.

