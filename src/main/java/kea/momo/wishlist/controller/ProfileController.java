package kea.momo.wishlist.controller;

import kea.momo.wishlist.model.Profile;
import kea.momo.wishlist.service.ProfileService;
import kea.momo.wishlist.util.ProfileException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("profile")
public class ProfileController {

    //***ATTRIBUTES***--------------------------------------------------------------------------------------------------
    private final ProfileService profileService;

    //***CONSTRUCTOR***-------------------------------------------------------------------------------------------------
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    //***METHODS***-----------------------------------------------------------------------------------------------------
    //***CREATE PROFILE***---------------------------------------------------------------------------------------------C
    @GetMapping("/addprofile") // GetMapping henter data fra database
    public String addProfile(Model model) {
        Profile profile = new Profile();
        model.addAttribute("profile", profile);
        return "signup";  // TODO tilføj navn på html page
    }

    @PostMapping("/saveprofile") //PostMapping tilføjer data til database
    public String saveProfile(@ModelAttribute Profile profile) throws ProfileException {
        profileService.addProfile(profile);
        return "redirect:/"; // TODO tilføj navn på html page
    }



    //***UPDATE PROFILE***---------------------------------------------------------------------------------------------U
    @GetMapping("/edit/{id}")
    public String editProfile(@PathVariable("id") int profileId, Model model) throws ProfileException {
        Profile profile = profileService.getProfileById(profileId);
        model.addAttribute("profile", profile);
        model.addAttribute("profileName", profile.getProfileName());
        model.addAttribute("profileLastName", profile.getProfileLastName());
        model.addAttribute("profileEmail", profile.getProfileEmail());
        model.addAttribute("profilePassword",profile.getProfilePassword());
        return "editProfile"; //TODO tilføj nav på html page
    }

    @PostMapping("/update/{id}")
    public String updateProfile(@PathVariable("id") int profileId, @ModelAttribute Profile profile) throws ProfileException {

        profile.setProfileId(profileId);
        profileService.updateProfile(profile);
        return "redirect:/profile"; // TODO tilføj navn på html page
    }

    //***DELETE PROFILE***---------------------------------------------------------------------------------------------D
    @PostMapping("/delete/{id}")
    public String deleteProfile(@PathVariable("id") int profileId) throws ProfileException {
        Profile profile = profileService.getProfileById(profileId);
        profileService.deleteProfile(profile);
        return "redirect:/homepage"; // TODO: Change to the correct HTML page if necessary
    }

    //***EXCEPTION HANDLING***------------------------------------------------------------------------------------------
    @ExceptionHandler(ProfileException.class)
    public String handleError(Model model, Exception exception) {
        model.addAttribute("message",exception.getMessage());
        return "errorPage";
    }

    //***END***---------------------------------------------------------------------------------------------------------
}
