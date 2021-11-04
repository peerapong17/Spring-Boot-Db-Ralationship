package com.example.FootballRestApi.controllers;

import com.example.FootballRestApi.models.Club;
import com.example.FootballRestApi.models.Country;
import com.example.FootballRestApi.models.Player;
import com.example.FootballRestApi.models.Position;
import com.example.FootballRestApi.services.ClubService;
import com.example.FootballRestApi.services.CountryService;
import com.example.FootballRestApi.services.PlayerService;
import com.example.FootballRestApi.services.PositionService;
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
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private ClubService clubService;

    @Autowired
    private PositionService positionService;

    @GetMapping(value = "/players")
    public String getPlayers(Model model){
        List<Player> players = playerService.getPlayers();
        model.addAttribute("players", players);
        return "player/index";
    }

    @GetMapping(value = "players/{id}")
    public String getPlayerById(Model model, @PathVariable("id") Long player_id){
        Player player = playerService.getPlayerById(player_id);
        model.addAttribute("player", player);
        return "player/detail";
    }

    @GetMapping(value = "/players/create")
    public String createPlayer(Player player, Model model){
        model.addAttribute("positions", positionService.getPositions());
        model.addAttribute("countries", countryService.getCountries());
        model.addAttribute("clubs", clubService.getClubs());

        return "player/create";
    }

    @PostMapping(value = "/players/save")
    public String savePlayer(Player player, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors())
            return "player/index";

        playerService.createPlayer(player);

        return "redirect:/players";
    }

    @GetMapping(value = "/players/update/{id}")
    public String updatePlayer(Model model,@PathVariable("id") Long player_id){
        model.addAttribute("positions", positionService.getPositions());
        model.addAttribute("countries", countryService.getCountries());
        model.addAttribute("clubs", clubService.getClubs());
        Player player = playerService.getPlayerById(player_id);
        model.addAttribute("player", player);
        return "player/update";
    }

    @PostMapping(value = "/players/update/{id}")
    public String saveUpdatedPlayer(@PathVariable("id") Long club_id, Player updatedPlayer) {
        playerService.updatePlayer(club_id, updatedPlayer);
        return "redirect:/players";
    }

    @GetMapping(value = "/players/delete/{id}")
    public String deletePlayer(@PathVariable("id") Long player_id){
        playerService.deletePlayer(player_id);
        return "redirect:/players";
    }
}
