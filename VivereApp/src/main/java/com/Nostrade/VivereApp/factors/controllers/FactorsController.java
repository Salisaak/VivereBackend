package com.Nostrade.VivereApp.factors.controllers;

import com.Nostrade.VivereApp.factors.models.Factors;
import com.Nostrade.VivereApp.factors.services.FactorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vivere/factors")
public class FactorsController {

    @Autowired
    private FactorsService factorsService;

    @GetMapping
    public List<Factors> getAllFactors() {
        return factorsService.getAllFactors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Factors> getFactorById(@PathVariable long id) {
        Factors factor = factorsService.getFactorById(id);
        return ResponseEntity.ok(factor);
    }

    @PostMapping
    public ResponseEntity<Factors> createFactor(@RequestBody Factors factor) {
        Factors createdFactor = factorsService.createFactor(factor);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFactor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Factors> updateFactor(@PathVariable long id, @RequestBody Factors factor) {
        Factors updatedFactor = factorsService.updateFactor(factor);
        return ResponseEntity.ok(updatedFactor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFactor(@PathVariable long id) {
        factorsService.deleteFactor(id);
        return ResponseEntity.ok("Factor deleted successfully");
    }

}