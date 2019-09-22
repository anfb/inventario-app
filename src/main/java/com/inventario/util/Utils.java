package com.inventario.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

public final class Utils {

	public static boolean isNullAndEmpty(final String value) {
		return (value == null) || value.trim().equals("");
	}

	public static boolean isNotNullAndEmpty(final String value) {
		return (value != null) && !value.trim().equals("");
	}

	public static boolean isNotNullAndEmpty(final Object objeto) {
		boolean retorno = Boolean.FALSE;
		if (objeto != null) {
			if (!objeto.toString().trim().equals("")) {
				retorno = Boolean.TRUE;
			}
		}
		return retorno;
	}

	public static boolean isNotNullAndEmpty(final Number value) {
		return (value != null) && (value.longValue() > 0);
	}

	public static boolean isNullAndEmpty(final Number value) {
		return !isNotNullAndEmpty(value);
	}

	public static boolean isEmptyList(final Collection<? extends Object> collection) {
		return (collection == null) || collection.isEmpty();
	}

	public static boolean isNotEmptyList(final Collection<? extends Object> collection) {
		return !isEmptyList(collection);
	}

	public static boolean isNull(final Object value) {
		return value == null;
	}

	public static boolean isNotNull(final Object value) {
		return !isNull(value);
	}

	public static boolean isNegativo(final Number value) {
		return isNotNull(value) && (value.longValue() <= 0);
	}

	public static boolean isNegativo(final String value) {
		Boolean retorno = Boolean.TRUE;
		try {
			final Long parsed = Long.valueOf(value);
			retorno = parsed < 0;
		} catch (final NumberFormatException exc) {
			retorno = Boolean.TRUE;
		}
		return retorno;
	}

	public static boolean isNotNumber(final String value) {
		Boolean retorno = Boolean.TRUE;
		try {
			Long.valueOf(value);
			retorno = Boolean.FALSE;
		} catch (final NumberFormatException exc) {
			retorno = Boolean.TRUE;
		}
		return retorno;
	}

	public static boolean areAllArgsNotNull(final Object... objects) {
		boolean retorno = true;
		for (final Object object : objects) {
			if (object == null) {
				retorno = false;
			}
		}
		return retorno;
	}
	
	public static Date stringToDate(final String data) {
		try {
		Date saida;
		final SimpleDateFormat formater = new SimpleDateFormat(data, Locale.ROOT);
			saida = formater.parse(data);
			return saida;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}	
	}
	
	public static String dateToString(final Date date) { 
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/dd/MM");  
	    String strDate = formatter.format(date);  
	    
	    return strDate;
	}
}
