package ua.com.yaminsky.bank.controller.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import ua.com.yaminsky.bank.controller.IBankAccountController;
import ua.com.yaminsky.bank.domain.BankAccount;
import ua.com.yaminsky.bank.service.IBankAccountService;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class BankAccountControllerImpl implements IBankAccountController {
    private IBankAccountService bankAccountService;

    @Override
    public Optional<List<BankAccount>> getBankAccountsByClientId(Integer clientId) {
        return bankAccountService.getBankAccountsByClientId(clientId);
    }

    @Override
    public List<BankAccount> getAll() {
        return bankAccountService.getAll();
    }

    @Override
    public Optional<BankAccount> getById(Integer id) {
        return bankAccountService.getById(id);
    }

    @Override
    public int create(BankAccount bankAccount) {
        return bankAccountService.create(bankAccount);
    }

    @Override
    public int delete(Integer id) {
        return bankAccountService.delete(id);
    }
}
