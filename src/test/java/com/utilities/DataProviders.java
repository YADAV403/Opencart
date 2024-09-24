package com.utilities;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name = "LoginData")
	public String[][] getData() throws Exception{
		
		String path = "./testData//DDtestdata.xlsx";
		
		ExcelUtility eu = new ExcelUtility(path);
		int totalrows = eu.getRowCount("Sheet1");
		int totalcols = eu.getCellCount("Sheet1", 1);
		
		String logindata[][] =  new String[totalrows][totalcols];
		for(int r=1; r<=totalrows; r++) {
			
			for(int c=0; c<totalcols; c++) {
				
				logindata[r-1][c] = eu.getCellData("Sheet1", r, c);
			}
		}
		return logindata;
	}

}
