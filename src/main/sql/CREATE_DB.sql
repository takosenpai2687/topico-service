DROP DATABASE `topico`;
CREATE DATABASE `topico`;
USE `topico`;
CREATE TABLE `t_browse_history`
(
    `id`      int      NOT NULL AUTO_INCREMENT,
    `user_id` int      NOT NULL,
    `post_id` int      NOT NULL,
    `ctime`   datetime NOT NULL,
    `utime`   datetime NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `t_checkin`
(
    `id`               int      NOT NULL AUTO_INCREMENT,
    `user_id`          int      NOT NULL,
    `community_id`     int      NOT NULL,
    `accumulated_days` int      NOT NULL DEFAULT 1,
    `ctime`            datetime NOT NULL,
    `utime`            datetime NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `t_comment`
(
    `id`                  int          NOT NULL AUTO_INCREMENT,
    `post_id`             int          NOT NULL,
    `author_id`           int          NOT NULL,
    `reply_to_comment_id` int          NULL,
    `reply_to_user_id`    int          NULL,
    `reply_to_name`       varchar(255) NULL,
    `content`             text         NOT NULL,
    `ctime`               datetime     NOT NULL,
    `utime`               datetime     NOT NULL,
    `deleted`             tinyint      NOT NULL DEFAULT 0,
    PRIMARY KEY (`id`)
);

CREATE TABLE `t_community`
(
    `id`          int          NOT NULL AUTO_INCREMENT,
    `name`        varchar(255) NOT NULL,
    `description` text         NULL,
    `ctime`       datetime     NOT NULL,
    `utime`       datetime     NOT NULL,
    `deleted`     tinyint      NOT NULL DEFAULT 0,
    PRIMARY KEY (`id`)
);

CREATE TABLE `t_community_tag`
(
    `id`           int      NOT NULL AUTO_INCREMENT,
    `community_id` int      NOT NULL,
    `tag_id`       int      NOT NULL,
    `ctime`        datetime NOT NULL,
    `utime`        datetime NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `t_community_tag_copy_1`
(
    `id`           int      NOT NULL AUTO_INCREMENT,
    `community_id` int      NOT NULL,
    `tag_id`       int      NOT NULL,
    `ctime`        datetime NOT NULL,
    `utime`        datetime NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `t_image`
(
    `uuid` varchar(255) NOT NULL,
    `path` varchar(255) NOT NULL,
    PRIMARY KEY (`uuid`)
);

CREATE TABLE `t_notification`
(
    `id`          int      NOT NULL AUTO_INCREMENT,
    `type`        tinyint  NOT NULL DEFAULT 0,
    `sender_id`   int      NOT NULL,
    `receiver_id` int      NOT NULL,
    `content`     text     NULL,
    `unread`      tinyint  NOT NULL DEFAULT 1,
    `ctime`       datetime NOT NULL,
    `utime`       datetime NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `t_permission`
(
    `id`    int          NOT NULL AUTO_INCREMENT,
    `name`  varchar(255) NOT NULL,
    `ctime` datetime     NOT NULL,
    `utime` datetime     NOT NULL,
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
    `ctime`        datetime     NOT NULL,
    `utime`        datetime     NOT NULL,
    `deleted`      tinyint      NOT NULL DEFAULT 0,
    PRIMARY KEY (`id`)
);

CREATE TABLE `t_post_tag`
(
    `id`      int NOT NULL AUTO_INCREMENT,
    `post_id` int NOT NULL,
    `tag_id`  int NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `t_role`
(
    `id`    int          NOT NULL AUTO_INCREMENT,
    `name`  varchar(255) NOT NULL,
    `ctime` datetime     NOT NULL,
    `utime` datetime     NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `t_role_permission`
(
    `id`            int      NOT NULL AUTO_INCREMENT,
    `role_id`       int      NOT NULL,
    `permission_id` int      NOT NULL,
    `ctime`         datetime NOT NULL,
    `utime`         datetime NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `t_tag`
(
    `id`    int          NOT NULL AUTO_INCREMENT,
    `name`  varchar(255) NOT NULL,
    `ctime` datetime     NOT NULL,
    `utime` datetime     NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `t_user`
(
    `id`          int          NOT NULL AUTO_INCREMENT,
    `email`       varchar(255) NOT NULL,
    `nick_name`   varchar(255) NOT NULL,
    `password`    varchar(255) NOT NULL,
    `gender`      tinyint      NOT NULL DEFAULT 0 COMMENT 'ISO/IEC 5218',
    `location`    varchar(255) NULL     DEFAULT '',
    `avatar`      varchar(255) NULL,
    `description` text         NULL,
    `ctime`       datetime     NOT NULL,
    `utime`       datetime     NOT NULL,
    `deleted`     tinyint      NOT NULL DEFAULT 0,
    PRIMARY KEY (`id`)
);

CREATE TABLE `t_user_community_role`
(
    `id`           int      NOT NULL AUTO_INCREMENT,
    `user_id`      int      NOT NULL,
    `community_id` int      NOT NULL,
    `role_id`      int      NOT NULL,
    `level`        int      NOT NULL DEFAULT 1,
    `exp`          int      NOT NULL DEFAULT 0,
    `ctime`        datetime NOT NULL,
    `utime`        datetime NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `t_user_like_comment`
(
    `id`         int      NOT NULL AUTO_INCREMENT,
    `user_id`    int      NOT NULL,
    `author_id`  int      NOT NULL,
    `comment_id` int      NOT NULL,
    `like`       tinyint  NOT NULL DEFAULT 1,
    `ctime`      datetime NOT NULL,
    `utime`      datetime NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `t_user_like_post`
(
    `id`        int      NOT NULL AUTO_INCREMENT,
    `user_id`   int      NOT NULL,
    `post_id`   int      NOT NULL,
    `author_id` int      NOT NULL,
    `like`      tinyint  NOT NULL DEFAULT 1,
    `ctime`     datetime NOT NULL,
    `utime`     datetime NOT NULL,
    PRIMARY KEY (`id`)
);


ALTER TABLE `t_browse_history`
    ADD CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`);
ALTER TABLE `t_browse_history`
    ADD CONSTRAINT `brow_history_post_id` FOREIGN KEY (`post_id`) REFERENCES `t_post` (`id`);
ALTER TABLE `t_checkin`
    ADD CONSTRAINT `user_id_copy_1` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`);
ALTER TABLE `t_checkin`
    ADD CONSTRAINT `community_id_copy_1` FOREIGN KEY (`community_id`) REFERENCES `t_community` (`id`);
ALTER TABLE `t_comment`
    ADD CONSTRAINT `comment_post_id` FOREIGN KEY (`post_id`) REFERENCES `t_post` (`id`);
ALTER TABLE `t_comment`
    ADD CONSTRAINT `reply_to` FOREIGN KEY (`reply_to_comment_id`) REFERENCES `t_comment` (`id`);
ALTER TABLE `t_community_tag`
    ADD CONSTRAINT `community_id_copy_4` FOREIGN KEY (`community_id`) REFERENCES `t_community` (`id`);
ALTER TABLE `t_community_tag`
    ADD CONSTRAINT `t_community_tag_tag_id` FOREIGN KEY (`tag_id`) REFERENCES `t_tag` (`id`);
ALTER TABLE `t_community_tag_copy_1`
    ADD CONSTRAINT `community_id_copy_3` FOREIGN KEY (`community_id`) REFERENCES `t_community` (`id`);
ALTER TABLE `t_community_tag_copy_1`
    ADD CONSTRAINT `tag_id_copy_1` FOREIGN KEY (`tag_id`) REFERENCES `t_tag` (`id`);
ALTER TABLE `t_notification`
    ADD CONSTRAINT `sender_id` FOREIGN KEY (`sender_id`) REFERENCES `t_user` (`id`);
ALTER TABLE `t_notification`
    ADD CONSTRAINT `receiver_id` FOREIGN KEY (`receiver_id`) REFERENCES `t_user` (`id`);
ALTER TABLE `t_post`
    ADD CONSTRAINT `t_post_author_id` FOREIGN KEY (`author_id`) REFERENCES `t_user` (`id`);
ALTER TABLE `t_post`
    ADD CONSTRAINT `community_id_copy_2` FOREIGN KEY (`community_id`) REFERENCES `t_community` (`id`);
ALTER TABLE `t_post_tag`
    ADD CONSTRAINT `t_post_tag_post_id` FOREIGN KEY (`post_id`) REFERENCES `t_post` (`id`);
ALTER TABLE `t_post_tag`
    ADD CONSTRAINT `t_post_tag_tag_id` FOREIGN KEY (`tag_id`) REFERENCES `t_tag` (`id`);
ALTER TABLE `t_role_permission`
    ADD CONSTRAINT `role_id_copy_1` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`);
ALTER TABLE `t_role_permission`
    ADD CONSTRAINT `permission_id` FOREIGN KEY (`permission_id`) REFERENCES `t_permission` (`id`);
ALTER TABLE `t_user_community_role`
    ADD CONSTRAINT `t_user_community_role_user_id` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`);
ALTER TABLE `t_user_community_role`
    ADD CONSTRAINT `community_id` FOREIGN KEY (`community_id`) REFERENCES `t_community` (`id`);
ALTER TABLE `t_user_community_role`
    ADD CONSTRAINT `role_id` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`);
ALTER TABLE `t_user_like_comment`
    ADD CONSTRAINT `t_user_like_comment_user_id` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`);
ALTER TABLE `t_user_like_comment`
    ADD CONSTRAINT `comment_id` FOREIGN KEY (`comment_id`) REFERENCES `t_comment` (`id`);
ALTER TABLE `t_user_like_comment`
    ADD CONSTRAINT `t_user_like_comment_author_id` FOREIGN KEY (`author_id`) REFERENCES `t_user` (`id`);
ALTER TABLE `t_user_like_post`
    ADD CONSTRAINT `t_user_like_post_user_id` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`);
ALTER TABLE `t_user_like_post`
    ADD CONSTRAINT `post_id` FOREIGN KEY (`post_id`) REFERENCES `t_post` (`id`);
ALTER TABLE `t_user_like_post`
    ADD CONSTRAINT `t_user_like_post_author_id` FOREIGN KEY (`author_id`) REFERENCES `t_user` (`id`);

