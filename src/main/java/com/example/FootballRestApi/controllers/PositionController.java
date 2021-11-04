package com.example.FootballRestApi.controllers;


import com.example.FootballRestApi.models.Country;
import com.example.FootballRestApi.models.Position;
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
public class PositionController {

    @Autowired
    private PositionService positionService;

    @GetMapping(value = "/positions")
    public String getPositions(Model model){
        List<Position> positions = positionService.getPositions();
        model.addAttribute("positions", positions);
        return "position/index";
    }

    @GetMapping(value = "/positions/{id}")
    public String getPositionById(Model models, @PathVariable("id") Long position_id){
        Position position = positionService.getPositionById(position_id);
        models.addAttribute("position", position);
        return "position/detail";
    }

    @GetMapping(value = "/positions/create")
    public String createPosition(Position position){
        return "position/create";
    }

    @PostMapping(value = "/positions/save")
    public String savePosition(Position position, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "position/index";

        positionService.createPosition(position);

        return "redirect:/positions";
    }

    @GetMapping(value = "/positions/update/{id}")
    public String updatePosition(Model model,@PathVariable("id") Long position_id){
        Position position = positionService.getPositionById(position_id);
        model.addAttribute("position", position);
        return "position/update";
    }

    @PostMapping(value = "/positions/update/{id}")
    public String saveUpdatedPosition(@PathVariable("id") Long position_id, Position updatedPosition){
        positionService.updatePosition(position_id, updatedPosition);
        return "redirect:/positions";
    }

    @GetMapping(value = "/positions/delete/{id}")
    public String deletePosition(@PathVariable("id") Long position_id){
        positionService.deletePosition(position_id);
        return "redirect:/positions";
    }
}
