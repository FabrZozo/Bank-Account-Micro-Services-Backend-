package fabrice.accountservice.dtos;

import fabrice.accountservice.model.Customer;
import lombok.*;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class BankAccountDetailsDto {
    private BankaccountDto bankaccount;
    private Customer customer;
}
