package ua.com.yaminsky.bank.dao.impl;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.com.yaminsky.bank.dao.ITransactionDao;
import ua.com.yaminsky.bank.domain.Transaction;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class TransactionDaoImpl implements ITransactionDao {
    private static final String GET_BY_ID = "SELECT * FROM transaction WHERE id=?";
    private static final String GET_ALL = "SELECT * FROM transaction";
    private static final String CREATE = "INSERT INTO transaction (id, event, sum_in_dollars, " +
            "bank_account_buyer_id, bank_account_seller_id) VALUES (?, ?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM transaction WHERE id=?";
    private static final String GET_BY_BUYER_REQUISITES = "SELECT * FROM transaction WHERE bank_account_buyer_id=?";
    private static final String GET_BY_SELLER_REQUISITES = "SELECT * FROM transaction WHERE bank_account_seller_id=?";

    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Transaction> getAll() {
        return jdbcTemplate.query(GET_ALL, BeanPropertyRowMapper.newInstance(Transaction.class));
    }

    @Override
    public Optional<Transaction> getById(Integer id) {
        Optional<Transaction> transaction;
        try {
            transaction = Optional.ofNullable(
                    jdbcTemplate.queryForObject(GET_BY_ID, BeanPropertyRowMapper.newInstance(Transaction.class), id));
        } catch (EmptyResultDataAccessException e) {
            transaction = Optional.empty();
        }
        return transaction;
    }

    @Override
    public int create(@NotNull Transaction transaction) {
        return jdbcTemplate.update(CREATE, transaction.getId(), transaction.getEvent(),
                transaction.getSumInDollars(), transaction.getBankAccountBuyerId(),
                transaction.getBankAccountSellerId());
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }

    @Override
    public Optional<List<Transaction>> getTransactionsByBuyerAccountId(Integer accountId) {
        Optional<List<Transaction>> transactions;
        try {
            transactions = Optional.of(jdbcTemplate.query(GET_BY_BUYER_REQUISITES,
                    BeanPropertyRowMapper.newInstance(Transaction.class), accountId));
        } catch (EmptyResultDataAccessException e) {
            transactions = Optional.empty();
        }
        return transactions;
    }

    @Override
    public Optional<List<Transaction>> getTransactionsBySellerAccountId(Integer accountId) {
        Optional<List<Transaction>> transactions;
        try {
            transactions = Optional.of(jdbcTemplate.query(GET_BY_SELLER_REQUISITES,
                    BeanPropertyRowMapper.newInstance(Transaction.class), accountId));
        } catch (EmptyResultDataAccessException e) {
            transactions = Optional.empty();
        }
        return transactions;
    }
}