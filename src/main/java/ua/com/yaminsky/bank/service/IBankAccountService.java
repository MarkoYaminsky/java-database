package ua.com.yaminsky.bank.service;

import ua.com.yaminsky.bank.domain.BankAccount;

import java.util.List;
import java.util.Optional;

public interface IBankAccountService extends IGeneralService<BankAccount, Integer> {
    Optional<List<BankAccount>> getBankAccountsByClientId(Integer clientId);
}
