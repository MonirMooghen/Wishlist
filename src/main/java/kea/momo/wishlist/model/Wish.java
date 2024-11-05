package kea.momo.wishlist.model;

public class Wish {
    private String name;
    private String description;
    private double price; //TODO men vil vi have price?
    //TODO flere attributter?

    public Wish(){
    }

    public Wish(String name, String description, double price){
        this.name = name;
        this.description = description;
        this.price = price;
    }

}
