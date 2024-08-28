package com.schoolchat.school.chat.repository;

import com.schoolchat.school.chat.model.SchoolModel;
import com.schoolchat.school.chat.schoolService.JSON_Schools;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;

import java.util.*;

public class SchoolSearch {
	JSON_Schools json_schools = new JSON_Schools();
	List<JSONObject> schools = json_schools.JSON_Schools();

	public List<JSONObject> getAllSchools() {
		return schools;
	}

	// Шукає ім'я шкіл по веденому name і видає name
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

	public List<SchoolModel> getAllSchoolsByOfficialId(String inputID) {
		List<SchoolModel> schoolList = new ArrayList<>();

		if (inputID != "") {
			for (JSONObject school : schools) {
				// Добавляю влястивості до класу SchoolModel
				String official_id = (String) school.get("official_id");
				String id = (String) school.get("id");

				String name = (String) school.get("name");
				

				String schoolType = (String) school.get("schoolType");
				String schoolTypeEntity = (String) school.get("schoolTypeEntity");
				String address = (String) school.get("address");
				Boolean fullTimeSchool = (Boolean) school.get("fullTimeSchool");
				String state = (String) school.get("state");
				String phone = (String) school.get("phone");
				String fax = (String) school.get("fax");

				if (((id != null) && id.contains(inputID))) {
					SchoolModel newSchool = new SchoolModel();

					// Добавляю влястивості до класу SchoolModel з кожного знайденого ім'я
					newSchool.setOfficial_id(official_id);
					newSchool.setId(id);
					newSchool.setName(name);
					newSchool.setSchoolType(schoolType);
					newSchool.setSchoolTypeEntity(schoolTypeEntity);
					newSchool.setAddress(address);
					newSchool.setFullTimeSchool(fullTimeSchool);
					newSchool.setState(state);
					newSchool.setPhone(phone);
					newSchool.setFax(fax);
					
					schoolList.add(newSchool);
				}
			}

			return schoolList;

		} else {
			return null;

		}

	}

	// Шукає по веденому name і зберігає всі дані до цього ім'я і видає об'єкт
	public List<SchoolModel> getAllSchoolsByNameObjeckt(String searchName) {
		List<SchoolModel> schoolList = new ArrayList<>();

		if (searchName != "") {
			for (JSONObject school : schools) {
				// Добавляю влястивості до класу SchoolModel
				String official_id = (String) school.get("official_id");
				String id = (String) school.get("id");
				String name = (String) school.get("name");
				String schoolType = (String) school.get("schoolType");
				String schoolTypeEntity = (String) school.get("schoolTypeEntity");
				String address = (String) school.get("address");
				Boolean fullTimeSchool = (Boolean) school.get("fullTimeSchool");
				String state = (String) school.get("state");
				String phone = (String) school.get("phone");
				String fax = (String) school.get("fax");

				if ((name != null && name.contains(searchName)) || (address != null && address.contains(searchName))) {
					SchoolModel newSchool = new SchoolModel();

					// Добавляю влястивості до класу SchoolModel з кожного знайденого ім'я
					newSchool.setOfficial_id(official_id);
					newSchool.setId(id);
					newSchool.setName(name);
					newSchool.setSchoolType(schoolType);
					newSchool.setSchoolTypeEntity(schoolTypeEntity);
					newSchool.setAddress(address);
					newSchool.setFullTimeSchool(fullTimeSchool);
					newSchool.setState(state);
					newSchool.setPhone(phone);
					newSchool.setFax(fax);
					System.out.println(name);
					schoolList.add(newSchool);
				}
			}

			return schoolList;

		} else {
			return null;
		}

	}

}
