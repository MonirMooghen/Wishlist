package kea.momo.wishlist.controller;

import jakarta.servlet.http.HttpSession;
import kea.momo.wishlist.model.Profile;
import kea.momo.wishlist.model.Wish;
import kea.momo.wishlist.model.Wishlist;
import kea.momo.wishlist.service.ProfileService;
import kea.momo.wishlist.service.WishlistService;
import kea.momo.wishlist.util.ProfileException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("homepage")
public class WishlistController {

    //***ATTRIBUTES***--------------------------------------------------------------------------------------------------
    private final WishlistService wishlistService;
    private final ProfileService profileService;
    private HttpSession session;
    private Model model;

    //***CONSTRUCTOR***-------------------------------------------------------------------------------------------------
    public WishlistController(WishlistService wishlistService, ProfileService profileService) {
        this.wishlistService = wishlistService;
        this.profileService = profileService;
    }


    //***LOGIN METHODS***-----------------------------------------------------------------------------------------------
    private boolean isLoggedIn(HttpSession session) {
        return session.getAttribute("profile") != null;
    }


    @GetMapping("/login")
    public String showLogin() {
        // return login form
        return "login"; // TODO rename så det passer til html page
    }

    @PostMapping("/login")
    public String login(@RequestParam("profileEmail") String profileEmail, @RequestParam("profilePassword")
    String profilePassword, HttpSession session, Model model) throws ProfileException {


        if (profileService.login(profileEmail, profilePassword)) {
            Profile profileToCheck = profileService.getProfileByEmailAndPassword(profileEmail, profilePassword);
            // create session for user and set session timeout to 30 sec (container default: 15 min)
            session.setAttribute("profile", profileToCheck);
            session.setMaxInactiveInterval(30);

            return "redirect:/homepage/userProfile"; // TODO rename så det passer til html page user dashboard
        }
        // wrong credentials
        model.addAttribute("wrongCredentials", true);
        return "login"; // TODO rename så det passer til html page
    }


    @GetMapping("/userProfile")
    public String showProfileDashboard(HttpSession session, Model model) {
        Profile profile = (Profile) session.getAttribute("profile");
        if (profile == null) {
            // Redirect to login page if the profile is not in session (i.e., not logged in)
            return "redirect:/login";
        }
        model.addAttribute("profile", profile);
        return "userProfile"; // Name of your Thymeleaf template for the profile dashboard
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // Invalidate the session to log out the user
        session.invalidate();
        // Redirect to the homepage or login page
        return "redirect:/homepage"; // Adjust redirect as needed
    }

    //***WISHLIST***----------------------------------------------------------------------------------------------------
    @GetMapping("")
    public String homepage() {
        return "homepage";
    }

    //***CREATE WISHLIST***--------------------------------------------------------------------------------------------C
    @GetMapping("/addwishlist")
    public String addWishlist(Model model) {
        Wishlist wishlist = new Wishlist();
        model.addAttribute("wishlist", wishlist);
        model.addAttribute("wishlistId", wishlist.getWishlistId());
        model.addAttribute("wishName", wishlist.getWishlist()); //ændre attributename til wishlistName
        return "homepage";
    }


@PostMapping("/savewishlist")
public String addWishList(@ModelAttribute Wishlist wishList, HttpSession session, Model model) {

    // Retrieve the Profile object from the session
    Profile profile = (Profile) session.getAttribute("profile");

    // Check if the profile is available in the session
    if (profile != null) {
        // Set the profileId in the wishList object
        wishList.setProfileId(profile.getProfileId());

        // Save the wish list through the service
        wishlistService.addWishlist(wishList);

        // Redirect to the user profile or dashboard page after saving
        return "redirect:/profile";
    } else {
        // If the profile is not in the session, redirect to login or show an error
        model.addAttribute("error", "Please log in to create a wish list.");
        return "redirect:/login";
    }
}


    //***READ WISHLIST***----------------------------------------------------------------------------------------------R
    @GetMapping("/wishlists")
    public String getAllWishLists(Model model) {
        List<Wishlist> wishLists = wishlistService.getAllWishLists();
        model.addAttribute("wishLists", wishLists);
        return "userProfile";
    }

    //***UPDATE WISHLIST***--------------------------------------------------------------------------------------------U
    @GetMapping("/{wishlist}/edit")
    public String editWishlist(@PathVariable("wishlist") int wishlistId, Model model){
        Wishlist wishlist = wishlistService.findWishlistById(wishlistId);
        model.addAttribute("wishlist", wishlist);
        model.addAttribute("wishlistName", wishlist.getWishlistName());
        return "homepage";
    }

    @PostMapping("/{wishlist}/update")
    public String updateWishlist(@PathVariable("name") String name, @ModelAttribute Wishlist wishlist, Model model) {
        model.addAttribute("wishlist", wishlist);
        model.addAttribute("wishlistId", wishlist.getWishlistId());
        model.addAttribute("wishName", wishlist.getWishlist());
        wishlistService.updateWishlist(wishlist);
        return "redirect:/wishlist";
    }


    //***DELETE WISHLIST***--------------------------------------------------------------------------------------------D
    @PostMapping("/{wishlist}/remove")
    public String removeWishlist(@PathVariable int id){
        Wishlist wishlist = wishlistService.findWishlistById(id);
        wishlistService.deleteWishlist(wishlist);
        return "redirect:/wishlists";
    }

    //***WISHES***------------------------------------------------------------------------------------------------------
    //***CREATE WISH***------------------------------------------------------------------------------------------------C
    @GetMapping("/addwish")
    public String addWish(Model model) {
        Wish wish = new Wish();
        model.addAttribute("wish", wish);
        model.addAttribute("wishId", wish.getWishId());
        model.addAttribute("wishName", wish.getWishName());
        model.addAttribute("wishDescription", wish.getWishDescription());
        model.addAttribute("wishPrice", wish.getWishPrice());
        model.addAttribute("wishLink",wish.getWishLink());
        model.addAttribute("wishlistId",wish.getWishlistId());
        return "addWish";
    }


    @PostMapping("/savewish")
    public String saveWish(@ModelAttribute Wish wish){
        wishlistService.addWish(wish);
        return "redirect:/wishlist";
    }

    //***READ WISH***--------------------------------------------------------------------------------------------------R
    @GetMapping("wishes")
    public String getAllWishes(Model model) {
        List<Wish> wishes = wishlistService.getAllWishes();
        model.addAttribute("wishes", wishes);
        return "homepage";
    }

    //***UPDATE WISH***------------------------------------------------------------------------------------------------U
    @GetMapping("/{wish}/edit")
    public String editWish(@PathVariable("wish") int wishId, Model model){
        Wish wish = wishlistService.findWishById(wishId);
        model.addAttribute("wish", wish);
        model.addAttribute("wishId", wish.getWishId());
        model.addAttribute("wishName", wish.getWishName());
        model.addAttribute("wishDescription", wish.getWishDescription());
        model.addAttribute("wishPrice", wish.getWishPrice());
        model.addAttribute("wishLink",wish.getWishLink());
        model.addAttribute("wishlistId",wish.getWishlistId());
        return "homepage";
    }

    @PostMapping("/{wish}/update")
    public String updateWish(@PathVariable("name") String name, @ModelAttribute Wish wish, Model model) {
        model.addAttribute("wish", wish);
        wishlistService.updateWish(wish);
        return "redirect:/wishlist";
    }

    //***DELETE WISH***------------------------------------------------------------------------------------------------D
    @PostMapping("/{wish}/remove")
    public String removeWish(@PathVariable int id){
        Wish wish = wishlistService.findWishById(id);
        wishlistService.deleteWish(wish);
        return "redirect:/wishlists";
    }

    //***GET WISHLIST FROM PROFILE***-----------------------------------------------------------------------------------
    @GetMapping("")
    public String getWishListFromProfile(Model model, @PathVariable int profileId) throws WishlistException {
        List<Wishlist> wishLists = wishlistService.getWishlistsFromProfile(profileId);
        model.addAttribute("wishlists", wishLists);
        return "";
    }


    //***END***---------------------------------------------------------------------------------------------------------


}
