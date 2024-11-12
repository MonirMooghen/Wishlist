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

@Controller("wishlist")
public class LoginController {

    //***ATTRIBUTES***--------------------------------------------------------------------------------------------------
    private ProfileService profileService;

    //***LOGIN METHODS***-----------------------------------------------------------------------------------------------
    private boolean isLoggedIn(HttpSession session) {
        return session.getAttribute("profile") != null;
    }

    @GetMapping("/")
    public String index() {
        // return landing page
        return "index"; // TODO change
    }

    @GetMapping("login")
    public String showLogin() {
        // return login form
        return "login"; // TODO rename så det passer til html page
    }

    @PostMapping("login")
    public String login(@RequestParam("profileEmail") String profileEmail, @RequestParam("profilePassword")
                        String profilePassword, HttpSession session, Model model) throws ProfileException {

        if (profileService.login(profileEmail, profilePassword)) {
            // create session for user and set session timeout to 30 sec (container default: 15 min)
            session.setAttribute("profile", new Profile(profileEmail, profilePassword)); // hvorfor nyt object her?
            session.setMaxInactiveInterval(30);
            // redirect to starting page - admin1
            return "redirect:/admin1"; // TODO rename så det passer til html page
        }
        // wrong credentials
        model.addAttribute("wrongCredentials", true);
        return "login"; // TODO rename så det passer til html page
    }

    @GetMapping("admin1")
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
