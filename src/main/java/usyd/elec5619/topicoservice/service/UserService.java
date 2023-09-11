package usyd.elec5619.topicoservice.service;

import org.springframework.stereotype.Service;
import usyd.elec5619.topicoservice.model.User;


@Service
public interface UserService {
    User createUser();
}
