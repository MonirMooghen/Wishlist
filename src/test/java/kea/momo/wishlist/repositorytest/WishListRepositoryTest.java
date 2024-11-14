package kea.momo.wishlist.repositorytest;

import kea.momo.wishlist.model.Profile;
import kea.momo.wishlist.model.Wishlist;
import kea.momo.wishlist.repository.WishlistRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

public class WishListRepositoryTest {

    //***ATTRIBUTES***--------------------------------------------------------------------------------------------------
    @Autowired
    private WishlistRepository wishlistRepository;

    @MockBean
    MockMvc mockMvc;

//    @Test
//    void addWishList(){ // integration test since method connects with database
//        //arrange
//        Profile profile = new Profile();
//
//        //act
//
//        //assert
//
//    }

    //***END***---------------------------------------------------------------------------------------------------------
}
