package com.example.FootballRestApi.controllers;

import com.example.FootballRestApi.models.Continent;
import com.example.FootballRestApi.models.Country;
import com.example.FootballRestApi.services.ContinentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ContinentController {

    @Autowired
    private ContinentService continentService;

    @GetMapping(value = "/continents")
    public String getContinents(Model model){
        List<Continent> continents = continentService.getContinents();
        model.addAttribute("continents", continents);
        return "continent/index";
    }

    @GetMapping(value = "/continents/{id}")
    public String getContinentById(Model model,@PathVariable("id") Long continent_id){
        Continent continent = continentService.getContinentById(continent_id);
        model.addAttribute("continent", continent);
        return "continent/detail";
    }

    @GetMapping(value = "/continents/create")
    public String createCountry(Continent continent){
        return "continent/create";
    }

    @PostMapping(value = "/continents/save")
    public String saveContinent(Continent continent, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "continent/index";

        continentService.createContinent(continent);

        return "redirect:/continents";
    }

    @GetMapping(value = "/continents/update/{id}")
    public String updateContinent(Model model,@PathVariable("id") Long continent_id){
        Continent continent = continentService.getContinentById(continent_id);
        model.addAttribute("continent", continent);
        return "continent/update";
    }

    @PostMapping(value = "/continents/update/{id}")
    public String saveUpdatedContinent(@PathVariable("id") Long continent_id, Continent updatedContinent){
        continentService.updateContinent(continent_id, updatedContinent);
        return "redirect:/continents";
    }

    @GetMapping(value = "/continents/delete/{id}")
    public String deleteContinent(@PathVariable("id") Long continent_id){
        continentService.deleteContinent(continent_id);
        return "redirect:/continents";
    }
}
