package com.rest.api.example;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;

import org.json.simple.JSONObject;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
/* ======================================================
 * Class contains tests to validate rest APIs.
 * ======================================================*/

public class RestApiTests {

	@BeforeTest
	public void setup() {
		// Initialize 
		RestAssured.baseURI = "http://services.groupkt.com/country/get";
		
	}
	
	/* ====================================================================================
	 * Test : validateSpecificCountries() 
	 * Description : Get all countries and validate that US, DE and GB were returned in the 
	 * response.
	 * ====================================================================================*/
	
	@Test
	public void validateSpecificCountries() {
		RestAssured.given()
				   .get("/all")
				   .then()
				   .statusCode(200)
				   .body("RestResponse.result.alpha2_code", hasItems("US", "DE", "GB"));
	}

	/* =====================================================================================
	 * Test : validateEachCountry() 
	 * Description : Get each country (US, DE and GB) individually and validate the response
	 * =====================================================================================*/
	
	@Test
	public void validateEachCountry() {
		String[] country = { "US", "DE", "GB" };

		for (int cnt = 0; cnt < country.length; cnt++) {
			RestAssured.given()
					   .get("/iso2code/" + country[cnt])
					   .then()
					   .statusCode(200)
					   .body("" + "RestResponse.result.alpha2_code", equalTo(country[cnt]));
		}
	}

	/* ====================================================================================
	 * Test : validateIncorrectCountries() 
	 * Description : Get information for inexistent countries and validate the response
	 * ====================================================================================*/
	@Test
	public void validateIncorrectCountries() {
		String[] incorrectCountries = { "XX", "ZZ" };
		for (int cnt = 0; cnt < incorrectCountries.length; cnt++) {
			RestAssured.given()
					   .get("/iso2code/" + incorrectCountries[cnt])
					   .then()
					   .statusCode(200).body("RestResponse.messages[0]", equalTo("No matching country found for requested code [" + incorrectCountries[cnt] + "]."));
		}
	}
	
	/* =======================================================================================
	 * Test : validateNewCountryAdd()
	 * Description : Post request to add new country
	 * =======================================================================================*/
 
	@Test(enabled=false)
	public void validateNewCountryAdd() {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("name", "Test Country");
		jsonObj.put("alpha2_code", "TC");
		jsonObj.put("alpha3_code", "TCY");

		RestAssured.given()
				   .contentType("application/json")
				   .body(jsonObj.toJSONString())
				   .post("http://services.groupkt.com/country/add")
				   .then()
				   .statusCode(201)
				   .and()
				   .body("RestResponse.messages[0]", equalTo("A new country added."));

	}  
	
	@AfterTest
	public void clean() {
		// Close any handles, connections etc.
	}

}
