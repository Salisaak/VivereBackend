package com.Nostrade.VivereApp.SavedExpectancy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.Nostrade.VivereApp.savedExpectancy.exceptions.ResourceNotFoundException;
import com.Nostrade.VivereApp.savedExpectancy.models.SavedExpectancy;
import com.Nostrade.VivereApp.savedExpectancy.repos.SavedExpectancyRepository;
import com.Nostrade.VivereApp.savedExpectancy.services.SavedExpectancyServiceImpl;

@ExtendWith(MockitoExtension.class)
public class SavedExpectancyServiceImplTest {

    @Mock
    private SavedExpectancyRepository savedExpectancyRepository;

    @InjectMocks
    private SavedExpectancyServiceImpl savedExpectancyService;

    @Test
    void getAllSavedExpectancy() {
        List<SavedExpectancy> savedExpectancies = Arrays.asList(new SavedExpectancy(), new SavedExpectancy());
        when(savedExpectancyRepository.findAll()).thenReturn(savedExpectancies);

        List<SavedExpectancy> result = savedExpectancyService.getAllSavedExpectancy();

        assertEquals(savedExpectancies, result);
    }

    @Test
    void getSavedExpectancyById() {
        SavedExpectancy savedExpectancy = new SavedExpectancy();
        long id = 1L;
        when(savedExpectancyRepository.findById(id)).thenReturn(Optional.of(savedExpectancy));

        SavedExpectancy result = savedExpectancyService.getSavedExpectancyById(id);

        assertEquals(savedExpectancy, result);
    }

    @Test
    void getSavedExpectancyByIdNotFound() {
        long id = 1L;
        when(savedExpectancyRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            savedExpectancyService.getSavedExpectancyById(id);
        });
    }

    @Test
    void createSavedExpectancy() {
        SavedExpectancy savedExpectancy = new SavedExpectancy();
        when(savedExpectancyRepository.save(savedExpectancy)).thenReturn(savedExpectancy);

        SavedExpectancy result = savedExpectancyService.createSavedExpectancy(savedExpectancy);

        assertEquals(savedExpectancy, result);
    }

    @Test
    void updateSavedExpectancy() {
        long id = 1L;
        SavedExpectancy existingExpectancy = new SavedExpectancy();
        existingExpectancy.setId(id);
        SavedExpectancy updatedExpectancy = new SavedExpectancy();
        updatedExpectancy.setId(id);
        when(savedExpectancyRepository.findById(id)).thenReturn(Optional.of(existingExpectancy));
        when(savedExpectancyRepository.save(existingExpectancy)).thenReturn(updatedExpectancy);

        SavedExpectancy result = savedExpectancyService.updateSavedExpectancy(updatedExpectancy);

        assertEquals(updatedExpectancy, result);
    }

    @Test
    void updateSavedExpectancyNotFound() {
        long id = 1L;
        SavedExpectancy updatedExpectancy = new SavedExpectancy();
        updatedExpectancy.setId(id);
        when(savedExpectancyRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            savedExpectancyService.updateSavedExpectancy(updatedExpectancy);
        });
    }

}