package kea.momo.wishlist;

import kea.momo.wishlist.model.Wish;
import kea.momo.wishlist.repository.WishlistRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WishlistApplication {

    public static void main(String[] args) {
        SpringApplication.run(WishlistApplication.class, args);
        WishlistRepository wishlistRepository = new WishlistRepository();
        for(Wish wishes : wishlistRepository.getAllWishes()){

            System.out.println(wishes.getWishName());

        }
       // System.out.println(wishlistRepository.getAllWishes());
    }


}
