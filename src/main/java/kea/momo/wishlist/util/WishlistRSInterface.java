package kea.momo.wishlist.util;

import kea.momo.wishlist.model.Wish;
import kea.momo.wishlist.model.Wishlist;

import java.util.List;

public interface WishlistRSInterface {

    //***WISHLIST METHODS***--------------------------------------------------------------------------------------------
    //***CREATE WISHLIST***--------------------------------------------------------------------------------------------C
    public void addWishlist(Wishlist wishlist);

    //***READ WISHLIST***----------------------------------------------------------------------------------------------R
    List<Wishlist> getAllWishlists();

    Wishlist findWishlistById(int wishlistId);

    List<Wishlist> getWishlistsFromProfile(int profileId);

    //***UPDATE WISHLIST***--------------------------------------------------------------------------------------------U
    void updateWishlist(Wishlist wishlist);

    //***DELETE WISHLIST***--------------------------------------------------------------------------------------------D
    void deleteWishlist(Wishlist wishlist);

    //***WISH METHODS***------------------------------------------------------------------------------------------------
    //***CREATE WISH***------------------------------------------------------------------------------------------------C
    void addWish(Wish wish);

    //***READ WISH***--------------------------------------------------------------------------------------------------R
    List<Wish> getAllWishes();

    Wish findWishById(int id);

    List<Wish> getWishFromWishlistId(int wishlistId);

    //***UPDATE WISH***------------------------------------------------------------------------------------------------U
    void updateWish(Wish wish);

    //***DELETE WISH***------------------------------------------------------------------------------------------------D
    void deleteWish(Wish wish);

    //***END***---------------------------------------------------------------------------------------------------------
}
