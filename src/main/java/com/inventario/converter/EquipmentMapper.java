package com.inventario.converter;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.inventario.api.model.Equipment;
import com.inventario.api.model.EquipmentFinal;


@Configuration
public class EquipmentMapper {

	@Value("${percentege.over.value}")
	private Integer VAL_EQUIPMENT_WITH_PERCENT;
	
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
        
        /**
         * MAPPER EQUIPMENTFINAL
         **/
        
        PropertyMap<EquipmentFinal, com.inventario.domain.Equipment> EqpFinalMapper = 
        		new PropertyMap<EquipmentFinal, com.inventario.domain.Equipment>() {

        	@Override
			protected void configure() {
				using(EquipmentConverter.localdateToDate).map(source.getDtEquipment()).setDtEquipment(null);
			}

        };
        
        PropertyMap<com.inventario.domain.Equipment, EquipmentFinal> EqpFinalResponseMapper =
        		new PropertyMap<com.inventario.domain.Equipment, EquipmentFinal>() {

			@Override
			protected void configure() {
				using(EquipmentConverter.dateToLocaldate).map(source.getDtEquipment()).setDtEquipment(null);
				map().setValEquipmentWithPercent(source.getValEquipment());
			}

        };
        
        modelMapper.addMappings(EqpMapper);
        modelMapper.addMappings(EqpResponseMapper);
        modelMapper.addMappings(EqpFinalMapper);
        modelMapper.addMappings(EqpFinalResponseMapper);
        return modelMapper;
		
	}

}
