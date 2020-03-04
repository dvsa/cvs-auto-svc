package util;

import exceptions.AutomationException;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.NoSuchElementException;

import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WebDriverBrowsertack {

    static protected WebDriver driver;
    private static Loader loader;

    static {
        EnvironmentType envType = TypeLoader.getType();

        switch (envType) {
            case CI_DEVELOP:
                loader = new CIDevelopLoaderImpl();
                break;
            case LOCAL:
                loader = new LocalLoaderImpl();
                break;
            default:
                throw new AutomationException("Environment configuration not found");
        }

    }

    public static void setup() {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("os", "Windows");
        caps.setCapability("os_version", "10");
        caps.setCapability("browser", "Chrome");
        caps.setCapability("browser_version", "62.0");
        caps.setCapability("browserstack.local", "false");
        caps.setCapability("browserstack.selenium_version", "3.5.2");


        try {
            driver = new RemoteWebDriver(new URL("http://" + loader.getUsername() + ":" + loader.getPassword() + "@" + "hub-cloud.browserstack.com" + "/wd/hub"), caps);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static String getToken() {
        WebDriverBrowsertack.setup();

        driver.get(loader.getMicrosoftonlineUrl());
        driver.manage().window().maximize();
        WebElement element = driver.findElement(By.xpath("//div[@class='placeholderContainer']/*[1]"));
        element.sendKeys(loader.getMicrosoftonlineUserName());


        WebElement element2 = driver.findElement(By.xpath("//div[@class='inline-block'][2]"));
        element2.click();

        FluentWait wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofMillis(300))
                .ignoring(NoSuchElementException.class);

        wait.until(ExpectedConditions.and(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(text(),'Enter password')]")),
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[contains(text(),'Enter password')]"))
        ));

        WebElement element3 = driver.findElement(By.xpath("//div[@class='placeholderContainer']/*[1]"));
        element3.sendKeys(loader.getMicrosoftonlinePass());


        WebElement element4 = driver.findElement(By.xpath("//div[@class='inline-block'][1]"));
        element4.click();

        String token = driver.getCurrentUrl().split("id_token=")[1].split("&session_state=")[0];
        driver.quit();
        return token;
    }

    public static WebDriver checkVsaEmail(String randomVrm) {
        WebDriverBrowsertack.setup();
        FluentWait wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(250))
                .ignoring(NoSuchElementException.class);
        driver.get("https://outlook.live.com/owa/");

        System.out.println("Going to microsoft login page");
        wait.until(ExpectedConditions.and(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("nav.auxiliary-actions a[data-task='signin']")),
                ExpectedConditions.presenceOfElementLocated(By.cssSelector("nav.auxiliary-actions a[data-task='signin']"))
        ));
        driver.manage().window().maximize();
        driver.findElement(By.cssSelector("nav.auxiliary-actions a[data-task='signin']")).click();

        System.out.println("Filling in email address");
        wait.until(ExpectedConditions.and(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='loginfmt']")),
                ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[name='loginfmt']"))
        ));
        driver.findElement(By.cssSelector("input[name='loginfmt']")).sendKeys(loader.getEmailUserName());
        driver.findElement(By.cssSelector("#idSIButton9")).click();

        System.out.println("Filling in password");
        try {
            wait.until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("input[name='passwd']")),
                    ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("input[name='passwd']"))
            ));
            driver.findElement(By.cssSelector("input[name='passwd']")).sendKeys(loader.getEmailPass());
            driver.findElement(By.cssSelector("#idSIButton9")).click();
        } catch (StaleElementReferenceException e) {
            wait.until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("input[name='passwd']")),
                    ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("input[name='passwd']"))
            ));
            driver.findElement(By.cssSelector("input[name='passwd']")).sendKeys(loader.getEmailPass());
            driver.findElement(By.cssSelector("#idSIButton9")).click();
        }

        System.out.println("Confirming staying signed in");
        wait.until(ExpectedConditions.and(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#idBtn_Back")),
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("#idBtn_Back"))
        ));
        driver.findElement(By.cssSelector("#idSIButton9")).click();

        System.out.println("Searching for VSA email using VRM " + randomVrm);
        wait.until(ExpectedConditions.and(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("[placeholder='Search']")),
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("[placeholder='Search']"))
        ));
        driver.findElement(By.cssSelector("[placeholder='Search']")).sendKeys(randomVrm);
        driver.findElement(By.cssSelector("button[aria-label='Search']")).click();
        new WebDriverWait(driver, 1).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

        System.out.println("Confirming search returned at least one result");
        int i = 0;
        while ( (i < 60) && driver.findElement(
                By.cssSelector("div[aria-label='Message list']")).getText().contains("We didn't find anything.")) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.findElement(By.cssSelector("[placeholder='Search']")).clear();
            driver.findElement(By.cssSelector("[placeholder='Search']")).sendKeys(randomVrm);
            driver.findElement(By.cssSelector("button[aria-label='Search']")).click();
            i++;
            new WebDriverWait(driver, 1).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        }

        wait.until(ExpectedConditions.and(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div[aria-level='3']")),
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[aria-level='3']"))
        ));
        wait.until(
                ExpectedConditions.textToBePresentInElement(driver.findElement(By.cssSelector("div[aria-level='3']")), "Top results"));

        System.out.println("Select email that was searched for");
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[role='option']")));
        driver.findElement(By.cssSelector("div[role='option']")).click();
        return driver;
    }

    public static WebDriver checkAtfEmail(String testerName) {
        WebDriverBrowsertack.setup();
        FluentWait wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(250))
                .ignoring(NoSuchElementException.class);
        driver.get("https://outlook.live.com/owa/");

        System.out.println("Going to microsoft login page");
        wait.until(ExpectedConditions.and(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("nav.auxiliary-actions a[data-task='signin']")),
                ExpectedConditions.presenceOfElementLocated(By.cssSelector("nav.auxiliary-actions a[data-task='signin']"))
        ));
        driver.manage().window().maximize();
        driver.findElement(By.cssSelector("nav.auxiliary-actions a[data-task='signin']")).click();

        System.out.println("Filling in email address");
        wait.until(ExpectedConditions.and(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='loginfmt']")),
                ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[name='loginfmt']"))
        ));
        driver.findElement(By.cssSelector("input[name='loginfmt']")).sendKeys(loader.getEmailUserName());
        driver.findElement(By.cssSelector("#idSIButton9")).click();

        System.out.println("Filling in password");
        try {
            wait.until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("input[name='passwd']")),
                    ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("input[name='passwd']"))
            ));
            driver.findElement(By.cssSelector("input[name='passwd']")).sendKeys(loader.getEmailPass());
            driver.findElement(By.cssSelector("#idSIButton9")).click();
        } catch (StaleElementReferenceException e) {
            wait.until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("input[name='passwd']")),
                    ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("input[name='passwd']"))
            ));
            driver.findElement(By.cssSelector("input[name='passwd']")).sendKeys(loader.getEmailPass());
            driver.findElement(By.cssSelector("#idSIButton9")).click();
        }

        System.out.println("Confirming staying signed in");
        wait.until(ExpectedConditions.and(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#idBtn_Back")),
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("#idBtn_Back"))
        ));
        driver.findElement(By.cssSelector("#idSIButton9")).click();

        System.out.println("Searching for ATF email using tester name " + testerName);
        wait.until(ExpectedConditions.and(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("[placeholder='Search']")),
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("[placeholder='Search']"))
        ));
        driver.findElement(By.cssSelector("[placeholder='Search']")).sendKeys(testerName);
        driver.findElement(By.cssSelector("button[aria-label='Search']")).click();
        new WebDriverWait(driver, 1).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

        System.out.println("Confirming search returned at least one result");
        int i = 0;
        while ( (i < 60) && driver.findElement(
                By.cssSelector("div[aria-label='Message list']")).getText().contains("We didn't find anything.")) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.findElement(By.cssSelector("[placeholder='Search']")).clear();
            driver.findElement(By.cssSelector("[placeholder='Search']")).sendKeys(testerName);
            driver.findElement(By.cssSelector("button[aria-label='Search']")).click();
            i++;
            new WebDriverWait(driver, 1).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        }

        wait.until(ExpectedConditions.and(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div[aria-level='3']")),
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[aria-level='3']"))
        ));
        wait.until(
                ExpectedConditions.textToBePresentInElement(driver.findElement(By.cssSelector("div[aria-level='3']")), "Top results"));


        wait.until(ExpectedConditions.and(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[aria-level='3']")),
                ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[aria-level='3']")),
                ExpectedConditions.textToBePresentInElement(driver.findElement(By.cssSelector("div[aria-level='3']")), "Top results")
        ));

        System.out.println("Select email that was searched for");
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[role='option']")));
        driver.findElement(By.cssSelector("div[role='option']")).click();
        return driver;
    }

    public static void checkAtfEmailDetails(WebDriver driver, String testStationName, String testStationPNumber,
                                            String testerName, String startDate, String startTime) {
        FluentWait wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(250))
                .ignoring(NoSuchElementException.class);



        System.out.println("Checking the email title details are correct");
        wait.until(ExpectedConditions.and(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[role='main']>div:nth-of-type(1)")),
                ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[role='main']>div:nth-of-type(1)"))
        ));
        String actualString = driver.findElement(By.cssSelector("div[role='main']>div:nth-of-type(1)")).getText();
        String expectedString = "DVSA Commercial Vehicle Service – Site Activity Report: " +
                testStationPNumber + " – " + testerName + " – " + startDate;
        assertEquals(expectedString, actualString);

        System.out.println("Checking the details in the email are correct");
        wait.until(ExpectedConditions.and(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
                        "div[class^='rps']>div>table:nth-of-type(4)>tbody>tr:nth-of-type(2)>td:nth-of-type(2)>p:first-of-type")),
                ExpectedConditions.presenceOfElementLocated(By.cssSelector(
                        "div[class^='rps']>div>table:nth-of-type(4)>tbody>tr:nth-of-type(2)>td:nth-of-type(2)>p:first-of-type"))
        ));
        String actualString1 = driver.findElement(By
                .cssSelector("div[class^='rps']>div>table:nth-of-type(4)>tbody>tr:nth-of-type(2)>td:nth-of-type(2)>p:first-of-type"))
                .getText();
        String expectedString1 =
                "Please find below the report for the activities conducted on " + startDate + " by " + testerName + ".";
        assertTrue(actualString1.contains(expectedString1));

        wait.until(ExpectedConditions.and(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
                        "div[class^='rps']>div>table:nth-of-type(4)>tbody>tr:nth-of-type(2)>td:nth-of-type(2)>p:nth-of-type(3)")),
                ExpectedConditions.presenceOfElementLocated(By.cssSelector(
                        "div[class^='rps']>div>table:nth-of-type(4)>tbody>tr:nth-of-type(2)>td:nth-of-type(2)>p:nth-of-type(3)"))
        ));
        String siteVisitDetailsText = driver.findElement(By
                .cssSelector("div[class^='rps']>div>table:nth-of-type(4)>tbody>tr:nth-of-type(2)>td:nth-of-type(2)>p:nth-of-type(3)"))
                .getText();
        String expectedString2 = "Site: " + testStationName + " – " + testStationPNumber;
        String expectedString3 = "Assessor: " + testerName;
        String expectedString4 = "Date: " + startDate;
        String expectedString5 = "Start Time: " + startTime;
        assertTrue(siteVisitDetailsText.contains(expectedString2));
        assertTrue(siteVisitDetailsText.contains(expectedString3));
        assertTrue(siteVisitDetailsText.contains(expectedString4));
        assertTrue(siteVisitDetailsText.contains(expectedString5));

        driver.close();
        driver.quit();
    }

    public static void checkVsaEmailDetails(WebDriver driver, String randomVrm, String testName, String date) {
        System.out.println("Checking the email title details are correct");
        String actualString = driver.findElement(By.cssSelector("div[role='main']>div:nth-of-type(1)")).getText();
        String expectedString = randomVrm + " " + testName + "|" + date + " (Certificate 1 of 1)";
        assertEquals(expectedString, actualString);

        System.out.println("Checking the details in the email are correct");
        String actualString1 = driver.findElement(By.cssSelector("div[role='main']>div:nth-of-type(2)")).getText();
        String expectedString1 = "Please see the link below to access the test certificate for vehicle(s) " + randomVrm + " conducted on " + date;
        assertTrue(actualString1.contains(expectedString1));

        driver.close();
        driver.quit();
    }
}
