package com.schoolchat.school.chat.Schools;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.nio.charset.StandardCharsets;

public class JSON_Schools {

    public static final String JSON_FILE="../School-Chat/src/main/resources/bayern.json";
    private List<JSONObject> schoolObjects = new ArrayList<>();


    public List<JSONObject> JSON_Schools(){

        JSONParser jsonParser=new JSONParser();


        try(FileReader reader =new FileReader(JSON_FILE)){

            Object obj = jsonParser.parse(reader);

            JSONArray schoolList  = (JSONArray) obj;


            schoolList.forEach(sch -> {
                JSONObject schoolObject = (JSONObject) sch;
                schoolObjects.add(schoolObject);
            });



        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return schoolObjects;
    }


}
