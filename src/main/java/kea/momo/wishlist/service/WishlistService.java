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
    public void addWishlist(Wishlist wishlist){
        wishlistRepository.addWishlist(wishlist);
    }

    public List<Wishlist> getAllWishLists() {
        return wishlistRepository.getAllWishlists();
    }

    public Wishlist findWishlistById(int id) {
        return wishlistRepository.findWishlistById(id);
    }

    public void updateWishlist(Wishlist wishlist){
        wishlistRepository.updateWishlist(wishlist);
    }

    public void deleteWishlist(Wishlist wishlist){
        wishlistRepository.deleteWishlist(wishlist);
    }

    //***WISH METHODS***------------------------------------------------------------------------------------------------
    public List<Wish> getAllWishes(){
        return wishlistRepository.getAllWishes();
    }

    public void addWish(Wish wish) {
        wishlistRepository.addWish(wish);
    }

    public Wish findWishById(int id) {
        return wishlistRepository.findWishById(id);
    }

    public void updateWish(Wish wish){
        wishlistRepository.updateWish(wish);
    }

    public void deleteWish(Wish wish){
        wishlistRepository.deleteWish(wish);
    }

    //***END***---------------------------------------------------------------------------------------------------------

}
