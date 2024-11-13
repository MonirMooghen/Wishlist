package kea.momo.wishlist.repository;

import kea.momo.wishlist.model.Wish;
import kea.momo.wishlist.model.Wishlist;
import kea.momo.wishlist.util.WishlistException;
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

    //***WISHLIST METHODS***--------------------------------------------------------------------------------------------
    //***CREATE WISHLIST***--------------------------------------------------------------------------------------------C
    public void addWishlist(Wishlist wishlist) {
        String insertWishlistQuery = """
                
                INSERT INTO Wishlist (wishlistName,profileId) VALUES (?,?)""";

        try (Connection con = DriverManager.getConnection(db_url, db_username, db_password)) {
            PreparedStatement profileStatement = con.prepareStatement(insertWishlistQuery);
            profileStatement.setString(1, wishlist.getWishlistName());
            profileStatement.setInt(2, wishlist.getProfileId());
            profileStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //***READ WISHLIST***----------------------------------------------------------------------------------------------R
    public List<Wishlist> getAllWishlists() {
        List<Wishlist> allWishLists = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(db_url, db_username, db_password)) {
            String query = "SELECT * FROM Wishlist";
            Statement stmt = con.createStatement(); //Opretter et statement til at udføre SQL-forespørgsler
            ResultSet rs = stmt.executeQuery(query); //Udfører en SELECT-forespørgsel og returnerer resultaterne som et ResultSet.

            while (rs.next()) {
                String wishlistName = rs.getString("wishlistName");
                int wishlistId = rs.getInt("wishlistId");
                int profileId = rs.getInt("profileId");

                Wishlist wishListObj = new Wishlist(wishlistName, wishlistId, profileId);
                allWishLists.add(wishListObj);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return allWishLists;
    }

    public Wishlist findWishlistById(int wishlistId) {
        for (Wishlist wishlist : getAllWishlists()) {
            if (wishlistId == wishlist.getWishlistId()) {
                return wishlist;
            }
        }
        throw new IllegalArgumentException("No wishlist with this ID");
    }

    public List<Wishlist> getWishListFromProfile(int profileId) throws WishlistException {
        List<Wishlist> wishlists = new ArrayList<>();
        for (Wishlist wishlist : getAllWishlists()) {
            if (wishlist.getProfileId() == profileId) {
                wishlists.add(wishlist);
            }
        }

        if (!wishlists.isEmpty()) {
            throw new WishlistException("No wishlist exist for this profile");
        }

        return wishlists;
    }

    //***UPDATE WISHLIST***--------------------------------------------------------------------------------------------U
    public void updateWishlist(Wishlist wishlist){
        String updateQuery = """
        UPDATE Wishlist
        SET wishlistName = ?
        WHERE wishlistId = ?
        """;

        try (Connection con = DriverManager.getConnection(db_url, db_username, db_password)) {
            PreparedStatement wishlistStatement = con.prepareStatement(updateQuery);
            wishlistStatement.setString(1, wishlist.getWishlistName());
            wishlistStatement.setInt(2, wishlist.getWishlistId());

            wishlistStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //***DELETE WISHLIST***--------------------------------------------------------------------------------------------D
    public void deleteWishlist(Wishlist wishlist) {
        String deleteQuery = "DELETE FROM Wishlist WHERE wishlistId = ?";

        try (Connection con = DriverManager.getConnection(db_url, db_username, db_password)) {
            PreparedStatement prepstmt = con.prepareStatement(deleteQuery);
            prepstmt.setInt(1, wishlist.getWishlistId());
            prepstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //***WISH METHODS***------------------------------------------------------------------------------------------------
    //***CREATE WISH***------------------------------------------------------------------------------------------------C
    public void addWish(Wish wish) {
        System.out.println("Add wish");
        String insertWishQuery = """
        INSERT INTO Wish (wishName, wishDescription, wishPrice, wishLink, wishlistId)  
        VALUES (?, ?, ?, ?, ?)
    """;

        try (Connection con = DriverManager.getConnection(db_url, db_username, db_password)) {
            // Prepare the insert statement
            PreparedStatement wishStatement = con.prepareStatement(insertWishQuery);

            // Set values for the wish
            wishStatement.setString(1, wish.getWishName());
            wishStatement.setString(2, wish.getWishDescription());
            wishStatement.setDouble(3, wish.getWishPrice());
            wishStatement.setString(4, wish.getWishLink());
            wishStatement.setInt(5, wish.getWishlistId()); //addWish.html skal give id'et

            // Execute the insert statement
            wishStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //***READ WISH***--------------------------------------------------------------------------------------------------R
    public List<Wish> getAllWishes(){
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
                int wishlistId = rs.getInt("wishlistId");
                // opretter ny liste af tags for nye attraction

                Wish wishObj = new Wish(wishName, description, price, link, wishId, wishlistId);
                wishes.add(wishObj);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return wishes;

    }

    public Wish findWishById(int id){
        for (Wish wish : getAllWishes()){
            if (id == wish.getWishId()){
                return wish;
            }
        } throw new IllegalArgumentException("No wish with this ID");
    }

    //***UPDATE WISH***------------------------------------------------------------------------------------------------U
    public void updateWish(Wish wish){
        String updateQuery = """
                            UPDATE Wish
                            SET wishName = ?,
                                wishDescription = ?, 
                                wishPrice = ?, 
                                wishLink = ?                              
                            WHERE wishId = ?""";

        try (Connection con = DriverManager.getConnection(db_url, db_username, db_password)) {
            PreparedStatement wishStatement = con.prepareStatement(updateQuery);
            wishStatement.setString(1, wish.getWishName());
            wishStatement.setString(2, wish.getWishDescription());
            wishStatement.setDouble(3, wish.getWishPrice());
            wishStatement.setString(4, wish.getWishLink());
            wishStatement.setInt(5, wish.getWishId());
            wishStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //***DELETE WISH***------------------------------------------------------------------------------------------------D
    public void deleteWish(Wish wish) {
        String deleteQuery = " DELETE FROM Wish WHERE wishId = ?";

        try (Connection con = DriverManager.getConnection(db_url, db_username, db_password)) {
            PreparedStatement prepstmt = con.prepareStatement(deleteQuery);
            prepstmt.setInt(1, wish.getWishId());
            prepstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //***END***---------------------------------------------------------------------------------------------------------
}



