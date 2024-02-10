package utilities;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.LoginPage;

public class ExcelUtility {
	private WebDriver driver;
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	XSSFCell cell;
	 
	 @BeforeMethod
	 public void setup() {
		 driver = new ChromeDriver();
		 //LoginPage loginPage = new LoginPage(driver);
		 driver.get("https://demowebshop.tricentis.com/login");
	 }
	 
    @AfterTest
    public void teardown() {
        driver.quit();
    }
    @Test
	public void signInTest() {

		try {
			// Import xlsx sheet
			File src = new File("C:\\Users\\Predator\\PracticeExercise\\src\\test\\resources\\TestData.xlsx");

			// Load the file as FileInputStream.
			FileInputStream fileInput = new FileInputStream(src);

			// Load the workbook
			workbook = new XSSFWorkbook(fileInput);

			// Load the sheet in which data is stored. (0)
			sheet = workbook.getSheetAt(0);

			for (int row = 1; row < sheet.getLastRowNum(); row++) {
				// import data from cell : username
				cell = sheet.getRow(row).getCell(0);
				cell.setCellType(CellType.STRING);
				driver.findElement(By.id("Email")).sendKeys(cell.getStringCellValue());

				// import data from cell : password
				cell = sheet.getRow(row).getCell(1);
				cell.setCellType(CellType.STRING);
				driver.findElement(By.id("Password")).sendKeys(cell.getStringCellValue());

				driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[4]/div[2]/div/div[2]/div[1]/div[2]/div[2]/form/div[5]/input")).submit();

				String message = "pass";
				if(driver.getCurrentUrl().equals("https://demowebshop.tricentis.com/login")) {
					message = "Fail";
					driver.findElement(By.id("Email")).clear();
					driver.findElement(By.id("Password")).clear();
				}
				if(driver.getCurrentUrl().equals("https://demowebshop.tricentis.com/")) {
					message = "Pass";
					driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[1]/div[2]/div[1]/ul/li[2]/a")).click();
					driver.get("https://demowebshop.tricentis.com/login");
				}
				//Thread.sleep(400);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
				// Write data in the excel.
				FileOutputStream foutput=new FileOutputStream(src);
				
				// Specify the message needs to be written.
				
				//Create cell where data needs to be written.
				sheet.getRow(row).createCell(2).setCellValue(message);
				
				// Specify the file in which data needs to be written.
				FileOutputStream fileOutput =new FileOutputStream(src);
				
				//write content
				workbook.write(fileOutput);
				
				  // close the file
				fileOutput.close();
				}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
    }
}
