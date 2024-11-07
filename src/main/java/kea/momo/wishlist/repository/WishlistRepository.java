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

    //***METHODS***-----------------------------------------------------------------------------------------------------
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



    public List<Wishlist> getAllWishLists() {
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

                Wishlist wishListObj = new Wishlist(wishlistName, profileId,wishlistId);

                allWishLists.add(wishListObj);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return allWishLists;
    }


//    public void addWish(Wish wish) { //TODO rettes
//    }

    public void addWish(Wish wish/*, Wishlist wishlist*/) {
        System.out.println("Add wish"); //TODO hvorfor skal der være en sout her?
        //String getWishlistIdSQL = "SELECT wishlistId FROM Wishlist WHERE wishlistName = ?";
        String insertWishSQL = """
                                    
                INSERT INTO Wish(
                                     wishName, wishDescription, wishPrice, 
                                     wishLink) VALUES (?,?,?,?)""";

        try (Connection con = DriverManager.getConnection(db_url,db_username,db_password)){

            //con.setAutoCommit(false);
            //transaction begin

//            // gette wishlist id
//            PreparedStatement preparedStatement1 = con.prepareStatement(getWishlistIdSQL);
//            preparedStatement1.setString(1, wish.get());
//            ResultSet resultSet1 = preparedStatement1.executeQuery();
//            resultSet1.next();
//            int cityId = resultSet1.getInt("city_id");


            //indsæt attraktion  (hvordan ved vi at det er en ny attraktion vi opretter?)

            int wishlistId;
            PreparedStatement wishStatement = con.prepareStatement(insertWishSQL, Statement.RETURN_GENERATED_KEYS);

            wishStatement.setString(1,wish.getWishName());
            wishStatement.setString(2, wish.getWishDescription());
            wishStatement.setDouble(3, wish.getWishPrice());
            wishStatement.setString(4, wish.getWishLink());
            ResultSet generatedKeys = wishStatement.getGeneratedKeys();
            if
                (generatedKeys.next()){
            wishlistId = generatedKeys.getInt(1);
            }

            // TODO: get wishListID wishStatement.setString()


//            //Få det genererede attraction_id
//            ResultSet generatedKeys = wishStatement.getGeneratedKeys();
//            int wishId = -1;
//            if(generatedKeys.next()){
//            wishId = generatedKeys.getInt(1);

                wishStatement.
        executeUpdate(); //ge mmer hvad man har gjort?
            } catch (
    SQLException e){
            e.printStackTrace();
        }
        }



    //***END***-----------------------------------------------------------------------------------------------------
    }
//
//

//
//
//    public ArrayList<Wish> getAllWishes() { //TODO rettes
//        return null;
//    }

