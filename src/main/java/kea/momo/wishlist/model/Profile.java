package kea.momo.wishlist.model;

import org.springframework.stereotype.Component;

import java.util.List;

@Component // TODO skal model klasserne have annotationer?
public class Profile {

    //***ATTRIBUTES***--------------------------------------------------------------------------------------------------
    private String profileName;
    private String profileLastName;
    private String profileEmail;
    private String profilePassword;
    private int profileId;

    //***CONSTRUCTOR***-------------------------------------------------------------------------------------------------
    public Profile(String profileName, String profileLastName, String profileEmail, String profilePassword, int profileId) {
        this.profileName = profileName;
        this.profileLastName = profileLastName;
        this.profileEmail = profileEmail;
        this.profilePassword = profilePassword;
        this.profileId = profileId;
    }

    public Profile(String profileName, String profileLastName, String profileEmail, String profilePassword){
        this.profileName = profileName;
        this.profileLastName = profileLastName;
        this.profileEmail = profileEmail;
        this.profilePassword = profilePassword;
    }

    public Profile(String profileEmail, String profilePassword){
        this.profileEmail = profileEmail;
        this.profilePassword = profilePassword;
    }

    public Profile(){
    }

    //***GETTER METHODS***----------------------------------------------------------------------------------------------
    public String getProfileName() {
        return profileName;
    }

    public String getProfileLastName() {
        return profileLastName;
    }

    public String getProfileEmail() {
        return profileEmail;
    }

    public String getProfilePassword() {
        return profilePassword;
    }

    public int getProfileId() {
        return profileId;
    }

    //***SETTER METHODS***----------------------------------------------------------------------------------------------
    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public void setProfileLastName(String userFamilyName) {
        this.profileLastName = userFamilyName;
    }

    public void setProfileEmail(String profileEmail) {
        this.profileEmail = profileEmail;
    }

    public void setProfilePassword(String profilePassword) {
        this.profilePassword = profilePassword;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    //***TO STRING METHOD***--------------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return  "\nProfile name: "     + profileName     +
                "\nProfile lastname: " + profileLastName +
                "\nProfile password: " + profilePassword +
                "\nProfile id: "       + profileId       ;
    }

    //***END***---------------------------------------------------------------------------------------------------------
}
