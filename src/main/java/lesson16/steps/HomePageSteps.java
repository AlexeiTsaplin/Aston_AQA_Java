package lesson16.steps;

import lesson16.dto.PaymentSectionDto;
import lesson16.pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePageSteps {

    private final HomePage homePage;
    private final HomePage.PaymentSection paymentSection;
    private final WebDriver driver;

    public HomePageSteps(HomePage homePage, WebDriver driver) {
        this.homePage = homePage;
        this.paymentSection = homePage.new PaymentSection();
        this.driver = driver;
    }

    public void acceptCookies(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        try {
            WebElement acceptCookiesButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#cookie-agree")));
            acceptCookiesButton.click();

        } catch (Exception e) {
            System.out.println("Куки не появились.");
        }
    }

    public String TextSumPlaceholder(){
        WebElement sumField = driver.findElement(By.xpath("//form[@class='pay-form opened']//input[@placeholder='Сумма']"));
        return sumField.getAttribute("placeholder");
    }

    public String PhoneServicePlaceholder(){
        WebElement specialField = driver.findElement(By.xpath("//form[@class='pay-form opened']//input[@id='connection-phone']"));
        return specialField.getAttribute("placeholder");
    }

    public String PhoneInternetPlaceholder(){
        WebElement specialField = driver.findElement(By.xpath("//form[@class='pay-form opened']//input[@id='internet-phone']"));
        return specialField.getAttribute("placeholder");
    }

    public String ScoreInstalmentPlaceholder(){
        WebElement specialField = driver.findElement(By.xpath("//form[@class='pay-form opened']//input[@id='score-instalment']"));
        return specialField.getAttribute("placeholder");
    }

    public String ScoreDebtPlaceholder(){
        WebElement specialField = driver.findElement(By.xpath("//form[@class='pay-form opened']//input[@id='score-arrears']"));
        return specialField.getAttribute("placeholder");
    }

    public String emailPlaceholder(){
        WebElement emailField = driver.findElement(By.xpath("//form[@class='pay-form opened']//input[@placeholder='E-mail для отправки чека']"));
        return emailField.getAttribute("placeholder");
    }

    public void scrollToPaymentSection() {
        WebElement paymentSection = homePage.getPaymentSection();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", paymentSection);
    }

    public void ContinueButton(){
        WebElement continueButton = driver.findElement(By.xpath("//*[@id=\"pay-arrears\"]/button"));
        continueButton.click();
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
