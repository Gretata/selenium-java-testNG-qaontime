package utils;

import java.util.Random;
import org.apache.commons.lang3.RandomStringUtils;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Utils {

	public Utils() {
		// Auto-generated constructor to access methods
	}

	public String generateString(int length) {
		String result = RandomStringUtils.randomAlphabetic(length);
		return result;
	}

	public int generateSeverity(int min, int max) {
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

	public String getUserName() {
		return configData("userNameXml");
	}

	public String getPassword() {
		return configData("passwordXml");
	}

	public String getUrl() {
		return configData("url");
	}

	// susikuriame metoda, kuris padeda optimizuoti koda - nebereikia tris kartus
	// daryti try-catch.
	// Isivedame configData ir tada returninam virsuje, o try-catche panaudojam
	// susikurta commonTag

	public String configData(String commonTag) {
		try {
			File File = new File("config\\config.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(File);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("config");
			Node nNode = nList.item(0);
		
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;

				return eElement.getElementsByTagName(commonTag).item(0).getTextContent();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	// HOW TO READ XLSX FILES IN JAVA -->
	// kodas adaptuotas is:
	// https://www.javatpoint.com/how-to-read-excel-file-in-java

	public List<String> readExcelFileWithJava() {
		List<String> al = new ArrayList(); 
		try {
			File file = new File("test-data\\login.xlsx"); // creating a new file instance
			FileInputStream fis = new FileInputStream(file); // obtaining bytes from the file
			// creating Workbook instance that refers to .xlsx file
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(0); // creating a Sheet object to retrieve object
			Iterator<Row> itr = sheet.iterator(); // iterating over excel file
			while (itr.hasNext()) {
				Row row = itr.next();
				Iterator<Cell> cellIterator = row.cellIterator(); // iterating over each column
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING: // field that represents string cell type
						System.out.print("password is:" + cell.getStringCellValue() + "\t\t\t");
						al.add(cell.getStringCellValue());
						break;
					case Cell.CELL_TYPE_NUMERIC: // field that represents number cell type
						System.out.print(cell.getNumericCellValue() + "\t\t\t");
						break;
					default:
					}
				}
			}
			wb.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return al;
	}
}
