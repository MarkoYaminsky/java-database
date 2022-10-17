package ua.com.yaminsky.bank.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.yaminsky.bank.dao.ICountryDao;
import ua.com.yaminsky.bank.domain.Country;
import ua.com.yaminsky.bank.service.ICountryService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CountryServiceImpl implements ICountryService {
    private ICountryDao countryDao;

    @Override
    public List<Country> getAll() {
        return countryDao.getAll();
    }

    @Override
    public Optional<Country> getById(Integer id) {
        return countryDao.getById(id);
    }

    @Override
    public int create(Country country) {
        return countryDao.create(country);
    }

    @Override
    public int delete(Integer id) {
        return countryDao.delete(id);
    }
}
