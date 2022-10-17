package ua.com.yaminsky.bank.controller;

import ua.com.yaminsky.bank.domain.BankAccount;

import java.util.List;
import java.util.Optional;

public interface IBankAccountController extends IGeneralController<BankAccount, Integer> {
    Optional<List<BankAccount>> getBankAccountsByClientId(Integer clientId);
}
