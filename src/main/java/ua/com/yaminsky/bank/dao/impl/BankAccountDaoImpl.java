package ua.com.yaminsky.bank.dao.impl;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.com.yaminsky.bank.dao.IBankAccountDao;
import ua.com.yaminsky.bank.domain.BankAccount;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class BankAccountDaoImpl implements IBankAccountDao {
    private static final String GET_BY_ID = "SELECT * FROM bank_account WHERE id=?";
    private static final String GET_ALL = "SELECT * FROM bank_account";
    private static final String GET_BY_CLIENT_ID = "SELECT * FROM bank_account WHERE client_id=?";
    private static final String CREATE = "INSERT INTO bank_account (id, requisites, client_id, person_type, bank_id) VALUES (?, ?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM bank_account WHERE id=?";

    private JdbcTemplate jdbcTemplate;

    @Override
    public Optional<List<BankAccount>> getBankAccountsByClientId(Integer clientId) {
        Optional<List<BankAccount>> bankAccounts;
        try {
            bankAccounts = Optional.of(jdbcTemplate.query(GET_BY_CLIENT_ID,
                    BeanPropertyRowMapper.newInstance(BankAccount.class), clientId));
        } catch (EmptyResultDataAccessException e) {
            bankAccounts = Optional.empty();
        }
        return bankAccounts;
    }

    @Override
    public List<BankAccount> getAll() {
        return jdbcTemplate.query(GET_ALL, BeanPropertyRowMapper.newInstance(BankAccount.class));
    }

    @Override
    public Optional<BankAccount> getById(Integer id) {
        Optional<BankAccount> bankAccount;
        try {
            bankAccount = Optional.ofNullable(
                    jdbcTemplate.queryForObject(GET_BY_ID, BeanPropertyRowMapper.newInstance(BankAccount.class),
                            id));
        } catch (EmptyResultDataAccessException e) {
            bankAccount = Optional.empty();
        }
        return bankAccount;
    }

    @Override
    public int create(@NotNull BankAccount bankAccount) {
        return jdbcTemplate.update(CREATE, bankAccount.getId(),
                bankAccount.getRequisites(), bankAccount.getClientId(),
                bankAccount.getPersonType(), bankAccount.getBankId());
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }
}
