package utilities;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MyListener implements ITestListener {
	//String filePath = "C:\\Users\\Predator\\PracticeExercise\\screenshots";
	private ExtentReports extent;
    private Map<String, ExtentTest> testMap = new HashMap<>();
	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("Test failed: " + result.getName());
		WebDriver driver = (WebDriver) result.getTestContext().getAttribute("driver");
		System.out.println(driver);
		if ( driver instanceof TakesScreenshot) {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                org.apache.commons.io.FileUtils.copyFile(screenshot, new File("C:\\Users\\Predator\\PracticeExercise\\screenshots\\" + result.getName() + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
	}
		testMap.get(result.getName()).log(Status.FAIL, "Test failed due to: " + result.getThrowable().getMessage());
        
    }
		
	@Override
    public void onTestStart(ITestResult result) {
		System.out.println("Test Started: " + result.getName());
		ExtentTest test = extent.createTest(result.getName());
        testMap.put(result.getName(), test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("Test Passed: " + result.getName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("Test Skipped: " + result.getName());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("Test Failed within Success percentage: " + result.getName());
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("Test Start: " + context.getName());
		extent = new ExtentReports();
		
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        extent.setSystemInfo("Host Name", System.getenv("COMPUTERNAME"));
        extent.setSystemInfo("Browser", context.getCurrentXmlTest().getParameter("browser"));
        
        String nameOS = "os.name";
        String javaversion = "java.version";
        String hname = "COMPUTERNAME";
        String bname = "browser";

        nameOS = System.getProperty(nameOS);
        javaversion = System.getProperty(javaversion);
        hname = System.getenv(hname);
        bname = context.getCurrentXmlTest().getParameter("browser");
        

        System.out.println("The information about OS");
        System.out.println("Name of the OS: " + nameOS);
        System.out.println("Java version: " + javaversion);
        System.out.println("Host name: " + hname);
        System.out.println("Browser name: Chrome");
	}
	
	public void onFinish(ITestContext context) {
		System.out.println("Test Finish: " + context.getName());
		extent.flush();
	}

}
