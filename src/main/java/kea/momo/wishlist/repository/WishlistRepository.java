package kea.momo.wishlist.repository;

import kea.momo.wishlist.model.Wish;
import kea.momo.wishlist.model.Wishlist;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WishlistRepository {

    //***ATTRIBUTES***--------------------------------------------------------------------------------------------------
    private String db_url = System.getenv("DB_URL");
    private String db_username = System.getenv("DB_USER");
    private String db_password = System.getenv("DB_PASSWORD");

    //***METHODS***--------------------------
    public List<Wish> getAllWishes(){
        System.out.println("Wishes");
        List<Wish> wishes = new ArrayList<>();

           try (Connection con = DriverManager.getConnection(db_url, db_username, db_password)) {
                String query = "SELECT * FROM Wish";
                Statement stmt = con.createStatement(); //Opretter et statement til at udføre SQL-forespørgsler
                ResultSet rs = stmt.executeQuery(query); //Udfører en SELECT-forespørgsel og returnerer resultaterne som et ResultSet.

                while (rs.next()) {
                String wishName = rs.getString("wishName");
                String description = rs.getString("wishDescription");
                int price = rs.getInt("wishPrice");
                String link = rs.getString("wishLink");
                int wishId = rs.getInt("wishId");
                // opretter ny liste af tags for nye attraction

                Wish wishObj = new Wish(wishName, description, price, link, wishId);
                wishes.add(wishObj);
           }
        } catch (SQLException ex) {
                throw new RuntimeException(ex);
    }
           
            return wishes;

    }



    public List<Wishlist> getAllWishLists() { //TODO
        System.out.println("WishListssssssss");
        List<Wishlist> allWishLists = new ArrayList<>();


        try (Connection con = DriverManager.getConnection(db_url, db_username, db_password)) {
            String query = "SELECT * FROM Wishlist";
            Statement stmt = con.createStatement(); //Opretter et statement til at udføre SQL-forespørgsler
            ResultSet rs = stmt.executeQuery(query); //Udfører en SELECT-forespørgsel og returnerer resultaterne som et ResultSet.

            while (rs.next()) {
                String wishlistName = rs.getString("wishlistName");
                int wishlistId = rs.getInt("wishlistId");
                int profileId = rs.getInt("profileId");

                // opretter ny liste af tags for nye attraction

                Wishlist wishListObj = new Wishlist(wishlistName);
                //wishes.add(wishObj);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return List.of();
    }
        //***END***-----------------------------------------------------------------------------------------------------
}
//
//

//
//    public void addWish(Wish wish) { //TODO rettes
//    }
//
//    public ArrayList<Wish> getAllWishes() { //TODO rettes
//        return null;
//    }

