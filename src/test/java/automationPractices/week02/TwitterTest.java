package automationPractices.week02.test;

import automationPractices.TwitterAccountInfo;
import automationPractices.week02.pages.TweetDetailsPage;
import automationPractices.week02.pages.TwitterHomePage;
import automationPractices.week02.pages.TwitterLoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import utility.Utilities;

import java.time.Duration;
import java.util.Date;
import java.util.Random;

public class TwitterTest {

    static WebDriver driver;
    static WebDriverWait wait;

    @BeforeClass
    public static void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("https://twitter.com/");

        wait = new WebDriverWait(driver,Duration.ofSeconds(10));
    }

    @AfterClass
    public static void tearDown(){
        driver.quit();
    }



    @Test
    public void clickViewAfterSendingTweet(){

        TwitterLoginPage loginPage = new TwitterLoginPage(driver);
        loginPage.loginTwitter();
        Utilities.threadSleep(3);

        //Assert that user logged in successfully
        Assert.assertEquals(driver.getTitle(),"Home / Twitter");

        //Used random numbers for unique tweet and saved for assertion
        Random rn = new Random();
        int randonNumber = rn.nextInt(1000);
        String newTweet = "test tweet" + randonNumber;

        TwitterHomePage homePage = new TwitterHomePage(driver);
        homePage.dummyTweet(newTweet);
        Utilities.threadSleep(3);
        homePage.navigateSentTweet();

        wait.until(ExpectedConditions.titleContains(newTweet));


        //navigate tweet details and retweet
        TweetDetailsPage detailsPage = new TweetDetailsPage(driver);

        String initialRetweetStatus = detailsPage.getRetweetStatus();

        detailsPage.retweet();
        Utilities.threadSleep(3);
        String finalRetweetStatus = detailsPage.getRetweetStatus();

        //assert that attribute changed from 'Retweet' to 'Retweeted'
        Assert.assertNotEquals(initialRetweetStatus,finalRetweetStatus);

        System.out.println("initialRetweetStatus = " + initialRetweetStatus);
        System.out.println("finalRetweetStatus = " + finalRetweetStatus);


    }

}

