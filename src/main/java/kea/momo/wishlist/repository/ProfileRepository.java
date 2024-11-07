package kea.momo.wishlist.repository;

import kea.momo.wishlist.model.Profile;
import kea.momo.wishlist.model.Wish;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProfileRepository {

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

                Profile profileObj = new Profile(profileName,profileLastName,profileEmail,profilePassword);
                profiles.add(profileObj);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return profiles;

    }

    //***ADD PROFILE***-------------------------------------------------------------------------------------------------
    public Profile addProfile(Profile profile){
        System.out.println("Add Profile");
        Profile profile1 = null;

        String insertProfileQuery = """
                                        INSERT INTO Profile (
                                        profile_profileName,
                                        profile_profileLastname,
                                        profile_profileEmail),
                                        profile_profilePassword)
                                        VALUES (?,?,?,?)""";

        try (Connection con = DriverManager.getConnection(db_url,db_username,db_password)){

            //Opret en profile
            PreparedStatement profileStatement = con.prepareStatement(insertProfileQuery);
            profileStatement.setString(1, profile.getProfileName());
            profileStatement.setString(2, profile.getProfileLastName());
            profileStatement.setString(3, profile.getProfileEmail());
            profileStatement.setString(4, profile.getProfilePassword());
            profileStatement.executeUpdate();

            ResultSet rs = profileStatement.getGeneratedKeys();
            String profileName = rs.getString("profileName");
            String profileLastName = rs.getString("profileLastName");
            String profileEmail = rs.getString("profileEmail");
            String profilePassword = rs.getString("profilePassword");


            profile1 = new Profile(profileName,profileLastName,profileEmail,profilePassword);


        } catch (SQLException e){
            e.printStackTrace();
        }

        return profile1;
    }

    //***END***---------------------------------------------------------------------------------------------------------
}
