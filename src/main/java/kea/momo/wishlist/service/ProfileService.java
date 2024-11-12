package kea.momo.wishlist.service;

import kea.momo.wishlist.model.Profile;
import kea.momo.wishlist.repository.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {

    //***ATTRIBUTES***--------------------------------------------------------------------------------------------------
    private final ProfileRepository profileRepository;

    //***CONSTRUCTOR***-------------------------------------------------------------------------------------------------
    ProfileService(ProfileRepository profileRepository){
        this.profileRepository = profileRepository;
    }

    public boolean login(String profileEmail, String profilePassword) {
        Profile profile = profileRepository.getProfileByEmailAndPassword(profileEmail,profilePassword);
        if (profile != null)
            // user found - check credentials
            return profile.getProfilePassword().equals(profilePassword);
        // user not found
        return false;
    }

    //***METHODS***-----------------------------------------------------------------------------------------------------
    public Profile getProfileByEmailAndPassword(String profileEmail, String profilePassword){
        return profileRepository.getProfileByEmailAndPassword(profileEmail,profilePassword);
    }

    public Profile getProfileById(int profileId){
        return profileRepository.getProfileById(profileId);
    }

    public List<Profile> getAllProfiles(){
        return profileRepository.getAllProfiles();
    }


    public void addProfile(Profile profile){
               profileRepository.addProfile(profile);
    }

    public void updateProfile(Profile profile){
        profileRepository.updateProfile(profile);
    }

    public void deleteProfile(Profile profile){
        profileRepository.deleteProfile(profile);
    }

    //***END***---------------------------------------------------------------------------------------------------------
}
