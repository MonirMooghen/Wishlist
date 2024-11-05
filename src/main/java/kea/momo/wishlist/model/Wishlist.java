package kea.momo.wishlist.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Wishlist {
    private List<Wish> wishList;

    public Wishlist(List<Wish> wishList){
        wishList = new ArrayList<>();
    }

}
