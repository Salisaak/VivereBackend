package com.Nostrade.VivereApp.savedExpectancy.repos;

import com.Nostrade.VivereApp.savedExpectancy.models.SavedExpectancy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavedExpectancyRepository extends JpaRepository<SavedExpectancy, Long> {

}