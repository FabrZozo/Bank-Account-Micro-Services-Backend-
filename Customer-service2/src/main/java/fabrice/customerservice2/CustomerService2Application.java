package fabrice.customerservice2;

import fabrice.customerservice2.dtos.CustomerDto;
import fabrice.customerservice2.repository.CustomerRepository;
import fabrice.customerservice2.service.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication

public class CustomerService2Application {

    public static void main(String[] args) {
        SpringApplication.run(CustomerService2Application.class, args);
    }

}
