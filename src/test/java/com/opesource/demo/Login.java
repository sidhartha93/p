package com.opesource.demo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class Login {

	public static void main(String[] args) throws Exception {

		String baseUrl = "https://s1.demo.opensourcecms.com/s/44";
		String username = "opensourcecms";
		String password = "opensourcecms";
		String expectedTitle = "orangehrm - DEMO by opensourcecms.com";
		String actualTitle = "";
		String eUser = "Welcome Admin";
		String aUser = "";

		// Open Browser
		//CHROME
//		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
//		WebDriver driver = new ChromeDriver();
		
		//FireFox
		System.setProperty("webdriver.chrome.driver", "geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		Thread.sleep(3000);

		// Open Url
		driver.navigate().to(baseUrl);
		
		// Verify Url
		actualTitle = driver.getTitle();
		if (actualTitle.contentEquals(expectedTitle)) {
			System.out.println("Successfully opened " + actualTitle);
		} else {
			System.out.println("Please verify the URL");
			driver.close();
		}
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS) ;
		driver.switchTo().frame("preview-frame");
		driver.findElement(By.id("txtUsername")).clear();
		driver.findElement(By.id("txtUsername")).sendKeys(username);
		driver.findElement(By.id("txtPassword")).clear();
		driver.findElement(By.id("txtPassword")).sendKeys(password);

		driver.findElement(By.id("btnLogin")).click();
		Thread.sleep(4000);
		System.out.println("Successfully Logged-In");
		aUser = driver.findElement(By.xpath("/html/body/div[4]/ul/li[1]")).getText();
		if (aUser.contentEquals(eUser)) {
			System.out.println(aUser);
		} else {
			System.out.println("You are not Admin");
		}

		// MouseOver
		WebElement adminmenu = driver.findElement(By.linkText("Admin"));
		Actions am = new Actions(driver);
		am.moveToElement(adminmenu).build().perform();
		WebElement org = driver.findElement(By.linkText("Organization"));
		Actions or = new Actions(driver);
		or.moveToElement(org).build().perform();
		driver.findElement(By.linkText("General Information")).click();
		Thread.sleep(4000);

		// Navigate to General Information Page
		driver.switchTo().frame("rightMenu");
		String title = driver.findElement(By.id("genInfoHeading")).getText();
		System.out.println("Navigated to " + title);
		Thread.sleep(3000);
		WebElement edit = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[10]/input"));
		edit.click();
		WebElement name = driver.findElement(By.xpath("//*[@id=\"organization_name\"]"));
		name.clear();
		name.sendKeys("Sidhartha");
		WebElement tax = driver.findElement(By.xpath("//*[@id=\"organization_taxId\"]"));
		tax.clear();
		tax.sendKeys("232324");
		WebElement rgstn = driver.findElement(By.xpath("//*[@id=\"organization_registraionNumber\"]"));
		rgstn.clear();
		rgstn.sendKeys("342434");
		WebElement phone = driver.findElement(By.xpath("//*[@id=\"organization_phone\"]"));
		phone.clear();
		phone.sendKeys("9040129436");
		WebElement email = driver.findElement(By.xpath("//*[@id=\"organization_email\"]"));
		email.clear();
		email.sendKeys("pandasidhartha93@gmail.com");
		WebElement fax = driver.findElement(By.xpath("//*[@id=\"organization_fax\"]"));
		fax.clear();
		fax.sendKeys("434223422");
		WebElement strt1 = driver.findElement(By.xpath("//*[@id=\"organization_street1\"]"));
		strt1.clear();
		strt1.sendKeys("asdf asdf");
		WebElement strt2 = driver.findElement(By.xpath("//*[@id=\"organization_street2\"]"));
		strt2.clear();
		strt2.sendKeys("qwer qwer");
		WebElement city = driver.findElement(By.xpath("//*[@id=\"organization_city\"]"));
		city.clear();
		city.sendKeys("Vadodara");
		WebElement state = driver.findElement(By.xpath("//*[@id=\"organization_province\"]"));
		state.clear();
		state.sendKeys("Gujarat");
		WebElement zip = driver.findElement(By.xpath("//*[@id=\"organization_zipCode\"]"));
		zip.clear();
		zip.sendKeys("390022");
		WebElement note = driver.findElement(By.xpath("//*[@id=\"organization_note\"]"));
		note.clear();
		note.sendKeys("Test Note");
		driver.findElement(By.xpath("//*[@id=\"organization_country\"]")).sendKeys("India");
		driver.findElement(By.xpath("//*[@id=\"organization_country\"]/option[100]")).click();

		WebElement btn = driver.findElement(By.xpath("//*[@id=\"btnSaveGenInfo\"]"));
		btn.click();

		String msg = driver.findElement(By.id("messagebar")).getText();
		System.out.println(title + " " + msg);
		Thread.sleep(3000);

		driver.switchTo().parentFrame();

		// Navigate to Membership page
		WebElement menu = driver.findElement(By.linkText("Admin"));
		Actions m = new Actions(driver);
		m.moveToElement(menu).build().perform();
		driver.findElement(By.linkText("Memberships")).click();
		Thread.sleep(3000);
		driver.switchTo().frame("rightMenu");
		String title1 = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div/h2")).getText();
		System.out.println("Navigated to " + title1);
		driver.findElement(By.id("btnAdd")).click();
		WebElement name1 = driver.findElement(By.id("membership_name"));
		name1.clear();
		name1.sendKeys("Sidhartha Panda");
		driver.findElement(By.id("btnSave")).click();
		String msg1 = driver.findElement(By.id("messagebar")).getText();
		System.out.println(title1 + " " + msg1);
		driver.switchTo().parentFrame();

		// Logout
		driver.findElement(By.linkText("Logout")).click();
		System.out.println("Successfully Logged out");

		// Close the Browser
		driver.quit();
	}
}
