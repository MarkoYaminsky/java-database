package ua.com.yaminsky.bank.dao.impl;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.com.yaminsky.bank.dao.ICountryDao;
import ua.com.yaminsky.bank.domain.Country;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class CountryDaoImpl implements ICountryDao {
    private static final String GET_BY_ID = "SELECT * FROM country WHERE id=?";
    private static final String GET_ALL = "SELECT * FROM country";
    private static final String CREATE = "INSERT INTO country (id, name) VALUES (?, ?)";
    private static final String DELETE = "DELETE FROM country WHERE id=?";

    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Country> getAll() {
        return jdbcTemplate.query(GET_ALL, BeanPropertyRowMapper.newInstance(Country.class));
    }

    @Override
    public Optional<Country> getById(Integer id) {
        Optional<Country> country;
        try {
            country = Optional.ofNullable(
                    jdbcTemplate.queryForObject(GET_BY_ID, BeanPropertyRowMapper.newInstance(Country.class), id));
        } catch (EmptyResultDataAccessException e) {
            country = Optional.empty();
        }
        return country;
    }

    @Override
    public int create(@NotNull Country country) {
        return jdbcTemplate.update(CREATE, country.getId(), country.getName());
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }
}
