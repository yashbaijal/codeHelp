package com.RestAPI.Pojos.response;

import java.io.Serializable;

public class Results  implements Serializable {

    private String gender;
    private Name  name;
    private Location location;
    private String email;
    private Login login;
    private DOB dob;
    private Registered registered;
    private String phone;
    private String cell;
    private ID id;
    private Picture picture;
    private String nat;

    public Results() {
    }

    public String getNAT() {
        return nat;
    }

    public void setNAT(String nat) {
        this.nat = nat;
    }
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public DOB getDob() {
        return dob;
    }

    public void setDob(DOB dob) {
        this.dob = dob;
    }

    public Registered getRegistered() {
        return registered;
    }

    public void setRegistered(Registered registered) {
        this.registered = registered;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }
}
