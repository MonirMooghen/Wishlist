package kea.momo.wishlist.service;

import kea.momo.wishlist.model.Wish;
import kea.momo.wishlist.model.Wishlist;
import kea.momo.wishlist.repository.WishlistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {

    //***ATTRIBUTES***--------------------------------------------------------------------------------------------------
    private final WishlistRepository wishlistRepository;

    //***CONSTRUCTOR***-------------------------------------------------------------------------------------------------
    WishlistService(WishlistRepository wishlistRepository){
        this.wishlistRepository = wishlistRepository;
    }

    //***WISHLIST METHODS***--------------------------------------------------------------------------------------------
    //***CREATE WISHLIST***--------------------------------------------------------------------------------------------C
    public void addWishlist(Wishlist wishlist){
        wishlistRepository.addWishlist(wishlist);
    }

    //***READ WISHLIST***----------------------------------------------------------------------------------------------R
    public List<Wishlist> getAllWishLists() {
        return wishlistRepository.getAllWishlists();
    }

    public Wishlist findWishlistById(int id) {
        return wishlistRepository.findWishlistById(id);
    }

    public List<Wishlist> getWishlistsFromProfile(int profileId) {
        return wishlistRepository.getWishListFromProfile(profileId);
    }

    //***UPDATE WISHLIST***--------------------------------------------------------------------------------------------U
    public void updateWishlist(Wishlist wishlist){
        wishlistRepository.updateWishlist(wishlist);
    }

    //***DELETE WISHLIST***--------------------------------------------------------------------------------------------D
    public void deleteWishlist(Wishlist wishlist){
        wishlistRepository.deleteWishlist(wishlist);
    }

    //***WISH METHODS***------------------------------------------------------------------------------------------------
    //***CREATE WISH***------------------------------------------------------------------------------------------------C
    public void addWish(Wish wish) {
        wishlistRepository.addWish(wish);
    }

    //***READ WISH***--------------------------------------------------------------------------------------------------R
    public List<Wish> getAllWishes(){
        return wishlistRepository.getAllWishes();
    }

    public Wish findWishById(int id) {
        return wishlistRepository.findWishById(id);
    }

    //***UPDATE WISH***------------------------------------------------------------------------------------------------U
    public void updateWish(Wish wish){
        wishlistRepository.updateWish(wish);
    }

    //***DELETE WISH***------------------------------------------------------------------------------------------------D
    public void deleteWish(Wish wish){
        wishlistRepository.deleteWish(wish);
    }

    //***END***---------------------------------------------------------------------------------------------------------
}