package lesson16.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage {

    private WebDriver driver;
    private final By paymentSection = By.xpath("//section[@class='pay']");
    private final By secondFrame = By.className("bepaid-iframe");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getPaymentSection() {
        return driver.findElement(paymentSection);
    }

    public WebElement getSecondFrame() {
        return driver.findElement(secondFrame);
    }

    public class PaymentSection {

        private final By dropdownButton = By.xpath("//div[@class='select']");
        private final By paymentTypes = By.xpath("//div[@class='select']//ul[@class='select__list']/li/p");
        private final By sumField = By.xpath("//form[@class='pay-form opened']//input[@placeholder='Сумма']");
        private final By emailField = By.xpath("//form[@class='pay-form opened']//input[@placeholder='E-mail для отправки чека']");
        private final By continueButton = By.xpath("//button[text()='Продолжить']");

        private final By sumByn = By.className("pay-description__cost");

        public WebElement getSumByn() {
            return driver.findElement(sumByn);
        }

        private final By payDetails = By.className("pay-description__text");

        public WebElement getPayDetails() {
            return driver.findElement(payDetails);
        }

        private final By googlePayButton = By.id("google-pay-button");

        public WebElement getGooglePayButton() {
            return driver.findElement(googlePayButton);
        }

        private final By yandexPayButton = By.id("yandex-button");

        public WebElement getYandexPayButton() {
            return driver.findElement(yandexPayButton);
        }

        private final By cardNumber = By.xpath("//*[@autocomplete='cc-number']/following::label[1]");
        private final By cardExpired = By.xpath("//*[@autocomplete='cc-exp']/following::label[1]");
        private final By codeCvc = By.xpath("//*[@autocomplete='cc-csc']/following::label[1]");
        private final By cardHolder = By.xpath("//*[@autocomplete='cc-name']/following::label[1]");

        private final By logoVisa = By.xpath("//img[contains(@src,'visa-system')]");
        private final By logoMasterCard = By.xpath("//img[contains(@src,'mastercard-system')]");
        private final By logoBelkart = By.xpath("//img[contains(@src,'belkart-system')]");
        private final By logoMir = By.xpath("//img[contains(@src,'mir-system-ru')]");
        private final By logoMaestro = By.xpath("//img[contains(@src,'maestro-system')]");

        private final By paymentButton = By.xpath("//button[@class='colored disabled']");

        public WebElement getCardNumber() {
            return driver.findElement(cardNumber);
        }

        public WebElement getCardExpired() {
            return driver.findElement(cardExpired);
        }

        public WebElement getCodeCvc() {
            return driver.findElement(codeCvc);
        }

        public WebElement getCardHolder() {
            return driver.findElement(cardHolder);
        }

        public WebElement getLogoVisa() {
            return driver.findElement(logoVisa);
        }

        public WebElement getLogoMasterCard() {
            return driver.findElement(logoMasterCard);
        }

        public WebElement getLogoBelkart() {
            return driver.findElement(logoBelkart);
        }

        public WebElement getLogoMir() {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement pauseWait = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[contains(@src,'mir-system-ru')]")));
            return driver.findElement(logoMir);
        }

        public WebElement getLogoMaestro() {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement pauseWait = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[contains(@src,'maestro-system')]")));
            return driver.findElement(logoMaestro);
        }

        public WebElement getPaymentButton() {
            return driver.findElement(paymentButton);
        }

        public WebElement getDropdownButton() {
            return driver.findElement(dropdownButton);
        }

        public WebElement getContinueButton() {
            return driver.findElement(continueButton);
        }

        public WebElement getSpecialField(String paymentType) {
            String specialFieldPattern = "//form[@class='pay-form opened']//input[@id='%s']";
            String specialFieldName = getSpecialFieldId(paymentType);
            By specialField = By.xpath(String.format(specialFieldPattern, specialFieldName));
            return driver.findElement(specialField);
        }

        public WebElement getPaymentType(String paymentType) {
            WebElement elementType = null;
            List<WebElement> types = driver.findElements(paymentTypes);
            for (WebElement element : types) {
                if (element.getText().equals(paymentType)) {
                    elementType = element;
                }
            }
            return elementType;
        }

        public WebElement getSumField() {
            return driver.findElement(sumField);
        }

        public WebElement getEmailField() {
            return driver.findElement(emailField);
        }

        private String getSpecialFieldId(String paymentType) {
            return switch (paymentType) {
                case "Услуги связи" -> "connection-phone";
                case "Домашний интернет" -> "internet-phone";
                case "Рассрочка" -> "score-instalment";
                case "Задолженность" -> "score-arrears";
                default -> throw new IllegalStateException("Unexpected value: " + paymentType);
            };
        }
    }
}
