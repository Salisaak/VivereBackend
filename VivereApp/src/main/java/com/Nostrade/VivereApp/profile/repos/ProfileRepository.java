package com.Nostrade.VivereApp.profile.repos;

import com.Nostrade.VivereApp.profile.models.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends CrudRepository<Profile, Long> {


}
