package kea.momo.wishlist.repository;

import kea.momo.wishlist.model.Profile;
import kea.momo.wishlist.util.ProfileException;
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
    //***GET PROFILE(S)***----------------------------------------------------------------------------------------------
    public Profile getProfileByEmailAndPassword(String profileEmail, String profilePassword) {
        for (Profile profile: getAllProfiles()) {
            if (profile.getProfileEmail().equalsIgnoreCase(profileEmail) && profile.getProfilePassword().equalsIgnoreCase(profilePassword))
                return profile;
        }
        return null;
    }

    public Profile getProfileById(int profileId) {
        for (Profile profile: getAllProfiles()) {
            if (profile.getProfileId() == (profileId))
                return profile;
        }
        return null;
    }

    public List<Profile> getAllProfiles() throws ProfileException {
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

                Profile profileObj = new Profile(profileName,profileLastName,profileEmail,profilePassword, profileId);
                profiles.add(profileObj);
            }
        } catch (SQLException ex) {
            //throw new RuntimeException(ex);
            throw new ProfileException("Something wrong with getAllProfiles()"); //TODO ret error message så det passer til users behov
        }

        return profiles;
    }

//    public Profile findProfileById(int id) {
//        for (Profile profile : getAllProfiles()) {
//            if (id == profile.getProfileId()) {
//                return profile;
//            }
//        }
//        throw new IllegalArgumentException("No profile with this ID");
//    }

    //***ADD PROFILE***-------------------------------------------------------------------------------------------------
    public void addProfile(Profile profile) throws ProfileException{
        String insertProfileQuery = """
        INSERT INTO Profile (
            profileName,
            profileLastName,
            profileEmail,
            profilePassword)
        VALUES (?,?,?,?)
    """;

        try (Connection con = DriverManager.getConnection(db_url, db_username, db_password)) {
            PreparedStatement profileStatement = con.prepareStatement(insertProfileQuery);
            profileStatement.setString(1, profile.getProfileName());
            profileStatement.setString(2, profile.getProfileLastName());
            profileStatement.setString(3, profile.getProfileEmail());
            profileStatement.setString(4, profile.getProfilePassword());
            profileStatement.executeUpdate();

        } catch (SQLException e) {
            //e.printStackTrace();
            throw new ProfileException("Something wrong with addProfile()"); //TODO ret error message så det passer til users behov
        }
    }

    //***DELETE PROFILE***------------------------------------------------------------------------------------------------
    public void deleteProfile(Profile profile) throws ProfileException{
        String deleteQuery = "DELETE FROM Profile WHERE profileId = ?";

        try (Connection con = DriverManager.getConnection(db_url, db_username, db_password)) {
            PreparedStatement prepstmt = con.prepareStatement(deleteQuery);
            prepstmt.setInt(1, profile.getProfileId());  // Assuming profile has a getProfileId() method
            prepstmt.executeUpdate();

        } catch (SQLException e) {
            //e.printStackTrace();
            throw new ProfileException("Something wrong with deleteProfile()"); //TODO ret error message så det passer til users behov
        }
    }

    //***UPDATE***------------------------------------------------------------------------------------------------------
    public void updateProfile(Profile profile) throws ProfileException{
    String updateQuery = """
        UPDATE Profile
        SET profileName = ?, profileLastName = ?, profileEmail = ?, profilePassword = ?
        WHERE profileId = ?
    """;

    try (Connection con = DriverManager.getConnection(db_url, db_username, db_password)) {
            PreparedStatement profileStatement = con.prepareStatement(updateQuery);
            profileStatement.setString(1, profile.getProfileName());
            profileStatement.setString(2, profile.getProfileLastName());
            profileStatement.setString(3, profile.getProfileEmail());
            profileStatement.setString(4, profile.getProfilePassword());

            //profilId
            profileStatement.setInt(5, profile.getProfileId());

            profileStatement.executeUpdate();

        } catch (SQLException e) {
            //e.printStackTrace();
        throw new ProfileException("Something wrong with updateProfile()"); //TODO ret error message så det passer til users behov
        }
    }

    //***END***---------------------------------------------------------------------------------------------------------
}