package kea.momo.wishlist.model;

public class Wish {

    //***ATTRIBUTES***--------------------------------------------------------------------------------------------------
    private int wishId; // todo autoincrement kommer fra database - men vi tager det ind i objektet og henter id fra database
    private String wishName;
    private String wishDescription;
    private double wishPrice;
    private String wishLink;
    private int wishlistId;

    //***CONSTRUCTORS***------------------------------------------------------------------------------------------------
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

    public int getWishId(){
        return wishId;
    }

    public int getWishlistId(){
        return wishlistId;
    }

    //***SETTER***------------------------------------------------------------------------------------------------------
    public void setWishName(String name) {
        this.wishName = name;
    }

    public void setWishDescription(String wishDescription) {
        this.wishDescription = wishDescription;
    }

    public void setWishPrice(double price) {
        this.wishPrice = wishPrice;
    }

    public void setWishLink(String link) {
        this.wishLink = wishLink;
    }

    public void setWishId(int id) {
        this.wishId = wishId;
    }

    public void setWishlistId(int id){
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
                "\n Wish link: "        + wishLink  + '\n';
    }
    //***END***---------------------------------------------------------------------------------------------------------
}
