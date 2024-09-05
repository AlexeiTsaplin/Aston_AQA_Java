package lesson15MTSTest;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

class TestMTSby {

    WebDriver driver;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.mts.by/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        try {
            WebElement acceptCookiesButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#cookie-agree")));
            acceptCookiesButton.click();

        } catch (Exception e) {
            System.out.println("Куки не появились.");
        }

    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
    @DisplayName("Проверка корректности названия блока 'Онлайн пополнение без комиссии' ")
    void testName() {
        WebElement blockTitle = driver.findElement(By.cssSelector("#pay-section"));

        String blockText = blockTitle.getText();

        String expectedPart1 = "Онлайн пополнение";
        String expectedPart2 = "без комиссии";

        boolean isBothPartsPresent = blockText.contains(expectedPart1) && blockText.contains(expectedPart2);

        assert isBothPartsPresent : "Название блока не содержит ожидаемые части: \"" + expectedPart1 + "\" и \"" + expectedPart2 + "\"";
    }

    @Test
    @DisplayName("Проверка наличия логотипов платежных систем")
    void testIMG() {
        List<WebElement> logo = driver.findElements(By.cssSelector(".pay__partners img"));

        if (logo.size() == 5) {
            System.out.println("Найдено 5 изображений логотипов на странице.");
        } else {
            System.out.println("Количество найденных логотипов: " + logo.size());
        }
    }

    @Test
    @DisplayName("Проверка работы ссылки 'Подробнее о сервисе'")
    void testLink() {
        WebElement detailsLink = driver.findElement(By.linkText("Подробнее о сервисе"));
        detailsLink.click();
        String currentUrl = driver.getCurrentUrl();
        assert currentUrl.contains("https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/");
    }

    @Test
    @DisplayName("Проверка работы кнопки 'Продолжить'")
    void testContinueButton() {
        WebElement phoneNumber = driver.findElement(By.id("connection-phone"));
        phoneNumber.click();
        phoneNumber.sendKeys("297777777");

        WebElement sumRub = driver.findElement(By.id("connection-sum"));
        sumRub.click();
        sumRub.sendKeys("15");

        WebElement continueButton = driver.findElement(By.cssSelector("#pay-connection > button"));
        continueButton.click();

        WebElement elementInsideNewFrame = driver.findElement(By.className("bepaid-app"));

        assert elementInsideNewFrame.isDisplayed();
    }
}
