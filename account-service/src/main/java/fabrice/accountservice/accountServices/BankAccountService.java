package fabrice.accountservice.accountServices;

import fabrice.accountservice.accountRepository.BankAccountRepository;
import fabrice.accountservice.dtos.BankAccountDetailsDto;
import fabrice.accountservice.dtos.BankaccountDto;
import fabrice.accountservice.entities.Bankaccount;
import fabrice.accountservice.mappers.BankAccountMapper;
import fabrice.accountservice.model.Customer;
import fabrice.accountservice.model.CustomerClient;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountException;
import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RefreshScope
public class BankAccountService {
    private BankAccountRepository bankAccountRepository;

    private CustomerClient customerclient;
    @Value("${account.somme-max}")
    private double sommeMax;

    public BankAccountService(BankAccountRepository bankAccountRepository, CustomerClient customerclient) {
        this.bankAccountRepository = bankAccountRepository;
        this.customerclient= customerclient;
    }

    public List<BankaccountDto> getListBankAccount() {
        return bankAccountRepository.findAll().stream().map(BankAccountMapper::toBankaccountDto).toList();
    }

    public BankaccountDto getBankAccountById(String accountId) throws AccountException {
        if(bankAccountRepository.findById(accountId).isEmpty()) throw new AccountException("this Account is no exist");
        return BankAccountMapper.toBankaccountDto(bankAccountRepository.findById(accountId).get());
    }

    public void deleteBankAccount(String accountId) throws AccountException {
        if(bankAccountRepository.findById(accountId).isEmpty()) throw new AccountException("this Account is no exist");
        bankAccountRepository.deleteById(accountId);
    }
    public BankaccountDto updateBankAccount(String idAccount,BankaccountDto bankaccountDto) throws AccountException {

        if(bankAccountRepository.findById(idAccount).isEmpty()) throw new AccountException("this Account is no exist");

        Bankaccount bankaccount = bankAccountRepository.findById(idAccount).get();

        bankaccount.setAccountType(bankaccountDto.getAccountType());
        bankaccount.setBalance(bankaccountDto.getBalance());
        bankaccount.setCreateAt(bankaccountDto.getCreateAt());
        bankaccount.setCurrency(bankaccountDto.getCurrency());
        bankaccount.setCustomerId(bankaccountDto.getCustomerId());

        bankAccountRepository.save(bankaccount);

        return BankAccountMapper.toBankaccountDto(bankaccount);
    }
    public BankaccountDto createBankAccount(BankaccountDto bankaccountDto) {
        Bankaccount bankaccount = Bankaccount.builder().
                accountId(UUID.randomUUID().toString()).accountType(bankaccountDto.getAccountType())
                .balance(bankaccountDto.getBalance()).currency(bankaccountDto.getCurrency()).
                customerId(bankaccountDto.getCustomerId()).createAt(bankaccountDto.getCreateAt()).build();
        bankAccountRepository.save(bankaccount);

        return bankaccountDto;

    }
    public List<String> getAllIds(){
        return bankAccountRepository.findAll().stream().map(Bankaccount::getAccountId).toList();
    }
    public BankAccountDetailsDto getBankAccountWithCustomer(String accountId) throws AccountException {
        Bankaccount bankaccount = bankAccountRepository.findById(accountId).orElseThrow(()-> new AccountException("this Account is no exist"));
        Customer customer = customerclient.getCustomerById(bankaccount.getCustomerId());
        BankaccountDto bankaccountDto=  BankAccountMapper.toBankaccountDto(bankaccount);
        return new BankAccountDetailsDto(bankaccountDto ,customer);
    }

    public  boolean depasseSommeMax(String accountId) throws AccountException {
        BankaccountDto account = getBankAccountById(accountId);

        return account.getBalance() > sommeMax;
    }
}
