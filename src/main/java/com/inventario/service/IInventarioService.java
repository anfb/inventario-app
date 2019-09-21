package com.inventario.service;

import com.inventario.api.model.Equipamento;

public interface IInventarioService {
	
	Equipamento findByCodeEquipamento (final Long codeEquipamento);
	
	Equipamento create(final Equipamento equipamento);
	
	void delete (final Long codeEquipamento);
	
	Equipamento update(final Equipamento equipamento);
	
	//Page<Equipamento> findAll(final Pageable pageable);
	
	
	
	
	
}
