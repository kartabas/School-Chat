package com.schoolchat.school.chat.model;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.scheduling.annotation.Scheduled;

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
    private Boolean fullTimeSchool;
    private double latitude;
    private double longitude;


    public Boolean getFullTimeSchool() {
        return fullTimeSchool;
    }

    public void setFullTimeSchool(Boolean fullTimeSchool) {
        this.fullTimeSchool = fullTimeSchool;
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
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(currentSchool);

            this.official_id = jsonNode.get("official_id").asText();
            this.id = jsonNode.get("id").asText();
            this.name = jsonNode.get("name").asText();
            this.schoolType = jsonNode.get("schoolType").asText();
            this.address = jsonNode.get("address").asText();
            this.state = jsonNode.get("state").asText();
            this.phone = jsonNode.get("phone").asText();
            this.fax = jsonNode.get("fax").asText();
            this.fullTimeSchool = Boolean.valueOf(jsonNode.get("fullTimeSchool").asText());
            this.latitude = Double.parseDouble(jsonNode.get("latitude").asText());;
            this.longitude= Double.parseDouble(jsonNode.get("longitude").asText());;



        } catch (Exception e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

    }




    @Override
    public String toString() {
        return "UserCurrentSchoolModel{" +
                "officialId=" + official_id +
                ", id=" + id  +
                ", name=" + name +
                ", schoolType=" + schoolType +
                ", address=" + address +
                ", state=" + state +
                ", phone=" + phone +
                ", fax=" + fax +"}";
    }



}
