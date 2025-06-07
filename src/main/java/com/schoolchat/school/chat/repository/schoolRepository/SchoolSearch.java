package com.schoolchat.school.chat.repository.schoolRepository;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import com.schoolchat.school.chat.model.schoolModels.SchoolModel;
import com.schoolchat.school.chat.schoolService.JSON_Schools;

public class SchoolSearch {
	//TODO зробити так що при пошуку школи для входу в HomeController буде шукати потрібну школу з регіону 
	JSON_Schools json_schools = new JSON_Schools();
	List<JSONObject> schools = json_schools.JSON_Schools();

	public SchoolSearch() {

	}

	public SchoolSearch(List<JSONObject> schools) {
		this.schools = schools;
		//System.out.println("Search School region: " + this.json_schools.getJSON_FILE());
	}
	

	

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

		if (inputID != "" && inputID.contains("BY") || inputID.contains("BE") || inputID.contains("BW") ||
				inputID.contains("HH") || inputID.contains("BB") || inputID.contains("HB") || inputID.contains("NI") ||
				inputID.contains("HE") || inputID.contains("MV") || inputID.contains("NRW") || inputID.contains("RP") ||
				inputID.contains("SL") || inputID.contains("ST") || inputID.contains("SN") || inputID.contains("SH")
				|| inputID.contains("TH")) {
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
					// System.out.println(name);
					schoolList.add(newSchool);
				}
			}

			return schoolList;

		} else {
			return null;
		}

	}

}
