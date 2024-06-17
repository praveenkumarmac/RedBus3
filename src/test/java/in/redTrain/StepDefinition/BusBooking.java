package in.redTrain.StepDefinition;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BusBooking {
	public static WebDriver driver;
	public static WebDriverWait Wait;

	@Given("Launch the browser and appilication {string}")
	public void launch_the_browser_and_appilication(String url) {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();		
		options.addArguments("disable-notifications");
		options.addArguments("disable-popups");
		options.addArguments("start-maximized");
		driver = new ChromeDriver(options);
		//String url = "https://www.redbus.in";
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(240));
	}
	
	
	@Given("User enters the value in from place with one dim list")
	public void user_enters_the_value_in_from_place_with_one_dim_list(DataTable dataTable) {
		List<String> list = dataTable.asList();
		//String fromPlace = "Chennai";
		String fromPlace =  list.get(0);
		WebElement from = driver.findElement(By.xpath("//label[text()='From']/preceding-sibling::input"));
		Wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		from.sendKeys(fromPlace);
		WebElement frompl = driver.findElement(By.xpath("//text[text()='"+fromPlace+"']/parent::div[contains(@class,'grvhsy')]"));
		frompl.click();
	}

	@Given("User enters the value in to place with one dim map")
	public void user_enters_the_value_in_to_place_with_one_dim_map(DataTable dataTable) throws InterruptedException {
		Map<String, String> map = dataTable.asMap(String.class, String.class);
		String toPlace = map.get("location1");
		//String toPlace = "Hyderabad";
		//WebElement to = driver.findElement(By.id("dest"));
		//Thread.sleep(6000);
		WebElement to = driver.findElement(By.xpath("//input[@id='dest']"));	
		//Wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		//Thread.sleep(6000);
		Actions act = new Actions(driver);
		act.sendKeys(to, toPlace).build().perform();
		//to.sendKeys(toPlace);
		WebElement topl = driver.findElement(By.xpath("//text[text()='"+toPlace+"']/parent::div[contains(@class,'grvhsy')]"));
		topl.click();
	}

	@When("User selects a data  in the Date DropDown")
	public void user_selects_a_data_in_the_date_drop_down() throws InterruptedException {							
		WebElement date = driver.findElement(By.xpath("//span[text()='18' and contains(@class,'dkWAbH')]"));
		date.click();
	}

	@When("User clicks on search button")
	public void user_clicks_on_search_button() {
		WebElement searchButton = driver.findElement(By.xpath("//button[contains(text(),'SEARCH')]"));
		searchButton.click();
	}

	@Then("Validate the bus displayed in the UI")
	public void validate_the_bus_displayed_in_the_ui() {
		List<WebElement> buses = driver.findElements(By.xpath("//div[contains(@class,'travels lh-24 f-bold d-color')]"));
		List<WebElement> depTimes = driver.findElements(By.xpath("//div[contains(@class,'dp-time f-19 d-color f-bold')]"));		
		List<WebElement> arrTimes = driver.findElements(By.xpath("//div[contains(@class,'bp-time f-19 d-color disp-Inline')]"));
		List<WebElement> fare = driver.findElements(By.xpath("//span[contains(@class,'f-19')]"));
		int size = buses.size(); 
		for(int i=0; i<size;i++) {
			//System.out.println(buses.size());
			if(i==buses.size()-1) {
				//System.out.println(buses.size());
				JavascriptExecutor js = (JavascriptExecutor)driver;
				js.executeScript("arguments[0].scrollIntoView(true)", buses.get(i));
				buses = driver.findElements(By.xpath("//div[contains(@class,'travels lh-24 f-bold d-color')]"));
				depTimes = driver.findElements(By.xpath("//div[contains(@class,'dp-time f-19 d-color f-bold')]"));		
				arrTimes = driver.findElements(By.xpath("//div[contains(@class,'bp-time f-19 d-color disp-Inline')]"));
				fare = driver.findElements(By.xpath("//span[contains(@class,'f-19')]"));
				size = buses.size();
			}
			else {
				//System.out.println(size);
				String bus = buses.get(i).getText();
				//System.out.println(bus);
				String depTime = depTimes.get(i).getText();
				String arrTime = arrTimes.get(i).getText();
				String eachFare = fare.get(i).getText();
				System.out.println(bus+" "+depTime+" "+arrTime+" "+eachFare);
			}										
		}
	}
}
