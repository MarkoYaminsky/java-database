package ua.com.yaminsky.bank.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.yaminsky.bank.dao.IBankDao;
import ua.com.yaminsky.bank.domain.Bank;
import ua.com.yaminsky.bank.service.IBankService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BankServiceImpl implements IBankService {
    private IBankDao bankDao;

    @Override
    public List<Bank> getAll() {
        return bankDao.getAll();
    }

    @Override
    public Optional<Bank> getById(Integer id) {
        return bankDao.getById(id);
    }

    @Override
    public int create(Bank bank) {
        return bankDao.create(bank);
    }

    @Override
    public int delete(Integer id) {
        return bankDao.delete(id);
    }

    @Override
    public int update(Integer id, Bank bank) {
        return bankDao.update(id, bank);
    }
}
