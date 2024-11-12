package kea.momo.wishlist.controller;

import jakarta.servlet.http.HttpSession;
import kea.momo.wishlist.model.Profile;
import kea.momo.wishlist.service.ProfileService;
import kea.momo.wishlist.util.ProfileException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("profile") //skal ændres til wishlist
public class ProfileController {

    //***ATTRIBUTES***--------------------------------------------------------------------------------------------------
    private final ProfileService profileService;

    //***CONSTRUCTOR***-------------------------------------------------------------------------------------------------
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    //***METHODS***-----------------------------------------------------------------------------------------------------
//    @GetMapping("/login")
//    public String showLoginPage() {
//        return "login";
//    }

    @GetMapping("/profileDash")
    public String dash() {
        return "userProfile";
    }

    //***CREATE PROFILE***---------------------------------------------------------------------------------------------C
    @GetMapping("/addprofile") // GetMapping henter data fra database
    public String addProfile(Model model) {
        Profile profile = new Profile();
        model.addAttribute("profile", profile);
        return "signup";  // TODO tilføj navn på html page
    }

    //TODO vi skal sikre at profile email... if(profileEmail.equals(existing) og ikke er tom
    @PostMapping("/saveprofile") //PostMapping tilføjer data til database
    public String saveProfile(@ModelAttribute Profile profile){
        profileService.addProfile(profile);
        return "redirect:/wishlist"; // TODO tilføj navn på html page
    }

    //***READ PROFILE***-----------------------------------------------------------------------------------------------R
    //TODO Ved ikke om vi skal bruge den her?
//    @GetMapping("/all")
//    public String getAllProfiles(Model model) {
//        List<Profile> profiles = profileService.getAllProfiles();
//        model.addAttribute("profiles", profiles);
//        return "profiles"; // Specify the correct HTML page for displaying all profiles
//    }
    //***UPDATE PROFILE***---------------------------------------------------------------------------------------------U
    @GetMapping("/{profile}/edit")
    public String editProfile(@PathVariable("profile") int profileId, Model model){
        Profile profile = profileService.getProfileById(profileId);
        model.addAttribute("profile", profile);
        model.addAttribute("profileName", profile.getProfileName());
        model.addAttribute("profileLastName", profile.getProfileLastName());
        model.addAttribute("profileEmail", profile.getProfileEmail());
        model.addAttribute("profilePassword",profile.getProfilePassword());
        return "updateProfile"; //TODO tilføj nav på html page
    }

    @PostMapping("/{profile}/update")
    public String updateProfile(@PathVariable("profile") int profileId, @ModelAttribute Profile profile, Model model) {
        model.addAttribute("profile", profile);
        profileService.updateProfile(profile);
        return "redirect:/attractions"; // TODO tilføj navn på html page
    }

    //***DELETE PROFILE***---------------------------------------------------------------------------------------------D
    @PostMapping("/{profileId}/remove")
    public String removeAttraction(@PathVariable int profileId){
        Profile profile = profileService.getProfileById(profileId);
        profileService.deleteProfile(profile);
        return "redirect:/profiles"; // TODO: Change to the correct HTML page if necessary
    }

    //***EXCEPTION HANDLING***------------------------------------------------------------------------------------------
    @ExceptionHandler(ProfileException.class)
    public String handleError(Model model, Exception exception) {
        model.addAttribute("message",exception.getMessage());
        return "errorPage";
    }

    //***END***---------------------------------------------------------------------------------------------------------
}
