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
    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository, CustomerService customerService) {
        return args -> {

            customerRepository.deleteAll();

            System.out.println(
                    "Nombre de clients après suppression : " + customerRepository.count()
            );

            CustomerDto customerDto1 = CustomerDto.builder().firstName("fabrice").lastName("nzolang").
                    email("fabricenzolang@yahoo.com").build();
            CustomerDto customerDto2 = CustomerDto.builder().firstName("cedric").lastName("nzolang").
                    email("cedriczolang@yahoo.com").build();

            customerService.addCustomer(customerDto1);
            customerService.addCustomer(customerDto2);

            customerRepository.findAll().forEach(e-> System.out.println(e.getId()));

        };
    }
}
