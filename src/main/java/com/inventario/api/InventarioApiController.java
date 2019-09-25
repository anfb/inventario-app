package com.inventario.api;

import java.math.BigDecimal;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

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
import com.inventario.util.Utils;

@RestController
public class InventarioApiController implements EquipmentsApi{

	@Value("${percentege.over.value}")
	private int VAL_EQUIPMENT_WITH_PERCENT;

	@Inject
	private IInventarioService inventarioService;

	@Inject
	private EquipmentMapper eqpmentMapper;

	@Override
	@RequestMapping(value = "/equipments", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public ResponseEntity<com.inventario.api.model.Equipment> createEquipment(
			com.inventario.api.model.@Valid Equipment body) {

		Equipment newEquipment = null;
		com.inventario.api.model.Equipment equipmentResponse = new com.inventario.api.model.Equipment();

		if (Utils.isNotNullAndEmpty(body)) {
			newEquipment = inventarioService.createEquipment(
					eqpmentMapper.modelMapperEquipment().map(body, Equipment.class));
			equipmentResponse = eqpmentMapper.modelMapperEquipment().map(newEquipment, com.inventario.api.model.Equipment.class);

			return new ResponseEntity<com.inventario.api.model.Equipment>(equipmentResponse, HttpStatus.CREATED);
		}
		
		return new ResponseEntity<com.inventario.api.model.Equipment>(equipmentResponse, HttpStatus.BAD_REQUEST);

	}

	@Override
	@RequestMapping(value = "/equipments/{codeEquipment}", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.GET)
	public ResponseEntity<com.inventario.api.model.EquipmentFinal> getEquipment(Integer codeEquipment) {

		EquipmentFinal result = new EquipmentFinal();

		if (Utils.isNotNull(codeEquipment)) {
			result = eqpmentMapper.modelMapperEquipment().map(inventarioService.findByCodeEquipment(codeEquipment), 
					com.inventario.api.model.EquipmentFinal.class);

			if (Utils.isNotNullAndEmpty(result)) {
				java.time.LocalDate today = java.time.LocalDate.now();
				int monthsBetween = Period.between(
						java.time.LocalDate.of(result.getDtEquipment().getYear(), result.getDtEquipment().getMonthOfYear(), 
								result.getDtEquipment().getDayOfMonth()).withDayOfMonth(1),
						today.withDayOfMonth(1)).getMonths();
				double percent = result.getValEquipment().doubleValue();
				
				for(int i = monthsBetween; i > 0; i--) {
					percent -= percent * VAL_EQUIPMENT_WITH_PERCENT/100;
				}
				
				result.setValEquipmentWithPercent(BigDecimal.valueOf(percent));	
				
				return new ResponseEntity<com.inventario.api.model.EquipmentFinal>(result, HttpStatus.OK);
				
			}else {
				return new ResponseEntity<com.inventario.api.model.EquipmentFinal>(result, HttpStatus.BAD_REQUEST);
			}
			
		}

		return new ResponseEntity<com.inventario.api.model.EquipmentFinal>(result, HttpStatus.BAD_REQUEST);
	}

	@Override
	@RequestMapping(value = "/equipments", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.GET)
	public ResponseEntity<List<com.inventario.api.model.EquipmentFinal>> listEquipments() {

		List<com.inventario.api.model.EquipmentFinal> result = new ArrayList<com.inventario.api.model.EquipmentFinal>();
		List<Equipment> listReturn = inventarioService.findAllEquipment();
		
		if (Utils.isNotEmptyList(listReturn)) {
			for (Equipment equipment : listReturn) {
				EquipmentFinal eqpFinal = eqpmentMapper.modelMapperEquipment().map(equipment, com.inventario.api.model.EquipmentFinal.class);

				result.add(eqpFinal);
			}
		}

		return new ResponseEntity<List<com.inventario.api.model.EquipmentFinal>>(result, HttpStatus.OK);
	}

	@Override
	@RequestMapping(value = "/equipments/{codeEquipment}", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.DELETE)
	public ResponseEntity<Void> removeEquipment(Integer codeEquipment) {
		
			if (Utils.isNotNull(codeEquipment)) {
				try {
					inventarioService.deleteEquipment(codeEquipment);
					return new ResponseEntity<Void>(HttpStatus.OK);
				} catch (Exception e) {
					return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
				}
				
			}else {
				return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
			}
			
		
	}

}
