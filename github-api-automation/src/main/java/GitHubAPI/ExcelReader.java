//package GitHubAPI;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.lang.reflect.Method;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.xssf.usermodel.XSSFCell;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.testng.ITestContext;
//import org.testng.annotations.DataProvider;
//
//
//
//
//public class ExcelReader {
//	public static String methodName;
//	private static XSSFSheet ExcelWSheet;
//	private static XSSFWorkbook ExcelWBook;
//    private static XSSFCell Cell;
//    static String[][] finalRetArray = null;
//
//
//	@DataProvider(name = "teams")
//	public synchronized static Object[][] getTestDataForMyTest(
//			ITestContext context, Method method) throws Exception {
//		methodName = method.getName();
//		System.out.println("Test Method that will be executed: " + methodName);
//		String dataSet = context.getCurrentXmlTest().getParameter("testData");
//		System.out.println("Data Set from TestNG XML : " + dataSet);
//		String[] sheetName = dataSet.split("\\|");
//		String workingSheet = sheetName[0].toString();
//		String workingDataSet = sheetName[1].toString();
//		String workingExcel;
//		System.out.println("Array Size is " + sheetName.length);
//		if (sheetName.length >= 3) {
//			System.out.println("Came in if");
//			workingExcel = sheetName[2].toString();
//		} else {
//			workingExcel = "testData.xlsx";
//		}
//		System.out.println("-------------------------------------------------");
//		System.out.println("workingSheet:" + workingSheet);
//		System.out.println("workingDataSet:" + workingDataSet);
//		System.out.println("workingExcel:" + workingExcel);
//		System.out.println("workingSheet:" + workingSheet);
//		System.out.println("workingDataSet:" + workingDataSet);
//		System.out.println("workingExcel:" + workingExcel);
//		// System.out.println("Sheetname is :" + sheetName[0].toString());
//		Object[][] testObjArray = getTableArray(
//				"src/test/resources/ExcelFiles" + File.separator + workingExcel,
//				workingSheet, workingDataSet, methodName);
//		System.out.println("testObjArray" + testObjArray);
//		System.out.println("testObjArray" + testObjArray);
//		return (testObjArray);
//	}
//	 public static String getCellData(int RowNum, int ColNum) {
//	        try {
//	            Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
//	            int dataType = Cell.getCellType();
//	            if (dataType == 3) {
//	                return "";
//	            } else {
//	                String CellData;
//	                try {
//	                    CellData = Cell.getStringCellValue();
//	                } catch (Exception e) {
//	                    CellData = Cell.getRawValue();
//	                }
//	                return CellData;
//	            }
//	        } catch (Exception e) {
//	            System.out.println(e.getMessage());
//	            
//	        }
//	        return null;
//	    }
//	public synchronized static Object[][] getTableArray(String FilePath,
//			String SheetName, String DataSet, String methodName)
//					throws Exception {
//		String[][] tabArray = null;
//		String[][] tempRetArray = null;
//		String[] header = null;
//		try {
//			System.out
//			.println("Getting test data from excel For Test Method = "
//					+ methodName + "\n From DataSet = " + DataSet
//					+ "\n FilePath =" + FilePath + "\n SheetName = "
//					+ SheetName);
//			System.out.println("Getting test data from excel For Test Method = "
//					+ methodName + "\n From DataSet = " + DataSet
//					+ "\n FilePath =" + FilePath + "\n SheetName = "
//					+ SheetName);
//			FileInputStream ExcelFile = new FileInputStream(FilePath);
//			// Access the required test data sheet
//			System.out.println("------PARSING DATA SHEET--------");
//			ExcelWBook = new XSSFWorkbook(ExcelFile);
//			ExcelWSheet = ExcelWBook.getSheet(SheetName);
//			int startCol = 0;
//			int ci, cj;
//			int totalRows = ExcelWSheet.getLastRowNum();
//			System.out.println("totalRows in Data Sheet:" + totalRows);
//			System.out.println("totalRows in Data Sheet:" + totalRows);
//
//			// To get the total number of columns used
//			int totalCols = 0;
//			Iterator rowIterator = ExcelWSheet.rowIterator();
//			if (rowIterator.hasNext()) {
//				Row headerRow = (Row) rowIterator.next();
//				// get the number of cells in the header row
//
//				totalCols = headerRow.getPhysicalNumberOfCells();
//			}
//			System.out.println("Total columns used:" + totalCols);
//			System.out.println("Total columns used:" + totalCols);
//			tabArray = new String[totalRows][totalCols];
//			header = new String[totalCols];
//			ci = 0;
//
//			// get column Header in to array
//			for (int j = startCol; j <= totalCols - 1; j++) {
//				header[j] = getCellData(0, j);
//			}
//
//			// Get column number for Test Method
//			int CollumNum = 0;
//			for (int i = 0; i < header.length; i++) {
//				if (header[i].equalsIgnoreCase(methodName)) {
//					CollumNum = i;
//					break;
//				}
//			}
//			// if column for data is not found then Log Error & Exit
//			if (CollumNum == 0) {
//				System.out.println("Data Set not found for test method "
//						+ methodName + "in Test sheet Path:" + FilePath
//						+ "and sheet name:" + SheetName);
//				System.out.println("Data Set not found for test method " + methodName
//						+ "in Test sheet Path:" + FilePath + "and sheet name:"
//						+ SheetName);
//				System.exit(0);
//			}
//			// Get Row no for provided data set
//
//			List<Integer> dataRows = new ArrayList<Integer>();
//			for (int i = 0; i <= totalRows; i++, ci++) {
//				if (getCellData(i, 0).contains(DataSet))
//					dataRows.add(i);
//				else if (getCellData(i, 0).equalsIgnoreCase("##"))
//					break;
//			}
//
//			System.out.println("For Data Set = " + DataSet
//					+ " Data Rows to be executed are : " + dataRows);
//			System.out.println("For Data Set = " + DataSet
//					+ " Data Rows to be executed are : " + dataRows);
//
//			// creating 2D array for storing dataSet values
//			// 2D array is of size [no of dataSet rows][1] as return array will
//			// have data for a method from which dataProvider was called.
//			tempRetArray = new String[dataRows.size()][1];
//			int x = 0;
//			// Get data for a cell using data set list as for Row no & Collum
//			// Num of Method Name
//			for (Iterator iterator = dataRows.iterator(); iterator.hasNext();) {
//				int rownum = (Integer) iterator.next();
//				String temp = getCellData(rownum, CollumNum);
//				if (temp.equalsIgnoreCase("##"))
//					break;
//				else
//					tempRetArray[x][0] = temp;
//				x++;
//			}
//		} catch (FileNotFoundException e) {
//			System.out.println("Could not read the Excel sheet");
//			System.out.println("Could not read the Excel sheet");
//			e.printStackTrace();
//		} catch (Exception e) {
//			System.out.println("Could not read the Excel sheet");
//			System.out.println("Could not read the Excel sheet");
//			e.printStackTrace();
//		}
//		// To remove NULL values from tempRetArray
//		// Put All NON NULL values in a Arrary list, then get its size & put all
//		// values in finalRetArray
//		ArrayList<String> al = new ArrayList<String>();
//
//		for (int i = 0; i < tempRetArray.length; i++) {
//			if (!(tempRetArray[i][0] == null || tempRetArray[i][0].isEmpty())) {
//				al.add(tempRetArray[i][0]);
//			} else {
//				System.out.println("NULL value observed at index no " + i);
//				System.out.println("NULL value observed at index no " + i);
//			}
//		}
//
//		try {
//			finalRetArray = new String[al.size()][1];
//			for (int i = 0; i < al.size(); i++) {
//				finalRetArray[i][0] = al.get(i);
//			}
//
//		} catch (Exception e) {
//			System.out.println(e);
//			e.printStackTrace();
//		}
//		System.out.println("data array creation done");
//		System.out.println("data array creation done");
//		return (finalRetArray);
//	}
//
//
//
//}
