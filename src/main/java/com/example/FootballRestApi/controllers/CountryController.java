package com.example.FootballRestApi.controllers;

import com.example.FootballRestApi.models.Continent;
import com.example.FootballRestApi.models.Country;
import com.example.FootballRestApi.services.ContinentService;
import com.example.FootballRestApi.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CountryController {

    @Autowired
    private CountryService countryService;

    @Autowired
    private ContinentService continentService;

    @GetMapping(value = "/countries")
    public String getCountries(Model model){
            List<Country> countries = countryService.getCountries();
            model.addAttribute("countries", countries);
            return "country/index";
    }

    @GetMapping(value = "/countries/{id}")
    public String getCountryById(Model model, @PathVariable("id") Long country_id){
        Country country = countryService.getCountryById(country_id);
        model.addAttribute("country", country);
        return "country/detail";
    }

    @GetMapping(value = "/countries/create")
    public String createCountry(Country country,Model model){
        model.addAttribute("continents", continentService.getContinents());
        return "country/create";
    }

    @PostMapping(value = "/countries/create")
    public String saveCountry(Country country, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors())
            return "country/index";

        countryService.createCountry(country);

        return "redirect:/countries";
    }

    @GetMapping(value = "/countries/update/{id}")
    public String updateCountry(Model model,@PathVariable("id") Long country_id){
        Country country = countryService.getCountryById(country_id);
        model.addAttribute("continents", continentService.getContinents());
        model.addAttribute("country", country);
        return "country/update";
    }

    @PostMapping(value = "/countries/update/{id}")
    public String saveUpdatedCountry(@PathVariable("id") Long country_id, Country updatedCountry){
        countryService.updateCountry(country_id, updatedCountry);
        return "redirect:/countries";
    }

    @GetMapping(value = "/countries/delete/{id}")
    public String deleteCountry(@PathVariable("id") Long country_id){
        countryService.deleteCountry(country_id);
        return "redirect:/countries";
    }
}
