package kea.momo.wishlist.util;

import kea.momo.wishlist.model.Profile;

import java.util.List;

public interface ProfileRSInterface {

    //***METHODS***-----------------------------------------------------------------------------------------------------
    //***CREATE PROFILE***---------------------------------------------------------------------------------------------C
    void addProfile(Profile profile) throws ProfileException;

    //***READ PROFILE(S)***--------------------------------------------------------------------------------------------R
    List<Profile> getAllProfiles() throws ProfileException;

    Profile getProfileByEmailAndPassword(String profileEmail, String profilePassword) throws ProfileException;

    Profile getProfileById(int profileId) throws ProfileException;

    //***UPDATE***-----------------------------------------------------------------------------------------------------U
    void updateProfile(Profile profile) throws ProfileException;

    //***DELETE PROFILE***---------------------------------------------------------------------------------------------D
    void deleteProfile(Profile profile) throws ProfileException;

    //***END***---------------------------------------------------------------------------------------------------------
}
