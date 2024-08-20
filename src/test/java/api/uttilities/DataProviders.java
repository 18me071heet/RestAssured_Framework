package api.uttilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;



public class DataProviders {

	@DataProvider(name="Data")
	public String[][] getData() throws IOException{
		
		String path =System.getProperty("user.dir")+"//testData//UserData.xlsx";
		
		ExcelUtility xlutil = new ExcelUtility(path);
		
		int rowCount = xlutil.getrowCount("Sheet1");
		int cellCount = xlutil.getcellCount("Sheet1", 1);
		
		String apiData[][]= new String[rowCount][cellCount];
		
		for(int r=1;r<=rowCount;r++) {
			
			for(int c=0;c<cellCount;c++){
				
			apiData[r-1][c]= xlutil.getcellData("Sheet1", r, c);
			}
		}
		
		return apiData;
		
	}
	
	@DataProvider(name="UserNames")
	public String[] getUserNames() throws IOException {
		
		
		String path =System.getProperty("user.dir")+"//testData//UserData.xlsx";
		
		ExcelUtility xlutil = new ExcelUtility(path);
		
		int rowCount = xlutil.getrowCount("Sheet1");
	String apiData[]= new String[rowCount];
		
		for(int r=1;r<=rowCount;r++) {
			
			apiData[r-1]= xlutil.getcellData("Sheet1", r, 1);
			
			}
		
		return apiData;
	}
		
	}
	
	

