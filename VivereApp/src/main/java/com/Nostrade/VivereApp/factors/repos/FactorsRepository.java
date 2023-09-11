package com.Nostrade.VivereApp.factors.repos;

import com.Nostrade.VivereApp.factors.models.Factors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactorsRepository extends JpaRepository<Factors, Long> {


}