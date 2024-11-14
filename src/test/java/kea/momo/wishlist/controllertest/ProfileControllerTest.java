package kea.momo.wishlist.controllertest;

import kea.momo.wishlist.controller.ProfileController;
import kea.momo.wishlist.model.Profile;
import kea.momo.wishlist.service.ProfileService;
import kea.momo.wishlist.util.ProfileException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(ProfileController.class)
public class ProfileControllerTest {

    //***ATTRIBUTES***
//    private ProfileController profileController = new ProfileController(profileService);
//
//    @Autowired // Springboot instantierer selv objektet
//    private MockMvc mockMvc; // i stedet for en rigtig web server
//
//    @MockBean // mock af interne objekter som gør os i stand til at unit teste controller
//    ProfileService profileService;
//
//
//    //***TEST***--------------------------------------------------------------------------------------------------------
//    @Test
//    void getAllProfiles() throws Exception {
//        mockMvc.perform(get("/homepage"))
//                .andExpect(status().isOk())
//                .andExpect(view()
//                        .name(""));
//
//        //arrange
//
//        //act
//
//
//        //assert
//    }

    //***END***---------------------------------------------------------------------------------------------------------
}
