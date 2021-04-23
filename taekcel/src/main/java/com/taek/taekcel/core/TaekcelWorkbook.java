package com.taek.taekcel.core;

import java.util.List;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.checkerframework.checker.nullness.qual.NonNull;

import com.taek.taekcel.core.type.WorkbookType;

public class TaekcelWorkbook<T> {
	
	private Workbook workbook;
	private List<TaekcelSheet<T>> sheets;
	
	public TaekcelWorkbook(@NonNull WorkbookType workbookType) {
		workbook = workbookType.createWorkbook();
	}
	
	public TaekcelSheet<T> createSheet(List<T> excelData) {
		TaekcelSheet<T> sheet = new TaekcelSheet<T>(workbook, excelData);
		this.sheets.add(sheet);
		return sheet;
	}
	
	public void write() {
		
	}

}
