package Zadanie2;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;

public class Zadanie2 {
    //zmienne do logowania
    private final String email = "wipini9515@lurenwu.com";
    private final String password = "1234abc";
    private WebDriver driver;

    @Given("an opened site")
    public void openSite() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://mystore-testlab.coderslab.pl");
    }

    @And("correct email and password is entered and submitted")
    public void userLogin() {
        WebElement userInfo = driver.findElement(By.className("user-info"));
        userInfo.click();
        WebElement emailInput = driver.findElement(By.name("email"));
        WebElement passwordInput = driver.findElement(By.name("password"));
        WebElement buttonSignIn = driver.findElement(By.id("submit-login"));
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        buttonSignIn.click();
    }

    @And("button 'CLOTHES' is clicked")
    public void clickButtonClothes() {
        WebElement buttonClothes = driver.findElement(By.id("category-3"));
        buttonClothes.click();
    }

    @And("choose correct size")
    public void chooseCorrectSize() {
        WebElement item = driver.findElement(By.xpath("(//a[@class='thumbnail product-thumbnail'])[2]"));
        item.click();
        WebElement sizeSelect = driver.findElement(By.id("group_1"));
        sizeSelect.click();
        WebElement chosenSize = driver.findElement(By.xpath("//option[@title='M']"));
        chosenSize.click();
    }

    @And("choose correct quantity")
    public void chooseCorrectQuantity() {
        WebElement quantityInput = driver.findElement(By.id("quantity_wanted"));
        quantityInput.click();
        quantityInput.clear();
        quantityInput.sendKeys("5");
    }

    @And("add item to cart")
    public void addItemToCart() throws InterruptedException {
        WebElement buttonAddToCart = driver.findElement(By.xpath("//button[@class='btn btn-primary add-to-cart']"));
        buttonAddToCart.click();
        Thread.sleep(1500);
    }

    @And("go to checkout")
    public void goToCheckout(){
        WebElement checkoutFirstStep = driver.findElement(By.xpath("//a[@class='btn btn-primary']"));
        checkoutFirstStep.click();
        WebElement checkoutSecondStep  = driver.findElement(By.xpath("//div[@class='text-sm-center']"));
        checkoutSecondStep.click();
    }
    @And ("confirm address")
    public void confirmAddress() {
        WebElement addressInfo = driver.findElement(By.xpath("//div[@class='address']"));
        String[] addressConfirmation = addressInfo.getText().split("\\n");
        Assert.assertEquals("Urokliwa 10", addressConfirmation[1]);
        Assert.assertEquals("Warszawa", addressConfirmation[2]);
        Assert.assertEquals("01-120", addressConfirmation[3]);
        Assert.assertEquals("United Kingdom", addressConfirmation[4]);
    }
    @And ("choose shipping method")
    public void chooseShippingMethod() {
        WebElement buttonShippingMethod = driver.findElement(By.xpath("//section[@id='checkout-delivery-step']/h1[@class='step-title h3']"));
        buttonShippingMethod.click();
    }
    @And ("choose payment method")
    public void choosePaymentMethod() {
        WebElement buttonPayment= driver.findElement(By.xpath("//section[@id='checkout-payment-step']/h1[@class='step-title h3']"));
        buttonPayment.click();
        WebElement payByCheckPayment = driver.findElement(By.id("payment-option-1"));
        payByCheckPayment.click();
    }
    @And ("click agreement checkbox and obligation to pay button")
    public void clickAgreementCheckbox() {
        WebElement agreementCheckbox = driver.findElement(By.xpath("//input[@id='conditions_to_approve[terms-and-conditions]']"));
        agreementCheckbox.click();
        WebElement buttonObligationToPay = driver.findElement(By.xpath("//div[@class='ps-shown-by-js']/button[@class='btn btn-primary center-block']"));
        buttonObligationToPay.click();
    }
    @Then ("take a screenshot")
    public void takeAScreenshot() throws IOException {
        TakesScreenshot screenshot = ((TakesScreenshot) driver);
        File screenshotFile = screenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFileToDirectory(screenshotFile, new File("/Users/marzenakulikowska/Desktop/Screenshot"));
    }
}
