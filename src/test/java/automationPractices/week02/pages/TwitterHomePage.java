package automationPractices.week02.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TwitterHomePage {

    WebDriver driver;

    public TwitterHomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver,this);
    }

    @FindBy (xpath = "//*[@class='public-DraftStyleDefault-block public-DraftStyleDefault-ltr']")
    public WebElement newTweetField;

    @FindBy(xpath = "//div[@role='button'][@data-testid='tweetButtonInline']")
    public WebElement sendTweetButton;

    @FindBy(xpath = "//*[@aria-label='Profile']")
    public WebElement profileNavigateButton;

    @FindBy(xpath = "//span[.='View']")
    public WebElement viewSentTweetButton;

    public void dummyTweet(String tweet) {
        //Enter a new Tweet
        newTweetField.click();
        newTweetField.sendKeys(tweet);

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(sendTweetButton));
        //send tweet
        sendTweetButton.click();
    }

    public void navigateSentTweet(){
        new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(viewSentTweetButton));
        viewSentTweetButton.click();
    }

}
