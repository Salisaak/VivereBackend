package com.Nostrade.VivereApp.profile.services;

import com.Nostrade.VivereApp.profile.exceptions.ResourceNotFoundException;
import com.Nostrade.VivereApp.profile.models.Profile;
import com.Nostrade.VivereApp.profile.repos.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public List<Profile> getAllProfiles() {
        return (List<Profile>) profileRepository.findAll();
    }

    @Override
    public Profile getProfileById(long id) {
        return profileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found with id " + id));
    }

    @Override
    public Profile createProfile(Profile profile) {
        return profileRepository.save(profile);
    }

    @Override
    public Profile updateProfile(Profile profile) {
        Profile existingProfile = profileRepository.findById(profile.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found with id " + profile.getId()));

        existingProfile.setName(profile.getName());
        existingProfile.setGender(profile.getGender());
        existingProfile.setRace(profile.getRace());
        existingProfile.setHispanic(profile.isHispanic());
        existingProfile.setHeight(profile.getHeight());
        existingProfile.setWeight(profile.getWeight());
        existingProfile.setBirthday(profile.getBirthday());

        return profileRepository.save(existingProfile);
    }

    @Override
    public void deleteProfile(long id) {
        profileRepository.deleteById(id);
    }

}