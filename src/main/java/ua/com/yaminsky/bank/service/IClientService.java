package ua.com.yaminsky.bank.service;

import ua.com.yaminsky.bank.domain.Client;

import java.util.List;
import java.util.Optional;

public interface IClientService extends IUpdatableService<Client, Integer> {
    Optional<List<Client>> getClientByFirstName(String firstName);

    Optional<List<Client>> getClientByLastName(String lastName);
}
