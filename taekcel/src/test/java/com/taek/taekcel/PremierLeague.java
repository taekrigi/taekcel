package com.taek.taekcel;

import com.taek.taekcel.annotation.ExcelHeader;

public class PremierLeague {

	@ExcelHeader(headerName = "�� ��")
	private String team;
	
	@ExcelHeader(headerName = "����")
	private byte rank;
	
	@ExcelHeader(headerName = "����")
	private byte point;
}
