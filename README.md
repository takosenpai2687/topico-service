# topico-service

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

## Team Responsibilities

### Taohan Kang

- Checkin
- Notification
- Top Search & Top Communities

### Yiyuan Lu

- Post
- Post likes

### Paddy Zong

- Auth / Login / Signup
- Comment
- Comment likes

### Ao Xiao

- Community
- Community Follow
- Search

### Mengting Nian

- Admin Operations: create / delete community, assign admin
- User Info Update / User password update
- Image Upload / Fetch

## TODO

1. `t_post`表中的`replies`字段要在comment创建时更新
2. 所有mapper中的@Results子查询都要改成Java代码实现，涉及类：

- PostMapper
- CommentMapper
- NotificationMapper

以及所调用的任何serviceImpl类！

3. 测试topSearch