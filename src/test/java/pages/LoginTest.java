package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginTest {
	private WebDriver driver;

    @BeforeTest
    public void setup() {
        driver = new ChromeDriver();
    }

    @Test
    public void testSuccessfulLogin() {
        LoginPage loginPage = new LoginPage(driver);
        driver.get("https://demowebshop.tricentis.com/login"); 

        loginPage.enterUsername("Nik905@gmail.com");
        loginPage.enterPassword("123456789");
        loginPage.clickLoginButton();

        // Verify successful login (e.g., check for expected home page elements)
        Assert.assertTrue(driver.getTitle().contains("Demo Web Shop")); // Replace with your expected title
    }
    @Test
    public void testInvalidLogin() {
    	LoginPage loginPage = new LoginPage(driver);
        driver.get("https://demowebshop.tricentis.com/login"); 

        loginPage.enterUsername("Nik905@mil.com");
        loginPage.enterPassword("123789");
        loginPage.clickLoginButton();

        // Verify successful login (e.g., check for expected home page elements)
        Assert.assertTrue(driver.getTitle().contains("Demo Web Shop. Login"));
        
    	
    }

    @AfterTest
    public void teardown() {
        driver.quit();
    }

}
