package GenericUtilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * @author B.Nandini This Class is used to fetch and write back data to Excel
 *         file
 */
public class ExcelFileUtility {

	Workbook wb = null;

	/**
	 * This method is used to fetch data from excel file
	 * 
	 * @param sheetname
	 * @param Rindex
	 * @param Cindex
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public String fetchDataFromExcelFile(String sheetname, int Rindex, int Cindex)
			throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream("./src/test/resources/VtigerTestData.xlsx");
		wb = WorkbookFactory.create(fis);
		String data = wb.getSheet(sheetname).getRow(Rindex).getCell(Cindex).toString();
		return data;
	}

	/**
	 * This is used to write back data to new row and new cell in excel
	 * 
	 * @param sheetname
	 * @param Rindex
	 * @param Cindex
	 * @param data
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public void writeBackDataToExcelFile(String sheetname, int Rindex, int Cindex, String data)
			throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream("./src/test/resources/VtigerTestData.xlsx");
		wb = WorkbookFactory.create(fis);
		wb.getSheet(sheetname).createRow(Rindex).createCell(Cindex).setCellValue(data);
		FileOutputStream fos = new FileOutputStream("./src/test/resources/VtigerTestData.xlsx");
		wb.write(fos);

	}

	/**
	 * This is used to write back data to existing row and new cell in excel
	 * 
	 * @param sheetname
	 * @param Rindex
	 * @param Cindex
	 * @param data
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public void writeBackDataToExcel_ExistingRow(String sheetname, int Rindex, int Cindex, String data)
			throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream("./src/test/resources/VtigerTestData.xlsx");
		wb = WorkbookFactory.create(fis);
		wb.getSheet(sheetname).getRow(Rindex).createCell(Cindex).setCellValue(data);
		FileOutputStream fos = new FileOutputStream("./src/test/resources/VtigerTestData.xlsx");
		wb.write(fos);

	}

	/**
	 * This method is used to close Excel file
	 * 
	 * @throws IOException
	 */
	public void closeExcel() throws IOException {
		wb.close();
	}

}
