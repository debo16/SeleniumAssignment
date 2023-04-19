package makemytrip;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MakeMyTrip {
	public static String url ="https://www.makemytrip.com/";
	ExtentReports er;
	ExtentHtmlReporter ehr;
	ExtentTest et;
	WebDriver driver;

@BeforeTest
	public void bt() {
	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
	driver.get(url);
	er = new ExtentReports();
	ehr = new ExtentHtmlReporter("extent.html");
	er.attachReporter(ehr);
	driver.manage().window().maximize();

}
@Test
public void HolidayPackage() throws InterruptedException {
	et = er.createTest("HolidayPackage Report");
	Thread.sleep(10000);
	WebElement holidaypackages = driver.findElement(By.linkText("Holiday Packages"));
	Thread.sleep(5000);
	holidaypackages.click();
	et.pass("Clicked on HolidayPackage");
	Thread.sleep(11000);
	WebElement departcity = driver.findElement(By.xpath("//*[@for='fromCity']"));
	Thread.sleep(3000);
	departcity.click();
	et.pass("clicked on fromCity");
	Thread.sleep(5000);
	List<WebElement> Bangalore = driver.findElements(By.xpath("//li[contains(text(),'Bangalore')]"));
	Thread.sleep(5000);
	Bangalore.get(0).click();
	et.pass("entered Bangalore in FromCity ");

	WebElement destcity = driver.findElement(By.id("toCity"));
	Thread.sleep(5000);
	destcity.click();
	et.pass("clicked on ToCity ");
	Thread.sleep(5000);
	WebElement search = driver.findElement(By.xpath("//input[@class='dest-search-input']"));
	Thread.sleep(5000);
	search.click();
	et.pass("clicked on search tab in ToCity ");
	Thread.sleep(15000);
	search.sendKeys("Singapore");
	et.pass("sent value as Singapore");
	Thread.sleep(9000);
	List<WebElement> Singapore = driver.findElements(By.xpath("//div[@class='dest-city-name']"));
	Thread.sleep(8000);
	Singapore.get(0).click();
	et.pass("accepted value as Singapore");
	Thread.sleep(5000);
	List<WebElement> pickdate = driver.findElements(By.xpath("//p[contains(text(),'18')]"));
	Thread.sleep(5000);
	pickdate.get(1).click();
	et.pass("selected date");
	Thread.sleep(5000);
	driver.findElement(By.xpath("//button[@class='applyBtn']")).click();
	et.pass("got clicked on apply button");
	Thread.sleep(5000);

	WebElement filter = driver.findElement(By.xpath("//div[(text() ='Filters')]"));
	Thread.sleep(1000);
	filter.click();


	driver.findElement(By.xpath("//button[@id='search_button']")).click();
	Thread.sleep(15000);
	et.pass("clicked on Search button");

	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("window.scrollBy(0,6000)");
	Thread.sleep(10000);

	js.executeScript("window.scrollBy(0,1000)");
	Thread.sleep(10000);



	List<WebElement> element = driver.findElements(By.xpath("//button[@class='slick-arrow slick-next ']"));
	Thread.sleep(4000);
	element.get(4).click();
	et.pass("clicked on slick-arrow slick-next");
	Thread.sleep(4000);
	driver.findElement(By.xpath("//h4[contains(text(),'Relaxing Singapore Getaway')]")).click();
	Thread.sleep(20000);
	et.pass("selected Relaxing Singapore Getaway package ");


	//Loop through until we find a new window handle
	String originalWindow = driver.getWindowHandle();

	for (String windowHandle : driver.getWindowHandles()) {
	    if(!originalWindow.contentEquals(windowHandle)) {
	        driver.switchTo().window(windowHandle);
	        break;
	    }
	}
	js.executeScript("window.scrollBy(0,500)");

	Thread.sleep(3000);
	driver.findElement(By.xpath("//*[@id=\"initeraryNav\"]/li[3]")).click();
	Thread.sleep(3000);
	et.pass("Clicked on hotel");
	driver.findElement(By.xpath("//span[@id='change']")).click();
	et.pass("clicked on change");
	Thread.sleep(25000);


	driver.findElement(By.xpath("//*[@id='starRatingContainer_star_rating']/div/div[3]/span[1]")).click();
	Thread.sleep(5000);


	List<WebElement> element1 = driver.findElements(By.xpath("//span[text()='Select']"));
	Thread.sleep(3000);

	element1.get(0).click();
	Thread.sleep(5000);
	et.pass("selected hotel");

	driver.findElement(By.xpath("//p[text()='Update Package']")).click();
	
	Thread.sleep(10000);
	et.pass("updated package");


	WebElement e = driver.findElement(By.xpath("//div[@class='hotel-row-body']"));
	Thread.sleep(3000);

	WebElement eleSelected1= driver.findElement(By.xpath("//div[@class='hotel-row-body']"));
	String c = eleSelected1.getText();


	Assert.assertTrue( c.contains("PARKROYAL COLLECTION Pickering, Singapore"));
	et.info("validation done for selected hotel");
	WebElement actionplan = driver.findElement(By.xpath("//*[@id=\"initeraryNav\"]/li[1]"));
	Thread.sleep(3000);
	actionplan.click();
	Thread.sleep(3000);
	js.executeScript("window.scrollBy(0,800)");
	List<WebElement> activity = driver.findElements(By.xpath("//*[@id=\"chooseAndAddBtn\"]"));
	Thread.sleep(3000);
	activity.get(0).click();
	et.pass("clicked to add activity");
	Thread.sleep(4000);
	WebElement searchact = driver.findElement(By.xpath("//input[@placeholder='Search by Activity name']"));
	Thread.sleep(2000);
	searchact.sendKeys("mirr");
	et.pass("searched for mirror maze activity");
	Thread.sleep(2000);
	driver.findElement(By.xpath("//span[contains(text(),'Select')]")).click();
	Thread.sleep(2000);
	et.pass("selected activity");
	driver.findElement(By.xpath("//p[contains(text(),'Update Package')]")).click();
	et.info("updated package after selecting activity");

	Thread.sleep(5000);
	String activity2 = driver.findElement(By.xpath("//p[@class='activity-row-details-title']")).getText();
	Assert.assertTrue( activity2.contains("Mirror Maze"));
	et.info("validated activity name");
	er.flush();
	driver.quit();
}

}


