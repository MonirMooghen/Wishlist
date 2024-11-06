package kea.momo.wishlist.service;

import kea.momo.wishlist.model.Wish;
import kea.momo.wishlist.model.Wishlist;
import kea.momo.wishlist.repository.WishlistRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WishlistService {
    //***ATTRIBUTES***--------------------------------------------------------------------------------------------------
    private final WishlistRepository wishlistRepository;

    //***CONSTRUCTOR***-------------------------------------------------------------------------------------------------
    WishlistService(WishlistRepository wishlistRepository){
        this.wishlistRepository = wishlistRepository;
    }

    //***METHODS***-----------------------------------------------------------------------------------------------------
    public ArrayList<Wish> getAllWishes(){
        return wishlistRepository.getAllWishes();
    }

    public void addWish(Wish wish){
        wishlistRepository.addWish(wish);
    }

    public List<Wishlist> getAllWishLists() {
        return wishlistRepository.getAllWishLists();
    }

    //***END***---------------------------------------------------------------------------------------------------------

}
