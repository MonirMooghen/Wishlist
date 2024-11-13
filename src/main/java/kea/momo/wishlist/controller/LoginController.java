//package kea.momo.wishlist.controller;
//
//import jakarta.servlet.http.HttpSession;
//import kea.momo.wishlist.model.Profile;
//import kea.momo.wishlist.service.ProfileService;
//import kea.momo.wishlist.util.ProfileException;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@Controller
//@RequestMapping("login")
//public class LoginController {
//
//    //***ATTRIBUTES***--------------------------------------------------------------------------------------------------
//    private ProfileService profileService;
//    public LoginController(ProfileService profileService) {
//        this.profileService = profileService;
//    }
//
//    //***LOGIN METHODS***-----------------------------------------------------------------------------------------------
//    private boolean isLoggedIn(HttpSession session) {
//        return session.getAttribute("profile") != null;
//    }
//
//
//    @GetMapping("")
//    public String showLogin() {
//        // return login form
//        return "login"; // TODO rename så det passer til html page
//    }
//
//    @PostMapping("")
//    public String login(@RequestParam("profileEmail") String profileEmail, @RequestParam("profilePassword")
//                        String profilePassword, HttpSession session, Model model) throws ProfileException {
//
//
//        if (profileService.login(profileEmail, profilePassword)) {
//                   Profile profileToCheck = profileService.getProfileByEmailAndPassword(profileEmail, profilePassword);
//            // create session for user and set session timeout to 30 sec (container default: 15 min)
//            session.setAttribute("profile", profileToCheck);
//            session.setMaxInactiveInterval(30);
//
//            return "redirect:/login/userProfile"; // TODO rename så det passer til html page user dashboard
//        }
//        // wrong credentials
//        model.addAttribute("wrongCredentials", true);
//        return "login"; // TODO rename så det passer til html page
//    }
//
//
//    @GetMapping("/userProfile")
//    public String showProfileDashboard(HttpSession session, Model model) {
//        Profile profile = (Profile) session.getAttribute("profile");
//        if (profile == null) {
//            // Redirect to login page if the profile is not in session (i.e., not logged in)
//            return "redirect:/login";
//        }
//        model.addAttribute("profile", profile);
//        return "userProfile"; // Name of your Thymeleaf template for the profile dashboard
//    }
//
//
//    @GetMapping("/logout")
//    public String logout(HttpSession session) {
//        // Invalidate the session to log out the user
//        session.invalidate();
//        // Redirect to the homepage or login page
//        return "redirect:/homepage"; // Adjust redirect as needed
//    }
//
//    //***END***---------------------------------------------------------------------------------------------------------
//}
