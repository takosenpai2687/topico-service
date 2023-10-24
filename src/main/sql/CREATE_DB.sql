DROP DATABASE `topico`;
set global time_zone = 'Australia/Sydney';
set time_zone = 'Australia/Sydney';
flush privileges;

CREATE DATABASE IF NOT EXISTS `topico`;
USE `topico`;

CREATE TABLE `t_comment`
(
    `id`               int          NOT NULL AUTO_INCREMENT,
    `post_id`          int          NOT NULL,
    `author_id`        int          NOT NULL,
    `parent_id`        int          NULL,
    `reply_to_user_id` int          NULL,
    `content`          text         NOT NULL,
    `likes`            int          not null default 0,
    `dislikes`         int          not null default 0,
    `replies`          int          not null default 0,
    `location`         varchar(255) NULL     DEFAULT 'Unknown',
    `ctime`            TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
    `utime`            TIMESTAMP             DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
);

CREATE TABLE `t_community`
(
    `id`          int          NOT NULL AUTO_INCREMENT,
    `name`        varchar(255) NOT NULL,
    `description` text         NULL,
    `followers`   int          NOT NULL DEFAULT 0,
    `avatar`      int          NULL,
    `banner`      int          NULL,
    `tags`        text,
    `ctime`       TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
    `utime`       TIMESTAMP             DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
);


CREATE TABLE `t_image`
(
    `id`    int          NOT NULL AUTO_INCREMENT,
    `data`  longblob     NOT NULL,
    `md5`   varchar(255) NOT NULL,
    `ext`   varchar(255) NOT NULL,
    `ctime` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `utime` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
);

CREATE TABLE `t_notification`
(
    `id`                  int         NOT NULL AUTO_INCREMENT,
    `type`                VARCHAR(50) NOT NULL,
    `sender_id`           int         NOT NULL,
    `receiver_id`         int         NOT NULL,
    `post_id`             int         NULL,
    `comment_id`          int         NULL,
    `reply_to_comment_id` int         NULL,
    `content`             text        NULL,
    `unread`              tinyint     NOT NULL DEFAULT 1,
    `ctime`               TIMESTAMP            DEFAULT CURRENT_TIMESTAMP,
    `utime`               TIMESTAMP            DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
);

CREATE TABLE `t_post`
(
    `id`           int          NOT NULL AUTO_INCREMENT,
    `community_id` int          NOT NULL,
    `author_id`    int          NOT NULL,
    `title`        varchar(255) NOT NULL,
    `content`      varchar(255) NOT NULL,
    `spoiler`      tinyint      NOT NULL DEFAULT 0,
    `likes`        int          NOT NULL DEFAULT 0,
    `dislikes`     int          NOT NULL DEFAULT 0,
    `replies`      int          not null default 0,
    `tags`         text,
    `location`     varchar(255) NULL     DEFAULT 'Unknown',
    `ctime`        TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
    `utime`        TIMESTAMP             DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
);

CREATE TABLE `t_post_image`
(
    `id`       int NOT NULL AUTO_INCREMENT,
    `post_id`  int NOT NULL,
    `image_id` int NOT NULL,
    `ctime`    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `utime`    TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
);


CREATE TABLE `t_user`
(
    `id`          int          NOT NULL AUTO_INCREMENT,
    `email`       varchar(255) NOT NULL,
    `nick_name`   varchar(255) NOT NULL,
    `password`    varchar(255) NOT NULL,
    `gender`      varchar(20)  NOT NULL COMMENT ' ISO/IEC 5218 ',
    `location`    varchar(255) NULL DEFAULT 'Unknown',
    `avatar`      int          NULL,
    `description` text         NULL DEFAULT NULL,
    `role`        varchar(255) NULL DEFAULT ' user ',
    `ctime`       TIMESTAMP         DEFAULT CURRENT_TIMESTAMP,
    `utime`       TIMESTAMP         DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
);

CREATE TABLE `t_user_community`
(
    `id`           int NOT NULL AUTO_INCREMENT,
    `user_id`      int NOT NULL,
    `community_id` int NOT NULL,
    `level`        int NOT NULL DEFAULT 1,
    `exp`          int NOT NULL DEFAULT 0,
    `checkin`      int NOT NULL DEFAULT 0,
    `ctime`        TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,
    `utime`        TIMESTAMP    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
);

CREATE TABLE `t_user_like_comment`
(
    `id`         int     NOT NULL AUTO_INCREMENT,
    `user_id`    int     NOT NULL,
    `comment_id` int     NOT NULL,
    `liked`      TINYINT NOT NULL DEFAULT 1,
    `ctime`      TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
    `utime`      TIMESTAMP        DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
);

CREATE TABLE `t_user_like_post`
(
    `id`      int     NOT NULL AUTO_INCREMENT,
    `user_id` int     NOT NULL,
    `post_id` int     NOT NULL,
    `liked`   TINYINT NOT NULL DEFAULT 1,
    `ctime`   TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
    `utime`   TIMESTAMP        DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
);


ALTER TABLE `t_comment`
    ADD CONSTRAINT `comment_post_id` FOREIGN KEY (`post_id`) REFERENCES `t_post` (`id`);
ALTER TABLE `t_comment`
    ADD CONSTRAINT `reply_to` FOREIGN KEY (`parent_id`) REFERENCES `t_comment` (`id`);
ALTER TABLE `t_notification`
    ADD CONSTRAINT `sender_id` FOREIGN KEY (`sender_id`) REFERENCES `t_user` (`id`);
ALTER TABLE `t_notification`
    ADD CONSTRAINT `receiver_id` FOREIGN KEY (`receiver_id`) REFERENCES `t_user` (`id`);
ALTER TABLE `t_post`
    ADD CONSTRAINT `t_post_author_id` FOREIGN KEY (`author_id`) REFERENCES `t_user` (`id`);
ALTER TABLE `t_post`
    ADD CONSTRAINT `t_post_community_id` FOREIGN KEY (`community_id`) REFERENCES `t_community` (`id`);
ALTER TABLE `t_post_image`
    ADD CONSTRAINT `t_post_image_post_id` FOREIGN KEY (`post_id`) REFERENCES `t_post` (`id`);
ALTER TABLE `t_post_image`
    ADD CONSTRAINT `t_post_image_image_id` FOREIGN KEY (`image_id`) REFERENCES `t_image` (`id`);
ALTER TABLE `t_user_community`
    ADD CONSTRAINT `t_user_community_user_id` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`);
ALTER TABLE `t_user_community`
    ADD CONSTRAINT `t_user_community_community_id` FOREIGN KEY (`community_id`) REFERENCES `t_community` (`id`);
ALTER TABLE `t_user_like_comment`
    ADD CONSTRAINT `t_user_like_comment_user_id` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`);
ALTER TABLE `t_user_like_comment`
    ADD CONSTRAINT `comment_id` FOREIGN KEY (`comment_id`) REFERENCES `t_comment` (`id`);
ALTER TABLE `t_user_like_post`
    ADD CONSTRAINT `t_user_like_post_user_id` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`);
ALTER TABLE `t_user_like_post`
    ADD CONSTRAINT `post_id` FOREIGN KEY (`post_id`) REFERENCES `t_post` (`id`);

