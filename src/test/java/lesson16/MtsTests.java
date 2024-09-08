package lesson16;

import lesson16.dto.PaymentSectionDto;
import lesson16.pages.HomePage;
import lesson16.steps.HomePageSteps;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class MtsTests {

    private HomePageSteps steps;
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = WebDriverInstance.getInstance(); // Предположим, у вас есть класс DriverManager для получения экземпляра драйвера
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.mts.by/");

        // Создаем экземпляр HomePage и передаем его в HomePageSteps
        HomePage homePage = new HomePage(driver);
        steps = new HomePageSteps(homePage, driver); // Передаём homePage и driver в шаги
    }

    @DataProvider(name = "paymentData")
    public static Object[][] paymentData() {
        return new Object[][] {
                {"Услуги связи", "297777777", "34", "connection@mail.ru"},
                {"Домашний интернет", "297777777", "48", "internet@mail.ru"},
                {"Рассрочка", "446564645387", "75", "installment@mail.ru"},
                {"Задолженность", "207315158812", "62", "debt@mail.ru"}
        };
    }

    @DataProvider(name = "paymentDataService")
    public static Object[][] paymentDataService() {
        return new Object[][] {
                {"Услуги связи", "297777777", "34", "connection@mail.ru"},
        };
    }

    @Test(description = "check payment type Debt", dataProvider = "paymentData")
    public void paymentTypeDebtTest(String type, String specText, String sum, String email) {
        PaymentSectionDto paymentSectionDto = PaymentSectionDto.builder()
                .paymentType(type)
                .specialText(specText)
                .sum(sum)
                .email(email)
                .build();
        steps.acceptCookies();
        steps.scrollToPaymentSection();
        steps.clickDropdownButton();
        steps.fillPaymentSection(paymentSectionDto);
    }

    @Test(description = "check placeholders correct")
    public void placeholdersCorrectTest() {
        steps.acceptCookies();
        steps.scrollToPaymentSection();
        steps.clickDropdownButton();
        steps.selectPaymentType("Услуги связи");

        Assert.assertEquals(steps.TextSumPlaceholder(), "Сумма");
        Assert.assertEquals(steps.PhoneServicePlaceholder(), "Номер телефона");
        Assert.assertEquals(steps.emailPlaceholder(), "E-mail для отправки чека");

        steps.clickDropdownButton();
        steps.selectPaymentType("Домашний интернет");

        Assert.assertEquals(steps.TextSumPlaceholder(), "Сумма");
        Assert.assertEquals(steps.PhoneInternetPlaceholder(), "Номер абонента");
        Assert.assertEquals(steps.emailPlaceholder(), "E-mail для отправки чека");

        steps.clickDropdownButton();
        steps.selectPaymentType("Рассрочка");

        Assert.assertEquals(steps.TextSumPlaceholder(), "Сумма");
        Assert.assertEquals(steps.ScoreInstalmentPlaceholder(), "Номер счета на 44");
        Assert.assertEquals(steps.emailPlaceholder(), "E-mail для отправки чека");

        steps.clickDropdownButton();
        steps.selectPaymentType("Задолженность");

        Assert.assertEquals(steps.TextSumPlaceholder(), "Сумма");
        Assert.assertEquals(steps.ScoreDebtPlaceholder(), "Номер счета на 2073");
        Assert.assertEquals(steps.emailPlaceholder(), "E-mail для отправки чека");
    }

    @Test(description = "check Paid App", dataProvider = "paymentDataService")
    public void paidAppTest(String type, String specText, String sum, String email) {
        PaymentSectionDto paymentSectionDto = PaymentSectionDto.builder()
                .paymentType(type)
                .specialText(specText)
                .sum(sum)
                .email(email)
                .build();
        steps.acceptCookies();
        steps.scrollToPaymentSection();
        steps.clickDropdownButton();
        steps.fillPaymentSection(paymentSectionDto);
        steps.ContinueButton();
    } // прошу прощение но ночью не с тестовым номером ни с другими, у меня не получилось открыть Фрейм после кнопки Продолжить.

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }
}
