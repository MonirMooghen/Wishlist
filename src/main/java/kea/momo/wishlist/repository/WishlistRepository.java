package kea.momo.wishlist.repository;

import kea.momo.wishlist.model.Profile;
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
    public List<Wishlist> getAllWishLists() {
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

    //***ADD WISHLIST***------------------------------------------------------------------------------------------------
    public void addWishList(Wishlist wishlist){
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
                // opretter ny liste af tags for nye attraction

                Wish wishObj = new Wish(wishName, description, price, link, wishId);
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


    public Wishlist findWishlistById(int id){
        for (Wishlist wishlist : getAllWishLists()){
            if (id == wishlist.getWishlistId()){
                return wishlist;
            }
        } throw new IllegalArgumentException("No wishlist with this ID");
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
            PreparedStatement profileStatement = con.prepareStatement(insertWishQuery);

            // Set values for the wish
            profileStatement.setString(1, wish.getWishName());
            profileStatement.setString(2, wish.getWishDescription());
            profileStatement.setDouble(3, wish.getWishPrice());
            profileStatement.setString(4, wish.getWishLink());
            profileStatement.setInt(5, wish.getWishlistId());

            // Execute the insert statement
            profileStatement.executeUpdate();

            System.out.println("Wish added successfully to the wishlist.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //***END***-----------------------------------------------------------------------------------------------------
}

