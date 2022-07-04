package automationPractices.week02.pages;

import automationPractices.TwitterAccountInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.Utilities;

import java.awt.event.WindowAdapter;
import java.time.Duration;

public class TwitterLoginPage {

    WebDriver driver;

    public TwitterLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(name = "text")
    public WebElement emailField;

    @FindBy(xpath = "//a[@href='/login']")
    public WebElement loginButton;

    @FindBy(name = "password")
    public WebElement passwordField;


    /**
     * Email ve şifre TwitterAccountInfo class'ından geliyor. Orda düzenleme yapmak gerekiyor
     */
    public void loginTwitter() {
        loginButton.click();

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(emailField));
        emailField.sendKeys(TwitterAccountInfo.email + Keys.ENTER);

        try {
            //Too many attempt
            driver.findElement(By.name("text")).sendKeys(TwitterAccountInfo.username + Keys.ENTER);
        } catch (Exception e) {
            //Successful login
        }

        wait.until(ExpectedConditions.visibilityOf(passwordField));

        passwordField.sendKeys(TwitterAccountInfo.password + Keys.ENTER);
        wait.until(ExpectedConditions.titleContains("Home"));
    }


}
