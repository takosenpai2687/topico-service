package com.topico.dao;

import com.topico.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findUserByIdAndDeletedIsFalse(Long id);

    User findUserByNickNameAndDeletedIsFalse(String nickName);

    User findUserByEmailIgnoreCaseAndDeletedIsFalse(String email);
}
