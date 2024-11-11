package kea.momo.wishlist.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kea.momo.wishlist.model.Profile;
import kea.momo.wishlist.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class SessionController {

    @Autowired
    ProfileService profileService;

    //***METHODS***-----------------------------------------------------------------------------------------------------
    //***LOGIN METHODS***-----------------------------------------------------------------------------------------------
    @GetMapping("/login")
    public String login(){
        return "wishlist/login"
    }

    @PostMapping("/validate_login")
    public String validateLogin(HttpSession session, @RequestParam String profileEmail, @RequestParam String profilePassword, Model model){
        Profile profile = profileService.getProfile(profileEmail,profilePassword);
        model.addAttribute("profile", profile);

        if(profile != null){
        return "redirect:/logged_in";
        } else {
            return "wishlist/login";
        }
    }

    @GetMapping("/logged_in")
    public String loggedIn(HttpSession session, Model model){
    Profile profile = (Profile)session.getAttribute("profile");
    model.addAttribute("profile", profile);
    return "wishlist/logged_in";
    }

    @GetMapping("/logout")
    public String logOut(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

    //***SESSION METHODS***---------------------------------------------------------------------------------------------
    @GetMapping("/set_session_id")
    public String setSessionId(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("sessionId", session.getId());
        return "redirect:/show_session_id";
    }

    @GetMapping("/show_session_id")
    public String showSessionId(Model model, HttpServletRequest request){
        HttpSession session = request.getSession(false); // opretter ikke en ny session hvis der er en eksisterende (derfor false)
        if(session != null)
            model.addAttribute("sessionId", (String) session.getAttribute("sessionId"));
        else
            model.addAttribute("sessionId", ("Session Id not found"));

        return "home/show_session_id";
    }

    //***END***---------------------------------------------------------------------------------------------------------
}
