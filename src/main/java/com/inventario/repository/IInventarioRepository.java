package com.inventario.repository;

import java.util.Iterator;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.inventario.domain.Equipment;



public interface IInventarioRepository extends CrudRepository<Equipment, Integer> {

	@Query("SELECT e FROM Equipment e")
	public Iterator<Equipment> findByAll();

}
