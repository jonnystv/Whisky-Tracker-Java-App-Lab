package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WhiskyController {

    @Autowired
    WhiskyRepository whiskyRepository;

    @GetMapping(value = "/whiskies")
    public ResponseEntity<List<Whisky>> findWhiskiesFilteredByYear(
            @RequestParam(name = "year", required = false) Integer year) {
        if (year != null) {
            return new ResponseEntity<>(whiskyRepository.findWhiskiesByYear(year), HttpStatus.OK);
        }
        return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/whiskies")
    public ResponseEntity<List<Whisky>> findWhiskiesFilteredByDistillery(
            @RequestParam(name = "whiskyDistillery", required = false) String whiskyDistillery,
            @RequestParam(name = "whiskyAge", required = false) Integer whiskyAge) {

        if (whiskyDistillery != null) {
            return new ResponseEntity<>(whiskyRepository.findWhiskyByDistillery(whiskyDistillery), HttpStatus.OK);
        }
        if (whiskyAge != null) {
            return new ResponseEntity<>(whiskyRepository.findWhiskyByAge(whiskyAge), HttpStatus.OK);
        }
        return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);

    }


}
