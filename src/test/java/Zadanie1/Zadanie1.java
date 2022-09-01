package Zadanie1;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Zadanie1 {
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
    public void userLogin(){
        WebElement userInfo = driver.findElement(By.className("user-info"));
        userInfo.click();
        WebElement emailInput = driver.findElement(By.name("email"));
        WebElement passwordInput = driver.findElement(By.name("password"));
        WebElement buttonSignIn = driver.findElement(By.id("submit-login"));
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        buttonSignIn.click();
    }

    @And("button 'Addresses' is clicked")
    public void clickButtonAddresses() {
        WebElement buttonAddresses = driver.findElement(By.cssSelector("[title=\"Addresses\"]"));
        buttonAddresses.click();
    }

    @And ("button 'Create new address' is clicked")
    public void clickButtonCreateAddress() {
        WebElement buttonCreateAddress = driver.findElement(By.cssSelector("[data-link-action=\"add-address\"]"));
        buttonCreateAddress.click();
    }

    @And ("new address form is filled with {string}, {string}, {string}, {string}, {string}")
    public void newAddressFormIsFilledWith(String alias, String address, String city, String postcode, String phone) {
        WebElement aliasInput = driver.findElement(By.name("alias"));
        WebElement addressInput = driver.findElement(By.name("address1"));
        WebElement postcodeInput = driver.findElement(By.name("postcode"));
        WebElement cityInput = driver.findElement(By.name("city"));
        WebElement chooseCountry = driver.findElement(By.name("id_country"));
        WebElement chosenCountry = driver.findElement(By.xpath("//*[text() = 'United Kingdom']"));
        WebElement phoneInput = driver.findElement(By.name("phone"));

        aliasInput.click();
        aliasInput.clear();
        aliasInput.sendKeys(alias);

        addressInput.click();
        addressInput.clear();
        addressInput.sendKeys(address);

        postcodeInput.click();
        postcodeInput.clear();
        postcodeInput.sendKeys(postcode);

        cityInput.click();
        cityInput.clear();
        cityInput.sendKeys(city);

        chooseCountry.click();
        chosenCountry.click();

        phoneInput.click();
        phoneInput.clear();
        phoneInput.sendKeys(phone);
    }
    @And ("button 'SAVE is clicked'")
    public void sendAddress() {
        WebElement saveButton = driver.findElement(By.xpath("//button[@class='btn btn-primary float-xs-right']"));
        saveButton.click();
    }
    @Then("check address with {string}, {string}, {string}, {string}, {string}")
    public void checkAddressWith(String alias, String address, String city, String postcode, String phone) {
        WebElement addressDetails = driver.findElement(By.xpath("//section[@id='content']/div[2]/article/div"));
        String[] addressConfirmation = addressDetails.getText().split("\\n");
        Assert.assertEquals(address, addressConfirmation[2]);
        Assert.assertEquals(city, addressConfirmation[3]);
        Assert.assertEquals(postcode, addressConfirmation[4]);
        Assert.assertEquals(phone, addressConfirmation[6]);
    }
}