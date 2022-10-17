package ua.com.yaminsky.bank.controller.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import ua.com.yaminsky.bank.controller.ITransactionController;
import ua.com.yaminsky.bank.domain.Transaction;
import ua.com.yaminsky.bank.service.ITransactionService;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class TransactionControllerImpl implements ITransactionController {
    private ITransactionService transactionService;

    @Override
    public List<Transaction> getAll() {
        return transactionService.getAll();
    }

    @Override
    public Optional<Transaction> getById(Integer id) {
        return transactionService.getById(id);
    }

    @Override
    public int create(Transaction transaction) {
        return transactionService.create(transaction);
    }

    @Override
    public int delete(Integer id) {
        return transactionService.delete(id);
    }

    @Override
    public Optional<List<Transaction>> getTransactionsByBuyerAccountId(Integer accountId) {
        return transactionService.getTransactionsByBuyerAccountId(accountId);
    }

    @Override
    public Optional<List<Transaction>> getTransactionsBySellerAccountId(Integer accountId) {
        return transactionService.getTransactionsBySellerAccountId(accountId);
    }
}
