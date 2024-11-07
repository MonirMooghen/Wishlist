package kea.momo.wishlist.model;

public class Wish {

    //***ATTRIBUTES***--------------------------------------------------------------------------------------------------
    private int wishId; // todo autoincrement
    private String wishName;
    private String wishDescription;
    private double wishPrice;
    private String wishLink;

    //***CONSTRUCTORS***------------------------------------------------------------------------------------------------
    public Wish(String wishName, String wishDescription, double wishPrice, String wishLink){
        this.wishName = wishName;
        this.wishDescription = wishDescription;
        this.wishPrice = wishPrice;
        this.wishLink = wishLink;
    }



    public Wish(){
    }

    //***GETTER***------------------------------------------------------------------------------------------------------
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

    //***SETTER***------------------------------------------------------------------------------------------------------
    public void setWishName(String name) {
        this.wishName = name;
    }

    public void setWishDescription(String wishDescription) {
        this.wishDescription = wishDescription;
    }

    public void setWishPrice(double price) {
        this.wishPrice = price;
    }

    public void setWishLink(String link) {
        this.wishLink = link;
    }

    //***END***---------------------------------------------------------------------------------------------------------
}
