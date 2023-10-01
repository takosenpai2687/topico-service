package usyd.elec5619.topicoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"usyd.elec5619.topicoservice", "usyd.elec5619.topicoservice.mock", "usyd.elec5619.topicoservice.config"})
public class TopicoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TopicoServiceApplication.class, args);
    }

}
