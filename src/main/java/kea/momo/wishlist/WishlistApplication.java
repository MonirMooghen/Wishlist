package kea.momo.wishlist;

import kea.momo.wishlist.model.Profile;
import kea.momo.wishlist.repository.ProfileRepository;
import kea.momo.wishlist.repository.WishlistRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WishlistApplication {

    public static void main(String[] args) {
        SpringApplication.run(WishlistApplication.class, args);

        //Wishes
        WishlistRepository wishlistRepository = new WishlistRepository();
        System.out.println(wishlistRepository.getAllWishes());



        //Profile
        ProfileRepository profileRepository = new ProfileRepository();
        profileRepository.addProfile(new Profile("Hassan", "Katchan", "hk@gmail.com","jfoifonwdwd2"));
        System.out.println(profileRepository.getAllProfiles());


        //Wishlist
        System.out.println(wishlistRepository.getAllWishLists());
    }


}
