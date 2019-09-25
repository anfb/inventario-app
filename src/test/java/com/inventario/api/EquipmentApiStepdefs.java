package com.inventario.api;

import static org.hamcrest.Matchers.is;

import java.math.BigDecimal;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.springframework.boot.test.context.SpringBootTest;

import com.inventario.InventarioApp;
import com.inventario.api.model.Equipment;
import com.inventario.api.model.EquipmentFinal;

import cucumber.api.java8.En;
import io.restassured.RestAssured;
import io.restassured.response.Response;

@SpringBootTest(classes = InventarioApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EquipmentApiStepdefs implements En {

	private Response resp;
	private Equipment eqpResponse;
	private static Long codeEquipmentCache;

	public EquipmentApiStepdefs() {
		this.implementSteps();
	}

	private void implementSteps() {
		Given("^user has access to the /equipment API$", () -> {});

		When("^user calls GET equipment$", () -> {
			resp = RestAssured.when().get("/equipments");
		});

		Then("^user receives status code of (\\d+) and the list of equipments$", (Integer status) -> {
			resp.then().statusCode(200);
		});

		When("^user calls POST equipments with \"([^\"]*)\", (\\d+), \"([^\"]*)\", (\\d+), \"([^\"]*)\"$", (String modelEquipment, Integer valEquipment, String dtEquipment, Integer typeEquipment, String imageEquipment) -> {
			resp = RestAssured.given().body(this.createEquipment(modelEquipment, valEquipment, dtEquipment, typeEquipment, imageEquipment)).when().post("/equipments");
			eqpResponse = resp.body().as(Equipment.class);
			codeEquipmentCache = eqpResponse.getCodeEquipment();
		});

		Then("^user receives status code of (\\d+) after register equipment$", (Integer status) -> {
			resp.then().statusCode(status)
			.body("modelEquipment", is(eqpResponse.getModelEquipment()));
		});
		
		When("^user calls GET equipment with codeEquipment$", () -> {
			resp = RestAssured.when().get("/equipments/"+codeEquipmentCache.intValue());
		});

		Then("^user receives status code of (\\d+) and one equipment$", (Integer status) -> {
			resp.then().statusCode(status);
		});

		When("^user calls GET equipment withough codeEquipment$", () -> {
			EquipmentFinal eqp = new EquipmentFinal();
			resp = RestAssured.when().get("/equipments/" + eqp.getCodeEquipment());
		});

		Then("^user receives status code of (\\d+) after find equipment withough code$", (Integer status) -> {
			resp.then().statusCode(status);
		});
		
		When("^user calls GET equipment wrong codeEquipment (\\d+)t$", (Integer codeEquipment) -> {
			resp = RestAssured.when().get("/equipments/"+ codeEquipment);
		});

		Then("^user receives status code of (\\d+) after find wrong code equipment$", (Integer status) -> {
			resp.then().statusCode(status);
		});

		When("^user calls DELETE equipment$", () -> {
			resp = RestAssured.when().delete("/equipments/"+ codeEquipmentCache.intValue());
		});

		Then("^user receives status code of (\\d+) after delete equipment$", (Integer status) -> {
			resp.then().statusCode(status);
		});
		
		When("^user calls DELETE equipment withough codeEquipment$", () -> {
			resp = RestAssured.when().delete("/equipments/");
		});

		Then("^user receives status code of (\\d+) after delete equipment withough code$", (Integer status) -> {
			resp.then().statusCode(status);
		});

	}

	private Equipment createEquipment(String modelEquipment, Integer valEquipment, String dtEquipment, Integer typeEquipment, String imageEquipment) {
		Equipment equipment = new Equipment();
		LocalDate localdate = LocalDate.parse(dtEquipment, DateTimeFormat.forPattern("yyyy-MM-dd"));

		equipment.setDtEquipment(localdate);
		equipment.setModelEquipment(modelEquipment);
		equipment.setTypeEquipment(typeEquipment.longValue());
		equipment.setImageEquipment(imageEquipment);
		equipment.setValEquipment(new BigDecimal(valEquipment.intValue()));

		return equipment;
	}

}
