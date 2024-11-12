package kea.momo.wishlist.controller;

import jakarta.servlet.http.HttpSession;
import kea.momo.wishlist.model.Profile;
import kea.momo.wishlist.service.ProfileService;
import kea.momo.wishlist.util.ProfileException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    //***ATTRIBUTES***--------------------------------------------------------------------------------------------------
    private ProfileService profileService;

    //***LOGIN METHODS***-----------------------------------------------------------------------------------------------
    private boolean isLoggedIn(HttpSession session) {
        return session.getAttribute("profile") != null;
    }

//    @GetMapping("/profile")
//    public String index() {
//        // return landing page
//        return "index"; // TODO change
//    }
//
//    @GetMapping("profile#loginModal")
//    public String showLogin() {
//        // return login form
//        return "homepage"; // TODO rename så det passer til html page
//    }

    @PostMapping("homepage#loginModal")
    public String login(@RequestParam("profileEmail") String profileEmail, @RequestParam("profilePassword")
                        String profilePassword, HttpSession session, Model model) throws ProfileException {
        Profile profileToCheck = new Profile();
        profileToCheck = profileService.getProfileByEmailAndPassword(profileEmail, profilePassword);

        if (profileToCheck!=null) {
            // create session for user and set session timeout to 30 sec (container default: 15 min)
            session.setAttribute("profile", new Profile
                    (profileToCheck.getProfileName(), profileToCheck.getProfileLastName(),profileToCheck.getProfileEmail(),profileToCheck.getProfilePassword())); // hvorfor nyt object her?
            session.setMaxInactiveInterval(30);
            // redirect to starting page - admin1
            return "redirect:/homepage"; // TODO rename så det passer til html page user dashboard
        }
        // wrong credentials
        model.addAttribute("wrongCredentials", true);
        return "signup"; // TODO rename så det passer til html page
    }

    @GetMapping("/profile/edit/{id}")
    public String showAdm1(HttpSession session) {
        return isLoggedIn(session) ? "admin1" : "login";
    }

    @GetMapping("admin2")
    public String showAdm2(HttpSession session) {
        return isLoggedIn(session) ? "admin2" : "login";
    }

    @GetMapping("logout")
    public String logout(HttpSession session) {
        // invalidate session and return landing page
        session.invalidate();
        return "index"; // TODO rename så det passer til html page
    }

    //***END***---------------------------------------------------------------------------------------------------------
}
