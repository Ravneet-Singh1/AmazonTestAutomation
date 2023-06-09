package testCases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {

	public WebDriver driver;
	public Properties prop;

	public WebDriver initializeDriver() throws IOException {

		prop = new Properties();

		// C:\\Users\\ravneesi\\eclipse-workspace\\amazon <-
		// System.getProperty("user.dir")
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\java\\testCases\\data.properties");

		prop.load(fis);

		String browserName = prop.getProperty("browser");

		// For Chrome
		if (browserName.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}

		// For Firefox
		else if (browserName.equalsIgnoreCase(browserName)) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}

		// For MicrosoftEdge
		else if (browserName.equalsIgnoreCase("MicrosoftEdge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}

		// Implicit wait of 10 sec
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

		return driver;
	}

	public String takeScreenshot(String testCaseName, WebDriver driver) throws IOException {

		String path = System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(path));
		return path;
	}

	@BeforeTest(alwaysRun = true)
	public WebDriver launchApplication() throws IOException {
		driver = initializeDriver();
		driver.get(prop.getProperty("url"));
		return driver;
	}

	@AfterTest(alwaysRun = true)
	public void TearDown() throws InterruptedException {
		driver.quit();
	}
}
