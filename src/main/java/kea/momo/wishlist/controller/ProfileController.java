package kea.momo.wishlist.controller;

import kea.momo.wishlist.service.ProfileService;
import org.springframework.stereotype.Controller;

@Controller
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

}
