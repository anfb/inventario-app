package com.inventario.converter;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.inventario.api.model.Equipment;


@Configuration
public class EquipmentMapper {

	
	@Bean
	public ModelMapper modelMapperEquipment() {
		ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
		
        PropertyMap<Equipment, com.inventario.domain.Equipment> EqpMapper = 
        		new PropertyMap<Equipment, com.inventario.domain.Equipment>() {

        	@Override
			protected void configure() {
				using(EquipmentConverter.localdateToDate).map(source.getDtEquipment()).setDtEquipment(null);
			}

        };
        
        PropertyMap<com.inventario.domain.Equipment, Equipment> EqpResponseMapper =
        		new PropertyMap<com.inventario.domain.Equipment, Equipment>() {

			@Override
			protected void configure() {
				using(EquipmentConverter.dateToLocaldate).map(source.getDtEquipment()).setDtEquipment(null);	
			}

        };
        
        PropertyMap<List<Equipment>, List<com.inventario.domain.Equipment>> EqpListMapper = 
        		new PropertyMap<List<Equipment>, List<com.inventario.domain.Equipment>>() {

        	
        	@Override
			protected void configure() {

        	}

        };
        
        modelMapper.addMappings(EqpMapper);
        modelMapper.addMappings(EqpResponseMapper);
        return modelMapper;
		
	}

}
