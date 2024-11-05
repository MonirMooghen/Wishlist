package kea.momo.wishlist.model;

public class Profile {

    //***ATTRIBUTES***--------------------------------------------------------------------------------------------------
    private String profileName;
    private String profileLastName;
    private String profileEmail;
    private String profilePassword;
    private int profileId; // todo autoincrement

    //***USER***--------------------------------------------------------------------------------------------------------
    public Profile(String profileName, String profileLastName, String profileEmail, String profilePassword) {
        this.profileName = profileName;
        this.profileLastName = profileLastName;
        this.profileEmail = profileEmail;
        this.profilePassword = profilePassword;
        // profileId =

    }

    public Profile(){
    }

    //***GETTER & SETTER***---------------------------------------------------------------------------------------------
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

    //***SETTER***------------------------------------------------------------------------------------------------------
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

    //***END***---------------------------------------------------------------------------------------------------------

}
