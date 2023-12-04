package com.schoolchat.school.chat.Schools;

import org.json.simple.JSONObject;

import java.util.*;

public class SchoolSearch {
    JSON_Schools json_schools =new JSON_Schools();
    List<JSONObject> schools = json_schools.JSON_Schools();




  public List<JSONObject> getAllSchools(){
      return schools;
  }

    //Шукає ім'я шкіл по веденому name і видає name
    public List<String> getAllSchoolsByName(String searchName) {
        List<String> schoolNames = new ArrayList<>();

        for (JSONObject school : schools) {
            String name = (String) school.get("name");
            if (name != null && name.contains(searchName)) {
                schoolNames.add(name);
            }
        }

        return schoolNames;
    }

    //Шукає по веденому name  і зберігає всі дані до цього ім'я і видає об'єкт
    public List<SchoolModel> getAllSchoolsByNameObjeckt(String searchName) {
        List<SchoolModel> schoolList = new ArrayList<>();

        for (JSONObject school : schools) {
            //Добавляю влястивості до класу SchoolModel
            String name = (String) school.get("name");
            String state = (String) school.get("state");
            String id = (String) school.get("id");

            if (name != null && name.contains(searchName)) {
                SchoolModel newSchool = new SchoolModel();

                //Добавляю влястивості до класу SchoolModel з кожного знайденого ім'я
                newSchool.setName(name);
                newSchool.setState(state);
                newSchool.setId(id);

                schoolList.add(newSchool);
            }
        }

        return schoolList;
    }



}
