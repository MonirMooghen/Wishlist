package kea.momo.wishlist.model;

import java.time.LocalDate;

public class Wishlist {
    private String name;
    private LocalDate date; //TODO vil vi have date?

    public Wishlist(){
    }

    public Wishlist(String name, LocalDate date){
        this.name = name;
        this.date = date;
    }

}
