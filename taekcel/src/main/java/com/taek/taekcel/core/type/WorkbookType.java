package com.taek.taekcel.core.type;

import java.util.function.Supplier;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public enum WorkbookType {
	
	HSSF(() -> new HSSFWorkbook()),
	XSSF(() -> new XSSFWorkbook()),
	SXSSF(() -> new SXSSFWorkbook())
	
	;
	
	Supplier<Workbook> supplier;
	
	WorkbookType(Supplier<Workbook> supplier) {
		this.supplier = supplier;
	}
	
	public Workbook createWorkbook() {
		return this.supplier.get();
	}

}
