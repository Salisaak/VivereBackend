package com.Nostrade.VivereApp.savedExpectancy.controllers;

import com.Nostrade.VivereApp.savedExpectancy.models.SavedExpectancy;
import com.Nostrade.VivereApp.savedExpectancy.services.SavedExpectancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vivere/saved-expectancies")
public class SavedExpectancyController {

    @Autowired
    private SavedExpectancyService savedExpectancyService;

    @GetMapping
    public List<SavedExpectancy> getAllSavedExpectancy() {
        return savedExpectancyService.getAllSavedExpectancy();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SavedExpectancy> getSavedExpectancyById(@PathVariable long id) {
        SavedExpectancy savedExpectancy = savedExpectancyService.getSavedExpectancyById(id);
        return ResponseEntity.ok(savedExpectancy);
    }

    @PostMapping
    public ResponseEntity<SavedExpectancy> createSavedExpectancy(@RequestBody SavedExpectancy savedExpectancy) {
        SavedExpectancy createdExpectancy = savedExpectancyService.createSavedExpectancy(savedExpectancy);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdExpectancy);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SavedExpectancy> updateSavedExpectancy(@PathVariable long id, @RequestBody SavedExpectancy savedExpectancy) {
        SavedExpectancy updatedExpectancy = savedExpectancyService.updateSavedExpectancy(savedExpectancy);
        return ResponseEntity.ok(updatedExpectancy);
    }

}