package kea.momo.wishlist.controller;

import kea.momo.wishlist.model.Wish;
import kea.momo.wishlist.model.Wishlist;
import kea.momo.wishlist.service.WishlistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class WishlistController {

    private final WishlistService wishlistService;

    //***CONSTRUCTOR***-------------------------------------------------------------------------------------------------

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    //***MAPPING METHODS***---------------------------------------------------------------------------------------------
    @GetMapping("") //TODO Skal rettes til
    public String getAllWishLists(Model model) {
        List<Wishlist> wishLists = wishlistService.getAllWishLists();
        model.addAttribute("wishLists", wishLists);
        return "allWishLists";
    }

    @GetMapping("")//TODO Skal rettes til
    public String getAllWishes(Model model) {
        List<Wish> wishes = wishlistService.getAllWishes();
        model.addAttribute("wishes", wishes);
        return "allWishes";
    }

    //***(/add)***------------------------------------------------------------------------------------------------------
    @GetMapping("/add")
    public String addWish(Model model) {
        Wish wish = new Wish();
        model.addAttribute("wish", wish);
        return "addWish";
    }

    @PostMapping("/save")
    public String saveWish(@ModelAttribute Wish wish){
        wishlistService.addWish(wish);
        return "redirect:/wishlist";
    }

    //***(/update)***---------------------------------------------------------------------------------------------------
    //TODO

    //***(/remove)***---------------------------------------------------------------------------------------------------
    //TODO h

    //***END***---------------------------------------------------------------------------------------------------------


}
