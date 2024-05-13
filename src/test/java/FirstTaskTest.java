import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class FirstTaskTest extends AbstractWebTest{

    @Test
    @Disabled
    void test() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        WebDriver driver = new ChromeDriver();
        chromeOptions.addArguments("--start-maximized");
        //chromeOptions.addArguments("--headless"); неявная функция
        driver.get("https://test-stand.gb.ru/login");
        Thread.sleep(5000l);
        driver.quit();
    }

    @Test
    void authorization() throws InterruptedException {
        WebDriverWait wait = new  WebDriverWait(driver,Duration.ofSeconds(10)); //test2 - явное ожидание
        //WebElement login = driver.findElement(By.xpath("//*[@type='text']")); // test1
        WebElement pass = driver.findElement(By.xpath("//*[@type='password']")); //test1
        WebElement login = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("form#login input[type='text']")));
        //WebElement button = driver.findElement(By.xpath("//*[@class='mdc-button_ripple']"));
        WebElement button = driver.findElement(By.cssSelector("form#login button"));

        login.sendKeys(myLogin);
        pass.sendKeys(myPassword);
        button.click();

        Thread.sleep(5000l);

        List<WebElement> result = driver.findElements(By.partialLinkText("Hello"));
        Assertions.assertEquals(1,result.size());
    }

    @Test
    void createDummyTest() throws InterruptedException {
        //Создаем данные болванчика
        String dummyFirstName = "Dummy4";
        String dummySecondName = "Tester";
        String dummyBirthdate = "10-05-2000";
        long currentTime = System.currentTimeMillis();
        String dummyLoginD = "Dummy" + currentTime;

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Клик на +
        WebElement addButton = driver.findElement(By.xpath("//*[@id=\"create-btn\"]"));
        addButton.click();
        // Имя
        WebElement firstName = driver.findElement(By.xpath("//*[@id=\"upsert-item\"]/div[1]/label"));
        firstName.sendKeys(dummyFirstName);
        //Фамилия
        WebElement secondName = driver.findElement(By.xpath("//*[@id=\"upsert-item\"]/div[2]/label"));
        secondName.sendKeys(dummySecondName);
        // Дата рождения
        WebElement birthdate = driver.findElement(By.xpath("//*[@id=\"upsert-item\"]/div[3]/label"));
        birthdate.sendKeys(dummyBirthdate);
        // Логин
        //WebElement loginD = driver.findElement(By.xpath("//*[@id=\"upsert-item\"]/div[5]/label"));
        //loginD.sendKeys(dummyLoginD);

        WebElement loginD = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[@id=\"upsert-item\"]/div[5]/label")));
        WebElement saveButton = driver.findElement(By.xpath("//*[@id=\"upsert-item\"]/div[8]/button/span"));

        loginD.sendKeys(dummyLoginD);
        saveButton.click();

        Thread.sleep(5000l);

        //WebElement closeButton = driver.findElement(By.xpath("//*[@id=\"app\"]/main/div/div/div[3]/div[2]/div/div[1]/button"));
        //closeButton.click();

        List<WebElement> elements = driver.findElements(By.xpath("//td[contains(., 'Tester Dummy4')]"));
        Assertions.assertEquals(2, elements.size());

        File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshotFile, new File("D:/IT/Селениум/Проект1/untitled/src/main/resources/screenshot.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
