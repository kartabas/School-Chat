package com.schoolchat.school.chat.Schools;


public class SchoolModel {

    private String official_id;
    private String id;
    private String name;
    private String schoolType;
    private String schoolTypeEntity;
    private String address ;
    private Boolean fullTimeSchool;
    private String state;
    private String phone;
    private  String fax;
    private double latitude;
    private double longitude;



    public SchoolModel() {
        // Default constructor
    }

    public SchoolModel(String officialId, String id, String name, String schoolType, String address,
                  Boolean fullTimeSchool, String state, String phone, String fax,
                  double latitude, double longitude) {

        this.official_id = officialId;
        this.id = id;
        this.name = name;
        this.schoolType = schoolType;
        this.address = address;
        this.fullTimeSchool = fullTimeSchool;
        this.state = state;
        this.phone = phone;
        this.fax = fax;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    public String getOfficial_id() {
        return official_id;
    }

    public void setOfficial_id(String official_id) {
        this.official_id = official_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchoolType() {
        return schoolType;
    }

    public void setSchoolType(String schoolType) {
        this.schoolType = schoolType;
    }

    public String getSchoolTypeEntity(String schoolTypeEntity) {
        return this.schoolTypeEntity;
    }

    public void setSchoolTypeEntity(String schoolTypeEntity) {
        this.schoolTypeEntity = schoolTypeEntity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getFullTimeSchool() {
        return fullTimeSchool;
    }

    public void setFullTimeSchool(Boolean fullTimeSchool) {
        this.fullTimeSchool = fullTimeSchool;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }



@Override

public String toString() {
    return "School{" +
            "officialId='" + official_id + '\'' +
            ", id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", schoolType='" + schoolType + '\'' +
            ", address='" + address + '\'' +
            ", fullTimeSchool='" + fullTimeSchool + '\'' +
            ", state='" + state + '\'' +
            ", phone='" + phone + '\'' +
            ", fax='" + fax + '\'' +
            ", latitude=" + latitude +
            ", longitude=" + longitude +
            '}';
}



}
