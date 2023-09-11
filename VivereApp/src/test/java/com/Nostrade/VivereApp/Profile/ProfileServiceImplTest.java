package com.Nostrade.VivereApp.Profile;

import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.Nostrade.VivereApp.profile.exceptions.ResourceNotFoundException;
import com.Nostrade.VivereApp.profile.models.Profile;
import com.Nostrade.VivereApp.profile.repos.ProfileRepository;
import com.Nostrade.VivereApp.profile.services.ProfileServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ProfileServiceImplTest {

    @Mock
    private ProfileRepository profileRepository;

    @InjectMocks
    private ProfileServiceImpl profileService;

    @Test
    void getAllProfiles() {
        List<Profile> profiles = Arrays.asList(new Profile(), new Profile());
        when(profileRepository.findAll()).thenReturn(profiles);

        List<Profile> result = profileService.getAllProfiles();

        assertEquals(profiles, result);
    }

    @Test
    void getProfileById() {
        Profile profile = new Profile();
        long id = 1L;
        when(profileRepository.findById(id)).thenReturn(Optional.of(profile));

        Profile result = profileService.getProfileById(id);

        assertEquals(profile, result);
    }

    @Test
    void getProfileByIdNotFound() {
        long id = 1L;
        when(profileRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            profileService.getProfileById(id);
        });
    }

    @Test
    void createProfile() {
        Profile profile = new Profile();
        when(profileRepository.save(profile)).thenReturn(profile);

        Profile result = profileService.createProfile(profile);

        assertEquals(profile, result);
    }

    @Test
    void updateProfile() {
        long id = 1L;
        Profile existingProfile = new Profile();
        existingProfile.setId(id);
        Profile updatedProfile = new Profile();
        updatedProfile.setId(id);
        when(profileRepository.findById(id)).thenReturn(Optional.of(existingProfile));
        when(profileRepository.save(existingProfile)).thenReturn(updatedProfile);

        Profile result = profileService.updateProfile(updatedProfile);

        assertEquals(updatedProfile, result);
    }

    @Test
    void updateProfileNotFound() {
        long id = 1L;
        Profile updatedProfile = new Profile();
        updatedProfile.setId(id);
        when(profileRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            profileService.updateProfile(updatedProfile);
        });
    }

    @Test
    void deleteProfile() {
        long id = 1L;
        profileService.deleteProfile(id);

        // verify delete was called
        verify(profileRepository).deleteById(id);
    }

}