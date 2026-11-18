package fabrice.accountservice;

import fabrice.accountservice.accountRepository.BankAccountRepository;
import fabrice.accountservice.accountServices.BankAccountService;
import fabrice.accountservice.dtos.BankaccountDto;
import fabrice.accountservice.enums.AccountType;
import fabrice.accountservice.model.CustomerClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
@EnableFeignClients(clients = CustomerClient.class)
public class AccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }


}
