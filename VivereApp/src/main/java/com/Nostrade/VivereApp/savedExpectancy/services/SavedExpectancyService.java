package com.Nostrade.VivereApp.savedExpectancy.services;

import com.Nostrade.VivereApp.savedExpectancy.models.SavedExpectancy;

import java.util.List;

public interface SavedExpectancyService {

    List<SavedExpectancy> getAllSavedExpectancy();

    SavedExpectancy getSavedExpectancyById(long id);

    SavedExpectancy createSavedExpectancy(SavedExpectancy savedExpectancy);

    SavedExpectancy updateSavedExpectancy(SavedExpectancy savedExpectancy);

}