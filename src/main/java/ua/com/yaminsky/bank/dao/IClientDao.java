package ua.com.yaminsky.bank.dao;

import ua.com.yaminsky.bank.domain.Client;

import java.util.List;
import java.util.Optional;

public interface IClientDao extends IUpdatableDao<Client, Integer> {
    Optional<List<Client>> getClientByFirstName(String firstName);

    Optional<List<Client>> getClientByLastName(String lastName);
}
