package kea.momo.wishlist.model;

public class Wish {

    //***ATTRIBUTES***--------------------------------------------------------------------------------------------------
    private String name;
    private String description;
    private double price;
    private String link;

    //***CONSTRUCTORS***------------------------------------------------------------------------------------------------
    public Wish(String name, String description, double price, String link){
        this.name = name;
        this.description = description;
        this.price = price;
        this.link = link;
    }

    public Wish(){
    }

    //***GETTER***------------------------------------------------------------------------------------------------------
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getLink() {
        return link;
    }

    //***SETTER***------------------------------------------------------------------------------------------------------
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setLink(String link) {
        this.link = link;
    }

    //***END***---------------------------------------------------------------------------------------------------------
}
