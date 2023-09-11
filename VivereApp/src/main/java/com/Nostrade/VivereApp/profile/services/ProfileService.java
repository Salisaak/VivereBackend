package com.Nostrade.VivereApp.profile.services;

import com.Nostrade.VivereApp.profile.models.Profile;

import java.util.List;

public interface ProfileService {

    List<Profile> getAllProfiles();

    Profile getProfileById(long id);

    Profile createProfile(Profile profile);

    Profile updateProfile(Profile profile);

    void deleteProfile(long id);

}