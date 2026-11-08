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

    @Bean
    CommandLineRunner commandLineRunner(BankAccountRepository bankAccountRepository, BankAccountService accountService,CustomerClient customerClient) {
        return args -> {

            bankAccountRepository.deleteAll();

            BankaccountDto bankaccountDto1 = BankaccountDto.builder().accountType(AccountType.CURRENT_ACCOUNT)
                    .createAt(LocalDate.now()).customerId(customerClient.getListCustomers().get(0).getId())
                    .balance(28000).currency("EUR").build();
            BankaccountDto bankaccountDto2 = BankaccountDto.builder().accountType(AccountType.SAVING_ACCOUNT)
                    .createAt(LocalDate.of(2025,10,5)).customerId(customerClient.getListCustomers().get(1).getId())
                    .balance(22000).currency("DOLAR").build();


            accountService.createBankAccount(bankaccountDto1);
            accountService.createBankAccount(bankaccountDto2);

            accountService.getAllIds().forEach(System.out::println);
        };
    }

}
