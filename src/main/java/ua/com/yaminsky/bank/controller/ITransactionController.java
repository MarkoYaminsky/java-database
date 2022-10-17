package ua.com.yaminsky.bank.controller;

import ua.com.yaminsky.bank.domain.Transaction;

import java.util.List;
import java.util.Optional;

public interface ITransactionController extends IGeneralController<Transaction, Integer>{
    Optional<List<Transaction>> getTransactionsByBuyerAccountId(Integer accountId);

    Optional<List<Transaction>> getTransactionsBySellerAccountId(Integer accountId);
}
