package com.mybeats.fixkoro.Model;

/**
 * Created by MUSABBIR on 27-Sep-17.
 */

public class TechListModel {

    public String getId_technician() {
        return id_technician;
    }

    public void setId_technician(String id_technician) {
        this.id_technician = id_technician;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWorking_area() {
        return working_area;
    }

    public void setWorking_area(String working_area) {
        this.working_area = working_area;
    }

    public String getSpecial_on() {
        return special_on;
    }

    public void setSpecial_on(String special_on) {
        this.special_on = special_on;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private String id_technician;
    private String name;
    private String email;
    private String phone;
    private String working_area;
    private String special_on;
    private String image;
    private String lattitude;

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLattitude() {
        return lattitude;
    }

    public void setLattitude(String lattitude) {
        this.lattitude = lattitude;
    }

    private String longitude;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    private String location;

}
