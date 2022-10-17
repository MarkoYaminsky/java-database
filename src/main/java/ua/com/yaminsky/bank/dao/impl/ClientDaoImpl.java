package ua.com.yaminsky.bank.dao.impl;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.com.yaminsky.bank.dao.IClientDao;
import ua.com.yaminsky.bank.domain.Client;

import java.util.List;
import java.util.Optional;


@Repository
@AllArgsConstructor
public class ClientDaoImpl implements IClientDao {
    private static final String GET_BY_ID = "SELECT * FROM client WHERE id=?";
    private static final String GET_ALL = "SELECT * FROM client";
    private static final String CREATE = "INSERT INTO client (id, first_name, last_name, country_id) VALUES (?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM client WHERE id=?";
    private static final String UPDATE = "UPDATE client SET first_name=?, last_name=?, country_id=? WHERE id=?";
    private static final String GET_BY_FIRST_NAME = "SELECT * FROM client WHERE first_name=?";
    private static final String GET_BY_LAST_NAME = "SELECT * FROM client WHERE last_name=?";
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Client> getAll() {
        return jdbcTemplate.query(GET_ALL, BeanPropertyRowMapper.newInstance(Client.class));
    }

    @Override
    public Optional<Client> getById(Integer id) {
        Optional<Client> client;
        try {
            client = Optional.ofNullable(
                    jdbcTemplate.queryForObject(GET_BY_ID, BeanPropertyRowMapper.newInstance(Client.class), id));
        } catch (EmptyResultDataAccessException e) {
            client = Optional.empty();
        }
        return client;
    }

    @Override
    public int create(@NotNull Client client) {
        return jdbcTemplate.update(CREATE, client.getId(),
                client.getFirstName(), client.getLastName(),
                client.getCountryId());
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }

    @Override
    public int update(Integer id, @NotNull Client client) {
        return jdbcTemplate.update(UPDATE,
                client.getFirstName(), client.getLastName(),
                client.getCountryId(), id);
    }

    @Override
    public Optional<List<Client>> getClientByFirstName(String firstName) {
        Optional<List<Client>> clients;
        try {
            clients = Optional.of(jdbcTemplate.query(GET_BY_FIRST_NAME,
                    BeanPropertyRowMapper.newInstance(Client.class), firstName));
        } catch (EmptyResultDataAccessException e) {
            clients = Optional.empty();
        }
        return clients;
    }

    @Override
    public Optional<List<Client>> getClientByLastName(String lastName) {
        Optional<List<Client>> clients;
        try {
            clients = Optional.of(jdbcTemplate.query(GET_BY_LAST_NAME,
                    BeanPropertyRowMapper.newInstance(Client.class), lastName));
        } catch (EmptyResultDataAccessException e) {
            clients = Optional.empty();
        }
        return clients;
    }
}
