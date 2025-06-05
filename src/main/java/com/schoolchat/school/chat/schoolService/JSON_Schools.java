package com.schoolchat.school.chat.schoolService;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSON_Schools {

	public String JSON_FILE = "../School-Chat/src/main/resources/all_shools_list/bayern.json";

	private List<JSONObject> schoolObjects = new ArrayList<>();

	public JSON_Schools(String selectedRegion) {
		this.setJSON_FILE(selectedRegion);
	}

	public JSON_Schools() {
		// this.JSON_FILE = "../School-Chat/src/main/resources/bayern.json";
		this.JSON_FILE = this.JSON_FILE;
		
	}

	public List<JSONObject> JSON_Schools() {

		JSONParser jsonParser = new JSONParser();

		try (FileReader reader = new FileReader(JSON_FILE)) {

			Object obj = jsonParser.parse(reader);

			JSONArray schoolList = (JSONArray) obj;

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

	public List<JSONObject> getJSON_List() {
		//System.out.println("List: " + this.JSON_Schools());
		return this.JSON_Schools();
	}



	public String getJSON_FILE() {
		return this.JSON_FILE;
	}

	// public List<JSONObject> getSchoolObjects() {
	// return JSON_Schools.JSON_Schools();
	// }

	public void setJSON_FILE(String region) {
		this.JSON_FILE = "../School-Chat/src/main/resources/all_shools_list/" + region + ".json";
		System.out.println("JSON_FILE: " + this.JSON_FILE);
	}

	public void setJSON_FILEValue(String JSON_FILE) {
		this.JSON_FILE = JSON_FILE;
	}


	
}
