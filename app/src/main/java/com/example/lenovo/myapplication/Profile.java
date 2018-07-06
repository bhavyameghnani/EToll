package com.example.lenovo.myapplication;

public class Profile {
    String profileId, profileName , profileAddress , profileLicense , profilePhone;

    public Profile(){

    }

    public Profile(String UID , String fullName , String address , String license , String cell){
        profileId = UID;
        profileName = fullName ;
        profileAddress = address;
        profileLicense = license;
        profilePhone = cell;
    }

    public String getProfileId() {
        return profileId;
    }

    public String getProfileName() {
        return profileName;
    }

    public String getProfileAddress() {
        return profileAddress;
    }

    public String getProfileLicense() {
        return profileLicense;
    }

    public String getProfilePhone() {
        return profilePhone;
    }
}
