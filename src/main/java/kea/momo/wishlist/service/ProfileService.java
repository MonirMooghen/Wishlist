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

    //***METHODS***-----------------------------------------------------------------------------------------------------
    public List<Profile> getAllProfiles(){
        return profileRepository.getAllProfiles();
    }

    public Profile findProfileById(int id){
        return profileRepository.findProfileById(id);
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
