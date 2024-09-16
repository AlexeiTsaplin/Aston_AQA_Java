package lesson16.steps;

import lesson16.dto.PaymentSectionDto;
import lesson16.pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static lesson16.WebDriverInstance.driver;

import java.time.Duration;

public class HomePageSteps {

    private final HomePage homePage = new HomePage();
    private final HomePage.PaymentSection paymentSection = new HomePage.PaymentSection();


    public void acceptCookies() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        try {
            WebElement acceptCookiesButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#cookie-agree")));
            acceptCookiesButton.click();

        } catch (Exception e) {
            System.out.println("Куки не появились.");
        }
    }

    public String textSumPlaceholder() {
        return paymentSection.getSumField().getAttribute("placeholder");
    }

    public String phoneServicePlaceholder() {
        return paymentSection.getSpecialField("Услуги связи").getAttribute("placeholder");
    }

    public String phoneInternetPlaceholder() {
        return paymentSection.getSpecialField("Домашний интернет").getAttribute("placeholder");
    }

    public String scoreInstalmentPlaceholder() {
        return paymentSection.getSpecialField("Рассрочка").getAttribute("placeholder");
    }

    public String scoreDebtPlaceholder() {
        return paymentSection.getSpecialField("Задолженность").getAttribute("placeholder");
    }

    public String emailPlaceholder() {
        return paymentSection.getEmailField().getAttribute("placeholder");
    }

    public void scrollToPaymentSection() {
        WebElement paymentSection = homePage.getPaymentSection();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", paymentSection);
    }

    public void continueButton() {
        paymentSection.getContinueButton().click();
    }

    public void switchToPaidFrame() {
        driver.switchTo().frame(homePage.getSecondFrame());

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement pauseWait = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("google-pay-button")));

    }

    public String sumBynText() {
        return paymentSection.getSumByn().getText();
    }

    public String payDetailsText() {
        return paymentSection.getPayDetails().getText();
    }

    public WebElement googlePayButton() {
        return paymentSection.getGooglePayButton();
    }

    public WebElement yandexPayButton() {
        return paymentSection.getYandexPayButton();
    }

    public String cardNumberPlaceholder() {
        return paymentSection.getCardNumber().getText();
    }

    public String cardExpiredPlaceholder() {
        return paymentSection.getCardExpired().getText();
    }

    public String codeCvcPlaceholder() {
        return paymentSection.getCodeCvc().getText();
    }

    public String cardHolderPlaceholder() {
        return paymentSection.getCardHolder().getText();
    }

    public WebElement logoVisa() {
        return paymentSection.getLogoVisa();
    }

    public WebElement logoMasterCard() {
        return paymentSection.getLogoMasterCard();
    }

    public WebElement logoBelkart() {
        return paymentSection.getLogoBelkart();
    }

    public WebElement logoMir() {
        return paymentSection.getLogoMir();
    }

    public WebElement logoMaestro() {
        return paymentSection.getLogoMaestro();
    }

    public WebElement paymentButton() {
        return paymentSection.getPaymentButton();
    }

    public String paymentButtonText() {
        return paymentSection.getPaymentButton().getText();
    }

    public void clickDropdownButton() {
        paymentSection.getDropdownButton().click();
    }

    public void selectPaymentType(String paymentType) {
        paymentSection.getPaymentType(paymentType).click();
    }

    public void fillSpecialField(String paymentType, String text) {
        paymentSection.getSpecialField(paymentType).sendKeys(text);
    }

    public void fillSumField(String sumAmount) {
        paymentSection.getSumField().sendKeys(sumAmount);
    }

    public void fillEmailField(String email) {
        paymentSection.getEmailField().sendKeys(email);
    }

    public void fillPaymentSection(PaymentSectionDto paymentSectionDto) {
        selectPaymentType(paymentSectionDto.getPaymentType());
        fillSpecialField(paymentSectionDto.getPaymentType(), paymentSectionDto.getSpecialText());
        fillSumField(paymentSectionDto.getSum());
        fillEmailField(paymentSectionDto.getEmail());
    }
}
