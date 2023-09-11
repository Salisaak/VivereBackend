package com.Nostrade.VivereApp.savedExpectancy.services;

import com.Nostrade.VivereApp.savedExpectancy.exceptions.ResourceNotFoundException;
import com.Nostrade.VivereApp.savedExpectancy.models.SavedExpectancy;
import com.Nostrade.VivereApp.savedExpectancy.repos.SavedExpectancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SavedExpectancyServiceImpl implements SavedExpectancyService {

    @Autowired
    private SavedExpectancyRepository savedExpectancyRepository;

    @Override
    public List<SavedExpectancy> getAllSavedExpectancy() {
        return savedExpectancyRepository.findAll();
    }

    @Override
    public SavedExpectancy getSavedExpectancyById(long id) {
        return savedExpectancyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Saved expectancy not found with id " + id));
    }

    @Override
    public SavedExpectancy createSavedExpectancy(SavedExpectancy savedExpectancy) {
        return savedExpectancyRepository.save(savedExpectancy);
    }

    @Override
    public SavedExpectancy updateSavedExpectancy(SavedExpectancy savedExpectancy) {
        SavedExpectancy existingExpectancy = savedExpectancyRepository.findById(savedExpectancy.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Saved expectancy not found with id " + savedExpectancy.getId()));

        existingExpectancy.setGender(savedExpectancy.getGender());
        existingExpectancy.setRace(savedExpectancy.getRace());
        existingExpectancy.setHispanic(savedExpectancy.isHispanic());
        existingExpectancy.setBirthday(savedExpectancy.getBirthday());
        existingExpectancy.setValue(savedExpectancy.getValue());

        return savedExpectancyRepository.save(existingExpectancy);
    }

}