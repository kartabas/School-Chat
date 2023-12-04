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

    public String getSchoolTypeEntity() {
        return schoolTypeEntity;
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
    public String toString(){
        StringBuilder sb = new StringBuilder();


        sb.append("\n***** Schools Details *****\n");
        sb.append("ID= "+getId()+"\n");
        sb.append("Official_id= " +getOfficial_id()+"\n");
        sb.append("Name= "+getName()+"\n");
        sb.append("Phone Numbers= " + getPhone()+"\n");
        sb.append("Address= "+getAddress());
        sb.append("\n*****************************\n");



        return sb.toString();

}



}
