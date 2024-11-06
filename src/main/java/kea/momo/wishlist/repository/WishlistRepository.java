package kea.momo.wishlist.repository;

import kea.momo.wishlist.model.Wish;
import kea.momo.wishlist.model.Wishlist;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class WishlistRepository {

    public List<Wishlist> getAllWishLists() { //TODO rettes
        return List.of();
    }

    public void addWish(Wish wish) { //TODO rettes
    }

    public ArrayList<Wish> getAllWishes() { //TODO rettes
        return null;
    }
}
