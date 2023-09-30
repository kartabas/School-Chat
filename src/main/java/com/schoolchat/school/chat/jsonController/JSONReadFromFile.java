package com.schoolchat.school.chat.jsonController;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JSONReadFromFile {


    public static void main(String[] args){
        JSONParser jsonParser=new JSONParser();
        try(FileReader reader =new FileReader("/Hard-Project/School-Chat/src/main/resources/bayern.json")){
            Object obj = jsonParser.parse(reader);
            JSONArray schoolList  = (JSONArray) obj;
            //System.out.println(schoolList);
            schoolList.forEach(sch -> parseSchoolObj((JSONObject) sch));

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }
    private  static  void parseSchoolObj(JSONObject sch){
        JSONObject schoolObj =(JSONObject) sch;




        String officialId = (String) schoolObj.get("official_id");
        String address = (String) schoolObj.get("address");
        String schoolType = (String) schoolObj.get("school_type");
        String state = (String) schoolObj.get("state");


        if( "684".equals(officialId)){

            System.out.println("ID: " + officialId);
            System.out.println("Address: " + address);

            System.out.println("School type: " + schoolType);
            System.out.println("State: " + state);
        }




    }

}
