package com.Nostrade.VivereApp.Profile;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.Nostrade.VivereApp.profile.controllers.ProfileController;
import com.Nostrade.VivereApp.profile.models.Profile;
import com.Nostrade.VivereApp.profile.services.ProfileService;

@WebMvcTest(ProfileController.class)
public class ProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProfileService profileService;

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void getAllProfiles() throws Exception {
        // Arrange
        List<Profile> profiles = Arrays.asList(new Profile(), new Profile());
        when(profileService.getAllProfiles()).thenReturn(profiles);

        // Act and Assert
        mockMvc.perform(get("/vivere/profiles"))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(profiles)));
    }

    @Test
    void getProfileById() throws Exception {
        // Arrange
        Profile profile = new Profile();
        profile.setId(1L);
        when(profileService.getProfileById(1L)).thenReturn(profile);

        // Act and Assert
        mockMvc.perform(get("/vivere/profiles/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(profile)));
    }

    @Test
    void createProfile() throws Exception {
        // Arrange
        Profile newProfile = new Profile();
        Profile createdProfile = new Profile();
        createdProfile.setId(1L);
        when(profileService.createProfile(newProfile)).thenReturn(createdProfile);

        // Act and Assert
        mockMvc.perform(post("/vivere/profiles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(newProfile)))
                .andExpect(status().isCreated())
                .andExpect(content().json(asJsonString(createdProfile)));
    }

    @Test
    void updateProfile() throws Exception {
        // Arrange
        Profile updatedProfile = new Profile();
        updatedProfile.setId(1L);
        when(profileService.updateProfile(updatedProfile)).thenReturn(updatedProfile);

        // Act and Assert
        mockMvc.perform(put("/vivere/profiles/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updatedProfile)))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(updatedProfile)));
    }

    @Test
    void deleteProfile() throws Exception {
        // Act and Assert
        mockMvc.perform(delete("/vivere/profiles/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Profile deleted successfully"));
    }

}