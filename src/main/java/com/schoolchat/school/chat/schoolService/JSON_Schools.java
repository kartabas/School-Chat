package com.schoolchat.school.chat.schoolService;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.core.io.ClassPathResource;

public class JSON_Schools {

	private String jsonFileName = "bayern";
	private List<JSONObject> schoolObjects = new ArrayList<>();

	public JSON_Schools(String selectedRegion) {
		this.setJSON_FILE(selectedRegion);
	}

	public JSON_Schools() {
		// default "bayern"
	}

	public List<JSONObject> JSON_Schools() {

		JSONParser jsonParser = new JSONParser();

		try {
			// Load JSON file from classpath inside JAR
			ClassPathResource resource = new ClassPathResource(
					"all_shools_list/" + jsonFileName + ".json");

			InputStream inputStream = resource.getInputStream();
			InputStreamReader reader = new InputStreamReader(inputStream);

			Object obj = jsonParser.parse(reader);
			JSONArray schoolList = (JSONArray) obj;

			schoolList.forEach(sch -> {
				JSONObject schoolObject = (JSONObject) sch;
				schoolObjects.add(schoolObject);
			});

		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}

		return schoolObjects;
	}

	public List<JSONObject> getJSON_List() {
		return this.JSON_Schools();
	}

	public String getJSON_FILE() {
		return this.jsonFileName;
	}

	public void setJSON_FILE(String region) {
		this.jsonFileName = region;
		System.out.println("JSON_FILE (region): " + this.jsonFileName);
	}

	public void setJSON_FILEValue(String JSON_FILE) {
		this.jsonFileName = JSON_FILE;
	}
}
