package fabrice.accountservice.web;

import fabrice.accountservice.accountServices.BankAccountService;
import fabrice.accountservice.dtos.BankAccountDetailsDto;
import fabrice.accountservice.dtos.BankaccountDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountException;
import java.util.List;

@RestController
@RequestMapping("/bankAccount")
public class BankAccountController {
    private BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }
    @GetMapping("/getListBankAccount")
    public ResponseEntity<List<BankaccountDto>> getListBankAccount() {
        return new ResponseEntity<>(bankAccountService.getListBankAccount(), HttpStatus.OK);
    }
    @GetMapping("/getBankAccountById/{accountId}")
    public ResponseEntity<BankaccountDto> getBankAccountById(@PathVariable String accountId) throws AccountException {
        return ResponseEntity.ok(bankAccountService.getBankAccountById(accountId));
    }
    @DeleteMapping("/deleteBankAccount/{accountId}")
    public ResponseEntity<Void> deleteBankAccount(@PathVariable String accountId) throws AccountException {
        bankAccountService.deleteBankAccount(accountId);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("updateBankAccount/{idAccount}")
    public ResponseEntity<BankaccountDto> updateBankAccount(@PathVariable String idAccount,@RequestBody BankaccountDto bankaccountDto) throws AccountException {
        return ResponseEntity.ok(bankAccountService.updateBankAccount(idAccount, bankaccountDto));
    }
    @PostMapping("/createBankAccount")
    public ResponseEntity<BankaccountDto> createBankAccount(@RequestBody BankaccountDto bankaccountDto) {
        return ResponseEntity.ok(bankAccountService.createBankAccount(bankaccountDto));
    }
    @GetMapping("/details/{accountId}")
    public ResponseEntity<BankAccountDetailsDto> detailsBankAccount(@PathVariable String accountId) throws AccountException {
        return ResponseEntity.ok(bankAccountService.getBankAccountWithCustomer(accountId));
    }
    @GetMapping("/depasseSommeMax/{accountId}")
    public ResponseEntity<Boolean> depasseSommeMax(
            @PathVariable String accountId) throws AccountException {

        return ResponseEntity.ok(
                bankAccountService.depasseSommeMax(accountId)
        );
    }
}
