package usyd.elec5619.topicoservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import usyd.elec5619.topicoservice.dto.auth.LoginDto;
import usyd.elec5619.topicoservice.dto.auth.SignupDto;
import usyd.elec5619.topicoservice.exception.http.BadRequestException;
import usyd.elec5619.topicoservice.mapper.UserMapper;
import usyd.elec5619.topicoservice.model.User;
import usyd.elec5619.topicoservice.service.AuthService;
import usyd.elec5619.topicoservice.service.JwtService;
import usyd.elec5619.topicoservice.type.Gender;
import usyd.elec5619.topicoservice.type.Role;
import usyd.elec5619.topicoservice.vo.LoginVO;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    @Override
    public LoginVO signup(SignupDto signupDto) {
        // Check existing email
        final String email = signupDto.getEmail();
        Optional<User> dbUser = userMapper.getByEmail(email);
        if (dbUser.isPresent()) {
            throw new BadRequestException("Email already exists");
        }
        // Check existing nickName
        final String nickName = signupDto.getNickName();
        if (userMapper.getByNickName(nickName) != null) {
            throw new BadRequestException("NickName already exists");
        }
        // Check password
        final String password = signupDto.getPassword();
        if (password.length() < 6 || password.length() > 16) {
            throw new BadRequestException("Password must be 6-16 characters");
        }
        // Create user
        User user = User.builder()
                .email(email)
                .nickName(nickName)
                .password(passwordEncoder.encode(password))
                .role(Role.ROLE_USER)
                .gender(Gender.NOT_KNOWN)
                .build();
        userMapper.insertOne(user);
        // JWT
        String token = jwtService.generateToken(user);
        return LoginVO.builder()
                .email(email)
                .nickName(nickName)
                .token(token)
                .build();
    }

    @Override
    public LoginVO login(LoginDto loginDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        var user = userMapper.getByEmail(loginDto.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid email"));
        String token = jwtService.generateToken(user);
        return LoginVO.builder()
                .email(user.getEmail())
                .nickName(user.getNickName())
                .token(token)
                .build();
    }
}
