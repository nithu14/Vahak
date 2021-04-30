package dataprovider;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.testng.annotations.DataProvider;

import utilities.Base;
import utilities.ExcelReadwrite;

public class CommomDP extends Base{
	@DataProvider(name="get_data")
	public Iterator<Object[]> getdata() throws Exception {
		String path=System.getProperty("user.dir")+"\\Resource\\Team_Vahak.xlsx";
			ex = new ExcelReadwrite(path);
			String sheetname = "Sign_In";
			int row = ex.rowCount(sheetname);
			int col = ex.colCount(sheetname);
			ArrayList<Object[]> ls = new ArrayList<Object[]>();	
			
//			iterate thru each row
			for(int iRow = 1 ;iRow<=row;iRow++){
					
//				create object array
				Object[] obj = new Object[1];
				
//				create map
				Map<String, String> Map = new HashMap<String,String>();
//				iterate thru column
				for(int iCol = 0;iCol<col;iCol++){
					
					String Key = ex.readCellValue(sheetname, 0, iCol);
					String Val = ex.readCellValue(sheetname, iRow, iCol);
					Map.put(Key, Val);
					
				}	//			iterate thru column				
				obj[0]=Map;
				ls.add(obj);

			}//		iterate thru each row
			return ls.iterator();	
	}
		
}
