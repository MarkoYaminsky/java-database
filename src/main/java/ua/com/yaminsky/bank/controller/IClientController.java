package ua.com.yaminsky.bank.controller;

import ua.com.yaminsky.bank.domain.Client;

import java.util.List;
import java.util.Optional;

public interface IClientController extends IUpdatableController<Client, Integer> {
    Optional<List<Client>> getClientByFirstName(String firstName);

    Optional<List<Client>> getClientByLastName(String lastName);
}
