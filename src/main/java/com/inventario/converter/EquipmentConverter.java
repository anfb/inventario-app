package com.inventario.converter;

import java.util.Date;

import org.joda.time.LocalDate;
import org.modelmapper.Converter;

import com.inventario.util.Utils;

public class EquipmentConverter {

	private EquipmentConverter() {}


	public static final Converter<LocalDate, Date> localdateToDate = ctx -> {
		if(Utils.isNotNull(ctx.getSource())) {	
			return ctx.getSource().toDate();
		}
		return null;
	};

	public static final Converter<Date, LocalDate> dateToLocaldate = ctx -> {
		if(Utils.isNotNull(ctx.getSource())) {	
			return new LocalDate(ctx.getSource());
		}
		return null;
	};
	
	
}
