package com.inventario.api;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.inventario.converter.EquipmentMapper;
import com.inventario.domain.Equipment;
import com.inventario.service.IInventarioService;

@RestController
public class InventarioApiController implements EquipmentsApi{
	
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
	public ResponseEntity<com.inventario.api.model.Equipment> getEquipment(Integer idEquipment) {
		
		com.inventario.api.model.Equipment result = eqpmentMapper.modelMapperEquipment().map(inventarioService.findByCodeEquipment(idEquipment), com.inventario.api.model.Equipment.class);
		return new ResponseEntity<com.inventario.api.model.Equipment>(result, HttpStatus.OK);
	}
	
	@Override
	@RequestMapping(value = "/equipments", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.GET)
	public ResponseEntity<List<com.inventario.api.model.Equipment>> listEquipments() {
		
		List<com.inventario.api.model.Equipment> result = new ArrayList<com.inventario.api.model.Equipment>();
		List<Equipment> listReturn = inventarioService.findAllEquipment();
		
		for (Equipment equipment : listReturn) {
			result.add(eqpmentMapper.modelMapperEquipment().map(equipment, com.inventario.api.model.Equipment.class));
		}
		return new ResponseEntity<List<com.inventario.api.model.Equipment>>(result, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> removerEquipment(Integer idEquipment) {
		inventarioService.deleteEquipment(idEquipment);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	
	
}
