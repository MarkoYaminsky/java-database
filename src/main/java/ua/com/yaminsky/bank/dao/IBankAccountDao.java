package ua.com.yaminsky.bank.dao;

import ua.com.yaminsky.bank.domain.BankAccount;

import java.util.List;
import java.util.Optional;

public interface IBankAccountDao extends IGeneralDao<BankAccount, Integer> {
    Optional<List<BankAccount>> getBankAccountsByClientId(Integer clientId);
}

