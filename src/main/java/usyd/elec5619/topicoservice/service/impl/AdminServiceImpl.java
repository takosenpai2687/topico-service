package usyd.elec5619.topicoservice.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import usyd.elec5619.topicoservice.mapper.AdminMapper;
import usyd.elec5619.topicoservice.service.AdminService;
import usyd.elec5619.topicoservice.service.UserService;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminMapper adminMapper;

    @Override
    public void assignAdmin(Long userId) {
        adminMapper.assignAdmin(userId);
    }
}
