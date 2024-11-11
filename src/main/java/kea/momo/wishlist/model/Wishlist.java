package kea.momo.wishlist.model;

import java.util.ArrayList;
import java.util.List;

public class Wishlist {


    //***ATTRIBUTES***--------------------------------------------------------------------------------------------------
    private int wishlistId; //
    private String wishlistName;
    private int profileId;
    private List<Wish> wishlist;


    //***CONSTRUCTORS***------------------------------------------------------------------------------------------------
    public Wishlist(String wishlistName, int wishlistId){
        this.wishlistName = wishlistName;
        this.wishlistId = wishlistId;
        wishlist = new ArrayList<>();
    }

    //***GETTER METHODS***----------------------------------------------------------------------------------------------
    public String getWishlistName() {
        return wishlistName;
    }

    public List<Wish> getWishlist() {
        return wishlist;
    }

    public int getWishlistId(){
        return wishlistId;
    }


    //***SETTER METHODS***----------------------------------------------------------------------------------------------
    public void setWishlistName(String wishlistName) {
        this.wishlistName = wishlistName;
    }

    public void setWishlist(List<Wish> wishlist) {
        this.wishlist = wishlist;
    }

    public void setWishlistId(int wishlistId){
        this.wishlistId = wishlistId;
    }

    //***ADD METHODS***-------------------------------------------------------------------------------------------------



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
