package kea.momo.wishlist;

import kea.momo.wishlist.model.Profile;
import kea.momo.wishlist.model.Wish;
import kea.momo.wishlist.model.Wishlist;
import kea.momo.wishlist.repository.ProfileRepository;
import kea.momo.wishlist.repository.WishlistRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class WishlistApplication {

    public static void main(String[] args) {
        SpringApplication.run(WishlistApplication.class, args);

//        //Wishes
//        WishlistRepository wishlistRepository = new WishlistRepository();
//        System.out.println(wishlistRepository.getAllWishes());



//        //Profile
//        ProfileRepository profileRepository = new ProfileRepository();
//        Profile hassan = new Profile("Hassan", "Katchan", "hk@gmail.com","jfoifonwdwd2");
//        profileRepository.addProfile(hassan);
//        profileRepository.updateProfile(hassan);
//        System.out.println(profileRepository.getAllProfiles());
//
//
//        //Wishlist
//        wishlistRepository.addWishList(new Wishlist("WishList1"));
//        System.out.println(wishlistRepository.getAllWishLists());


    }


}
