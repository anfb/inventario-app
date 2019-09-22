package com.inventario.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.inventario.domain.Equipment;
import com.inventario.repository.IInventarioRepository;
import com.inventario.util.Utils;

@Named
@Transactional	
public class InventarioServiceImpl implements IInventarioService{

	@Inject
	private IInventarioRepository repository;
	
	
	@Override
	public Equipment findByCodeEquipment(Integer codeEquipment) {
		return null; //repository.findByCodEquipamento(codeEquipment);
	}

	@Override
	public Equipment createEquipment(Equipment equipment) {

		if (Utils.isNotNullAndEmpty(equipment)) {
		 Equipment equipmentRegistered = repository.save(equipment);
		 return equipmentRegistered;
		}
		return null;
	}

	@Override
	public void deleteEquipment(Long codeEquipment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Equipment updateEquipment(Equipment equipamento) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Equipment> findAllEquipment() {
		 return (List<Equipment>) repository.findAll();
	}

	
}
