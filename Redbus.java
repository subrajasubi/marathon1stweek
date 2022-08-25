package marathon;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Redbus {

	public static void main(String[] args) throws InterruptedException, IOException {
	

				WebDriverManager.chromedriver().setup();
				ChromeOptions option = new ChromeOptions();
				option.addArguments("--disable-notifications");

				// Launch Firefox / Chrome
				ChromeDriver driver = new ChromeDriver(option);

				// Load https://www.redbus.in/
				driver.get("https://www.redbus.in/");
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

				// Type "Chennai" in the FROM text box
				driver.findElement(By.id("src")).sendKeys("Chennai");
				driver.findElement(By.xpath("//li[text()='Chennai']")).click();

				// Type "Bangalore" in the TO text box
				driver.findElement(By.id("dest")).sendKeys("Bangalore");
				driver.findElement(By.xpath("//li[text()='Bangalore']")).click();

				// Select tomorrow's date in the Date field
				driver.findElement(By.id("onward_cal")).click();
				driver.findElement(By.xpath("//label[text()='Date']")).click();

				// Click Search Buses
				driver.findElement(By.id("search_btn")).click();

				// Print the number of buses shown as results (104 Buses found)
				String busCount = driver.findElement(By.xpath("//span[@class='f-bold busFound']")).getText();
				System.out.println("Number of buses : "+busCount);

				// Close the redbus primo notification
				Thread.sleep(2000);
				driver.findElement(By.xpath("//div[@class='close-primo']")).click();

				// Choose 'Seater' checkbox in the left menu
				driver.findElement(By.xpath("//label[text()='After 6 pm']")).click();
				
				
				//Print the name of second resulting bus
				List<WebElement> busNames =driver.findElements(By.xpath("//div[@class='travels lh-24 f-bold d-color']")); 
				List<String> busName = new ArrayList<String>(); 
				  for (WebElement eachName :busNames) { 
					  busName.add(eachName.getText());
					  }
				System.out.println("Name of second resulting bus : "+busName.get(1));
				  
				  
				//Click the VIEW SEATS of that bus
				driver.findElement(By.xpath(("(//div[@class='button view-seats fr'])[2]"))).click();
				  
				//Take screenshot and close browser 
				Thread.sleep(1000);
				File source = driver.getScreenshotAs(OutputType.FILE); 
				File destination = new File("./snaps/seats.png"); 
				FileUtils.copyFile(source, destination);
				 
				// Close the browser
				driver.close();

			

	}

}
