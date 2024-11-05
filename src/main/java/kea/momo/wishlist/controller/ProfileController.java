package kea.momo.wishlist.controller;

import kea.momo.wishlist.model.Profile;
import kea.momo.wishlist.service.ProfileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("HomePage")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    // todo this method is just to test the HTML PAGE, must be deleted.
    @GetMapping("")
    public String profile(Model model) {
        //List<Profile> profileLists = profileService.
        return "homePage";
    }



}
