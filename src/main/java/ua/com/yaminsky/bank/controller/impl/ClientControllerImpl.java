package ua.com.yaminsky.bank.controller.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import ua.com.yaminsky.bank.controller.IClientController;
import ua.com.yaminsky.bank.domain.Client;
import ua.com.yaminsky.bank.service.IClientService;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class ClientControllerImpl implements IClientController {
    private IClientService clientService;

    @Override
    public Optional<List<Client>> getClientByFirstName(String firstName) {
        return clientService.getClientByFirstName(firstName);
    }

    @Override
    public Optional<List<Client>> getClientByLastName(String lastName) {
        return clientService.getClientByLastName(lastName);
    }

    @Override
    public List<Client> getAll() {
        return clientService.getAll();
    }

    @Override
    public Optional<Client> getById(Integer id) {
        return clientService.getById(id);
    }

    @Override
    public int create(Client client) {
        return clientService.create(client);
    }

    @Override
    public int delete(Integer id) {
        return clientService.delete(id);
    }

    @Override
    public int update(Integer id, Client client) {
        return clientService.update(id, client);
    }
}
