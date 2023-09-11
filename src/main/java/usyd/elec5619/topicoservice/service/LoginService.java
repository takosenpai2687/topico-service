package usyd.elec5619.topicoservice.service;

import usyd.elec5619.topicoservice.dto.LoginDto;
import usyd.elec5619.topicoservice.vo.LoginVO;

public interface LoginService {


    LoginVO login(LoginDto loginDto);
}
