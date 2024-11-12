package kea.momo.wishlist.service;

import kea.momo.wishlist.model.Profile;
import kea.momo.wishlist.repository.ProfileRepository;
import kea.momo.wishlist.util.ProfileException;
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

    public boolean login(String profileEmail, String profilePassword) throws ProfileException {
        Profile profile = profileRepository.getProfileByEmailAndPassword(profileEmail,profilePassword);
        if (profile != null)
            // user found - check credentials
            return profile.getProfilePassword().equals(profilePassword);
        // user not found
        return false;
    }

    //***METHODS***-----------------------------------------------------------------------------------------------------
    public Profile getProfileByEmailAndPassword(String profileEmail, String profilePassword) throws ProfileException {
        return profileRepository.getProfileByEmailAndPassword(profileEmail,profilePassword);
    }

    public Profile getProfileById(int profileId) throws ProfileException {
        return profileRepository.getProfileById(profileId);
    }

    public List<Profile> getAllProfiles() throws ProfileException {
        return profileRepository.getAllProfiles();
    }

    public void addProfile(Profile profile) throws ProfileException {
               profileRepository.addProfile(profile);
    }

    public void updateProfile(Profile profile) throws ProfileException {
        profileRepository.updateProfile(profile);
    }

    public void deleteProfile(Profile profile) throws ProfileException {
        profileRepository.deleteProfile(profile);
    }

    //***END***---------------------------------------------------------------------------------------------------------
}
