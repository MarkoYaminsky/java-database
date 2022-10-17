package ua.com.yaminsky.bank.dao.impl;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.com.yaminsky.bank.dao.IBankDao;
import ua.com.yaminsky.bank.domain.Bank;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class BankDaoImpl implements IBankDao {
    private static final String GET_BY_ID = "SELECT * FROM bank WHERE id=?";
    private static final String GET_ALL = "SELECT * FROM bank";
    private static final String CREATE = "INSERT INTO bank (id, name, country_id) VALUES (?, ?, ?)";
    private static final String DELETE = "DELETE FROM bank WHERE id=?";
    private static final String UPDATE = "UPDATE bank SET name=?, country_id=? WHERE id=?";

    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Bank> getAll() {
        return jdbcTemplate.query(GET_ALL, BeanPropertyRowMapper.newInstance(Bank.class));
    }

    @Override
    public Optional<Bank> getById(Integer id) {
        Optional<Bank> bank;
        try {
            bank = Optional.ofNullable(
                    jdbcTemplate.queryForObject(GET_BY_ID, BeanPropertyRowMapper.newInstance(Bank.class), id));
        } catch (EmptyResultDataAccessException e) {
            bank = Optional.empty();
        }
        return bank;
    }

    @Override
    public int create(@NotNull Bank bank) {
        return jdbcTemplate.update(CREATE, bank.getId(), bank.getName(),  bank.getCountryId());
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }

    @Override
    public int update(Integer id, @NotNull Bank bank) {
        return jdbcTemplate.update(UPDATE, bank.getName(), bank.getCountryId(), id);
    }
}
