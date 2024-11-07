package kea.momo.wishlist.model;

public class Profile {


    //***ATTRIBUTES***--------------------------------------------------------------------------------------------------
    private int profileId; // todo autoincrement
    private String profileName;
    private String profileLastName;
    private String profileEmail;
    private String profilePassword;

    //***USER***--------------------------------------------------------------------------------------------------------
    public Profile(String profileName, String profileLastName, String profileEmail, String profilePassword, int profileId) {
        this.profileName = profileName;
        this.profileLastName = profileLastName;
        this.profileEmail = profileEmail;
        this.profilePassword = profilePassword;
        this.profileId = profileId;

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

    public int getProfileId() {
        return profileId;
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


    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    //***END***---------------------------------------------------------------------------------------------------------
}
