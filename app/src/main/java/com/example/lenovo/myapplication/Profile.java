package com.example.lenovo.myapplication;

public class Profile {
    String ID, fullname , address , license , cell;

    public Profile(){

    }

    public Profile(String ID , String fullname , String address , String license , String cell){
        this.ID = ID;
        this.fullname = fullname ;
        this.address = address;
        this.license = license;
        this.cell = cell;
    }

    public String getID() {
        return ID;
    }

    public String getFullname() {
        return fullname;
    }

    public String getAddress() {
        return address;
    }

    public String getLicense() {
        return license;
    }

    public String getCell() {
        return cell;
    }
}
