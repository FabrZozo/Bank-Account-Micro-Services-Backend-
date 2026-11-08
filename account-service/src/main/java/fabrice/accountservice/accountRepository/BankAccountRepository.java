package fabrice.accountservice.accountRepository;

import fabrice.accountservice.entities.Bankaccount;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BankAccountRepository  extends JpaRepository<Bankaccount, String> {

}
