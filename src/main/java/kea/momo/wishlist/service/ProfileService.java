package kea.momo.wishlist.service;

import kea.momo.wishlist.repository.ProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;

    ProfileService(ProfileRepository profileRepository){
        this.profileRepository = profileRepository;
    }
}
