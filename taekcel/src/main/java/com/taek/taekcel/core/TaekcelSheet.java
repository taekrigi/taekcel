package com.taek.taekcel.core;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.taek.taekcel.annotation.ExcelHeader;
import com.taek.taekcel.util.ExcelHeaderConverter;

public class TaekcelSheet<T> {
	
	private List<T> excelData;
	private Sheet sheet;
	private Class<?> excelDataClass;
	
	public TaekcelSheet(Workbook workbook, List<T> excelData) {
		this.excelData = excelData;
		this.sheet = workbook.createSheet(); 
		this.excelDataClass = this.excelData.getClass();
	}
	
	public void applyCellStyle() {}
	
	private void generateSheetData() throws IllegalArgumentException, IllegalAccessException {
		Row row = this.sheet.createRow(0);
		this.createTitle(row);
		this.createContent(sheet, excelData);
	}
	
	private void createTitle(Row row) {
		List<String> excelHeaders = ExcelHeaderConverter.getExcelHeaders(excelDataClass);
		for (int i = 0; i < excelHeaders.size(); i++) {
			Cell cell = row.createCell(i);
			cell.setCellValue(excelHeaders.get(i));
		}
	}
	
	public void createContent(Sheet sheet, List<T> excelData) throws IllegalArgumentException, IllegalAccessException {
		List<Field> fields = getExcelHeaderAnnotatedFields();
				
		for (int i = 0, j = 1; i < excelData.size(); i++, j++) {
			Row row = sheet.createRow(j);
			Object object = excelData.get(i);
			for (int k = 0; k < fields.size(); k++) {
				Field field = fields.get(k);
				field.setAccessible(true);
				Cell cell = row.createCell(k);
				// TODO: call this method by data type
				cell.setCellValue((String) field.get(object));
			}
		}
	}
	
	private List<Field> getExcelHeaderAnnotatedFields() {
		return Arrays.stream(excelDataClass.getDeclaredFields())
				.filter(field -> field.isAnnotationPresent(ExcelHeader.class))
				.collect(Collectors.toList());
	}
}
