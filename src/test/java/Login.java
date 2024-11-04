import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Login {


    WebDriver driver;

    String baseUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
    String browser = "chrome";

    @BeforeMethod
    public  void setup(){

        if("chrome".equalsIgnoreCase(browser)){
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized","incognito");
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get(baseUrl);
        } else if ("edge".equalsIgnoreCase(browser)) {
            EdgeOptions options = new EdgeOptions();
            options.addArguments("start-maximized","incognito");
            driver = new EdgeDriver(options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get(baseUrl);
        }else
            throw new
                    IllegalArgumentException("unsupported browser: "+browser);


    }

    @Test
    public void loginWithValidCred(){

        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
        driver.findElement(By.xpath("//input[@name='password']"))   .sendKeys("admin123");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }
        @Test
        public void loginWithInvalidCred() {
        

            driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
            driver.findElement(By.xpath("//input[@name='password']"))   .sendKeys("admwwin123");
            driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    @AfterMethod
    public void teardown(){

        if(driver != null){
            driver.quit();
        }
        System.out.println("Browser Closed and Resource Released.");
    }
}
