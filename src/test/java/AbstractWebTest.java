import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class AbstractWebTest {
    static WebDriver driver;
    String myLogin = "GB202311d632412";
    String myPassword = "d060a954e4";
    @BeforeAll
    static void initElement() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        driver = new ChromeDriver();
        //chromeOptions.addArguments("--headless"); неявная функция
        driver.get("https://test-stand.gb.ru/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));//неявное ожидание(ответа веб-приложения)
    }
    @AfterAll
    static void closeApp(){
        if (driver != null) {
            driver.quit();
        }
    }
}
