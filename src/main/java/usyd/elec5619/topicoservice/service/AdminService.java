package usyd.elec5619.topicoservice.service;

import org.springframework.stereotype.Service;

@Service
public interface AdminService {
    void assignAdmin(Long userId);
}
