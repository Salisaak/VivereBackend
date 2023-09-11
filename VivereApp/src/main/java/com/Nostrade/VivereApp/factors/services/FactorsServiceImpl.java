package com.Nostrade.VivereApp.factors.services;

import com.Nostrade.VivereApp.factors.exceptions.ResourceNotFoundException;
import com.Nostrade.VivereApp.factors.models.Factors;
import com.Nostrade.VivereApp.factors.repos.FactorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FactorsServiceImpl implements FactorsService {

    @Autowired
    private FactorsRepository factorsRepository;

    @Override
    public List<Factors> getAllFactors() {
        return factorsRepository.findAll();
    }

    @Override
    public Factors getFactorById(long id) {
        return factorsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Factor not found with id " + id));
    }

    @Override
    public Factors createFactor(Factors factor) {
        return factorsRepository.save(factor);
    }

    @Override
    public Factors updateFactor(Factors factor) {
        Factors existingFactor = factorsRepository.findById(factor.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Factor not found with id " + factor.getId()));

        existingFactor.setSmokerCig(factor.isSmokerCig());
        existingFactor.setSmokerWeed(factor.isSmokerWeed());
        existingFactor.setMeth(factor.isMeth());
        existingFactor.setHeroin(factor.isHeroin());
        existingFactor.setAlcoholic(factor.isAlcoholic());
        existingFactor.setActive(factor.isActive());
        existingFactor.setHeart(factor.isHeart());
        existingFactor.setCancer(factor.isCancer());
        existingFactor.setStroke(factor.isStroke());
        existingFactor.setRespiratory(factor.isRespiratory());
        existingFactor.setKidney(factor.isKidney());
        existingFactor.setObese(factor.isObese());
        existingFactor.setHiv(factor.isHiv());
        existingFactor.setTrainDiet(factor.isTrainDiet());
        existingFactor.setHighDiet(factor.isHighDiet());
        existingFactor.setMedDiet(factor.isMedDiet());
        existingFactor.setLowDiet(factor.isLowDiet());

        return factorsRepository.save(existingFactor);
    }

    @Override
    public void deleteFactor(long id) {
        factorsRepository.deleteById(id);
    }

}