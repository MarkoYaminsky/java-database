package ua.com.yaminsky.bank.service;

import ua.com.yaminsky.bank.domain.Transaction;

import java.util.List;
import java.util.Optional;

public interface ITransactionService extends IGeneralService<Transaction, Integer> {
    Optional<List<Transaction>> getTransactionsByBuyerAccountId(Integer accountId);

    Optional<List<Transaction>> getTransactionsBySellerAccountId(Integer accountId);
}
