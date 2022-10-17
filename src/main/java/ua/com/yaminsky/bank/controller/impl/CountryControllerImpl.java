package ua.com.yaminsky.bank.controller.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import ua.com.yaminsky.bank.controller.ICountryController;
import ua.com.yaminsky.bank.domain.Country;
import ua.com.yaminsky.bank.service.ICountryService;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class CountryControllerImpl implements ICountryController {
    private ICountryService countryService;

    @Override
    public List<Country> getAll() {
        return countryService.getAll();
    }

    @Override
    public Optional<Country> getById(Integer id) {
        return countryService.getById(id);
    }

    @Override
    public int create(Country country) {
        return countryService.create(country);
    }

    @Override
    public int delete(Integer id) {
        return countryService.delete(id);
    }
}
