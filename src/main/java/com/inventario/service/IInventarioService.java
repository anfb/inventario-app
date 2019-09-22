package com.inventario.service;

import java.util.List;

import com.inventario.domain.Equipment;

public interface IInventarioService {
	
	Equipment findByCodeEquipment (final Integer codeEquipment);
	
	Equipment createEquipment(final Equipment equipment);
	
	void deleteEquipment (final Long codeEquipment);
	
	Equipment updateEquipment (final Equipment equipamento);

	List<Equipment> findAllEquipment(); 
}
