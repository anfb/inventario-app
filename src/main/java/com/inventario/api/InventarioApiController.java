package com.inventario.api;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.inventario.api.model.Equipamento;
import com.inventario.api.model.EquipamentoPaginado;

@RestController
public class InventarioApiController implements EquipamentosApi{

	
	@RequestMapping(value = "/olamundo", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.GET)
	public String HelloWorld() {
		return "Hello world";
	}

	@Override
	@RequestMapping(value = "/equipamentos", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.PUT)
	public ResponseEntity<Equipamento> atualizarEquipamento(String idEquipamento, @Valid Equipamento body) {
		return null;
	}

	@Override
	@RequestMapping(value = "/equipamentos", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public ResponseEntity<Equipamento> criarEquipamento(@Valid Equipamento body) {
		return null;
	}

	@Override
	@RequestMapping(value = "/equipamentos", produces = { MediaType.APPLICATION_JSON_VALUE, "text/csv" }, method = RequestMethod.GET)
	public ResponseEntity<EquipamentoPaginado> listarEquipamentos(String ids, String orderByType, String orderBy,
			Integer page, Integer perPage, String format) {
		return null;
	}

	@Override
	@RequestMapping(value = "/equipamentos", produces = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.GET)
	public ResponseEntity<Equipamento> obterEquipamento(String idEquipamento) {
		return null;
	}

	@Override
	@RequestMapping(value = "/{idEquipamento}", method = RequestMethod.DELETE)
	public ResponseEntity<Equipamento> removerEquipamento(String idEquipamento) {
		return null;
	}
	
	
}
