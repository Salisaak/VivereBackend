package com.Nostrade.VivereApp.Factors;

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

import com.Nostrade.VivereApp.factors.exceptions.ResourceNotFoundException;
import com.Nostrade.VivereApp.factors.models.Factors;
import com.Nostrade.VivereApp.factors.repos.FactorsRepository;
import com.Nostrade.VivereApp.factors.services.FactorsServiceImpl;

@ExtendWith(MockitoExtension.class)
public class FactorsServiceImplTest {

    @Mock
    private FactorsRepository factorsRepository;

    @InjectMocks
    private FactorsServiceImpl factorsService;

    @Test
    void getAllFactors() {
        List<Factors> factorsList = Arrays.asList(new Factors(), new Factors());
        when(factorsRepository.findAll()).thenReturn(factorsList);

        List<Factors> result = factorsService.getAllFactors();

        assertEquals(factorsList, result);
    }

    @Test
    void getFactorById() {
        Factors factor = new Factors();
        long id = 1L;
        when(factorsRepository.findById(id)).thenReturn(Optional.of(factor));

        Factors result = factorsService.getFactorById(id);

        assertEquals(factor, result);
    }

    @Test
    void getFactorByIdNotFound() {
        long id = 1L;
        when(factorsRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            factorsService.getFactorById(id);
        });
    }

    @Test
    void createFactor() {
        Factors factor = new Factors();
        when(factorsRepository.save(factor)).thenReturn(factor);

        Factors result = factorsService.createFactor(factor);

        assertEquals(factor, result);
    }

    @Test
    void updateFactor() {
        long id = 1L;
        Factors existingFactor = new Factors();
        existingFactor.setId(id);
        Factors updatedFactor = new Factors();
        updatedFactor.setId(id);
        when(factorsRepository.findById(id)).thenReturn(Optional.of(existingFactor));
        when(factorsRepository.save(existingFactor)).thenReturn(updatedFactor);

        Factors result = factorsService.updateFactor(updatedFactor);

        assertEquals(updatedFactor, result);
    }

    @Test
    void updateFactorNotFound() {
        long id = 1L;
        Factors updatedFactor = new Factors();
        updatedFactor.setId(id);
        when(factorsRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            factorsService.updateFactor(updatedFactor);
        });
    }

    @Test
    void deleteFactor() {
        long id = 1L;
        factorsService.deleteFactor(id);

        verify(factorsRepository).deleteById(id);
    }

}