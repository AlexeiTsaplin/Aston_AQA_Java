package lesson16;

import lesson16.dto.PaymentSectionDto;
import lesson16.steps.HomePageSteps;

import org.junit.jupiter.api.BeforeEach;
import static lesson16.WebDriverInstance.driver;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;

public class MtsTests {

    private HomePageSteps steps;

    @BeforeEach
    public void setUp() {
        steps = new HomePageSteps();
        driver = WebDriverInstance.getInstance();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.mts.by/");
    }

    @AfterAll
    public static void tearDown() {
        driver.close();
        driver.quit();
    }

    static Stream<Arguments> paymentData() {
        return Stream.of(
                Arguments.of("Услуги связи", "297777777", "34", "connection@mail.ru"),
                Arguments.of("Домашний интернет", "297777777", "48", "internet@mail.ru"),
                Arguments.of("Рассрочка", "446564645387", "75", "installment@mail.ru"),
                Arguments.of("Задолженность", "207315158812", "62", "debt@mail.ru")
        );
    }

    static Stream<Arguments> paymentDataService() {
        return Stream.of(
                Arguments.of("Услуги связи", "297777777", "34", "connection@mail.ru")
        );
    }

    @ParameterizedTest(name = "Проверка типа платежа")
    @MethodSource("paymentData")
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

    @Test
    @DisplayName("Проверка корректности плейсхолдеров Услуги связи")
    public void placeholdersCorrectTestConnection() {
        steps.acceptCookies();
        steps.scrollToPaymentSection();
        steps.clickDropdownButton();
        steps.selectPaymentType("Услуги связи");

        Assertions.assertEquals("Сумма", steps.textSumPlaceholder());
        Assertions.assertEquals("Номер телефона", steps.phoneServicePlaceholder());
        Assertions.assertEquals("E-mail для отправки чека", steps.emailPlaceholder());
    }

    @Test
    @DisplayName("Проверка корректности плейсхолдеров Домашний интернет")
    public void placeholdersCorrectTestInternet() {
        steps.acceptCookies();
        steps.scrollToPaymentSection();
        steps.clickDropdownButton();
        steps.selectPaymentType("Домашний интернет");

        Assertions.assertEquals("Сумма", steps.textSumPlaceholder());
        Assertions.assertEquals("Номер абонента", steps.phoneInternetPlaceholder());
        Assertions.assertEquals("E-mail для отправки чека", steps.emailPlaceholder());
    }

    @Test
    @DisplayName("Проверка корректности плейсхолдеров Рассрочка")
    public void placeholdersCorrectTestInstalment() {
        steps.acceptCookies();
        steps.scrollToPaymentSection();
        steps.clickDropdownButton();
        steps.selectPaymentType("Рассрочка");

        Assertions.assertEquals("Сумма", steps.textSumPlaceholder());
        Assertions.assertEquals("Номер счета на 44", steps.scoreInstalmentPlaceholder());
        Assertions.assertEquals("E-mail для отправки чека", steps.emailPlaceholder());
    }

    @Test
    @DisplayName("Проверка корректности плейсхолдеров Задолженность")
    public void placeholdersCorrectTestDebt() {
        steps.acceptCookies();
        steps.scrollToPaymentSection();
        steps.clickDropdownButton();
        steps.selectPaymentType("Задолженность");

        Assertions.assertEquals("Сумма", steps.textSumPlaceholder());
        Assertions.assertEquals("Номер счета на 2073", steps.scoreDebtPlaceholder());
        Assertions.assertEquals("E-mail для отправки чека", steps.emailPlaceholder());
    }

    @ParameterizedTest(name = "Проверка полей оплаты для Услуги связи")
    @MethodSource("paymentDataService")
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

        Assertions.assertEquals("34.00 BYN", steps.sumBynText());
        Assertions.assertEquals("Оплата: Услуги связи Номер:375297777777", steps.payDetailsText());

        Assertions.assertTrue(steps.googlePayButton().isDisplayed(), "Google Pay отсутвует");
        Assertions.assertTrue(steps.yandexPayButton().isDisplayed(), "Yandex Pay отсутвует");

        Assertions.assertEquals("Номер карты", steps.cardNumberPlaceholder());
        Assertions.assertEquals("Срок действия", steps.cardExpiredPlaceholder());
        Assertions.assertEquals("CVC", steps.codeCvcPlaceholder());
        Assertions.assertEquals("Имя держателя (как на карте)", steps.cardHolderPlaceholder());

        Assertions.assertTrue(steps.logoVisa().isDisplayed(), "logo Visa отсутвует");
        Assertions.assertTrue(steps.logoMasterCard().isDisplayed(), "logo MasterCard отсутвует");
        Assertions.assertTrue(steps.logoBelkart().isDisplayed(), "logo Belkart отсутвует");
        Assertions.assertTrue(steps.logoMir().isDisplayed(), "logo MIR отсутвует");
        Assertions.assertTrue(steps.logoMaestro().isDisplayed(), "logo Maestro отсутвует");

        Assertions.assertTrue(steps.paymentButton().isDisplayed(), "Payment Button отсутвует");
        Assertions.assertEquals("Оплатить 34.00 BYN", steps.paymentButtonText());
    }
}
