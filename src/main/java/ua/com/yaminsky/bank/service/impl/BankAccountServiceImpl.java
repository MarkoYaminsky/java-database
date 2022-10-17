package ua.com.yaminsky.bank.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.yaminsky.bank.dao.IBankAccountDao;
import ua.com.yaminsky.bank.domain.BankAccount;
import ua.com.yaminsky.bank.service.IBankAccountService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BankAccountServiceImpl implements IBankAccountService {
    private IBankAccountDao bankAccountDao;

    @Override
    public Optional<List<BankAccount>> getBankAccountsByClientId(Integer clientId) {
        return bankAccountDao.getBankAccountsByClientId(clientId);
    }

    @Override
    public List<BankAccount> getAll() {
        return bankAccountDao.getAll();
    }

    @Override
    public Optional<BankAccount> getById(Integer id) {
        return bankAccountDao.getById(id);
    }

    @Override
    public int create(BankAccount bankAccount) {
        return bankAccountDao.create(bankAccount);
    }

    @Override
    public int delete(Integer id) {
        return bankAccountDao.delete(id);
    }
}
