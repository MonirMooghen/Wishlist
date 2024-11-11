package kea.momo.wishlist.controller;

import kea.momo.wishlist.model.Wish;
import kea.momo.wishlist.model.Wishlist;
import kea.momo.wishlist.service.WishlistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("wishlist")
public class WishlistController {

    private final WishlistService wishlistService;

    //***CONSTRUCTOR***-------------------------------------------------------------------------------------------------
    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    //***WISHLIST***----------------------------------------------------------------------------------------------------
    //***CREATE WISHLIST***--------------------------------------------------------------------------------------------C
    @GetMapping("/addwishlist")
    public String addWishlist(Model model) {
        Wishlist wishlist = new Wishlist();
        model.addAttribute("wishlist", wishlist);
        //TODO attributter? wishlistName, wishlistId
        return "wishlist";
    }

    @PostMapping("/savewishlist")
    public String saveWishlist(@ModelAttribute Wishlist wishlist){
        wishlistService.addWishlist(wishlist);
        return "redirect:/wishlist";
    }

    //***READ WISHLIST***----------------------------------------------------------------------------------------------R
    @GetMapping("wishlists")
    public String getAllWishLists(Model model) {
        List<Wishlist> wishLists = wishlistService.getAllWishLists();
        model.addAttribute("wishLists", wishLists);
        return "wishlist";
    }

    //***UPDATE WISHLIST***--------------------------------------------------------------------------------------------U
    //TODO EDIT WISHLIST
    //TODO UPDATE WISHLIST

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
        //TODO atributter? wishName, wishDescription, wishPrice, wishLink, wishId
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
        return "wishlist";
    }

    //***UPDATE WISH***------------------------------------------------------------------------------------------------U
    //TODO EDIT WISH
    //TODO UPDATE WISH

    //***DELETE WISH***------------------------------------------------------------------------------------------------D
    @PostMapping("/{wish}/remove")
    public String removeWish(@PathVariable int id){
        Wish wish = wishlistService.findWishById(id);
        wishlistService.deleteWish(wish);
        return "redirect:/wishlists";
    }




    //***END***---------------------------------------------------------------------------------------------------------


}
