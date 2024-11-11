package kea.momo.wishlist.controller;

import kea.momo.wishlist.model.Wish;
import kea.momo.wishlist.model.Wishlist;
import kea.momo.wishlist.service.WishlistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("homepage")
public class WishlistController {

    private final WishlistService wishlistService;

    //***CONSTRUCTOR***-------------------------------------------------------------------------------------------------
    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
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
        model.addAttribute("wishName", wishlist.getWishlist());
        return "homepage";
    }

    @PostMapping("/savewishlist")
    public String saveWishlist(@ModelAttribute Wishlist wishlist){
        wishlistService.addWishlist(wishlist);
        return "redirect:/wishlist";
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


    //***END***---------------------------------------------------------------------------------------------------------


}
