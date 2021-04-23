package com.taek.taekcel.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.taek.taekcel.annotation.ExcelHeader;

public class ExcelHeaderConverter {

	public static <T> List<String> getExcelHeaders(Class<T> target) {
		List<String> excelHeaders = new ArrayList<>();
		
		for (Field field : target.getDeclaredFields()) {
			field.setAccessible(true);
			Optional.ofNullable(field.getAnnotation(ExcelHeader.class))
				.map(excelHeader -> excelHeader.headerName())
				.ifPresent(excelHeaders::add);
		}
		
		return excelHeaders;
	}
}
