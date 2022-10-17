package ua.com.yaminsky.bank.dao;

import ua.com.yaminsky.bank.domain.Transaction;

import java.util.List;
import java.util.Optional;

public interface ITransactionDao extends IGeneralDao<Transaction, Integer> {
    Optional<List<Transaction>> getTransactionsByBuyerAccountId(Integer accountId);

    Optional<List<Transaction>> getTransactionsBySellerAccountId(Integer accountId);
}
