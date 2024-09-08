package lesson16.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage {

    private WebDriver driver;
    private final By paymentSection = By.xpath("//section[@class='pay']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getPaymentSection() {
        return driver.findElement(paymentSection);
    }

    public class PaymentSection {

        private final By dropdownButton = By.xpath("//div[@class='select']");
        private final By paymentTypes = By.xpath("//div[@class='select']//ul[@class='select__list']/li/p");
        private final By sumField = By.xpath("//form[@class='pay-form opened']//input[@placeholder='Сумма']");
        private final By emailField = By.xpath("//form[@class='pay-form opened']//input[@placeholder='E-mail для отправки чека']");

        public WebElement getDropdownButton() {
            return driver.findElement(dropdownButton);
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
