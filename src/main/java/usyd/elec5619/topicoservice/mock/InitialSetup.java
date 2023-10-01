package usyd.elec5619.topicoservice.mock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import usyd.elec5619.topicoservice.model.User;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class InitialSetup {
    private final MockAdmin mockAdmin;
    private final MockCommunity mockCommunity;

    @Bean
    CommandLineRunner initialDataSetup() {
        return (args) -> {
            User admin = mockAdmin.createAdmin();
            log.info("Admin created with id: {}, email: {}, password: {}", admin.getId(), admin.getEmail(), mockAdmin.adminPassword);
            mockCommunity.createCommunities();
            log.info("Communities created");
        };
    }


}
