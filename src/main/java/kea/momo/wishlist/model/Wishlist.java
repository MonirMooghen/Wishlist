package kea.momo.wishlist.model;

import java.util.ArrayList;
import java.util.List;

public class Wishlist {
    //***ATTRIBUTES***--------------------------------------------------------------------------------------------------
    private int wishlistId; // todo autoincrement
    private String wishlistName;
    private int wishListId;
    private List<Wish> wishlist;
    private List<Wishlist> wishlistList;

    //***CONSTRUCTORS***------------------------------------------------------------------------------------------------
    public Wishlist(String wishlistName, List<Wish> wishList){
        this.wishlistName = wishlistName;
        wishlist = new ArrayList<>();
    }

    //***GETTER METHODS***----------------------------------------------------------------------------------------------
    public String getWishlistName() {
        return wishlistName;
    }

    public int getWishListId() {
        return wishListId;
    }

    public List<Wishlist> getWishlistList() {
        return wishlistList;
    }

    //***SETTER METHODS***----------------------------------------------------------------------------------------------
    public void setWishlistName(String wishlistName) {
        this.wishlistName = wishlistName;
    }

    public void setWishListId(int wishListId) {
        this.wishListId = wishListId;
    }

    public void setWishlistList(List<Wishlist> wishlistList) {
        this.wishlistList = wishlistList;
    }

    //***ADD METHODS***-------------------------------------------------------------------------------------------------
    public void addWishlist(Wishlist wishList){
        wishlistList.add(wishList);
    }

    //***TO STRING METHOD***--------------------------------------------------------------------------------------------

    @Override
    public String toString() {
        return
                "\n Wishlist name: " + wishlistName +
                "\n Wishlist Id: "   + wishlistId   +
                "\n Wishes: "        + wishlist     ;
    }

    //***END***---------------------------------------------------------------------------------------------------------
}
