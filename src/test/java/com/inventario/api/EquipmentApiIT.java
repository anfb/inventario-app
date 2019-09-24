package com.inventario.api;

import org.hamcrest.Matchers;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/equipment.feature", plugin = { "pretty", "json:src/test/resources/jsons/equipment.json",
        "html:target/reports/equipments.html" })
public class EquipmentApiIT {
	
	@Autowired
	static RequestSpecBuilder reqBuilder;
	
	@Autowired
	ResponseSpecBuilder resBuilder;
	
	@BeforeClass
	public static void setup() {
			RestAssured.port = 8090;
			
			reqBuilder = new RequestSpecBuilder();
			reqBuilder.setContentType(ContentType.JSON);
			RestAssured.requestSpecification = reqBuilder.build();
			
			ResponseSpecBuilder resBuilder = new ResponseSpecBuilder();
			resBuilder.expectResponseTime(Matchers.lessThan(2000000L));
			RestAssured.responseSpecification = resBuilder.build();
			
			RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
			
	}
	
	@AfterClass
	public static void finalTest() {
		RestAssured.reset();
	}
	
}
