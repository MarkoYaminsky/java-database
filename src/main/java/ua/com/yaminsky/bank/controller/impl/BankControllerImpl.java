package ua.com.yaminsky.bank.controller.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import ua.com.yaminsky.bank.controller.IBankController;
import ua.com.yaminsky.bank.domain.Bank;
import ua.com.yaminsky.bank.service.IBankService;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class BankControllerImpl implements IBankController {
    private IBankService bankService;

    @Override
    public List<Bank> getAll() {
        return bankService.getAll();
    }

    @Override
    public Optional<Bank> getById(Integer id) {
        return bankService.getById(id);
    }

    @Override
    public int create(Bank bank) {
        return bankService.create(bank);
    }

    @Override
    public int delete(Integer id) {
        return bankService.delete(id);
    }

    @Override
    public int update(Integer id, Bank bank) {
        return bankService.update(id, bank);
    }
}
