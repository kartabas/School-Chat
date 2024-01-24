package com.schoolchat.school.chat.model;


public class UserCurrentSchoolModel  {

    private String currentSchool;


    private String official_id;
    private String id;
    private String name;

    private String schoolType;
    private String address ;
    private String state;
    private String phone;
    private  String fax;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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




    public String getCurrentSchool() {
        return currentSchool;
    }

    public void setCurrentSchool(String currentSchool) {
        this.currentSchool = currentSchool;
    }

    public void setCurrentUserSchool(String currentSchool) {
        // Split the values using the appropriate delimiter (',' in this case)
        String[] schoolValues = currentSchool.split(",");

        // Set the properties of the UserCurrentSchoolModel based on the values
        this.official_id = schoolValues[0];
        this.id = schoolValues[1];
        this.name = schoolValues[2];
        this.schoolType = schoolValues[3];
        this.address = schoolValues[4];
        this.state = schoolValues[6];
        this.phone = schoolValues[7];
        this.fax = schoolValues[8];


    }

    @Override
    public String toString() {
        return "UserCurrentSchoolModel{" +
                "officialId='" + official_id + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", schoolType='" + schoolType + '\'' +
                ", address='" + address + '\'' +
                ", state='" + state + '\'' +
                ", phone='" + phone + '\'' +
                ", fax='" + fax + '\'';
    }



}
