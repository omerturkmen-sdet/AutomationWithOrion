package automationPractices.week02.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TweetDetailsPage {

    public TweetDetailsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver,this);
    }

    WebDriver driver;

    @FindBy(xpath = "//div[contains(@data-testid,'retweet')]")
    public WebElement retweetButton;

    public void retweet(){
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()",retweetButton);
        driver.findElement(By.xpath("//div[@data-testid='retweetConfirm']")).click();
    }

    public String getRetweetStatus(){
        return retweetButton.getAttribute("ariaLabel");
    }
}
