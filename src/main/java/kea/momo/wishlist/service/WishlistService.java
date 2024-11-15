package kea.momo.wishlist.service;

import kea.momo.wishlist.model.Wish;
import kea.momo.wishlist.model.Wishlist;
import kea.momo.wishlist.repository.WishlistRepository;
import kea.momo.wishlist.util.WishlistRSInterface;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService implements WishlistRSInterface {

    //***ATTRIBUTES***--------------------------------------------------------------------------------------------------
    private final WishlistRepository wishlistRepository;

    //***CONSTRUCTOR***-------------------------------------------------------------------------------------------------
    WishlistService(WishlistRepository wishlistRepository){
        this.wishlistRepository = wishlistRepository;
    }

    //***WISHLIST METHODS***--------------------------------------------------------------------------------------------
    //***CREATE WISHLIST***--------------------------------------------------------------------------------------------C
    @Override
    public void addWishlist(Wishlist wishlist){
        wishlistRepository.addWishlist(wishlist);
    }

    //***READ WISHLIST***----------------------------------------------------------------------------------------------R
    @Override
    public List<Wishlist> getAllWishlists() {
        return wishlistRepository.getAllWishlists();
    }

    @Override
    public Wishlist findWishlistById(int id) {
        return wishlistRepository.findWishlistById(id);
    }

    @Override
    public List<Wishlist> getWishlistsFromProfile(int profileId) {
        return wishlistRepository.getWishlistsFromProfile(profileId);
    }

    //***UPDATE WISHLIST***--------------------------------------------------------------------------------------------U
    @Override
    public void updateWishlist(Wishlist wishlist){
        wishlistRepository.updateWishlist(wishlist);
    }

    //***DELETE WISHLIST***--------------------------------------------------------------------------------------------D
    @Override
    public void deleteWishlist(Wishlist wishlist){
        wishlistRepository.deleteWishlist(wishlist);
    }

    //***WISH METHODS***------------------------------------------------------------------------------------------------
    //***CREATE WISH***------------------------------------------------------------------------------------------------C
    @Override
    public void addWish(Wish wish) {
        wishlistRepository.addWish(wish);
    }

    //***READ WISH***--------------------------------------------------------------------------------------------------R
    @Override
    public List<Wish> getAllWishes(){
        return wishlistRepository.getAllWishes();
    }

    @Override
    public Wish findWishById(int id) {
        return wishlistRepository.findWishById(id);
    }

    @Override
    public List<Wish> getWishFromWishlistId(int wishlistId){
        return wishlistRepository.getWishFromWishlistId(wishlistId);
    }

    //***UPDATE WISH***------------------------------------------------------------------------------------------------U
    @Override
    public void updateWish(Wish wish){
        wishlistRepository.updateWish(wish);
    }

    //***DELETE WISH***------------------------------------------------------------------------------------------------D
    @Override
    public void deleteWish(Wish wish){
        wishlistRepository.deleteWish(wish);
    }

    //***END***---------------------------------------------------------------------------------------------------------
}