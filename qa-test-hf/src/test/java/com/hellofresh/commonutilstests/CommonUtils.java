package com.hellofresh.commonutilstests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hellofresh.authenticationtests.NewUser;
import com.hellofresh.ordercheckout.DressDetails;

/* ================================================================= 
 * This is utility class to read application properties & test data.
 * ================================================================= */

public class CommonUtils {
	private static final Logger logger = LoggerFactory.getLogger(CommonUtils.class);

	static Properties props = new Properties();

	/*
	 * ============================================================ Method to read
	 * properties present in application.properties.
	 * ============================================================
	 */
	public static Properties loadProperties() {

		InputStream is = CommonUtils.class.getClassLoader().getResourceAsStream("application.properties");
		try {
			props.load(is);
		} catch (IOException e) {
			logger.error("Error loading application.properties file" + e);
			return null;
		}
		return props;
	}

	/*
	 * ============================================================ Method to read
	 * browser type property present in application.properties.
	 * ============================================================
	 */
	public static String getBrowserType() {
		Properties prop = loadProperties();
		String browserType = prop.get("browserType").toString();
		return browserType;
	}

	/*
	 * =============================================================== 
	 * Method to read new user test data present in NewUser.json file. 
	 * Input : NewUser.json file path 
	 * =============================================================== */
	public static List<NewUser> readNewUserDataFromJson(String testDataFile) {
		JSONParser jparser = new JSONParser();
		List<NewUser> newUserList = new ArrayList<>();
		try {
			JSONObject jObject = (JSONObject) jparser.parse(new FileReader(new File(testDataFile)));

			JSONArray jArray = (JSONArray) jObject.get("newusers");
			System.out.println("Array : "+jArray);
			Iterator itr = jArray.iterator();
			while (itr.hasNext()) {
				JSONObject jobj = (JSONObject) itr.next();
				NewUser newUser = new NewUser();
				newUser.setFirstName(jobj.get("firstName").toString());
				newUser.setLastName(jobj.get("lastName").toString());
				newUser.setPassword(jobj.get("password").toString());
				newUser.setBirthDate(jobj.get("birthDate").toString());
				newUser.setCompany(jobj.get("company").toString());
				newUser.setAddress1(jobj.get("address1").toString());
				newUser.setAddress2(jobj.get("address2").toString());
				newUser.setCity(jobj.get("city").toString());
				newUser.setState(jobj.get("state").toString());
				newUser.setPostalCode(jobj.get("postalCode").toString());
				newUser.setPhone(jobj.get("phone").toString());
				newUser.setMobilePhone(jobj.get("phoneMobile").toString());
				newUser.setOtherInfo(jobj.get("otherInfo").toString());
				newUser.setAlias(jobj.get("alias").toString());
				newUser.setEmail(jobj.get("email").toString());

				newUserList.add(newUser);
			}
		} catch (FileNotFoundException e) {
			logger.error("Test data file not found" + e);
		} catch (IOException e) {
			logger.error("Error occurred while reading test data file" + e);
		} catch (ParseException e) {
			logger.error("Unable to parse test data file" + e);
		}

		return newUserList;
	}

	/*
	@SuppressWarnings("unchecked")
	public static void saveEmailInJson(String email, String firstName, String lastName) {
		JSONParser jparser = new JSONParser();
		try {
			JSONObject jObject = (JSONObject) jparser.parse(new FileReader(new File(Constants.NEWUSERFILE)));

			JSONArray jArray = (JSONArray) jObject.get("newusers");
			Iterator itr = jArray.iterator();
			JSONObject jobj = null;
			while (itr.hasNext()) {
				jobj = (JSONObject) itr.next();
				if (jobj.get("firstName").equals(firstName) && jobj.get("lastName").equals(lastName)) {
					jobj.put("email", email);
				}
				jArray.add(jobj);
			}

			try (FileWriter file = new FileWriter(Constants.NEWUSERFILE)) {
				file.write(jArray.toJSONString());
				logger.info("Successfully copied a string in json...");
				// System.out.println("\nJSON Object: " + jObject);
			}
		} catch (FileNotFoundException e) {
			logger.error("Test data file not found" + e);
		} catch (IOException e) {
			logger.error("Error occurred while reading test data file" + e);
		} catch (ParseException e) {
			logger.error("Unable to parse test data file" + e);
		}

	}
    */
	
	/* =======================================================================
	 * This is utility method to read dress related test data from json file &
	 * store in DressDetails POJO.
	 * Input: json file path & id 
	 * =======================================================================*/
	public static void readDressDetailsFromJson(String testDataFile, String id) {
		JSONParser parser = new JSONParser();
		DressDetails dress = new DressDetails();
		try {
			JSONObject jObject = (JSONObject) parser.parse(new FileReader(new File(testDataFile)));

			// System.out.println(jObject.get("DressDetails").toString());
			JSONArray jArray = (JSONArray) jObject.get("DressDetails");
			Iterator itr = jArray.iterator();
			while (itr.hasNext()) {
				JSONObject jobj = (JSONObject) itr.next();
				if (jobj.get("id").equals(id)) {
					dress.setId(jobj.get("id").toString());
					dress.setDressName(jobj.get("dressName").toString());
					dress.setColor(jobj.get("color").toString().split(","));
					dress.setSize(jobj.get("size").toString().split(","));
				}
			}
			System.out.println("Dress: " + dress);
		} catch (FileNotFoundException e) {
			logger.error("Test data file not found" + e);
		} catch (IOException e) {
			logger.error("Error occurred while reading test data file" + e);
		} catch (ParseException e) {
			logger.error("Unable to parse test data file" + e);
		}
	}

	public static void main(String[] args) {
		readDressDetailsFromJson("src/test/resources/DressDetails.json", "001");
	}
}
