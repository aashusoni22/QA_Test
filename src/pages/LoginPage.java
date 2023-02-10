package pages;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;

	/*WebElement email = driver.findElement(By.name("usernameInput"));
	WebElement password = driver.findElement(By.name("password"));
	WebElement signIn = driver.findElement(By.id("signIn"));
	WebElement emailErr = driver.findElement(By.id("UsernameTextField__errors-usernameInput"));
	WebElement passwordErr = driver.findElement(By.cssSelector("div[class*='mSQlk Error__text']>span"));
	WebElement globalErr = driver.findElement(By.className("d_25yKbJzdQG5JaS-QB9EOCt"));*/
	
	
	//Here we are using @FindBy to avoid using driver at the beginning which will result into null driver  
	@FindBy(name = "usernameInput")
    public WebElement email;
	
	@FindBy(name = "password")
    public WebElement password;
	
	@FindBy(id = "signIn")
    public WebElement signIn;
	
	@FindBy(className = "d_25yKbJzdQG5JaS-QB9EOCt")
    public WebElement globalErr;
	
	@FindBy(id = "UsernameTextField__errors-usernameInput")
    public WebElement emailErr;
	
	@FindBy(css = "div[class*='mSQlk Error__text']>span")
    public WebElement passwordErr;
	
	
	public void openBrowser() throws IOException {
		FileInputStream f = new FileInputStream("C:\\QA\\Testing\\prop.properties");
		Properties prop = new Properties();
		prop.load(f);

		String browser = prop.getProperty("browser");

		if (browser.equals("Chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equals("Firefox")) {
			driver = new FirefoxDriver();
		} else {
			driver = new SafariDriver();
		}
		
		//Initializing driver 
		PageFactory.initElements(driver, this);
	}

	public void openLoginPage() {
		driver.get(
				"https://auth.scotiaonline.scotiabank.com/online?oauth_key=D97coSY45BQ&oauth_key_signature=eyJraWQiOiJrUFVqdlNhT25GWUVDakpjMmV1MXJvNGxnb2VFeXJJb2tCbU1oX3BiZXNVIiwidHlwIjoiSldUIiwiYWxnIjoiUlMyNTYifQ.eyJyZWZlcmVyIjoiaHR0cHM6XC9cL3d3dy5nb29nbGUuY29tXC8iLCJvYXV0aF9rZXkiOiJEOTdjb1NZNDVCUSIsImNvbnNlbnRfcmVxdWlyZWQiOmZhbHNlLCJyZWRpcmVjdF91cmkiOiJodHRwczpcL1wvd3d3LnNjb3RpYW9ubGluZS5zY290aWFiYW5rLmNvbVwvb25saW5lXC9sYW5kaW5nXC9vYXV0aGxhbmRpbmcuYm5zIiwiZXhwIjoxNjcxNzI4MDMzLCJpYXQiOjE2NzE3MjY4MzMsImp0aSI6Ijc5Nzc2ZTZhLTM5OWQtNDUzYS1hZDI4LTliMWJhYWVmZGM2MCIsImNsaWVudF9pZCI6IjhlZTkwYzM5LTFjNTItNGZmNC04YWU2LWE3YjU0YzUzOTkzMyIsImNsaWVudF9tZXRhZGF0YSI6eyJDaGFubmVsSUQiOiJTT0wiLCJBcHBsaWNhdGlvbkNvZGUiOiJINyJ9LCJpc3N1ZXIiOiJodHRwczpcL1wvcGFzc3BvcnQuc2NvdGlhYmFuay5jb20ifQ.aX15mKWDSGqhJqNkbSRb4RsQKZ1ZLfdxGZ5OpMeVrl5Ydv0QP2Q8cjjVsN9QyLZ99eNdf_PkzeayeiOYlADBqP2D1uCCJE3rCcpu7v7fpVa-PH2dwxPUmjClnnvp15ds_3N-jaHCJ5qhFz1L4MzsqALwso6jqY3fZezyxTQsAR3rFhbvBMVs3_NnP1XtyfEi1r-kv8ii_ntCElHWtJBUs-u2ZWSaxD_XZ-gFuwDOiVW4opR4TjDFmIfkltEtm_fw5dEVK0W61PJQ0QZcXqsYP3IohiBCf83wao_GDjUudN1Sh13wa7LRpAuohsNvnZm5F6CYwPkU3FRhrQQrcy3Y9Q&preferred_environment=");
	}

	public void closeBrowser() {
		driver.quit();
	}

	public void login(String a, String b) throws InterruptedException {
		email.sendKeys(a);
		password.sendKeys(b);
		signIn.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	public String readEmailErr() {
		String actual_msg = emailErr.getText();
		System.out.println(actual_msg);
		return actual_msg;
	}

	public String readPasswordErr() {
		String actual_msg = passwordErr.getText();
		System.out.println(actual_msg);
		return actual_msg;
	}

	public String readGlobalErr() {
		String actual_msg = globalErr.getText();
		System.out.println(actual_msg);
		return actual_msg;
	}

}
