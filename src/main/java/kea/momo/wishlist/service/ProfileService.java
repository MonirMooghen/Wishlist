package kea.momo.wishlist.service;

import kea.momo.wishlist.model.Profile;
import kea.momo.wishlist.repository.ProfileRepository;
import kea.momo.wishlist.util.ProfileException;
import kea.momo.wishlist.util.ProfileRSInterface;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService implements ProfileRSInterface {

    //***ATTRIBUTES***--------------------------------------------------------------------------------------------------
    private final ProfileRepository profileRepository;

    //***CONSTRUCTOR***-------------------------------------------------------------------------------------------------
    ProfileService(ProfileRepository profileRepository){
        this.profileRepository = profileRepository;
    }

    //***METHODS***-----------------------------------------------------------------------------------------------------
    public boolean login(String profileEmail, String profilePassword) throws ProfileException {
        Profile profile = profileRepository.getProfileByEmailAndPassword(profileEmail,profilePassword);
        if (profile != null)
            // user found - check credentials
            return profile.getProfilePassword().equals(profilePassword);
        // user not found
        return false;
    }

    //***CREATE PROFILE***---------------------------------------------------------------------------------------------C
    @Override
    public void addProfile(Profile profile) throws ProfileException {
        profileRepository.addProfile(profile);
    }

    //***READ PROFILE***-----------------------------------------------------------------------------------------------R
    @Override
    public Profile getProfileByEmailAndPassword(String profileEmail, String profilePassword) throws ProfileException {
        return profileRepository.getProfileByEmailAndPassword(profileEmail,profilePassword);
    }

    @Override
    public Profile getProfileById(int profileId) throws ProfileException {
        return profileRepository.getProfileById(profileId);
    }

    @Override
    public List<Profile> getAllProfiles() throws ProfileException {
        return profileRepository.getAllProfiles();
    }

    //***UPDATE PROFILE***---------------------------------------------------------------------------------------------U
    @Override
    public void updateProfile(Profile profile) throws ProfileException {
        profileRepository.updateProfile(profile);
    }

    //***DELETE PROFILE***---------------------------------------------------------------------------------------------D
    @Override
    public void deleteProfile(Profile profile) throws ProfileException {
        profileRepository.deleteProfile(profile);
    }

    //***END***---------------------------------------------------------------------------------------------------------
}
