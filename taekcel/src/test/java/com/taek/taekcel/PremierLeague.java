package com.taek.taekcel;

import com.taek.taekcel.annotation.ExcelHeader;

public class PremierLeague {

	@ExcelHeader(headerName = "팀 명")
	private String team;
	
	@ExcelHeader(headerName = "순위")
	private byte rank;
	
	@ExcelHeader(headerName = "점수")
	private byte point;
}
