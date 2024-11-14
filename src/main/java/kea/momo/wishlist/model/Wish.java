package kea.momo.wishlist.model;

import org.springframework.stereotype.Component;

@Component
public class Wish {

    //***ATTRIBUTES***--------------------------------------------------------------------------------------------------
    private int wishId;
    private String wishName;
    private String wishDescription;
    private double wishPrice; //TODO modtager database en double / eller skal vi rette til int?
    private String wishLink;
    private int wishlistId;

    //***CONSTRUCTORS***------------------------------------------------------------------------------------------------
    public Wish(String wishName, String wishDescription, double wishPrice, String wishLink, int wishlistId){
        this.wishName = wishName;
        this.wishDescription = wishDescription;
        this.wishPrice = wishPrice;
        this.wishLink = wishLink;
        this.wishlistId = wishlistId;
    }

    public Wish(String wishName, String wishDescription, double wishPrice, String wishLink, int wishId, int wishlistId){
        this.wishName = wishName;
        this.wishDescription = wishDescription;
        this.wishPrice = wishPrice;
        this.wishLink = wishLink;
        this.wishId = wishId;
        this.wishlistId = wishlistId;
    }

    public Wish(){
    }

    //***GETTER METHODS***----------------------------------------------------------------------------------------------
    public String getWishName() {
        return wishName;
    }

    public String getWishDescription() {
        return wishDescription;
    }

    public double getWishPrice() {
        return wishPrice;
    }

    public String getWishLink() {
        return wishLink;
    }

    public int getWishId(){
        return wishId;
    }

    public int getWishlistId() {
        return wishlistId;
    }

    //***SETTER METHODS***----------------------------------------------------------------------------------------------
    public void setWishName(String wishName) {
        this.wishName = wishName;
    }

    public void setWishDescription(String wishDescription) {
        this.wishDescription = wishDescription;
    }

    public void setWishPrice(double wishPrice) {
        this.wishPrice = wishPrice;
    }

    public void setWishLink(String wishLink) {
        this.wishLink = wishLink;
    }

    public void setWishId(int wishId) {
        this.wishId = wishId;
    }

    public void setWishlistId(int wishlistId) {
        this.wishlistId = wishlistId;
    }

    //***TO STRING METHOD***--------------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return
                "\n Wish name: "        + wishName        +
                "\n Wish ID: "          + wishId          +
                "\n Wish description: " + wishDescription +
                "\n Wish price: "       + wishPrice       +
                "\n Wish link: "        + wishLink        +
                "\n Wishlist ID: "      + wishlistId      ;
    }
    //***END***---------------------------------------------------------------------------------------------------------
}
