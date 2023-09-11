package com.Nostrade.VivereApp.factors.services;

import com.Nostrade.VivereApp.factors.models.Factors;

import java.util.List;

public interface FactorsService {

    List<Factors> getAllFactors();

    Factors getFactorById(long id);

    Factors createFactor(Factors factor);

    Factors updateFactor(Factors factor);

    void deleteFactor(long id);

}