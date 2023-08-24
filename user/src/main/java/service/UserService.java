package service;

import org.springframework.stereotype.Service;
import repo.UserRepo;

@Service
public class UserService {
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
}
