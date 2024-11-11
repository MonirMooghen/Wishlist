package kea.momo.wishlist.controller;

import kea.momo.wishlist.model.Profile;
import kea.momo.wishlist.service.ProfileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
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
    @GetMapping("/add") // GetMapping henter data fra database
    public String addProfile(Model model) {
        Profile profile = new Profile();
        model.addAttribute("profile", profile);
        return "";  // TODO tilføj navn på html page
    }

    @PostMapping("/save") //PostMapping tilføjer data til database
    public String saveProfile(@ModelAttribute Profile profile){
        profileService.addProfile(profile);
        return "redirect:/attractions"; // TODO tilføj navn på html page
    }

    //***READ PROFILE***-----------------------------------------------------------------------------------------------R
    //Ved ikke om vi skal bruge den her?
    @GetMapping("")
    public String getAllProfiles(Model model) {
        List<Profile> profiles = profileService.getAllProfiles();
        model.addAttribute("profiles", profiles);
        return ""; // TODO tilføj navn på html page
    }

    //***UPDATE PROFILE***---------------------------------------------------------------------------------------------U
    @GetMapping("/{profile}/edit")
    public String editProfile(@PathVariable("profile") int profileId, Model model){
        Profile profile = profileService.findProfileById(profileId);
        model.addAttribute("profile", profile);
        model.addAttribute("profileName", profile.getProfileName());
        model.addAttribute("profileLastName", profile.getProfileLastName());
        model.addAttribute("profileEmail", profile.getProfileEmail());
        model.addAttribute("profilePassword",profile.getProfilePassword());
        return "updateProfile";
    }

    @PostMapping("/{profile}/update")
    public String updateProfile(@PathVariable("name") String name, @ModelAttribute Profile profile, Model model) {
        model.addAttribute("profile", profile);
        profileService.updateProfile(profile);
        return "redirect:/attractions"; // TODO tilføj navn på html page
    }

    //***DELETE PROFILE***---------------------------------------------------------------------------------------------D
    @PostMapping("/{name}/remove")
    public String removeAttraction(@PathVariable int profileId){
        Profile profile = profileService.findProfileById(profileId);
        profileService.deleteProfile(profile);
        return "redirect:/attractions"; // tilføj navn på htmlpage
    }

    //***END***---------------------------------------------------------------------------------------------------------
}
