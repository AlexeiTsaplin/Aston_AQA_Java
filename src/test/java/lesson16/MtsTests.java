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
        driver = WebDriverInstance.getInstance();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.mts.by/");

        HomePage homePage = new HomePage(driver);
        steps = new HomePageSteps(homePage, driver);
    }

    @DataProvider(name = "paymentData")
    public static Object[][] paymentData() {
        return new Object[][]{
                {"Услуги связи", "297777777", "34", "connection@mail.ru"},
                {"Домашний интернет", "297777777", "48", "internet@mail.ru"},
                {"Рассрочка", "446564645387", "75", "installment@mail.ru"},
                {"Задолженность", "207315158812", "62", "debt@mail.ru"}
        };
    }

    @DataProvider(name = "paymentDataService")
    public static Object[][] paymentDataService() {
        return new Object[][]{
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

        Assert.assertEquals(steps.textSumPlaceholder(), "Сумма");
        Assert.assertEquals(steps.phoneServicePlaceholder(), "Номер телефона");
        Assert.assertEquals(steps.emailPlaceholder(), "E-mail для отправки чека");

        steps.clickDropdownButton();
        steps.selectPaymentType("Домашний интернет");

        Assert.assertEquals(steps.textSumPlaceholder(), "Сумма");
        Assert.assertEquals(steps.phoneInternetPlaceholder(), "Номер абонента");
        Assert.assertEquals(steps.emailPlaceholder(), "E-mail для отправки чека");

        steps.clickDropdownButton();
        steps.selectPaymentType("Рассрочка");

        Assert.assertEquals(steps.textSumPlaceholder(), "Сумма");
        Assert.assertEquals(steps.scoreInstalmentPlaceholder(), "Номер счета на 44");
        Assert.assertEquals(steps.emailPlaceholder(), "E-mail для отправки чека");

        steps.clickDropdownButton();
        steps.selectPaymentType("Задолженность");

        Assert.assertEquals(steps.textSumPlaceholder(), "Сумма");
        Assert.assertEquals(steps.scoreDebtPlaceholder(), "Номер счета на 2073");
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
        steps.continueButton();
        steps.switchToPaidFrame();

        Assert.assertEquals("34.00 BYN", steps.sumBynText());
        Assert.assertEquals("Оплата: Услуги связи Номер:375297777777", steps.payDetailsText());

        Assert.assertTrue(steps.googlePayButton().isDisplayed(), "Google Pay отсутвует");
        Assert.assertTrue(steps.yandexPayButton().isDisplayed(), "Yandex Pay отсутвует");

        Assert.assertEquals("Номер карты", steps.cardNumberPlaceholder());
        Assert.assertEquals("Срок действия", steps.cardExpiredPlaceholder());
        Assert.assertEquals("CVC", steps.codeCvcPlaceholder());
        Assert.assertEquals("Имя держателя (как на карте)", steps.cardHolderPlaceholder());

        Assert.assertTrue(steps.logoVisa().isDisplayed(), "logo Visa отсутвует");
        Assert.assertTrue(steps.logoMasterCard().isDisplayed(), "logo MasterCard отсутвует");
        Assert.assertTrue(steps.logoBelkart().isDisplayed(), "logo Belkart отсутвует");
        Assert.assertTrue(steps.logoMir().isDisplayed(), "logo MIR отсутвует");
        Assert.assertTrue(steps.logoMaestro().isDisplayed(), "logo Maestro отсутвует");

        Assert.assertTrue(steps.paymentButton().isDisplayed(), "Payment Button отсутвует");
        Assert.assertEquals("Оплатить 34.00 BYN", steps.paymentButtonText());
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }
}
