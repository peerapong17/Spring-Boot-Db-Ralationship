package com.example.FootballRestApi.controllers;

import com.example.FootballRestApi.models.Club;
import com.example.FootballRestApi.models.Continent;
import com.example.FootballRestApi.models.Country;
import com.example.FootballRestApi.services.ClubService;
import com.example.FootballRestApi.services.ContinentService;
import com.example.FootballRestApi.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ClubController {

    @Autowired
    private ClubService clubService;

    @Autowired
    private ContinentService continentService;

    @Autowired
    private CountryService countryService;

    @GetMapping(value = "/clubs")
    public String getClubs(Model model){
        List<Club> clubs = clubService.getClubs();
        model.addAttribute("clubs", clubs);
        return "club/index";
    }

    @GetMapping(value = "/clubs/{id}")
    public String getClubById(Model model, @PathVariable("id") Long club_id){
        Club club = clubService.getClubById(club_id);
        model.addAttribute("club", club);
        return "club/detail";
    }

    @GetMapping(value = "/clubs/create")
    public String createClub(Club club,Model model){
        model.addAttribute("continents", continentService.getContinents());
        model.addAttribute("countries", countryService.getCountries());
        return "club/create";
    }

    @PostMapping(value = "/clubs/save")
    public String saveClub(Club club, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors())
            return "club/index";

        clubService.createClub(club);

        return "redirect:/clubs";
    }

    @GetMapping(value = "/clubs/update/{id}")
    public String updateContinent(Model model,@PathVariable("id") Long continent_id){
        model.addAttribute("continents", continentService.getContinents());
        model.addAttribute("countries", countryService.getCountries());
        Club club = clubService.getClubById(continent_id);
        model.addAttribute("club", club);
        return "club/update";
    }

    @PostMapping(value = "/clubs/update/{id}")
    public String saveUpdatedContinent(@PathVariable("id") Long club_id, Club updatedClub){
        clubService.updateClub(club_id, updatedClub);
        return "redirect:/clubs";
    }

    @GetMapping(value = "/clubs/delete/{id}")
    public String deleteClub(@PathVariable("id") Long club_id){
        clubService.deleteClub(club_id);
        return "redirect:/clubs";
    }
}
