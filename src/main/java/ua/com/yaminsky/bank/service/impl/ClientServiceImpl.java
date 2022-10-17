package ua.com.yaminsky.bank.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.yaminsky.bank.dao.IClientDao;
import ua.com.yaminsky.bank.domain.Client;
import ua.com.yaminsky.bank.service.IClientService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements IClientService {
    private IClientDao clientDao;

    @Override
    public List<Client> getAll() {
        return clientDao.getAll();
    }

    @Override
    public Optional<Client> getById(Integer id) {
        return clientDao.getById(id);
    }

    @Override
    public int create(Client client) {
        return clientDao.create(client);
    }

    @Override
    public int delete(Integer id) {
        return clientDao.delete(id);
    }

    @Override
    public int update(Integer id, Client client) {
        return clientDao.update(id, client);
    }

    @Override
    public Optional<List<Client>> getClientByFirstName(String firstName) {
        return clientDao.getClientByFirstName(firstName);
    }

    @Override
    public Optional<List<Client>> getClientByLastName(String lastName) {
        return clientDao.getClientByLastName(lastName);
    }
}
