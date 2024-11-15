package kea.momo.wishlist.repository;

import kea.momo.wishlist.model.Wish;
import kea.momo.wishlist.model.Wishlist;
import kea.momo.wishlist.util.WishlistRSInterface;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WishlistRepository implements WishlistRSInterface {

    //***ATTRIBUTES***--------------------------------------------------------------------------------------------------
    private String db_url = System.getenv("DB_URL");
    private String db_username = System.getenv("DB_USER");
    private String db_password = System.getenv("DB_PASSWORD");

    //***WISHLIST METHODS***--------------------------------------------------------------------------------------------
    //***CREATE WISHLIST***--------------------------------------------------------------------------------------------C
    @Override
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
    @Override
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

    @Override
    public Wishlist findWishlistById(int wishlistId) {
        for (Wishlist wishlist : getAllWishlists()) {
            if (wishlistId == wishlist.getWishlistId()) {
                wishlist.setWishlist(getWishFromWishlistId(wishlist.getWishlistId()));
                return wishlist;
            }
        }
        throw new IllegalArgumentException("No wishlist with this ID");
    }

    @Override
    public List<Wishlist> getWishlistsFromProfile(int profileId) {
        List<Wishlist> wishlists = new ArrayList<>();
        for (Wishlist wishlist : getAllWishlists()) {
            if (wishlist.getProfileId() == profileId) {
                wishlist.setWishlist(getWishFromWishlistId(wishlist.getWishlistId()));
                wishlists.add(wishlist);
            }
        }

        return wishlists;
    }

    //***UPDATE WISHLIST***--------------------------------------------------------------------------------------------U
    @Override
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
    @Override
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
    @Override
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
    @Override
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

    @Override
    public Wish findWishById(int id){
        for (Wish wish : getAllWishes()){
            if (id == wish.getWishId()){
                return wish;
            }
        } throw new IllegalArgumentException("No wish with this ID");
    }

    @Override
    public List<Wish> getWishFromWishlistId(int wishlistId) {
        List<Wish> wishes = new ArrayList<>();
        for (Wish wish : getAllWishes()) {
            if (wish.getWishlistId() == wishlistId) {
                wishes.add(wish);
            }
        }

        return wishes;
    }

    //***UPDATE WISH***------------------------------------------------------------------------------------------------U
    @Override
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
    @Override
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



