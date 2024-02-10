package utilities;

import static org.testng.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;
@Listeners(MyListener.class)
public class TestLogin {
	// step1: formulate a test domain url & driver path
		String siteUrl = "https://demowebshop.tricentis.com/login";
		String driverPath = "drivers/chromedriver.exe";
		WebDriver driver ;
		ExtentSparkReporter htmlReporter;
		ExtentReports extentReports;
		ExtentTest test1, test2 ;
		
		@BeforeClass
		public void beforeClass() {

			// create the htmlReporter object
			htmlReporter = new ExtentSparkReporter("extentReport.html");

			// create ExtentReport and attach this reports
			extentReports = new ExtentReports();
			extentReports.attachReporter(htmlReporter);

			
			// initialize and start the browser
			WebDriverManager.chromedriver().setup();
					
			// step2: set system properties for selenium dirver
			

			// step3: instantiate selenium webdriver
			driver = new ChromeDriver();
			

			// step4: launch browser
			driver.get(siteUrl);
		}

		@AfterClass
		public void afterClass() {
			driver.quit();
			extentReports.flush();
		}

		@Test(description = "Valid Login")
		public void testSearch1() throws InterruptedException {
			// create a test and add logs
			test1 = extentReports.createTest("Login Test", "Valid Login Test");
			test1.log(Status.INFO, "Starting test case");
			
			WebElement Email = driver.findElement(By.id("Email"));
			test1.pass("Find Email box");
			
			Email.sendKeys("Nik905@gmail.com");
			test1.pass("Enter input data 'Nik905@gmail.com' ");
			
			WebElement Password = driver.findElement(By.id("Password"));
			test1.pass("Find Password box");
			
			Password.sendKeys("123456789");
			test1.pass("Enter input data '123456789' ");
			
			WebElement Submit = driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[4]/div[2]/div/div[2]/div[1]/div[2]/div[2]/form/div[5]/input"));
			Submit.click();
			test1.pass("Click Submit");
			
			// add delay
			Thread.sleep(2000);

			String expectedTitle = "Demo Web Shop";
			String actualTitle = driver.getTitle();

			assertEquals(actualTitle, expectedTitle);
			test1.pass("After search the title are matched");
			WebElement logout = driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[1]/div[2]/div[1]/ul/li[2]/a"));
			logout.click();	
			driver.get(siteUrl);
			test1.log(Status.INFO, "End test case");
		}

		@Test(description = "Invalid Login")
		public void testSearch2() throws InterruptedException {
			// create a test and add logs
			test2 = extentReports.createTest("Login Test", "Invalid Login Test");
			test2.log(Status.INFO, "Starting test case");
			
			WebElement Email = driver.findElement(By.id("Email"));
			test2.pass("Find Email box");
			
			Email.sendKeys("Nik905@mail.com");
			test2.pass("Enter input data 'Nik905@gmail.com' ");
			
			WebElement Password = driver.findElement(By.id("Password"));
			test2.pass("Find Password box");
			
			Password.sendKeys("123456789");
			test2.pass("Enter input data '123456789' ");
			
			WebElement Submit = driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[4]/div[2]/div/div[2]/div[1]/div[2]/div[2]/form/div[5]/input"));
			Submit.click();
			test2.pass("Click Submit");
			
			// add delay
			Thread.sleep(2000);

			String expectedTitle = "Demo Web Shop";
			String actualTitle = driver.getTitle();

			assertEquals(actualTitle, expectedTitle);
			test2.pass("After search the title are matched");
			
			test2.log(Status.INFO, "End test case");
		}

}
