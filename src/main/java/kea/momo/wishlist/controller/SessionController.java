package kea.momo.wishlist.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kea.momo.wishlist.model.Profile;
import kea.momo.wishlist.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("wishlist")
public class SessionController {

    //***ATTRIBUTES***--------------------------------------------------------------------------------------------------
    private ProfileService profileService;

    //***LOGIN METHODS***-----------------------------------------------------------------------------------------------
    private boolean isLoogedIn(HttpSession session) {
        return session.getAttribute("profile") != null;
    }

    @GetMapping("/")
    public String index() {
        // return landing page
        return "index";
    }

    @GetMapping("login")
    public String showLogin() {
        // return login form
        return "login";
    }

    @PostMapping("login")
    public String login(@RequestParam("profileId") String profileEmail, @RequestParam("profilePassword") String profilePassword,
                        HttpSession session,
                        Model model) {

        if (profileService.login(profileEmail, profilePassword)) {
            // create session for user and set session timeout to 30 sec (container default: 15 min)
            session.setAttribute("user", new Profile(profileEmail, profilePassword));
            session.setMaxInactiveInterval(30);
            // redirect to starting page - admin1
            return "redirect:/admin1";
        }
        // wrong credentials
        model.addAttribute("wrongCredentials", true);
        return "login";
    }


    @GetMapping("admin1")
    public String showAdm1(HttpSession session) {
        return isLoogedIn(session) ? "admin1" : "login";
    }

    @GetMapping("admin2")
    public String showAdm2(HttpSession session) {
        return isLoogedIn(session) ? "admin2" : "login";
    }

    @GetMapping("logout")
    public String logout(HttpSession session) {
        // invalidate session and return landing page
        session.invalidate();
        return "index";
    }

    //***END***---------------------------------------------------------------------------------------------------------
}
