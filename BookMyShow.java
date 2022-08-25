package marathon;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BookMyShow {
public static void main(String[] args) throws InterruptedException, IOException {
		
		WebDriverManager.chromedriver().setup();
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notifications");

		// Launch Edge / Chrome
		ChromeDriver driver = new ChromeDriver(option);

		// Load https://in.bookmyshow.com/
		driver.get("https://in.bookmyshow.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		///Type the city as "Hyderabad" in Select City
		driver.findElement(By.xpath("//input[@placeholder='Search for your city']")).sendKeys("Hyderabad");
		driver.findElement(By.xpath("//strong[text()='Hyderabad']")).click();

		// Confirm the URL has got loaded with Hyderabad
		String currentUrl = driver.getCurrentUrl();

		if (currentUrl.contains("hyderabad")) {
			System.out.println("The url contains hyderabad");
		} else {
			System.out.println("The url doesn't contains hyderabad");
		}

		// Search for your favorite movie
		driver.findElement(By.xpath("//span[contains(text(),'Search for Movies')]")).click();
		driver.findElement(By.xpath("//input[contains(@placeholder,'Search for Movies')]")).sendKeys("Sita Ramam");
		driver.findElement(By.xpath("//strong[text()='Sita Ramam']")).click();

		// Click Book Tickets
		driver.findElement(By.xpath("//span[text()='Book tickets']")).click();


		// Print the name of the theater displayed first
		String theaterName = driver.findElement(By.xpath("//div[@class='__name ']/div/a")).getText().substring(0, 11);
		System.out.println("Theater Name : "+theaterName);

		// Click on the info of the theater
		driver.findElement(By.linkText("AMB Cinemas: Gachibowli")).click();

		// Click on details
		driver.findElement(By.xpath("//section[@class='details-wrapper']")).click();

		// Confirm if there is a parking facility in the theater
		Thread.sleep(2000);
		String parkingFacility = driver.findElement(By.xpath("//div[contains(text(),'Parking Facility')]")).getText();

		if (parkingFacility.contains("Parking")) {
			System.out.println("It contains parking facility");
		} else {
			System.out.println("It does not contains parking facility");
		}

		// Close the theater popup
		driver.findElement(By.xpath("//section[@class='details-wrapper']")).click();
		
		// Navigate to previous page
		driver.navigate().back();

		// Click on the first green (available) timing
		driver.findElement(By.xpath("(//div[@class='showtime-pill-container _filling'])")).click();

		// Click Accept
		driver.findElement(By.id("btnPopupAccept")).click();

		// Choose 1 Seat
		driver.findElement(By.xpath("//div[@class='__list']//li")).click();
		
		// Click Select Seats
		driver.findElement(By.id("proceed-Qty")).click();
		
		// Choose first available ticket
		driver.findElement(By.xpath("//a[@class='_available']")).click();
		
		// Click Pay
		driver.findElement(By.id("btmcntbook")).click();

		// Print subTotal
		Thread.sleep(5000);
		String subTotal = driver.findElement(By.id("subTT")).getText();
		System.out.println("SubTotal : "+subTotal);

		// Take screenshot 
		  File source = driver.getScreenshotAs(OutputType.FILE);
		  File destination = new File("./snaps/number.png"); 
		  FileUtils.copyFile(source, destination);
		 
		// Close browser  
		driver.close();

	}


}
