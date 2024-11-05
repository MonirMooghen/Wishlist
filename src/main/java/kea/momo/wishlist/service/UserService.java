package kea.momo.wishlist.service;

import kea.momo.wishlist.repository.ProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final ProfileRepository profileRepository;

    UserService(ProfileRepository profileRepository){
        this.profileRepository = profileRepository;
    }
}
