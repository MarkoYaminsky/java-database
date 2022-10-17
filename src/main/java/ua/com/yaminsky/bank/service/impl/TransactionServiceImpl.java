package ua.com.yaminsky.bank.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.yaminsky.bank.dao.ITransactionDao;
import ua.com.yaminsky.bank.domain.Transaction;
import ua.com.yaminsky.bank.service.ITransactionService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements ITransactionService {
    private ITransactionDao transactionDao;

    @Override
    public List<Transaction> getAll() {
        return transactionDao.getAll();
    }

    @Override
    public Optional<Transaction> getById(Integer id) {
        return transactionDao.getById(id);
    }

    @Override
    public int create(Transaction transaction) {
        return transactionDao.create(transaction);
    }

    @Override
    public int delete(Integer id) {
        return transactionDao.delete(id);
    }

    @Override
    public Optional<List<Transaction>> getTransactionsByBuyerAccountId(Integer accountId) {
        return transactionDao.getTransactionsByBuyerAccountId(accountId);
    }

    @Override
    public Optional<List<Transaction>> getTransactionsBySellerAccountId(Integer accountId) {
        return transactionDao.getTransactionsBySellerAccountId(accountId);
    }
}
