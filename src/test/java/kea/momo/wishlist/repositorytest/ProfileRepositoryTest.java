package kea.momo.wishlist.repositorytest;

import kea.momo.wishlist.model.Profile;
import kea.momo.wishlist.repository.ProfileRepository;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProfileRepositoryTest {

    //***ATTRIBUTES***--------------------------------------------------------------------------------------------------
    private final ProfileRepository profileRepository = new ProfileRepository();

    //***TEST METHODS***------------------------------------------------------------------------------------------------
    @Test
    void findAttractionById(){
        //arrange
        int expectedId = 1;

        //act
        Profile profile = profileRepository.findProfileById(1);
        int actualId = profile.getProfileId();

        //assert
        assertEquals(actualId, expectedId);
    }

    @Test
    void addAttraction(){
        //arrange
        Profile profile = new Profile("Test", "Testsen", "test@test.com", "testkode", 1);

        //act
        profileRepository.addProfile(profile);
        String actualProfileName = "";
        String expectedProfileName = "Test";

        int actualProfileId = -1; //bruger -1 fordi Id aldrig vil være -1
        int expectedProfileId = 1;

        for(Profile p : profileRepository.getAllProfiles()){
            if(p.getProfileName().equalsIgnoreCase("Test") && p.getProfileId() == 1){
                actualProfileName = p.getProfileName();
                actualProfileId = p.getProfileId();
            }
        }

        //assert
        assertEquals(expectedProfileName, actualProfileName);
        assertEquals(expectedProfileId,actualProfileId);
    }

    //***END***---------------------------------------------------------------------------------------------------------
}
