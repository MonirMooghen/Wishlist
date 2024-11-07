package kea.momo.wishlist.repository;

import kea.momo.wishlist.model.Profile;
import kea.momo.wishlist.model.Wish;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProfileRepository {
    //***ATTRIBUTES***-------------------------------------------------------------------------------------------------
//    private String db_url = System.getenv("DB_URL");
//    private String db_username = System.getenv("DB_USER");
//    private String db_password = System.getenv("DB_PASSWORD");

    //***ATTRIBUTES***--------------------------------------------------------------------------------------------------
    private String db_url = System.getenv("DB_URL");
    private String db_username = System.getenv("DB_USER");
    private String db_password = System.getenv("DB_PASSWORD");

    //***METHODS***-----------------------------------------------------------------------------------------------------
    public List<Profile> getAllProfiles(){
        System.out.println("Profiles");
        List<Profile> profiles = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(db_url, db_username, db_password)) {
            String query = "SELECT * FROM Profile";
            Statement stmt = con.createStatement(); //Opretter et statement til at udføre SQL-forespørgsler
            ResultSet rs = stmt.executeQuery(query); //Udfører en SELECT-forespørgsel og returnerer resultaterne som et ResultSet.

            while (rs.next()) {
                String profileName = rs.getString("profileName");
                String profileLastName = rs.getString("profileLastName");
                String profileEmail = rs.getString("profileEmail");
                String profilePassword = rs.getString("profilePassword");
                int profileId = rs.getInt("profileId");
                // opretter ny liste af tags for nye attraction

                Profile profileObj = new Profile(profileName,profileLastName,profileEmail,profilePassword,profileId);
                profiles.add(profileObj);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return profiles;

    }

}
