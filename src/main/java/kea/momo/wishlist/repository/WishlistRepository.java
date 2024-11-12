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

    //***WISHLIST METHODS***--------------------------------------------------------------------------------------------
    //***GET***---------------------------------------------------------------------------------------------------------
    public List<Wishlist> getAllWishlists() {
        List<Wishlist> allWishLists = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(db_url, db_username, db_password)) {
            String query = "SELECT * FROM Wishlist";
            Statement stmt = con.createStatement(); //Opretter et statement til at udføre SQL-forespørgsler
            ResultSet rs = stmt.executeQuery(query); //Udfører en SELECT-forespørgsel og returnerer resultaterne som et ResultSet.

            while (rs.next()) {
                String wishlistName = rs.getString("wishlistName");
                int wishlistId = rs.getInt("wishlistId");

                Wishlist wishListObj = new Wishlist(wishlistName, wishlistId);
                allWishLists.add(wishListObj);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return allWishLists;
    }

    public Wishlist findWishlistById(int id){
        for (Wishlist wishlist : getAllWishlists()){
            if (id == wishlist.getWishlistId()){
                return wishlist;
            }
        } throw new IllegalArgumentException("No wishlist with this ID");
    }

    //***ADD WISHLIST***------------------------------------------------------------------------------------------------
    public void addWishlist(Wishlist wishlist){
        System.out.println("Add wishlist");
        String insertWishlistQuery = """
        INSERT INTO Wishlist (wishlistName) VALUES (?)""";

        try (Connection con = DriverManager.getConnection(db_url, db_username, db_password)) {
            PreparedStatement profileStatement = con.prepareStatement(insertWishlistQuery);
            profileStatement.setString(1, wishlist.getWishlistName());
            profileStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //***UPDATE WISHLIST***---------------------------------------------------------------------------------------------
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

    //***DELETE WISHLIST***------------------------------------------------------------------------------------------------
    public void deleteWishlist(Wishlist wishlist) {
        String deleteQuery = "DELETE FROM Wishlist WHERE wishlistId = ?";
        //String findIDQuery = "";

        try (Connection con = DriverManager.getConnection(db_url, db_username, db_password)) {
            //PreparedStatement prepstmt2 = con.prepareStatement(findIDQuery);
            //ResultSet rs = prepstmt2.getGeneratedKeys();
            PreparedStatement prepstmt = con.prepareStatement(deleteQuery);
            prepstmt.setInt(1, wishlist.getWishlistId());
            prepstmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //***WISH METHODS***------------------------------------------------------------------------------------------------
    //***GET***---------------------------------------------------------------------------------------------------------
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


    //***ADD WISH***----------------------------------------------------------------------------------------------------
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

            System.out.println("Wish added successfully to the wishlist.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //***UPDATE***------------------------------------------------------------------------------------------------------
    public void updateWish(Wish wish){
        String updateQuery = """
        UPDATE Wish
        SET wishName = ?, wishDescription = ?, wishPrice = ?, wishLink = ?
        WHERE wishId = ?
        """;

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

    //***DELETE WISHLIST***------------------------------------------------------------------------------------------------
    public void deleteWish(Wish wish) {
        String deleteQuery = "DELETE FROM Wish WHERE wishId = ?";

        try (Connection con = DriverManager.getConnection(db_url, db_username, db_password)) {
            PreparedStatement prepstmt = con.prepareStatement(deleteQuery);
            prepstmt.setInt(1, wish.getWishId());
            prepstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //***END***-----------------------------------------------------------------------------------------------------
}

