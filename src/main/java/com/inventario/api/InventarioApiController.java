package com.inventario.api;

import java.math.BigDecimal;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.inventario.api.model.EquipmentFinal;
import com.inventario.converter.EquipmentMapper;
import com.inventario.domain.Equipment;
import com.inventario.service.IInventarioService;

@RestController
public class InventarioApiController implements EquipmentsApi{
	
	@Value("${percentege.over.value}")
	private int VAL_EQUIPMENT_WITH_PERCENT;
	
	@Inject
	private IInventarioService inventarioService;
	
	@Inject
	private EquipmentMapper eqpmentMapper;

	@RequestMapping(value = "/hello", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.GET)
	public String helloWorld() {
		return "Hello world";
	}

	@Override
	@RequestMapping(value = "/equipments", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public ResponseEntity<com.inventario.api.model.Equipment> createEquipment(
			com.inventario.api.model.@Valid Equipment body) {
		
		final Equipment newEquipment = inventarioService.createEquipment(
				eqpmentMapper.modelMapperEquipment().map(body, Equipment.class));
		
		final com.inventario.api.model.Equipment equipmentResponse = eqpmentMapper.modelMapperEquipment().map(newEquipment, com.inventario.api.model.Equipment.class);
		return new ResponseEntity<com.inventario.api.model.Equipment>(equipmentResponse, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<com.inventario.api.model.Equipment> updateEquipment(String idEquipment,
			com.inventario.api.model.@Valid Equipment body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@RequestMapping(value = "/equipments/{idEquipment}", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.GET)
	public ResponseEntity<com.inventario.api.model.EquipmentFinal> getEquipment(Integer idEquipment) {
		
		com.inventario.api.model.EquipmentFinal result = eqpmentMapper.modelMapperEquipment().map(inventarioService.findByCodeEquipment(idEquipment), 
				com.inventario.api.model.EquipmentFinal.class);

		
		
		
		java.time.LocalDate today = java.time.LocalDate.now();
	//	int meses = today.getMonthValue() - result.getDtEquipment().getMonthOfYear();
		
		
		
		
		int monthsBetween = Period.between(
				java.time.LocalDate.of(result.getDtEquipment().getYear(), result.getDtEquipment().getMonthOfYear(), result.getDtEquipment().getDayOfMonth()).withDayOfMonth(1),
				today.withDayOfMonth(1)).getMonths();
				
		 
		double percent = result.getValEquipment().doubleValue();
		for(int i = monthsBetween; i > 0; i--) {
			percent -= percent * VAL_EQUIPMENT_WITH_PERCENT/100;
		}
		result.setValEquipmentWithPercent(BigDecimal.valueOf(percent));
		
		return new ResponseEntity<com.inventario.api.model.EquipmentFinal>(result, HttpStatus.OK);
	}
	
	@Override
	@RequestMapping(value = "/equipments", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.GET)
	public ResponseEntity<List<com.inventario.api.model.EquipmentFinal>> listEquipments() {
		
		List<com.inventario.api.model.EquipmentFinal> result = new ArrayList<com.inventario.api.model.EquipmentFinal>();
		List<Equipment> listReturn = inventarioService.findAllEquipment();
		
		for (Equipment equipment : listReturn) {
			EquipmentFinal eqpFinal = eqpmentMapper.modelMapperEquipment().map(equipment, com.inventario.api.model.EquipmentFinal.class);
			
			result.add(eqpFinal);
		}
		return new ResponseEntity<List<com.inventario.api.model.EquipmentFinal>>(result, HttpStatus.OK);
	}

	@Override
	@RequestMapping(value = "/equipments/{idEquipment}", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.DELETE)
	public ResponseEntity<Void> removerEquipment(Integer idEquipment) {
		inventarioService.deleteEquipment(idEquipment);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	
	
}
