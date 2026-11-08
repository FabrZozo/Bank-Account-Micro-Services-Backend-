package fabrice.customerservice;

import fabrice.customerservice.dtos.CustomerDto;
import fabrice.customerservice.repository.CustomerRepository;
import fabrice.customerservice.service.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
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

        };
    }
}
