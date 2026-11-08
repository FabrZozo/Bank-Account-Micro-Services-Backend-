package fabrice.accountservice.mappers;

import fabrice.accountservice.dtos.BankaccountDto;
import fabrice.accountservice.entities.Bankaccount;
import fabrice.accountservice.enums.AccountType;

public class BankAccountMapper {


    public static Bankaccount toBankAccount(BankaccountDto bankaccountDto) {
        return Bankaccount.builder().accountType(bankaccountDto.getAccountType()).
                balance(bankaccountDto.getBalance()).createAt(bankaccountDto.getCreateAt())
                .currency(bankaccountDto.getCurrency()).customerId(bankaccountDto.getCustomerId())
                .build();
    }
    public static BankaccountDto toBankaccountDto(Bankaccount bankaccount) {
        return BankaccountDto.builder().accountType(bankaccount.getAccountType()).
                balance(bankaccount.getBalance()).createAt(bankaccount.getCreateAt())
                .currency(bankaccount.getCurrency()).customerId(bankaccount.getCustomerId())
                .build();
    }
}
