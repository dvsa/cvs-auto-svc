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
import org.openqa.selenium.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WebDriverBrowsertack {

    static protected WebDriver driver;
    private static Loader loader = new LocalLoaderImpl();

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

        driver.get(loader.getMicrosoftOnlineUrlVersion2());
        driver.manage().window().maximize();
        WebElement element = driver.findElement(By.xpath("//div[@class='placeholderContainer']/*[1]"));
        element.sendKeys(loader.getMicrosoftOnlineUserNameVersion2());


        WebElement element2 = driver.findElement(By.xpath("//input[@type='submit']"));
        element2.click();

        FluentWait wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofMillis(300))
                .ignoring(NoSuchElementException.class);

        wait.until(ExpectedConditions.and(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='loginMessage']")),
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@id='loginMessage']"))
        ));

        WebElement element3 = driver.findElement(By.xpath("//input[@name='Password']"));
        element3.sendKeys(loader.getMicrosoftOnlinePassVersion2());


        WebElement element4 = driver.findElement(By.xpath("//span[@class='submit']"));
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
        driver.findElement(By.cssSelector("input[name='loginfmt']")).sendKeys(loader.getMicrosoftOnlineUserName());
        driver.findElement(By.cssSelector("#idSIButton9")).click();

        System.out.println("Filling in password");
        try {
            wait.until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("input[name='passwd']")),
                    ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("input[name='passwd']"))
            ));
            driver.findElement(By.cssSelector("input[name='passwd']")).sendKeys(loader.getMicrosoftOnlinePass());
            driver.findElement(By.cssSelector("#idSIButton9")).click();
        } catch (StaleElementReferenceException e) {
            wait.until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("input[name='passwd']")),
                    ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("input[name='passwd']"))
            ));
            driver.findElement(By.cssSelector("input[name='passwd']")).sendKeys(loader.getMicrosoftOnlinePass());
            driver.findElement(By.cssSelector("#idSIButton9")).click();
        }

        System.out.println("Confirming staying signed in");
        int i = 0;
        while (i < 5) {
            if (driver.findElements(By.cssSelector("#idBtn_Back")).size() == 0) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
            }
            else {
                wait.until(ExpectedConditions.and(
                        ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#idBtn_Back")),
                        ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("#idBtn_Back"))
                ));
                driver.findElement(By.cssSelector("#idSIButton9")).click();
                break;
            }
        }

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
        i = 0;
        while (i < 30) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!driver.findElement(
                    By.cssSelector("div[aria-label='Message list']")).getText().contains("We didn't find anything.")) {
                break;
            }
            driver.findElement(By.cssSelector("input[aria-label='Search']")).clear();
            driver.findElement(By.cssSelector("input[aria-label='Search']")).sendKeys(randomVrm);
            driver.findElement(By.cssSelector("button[aria-label='Search']")).click();
            i++;
            new WebDriverWait(driver, 1).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        }

        if (i == 30) {
            System.out.println("Go to junk folder");
            driver.findElement(By.cssSelector("div[title='Junk Email']")).click();
            wait.until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("[name='Empty folder']")),
                    ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("[name='Empty folder']"))
            ));

            driver.findElement(By.cssSelector("input[aria-label='Search']")).clear();
            driver.findElement(By.cssSelector("input[aria-label='Search']")).sendKeys(randomVrm);
            driver.findElement(By.cssSelector("button[aria-label='Search']")).click();
            new WebDriverWait(driver, 1).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

            int j = 0;
            while (j < 120) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!driver.findElement(
                        By.cssSelector("div[aria-label='Message list']")).getText().contains("We didn't find anything.")) {
                    break;
                }
                driver.findElement(By.cssSelector("input[aria-label='Search']")).clear();
                driver.findElement(By.cssSelector("input[aria-label='Search']")).sendKeys(randomVrm);
                driver.findElement(By.cssSelector("button[aria-label='Search']")).click();
                j++;
                new WebDriverWait(driver, 1).until(
                        webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            }

            if (j == 120) {
                throw new AutomationException("Email was not found");
            }
        }

        wait.until(
                ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("div[aria-label*='" + randomVrm + "']"), 0));

        System.out.println("Select email that was searched for");
        driver.findElements(By.cssSelector("div[aria-label*='" + randomVrm + "']")).get(0).click();
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
        driver.findElement(By.cssSelector("input[name='loginfmt']")).sendKeys(loader.getMicrosoftOnlineUserName());
        driver.findElement(By.cssSelector("#idSIButton9")).click();

        System.out.println("Filling in password");
        try {
            wait.until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("input[name='passwd']")),
                    ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("input[name='passwd']"))
            ));
            driver.findElement(By.cssSelector("input[name='passwd']")).sendKeys(loader.getMicrosoftOnlinePass());
            driver.findElement(By.cssSelector("#idSIButton9")).click();
        } catch (StaleElementReferenceException e) {
            wait.until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("input[name='passwd']")),
                    ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("input[name='passwd']"))
            ));
            driver.findElement(By.cssSelector("input[name='passwd']")).sendKeys(loader.getMicrosoftOnlinePass());
            driver.findElement(By.cssSelector("#idSIButton9")).click();
        }

        System.out.println("Confirming staying signed in");
        int i = 0;
        while (i < 5) {
            if (driver.findElements(By.cssSelector("#idBtn_Back")).size() == 0) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
            }
            else {
                wait.until(ExpectedConditions.and(
                        ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#idBtn_Back")),
                        ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("#idBtn_Back"))
                ));
                driver.findElement(By.cssSelector("#idSIButton9")).click();
                break;
            }
        }

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
        i = 0;
        while (i < 30) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!driver.findElement(
                    By.cssSelector("div[aria-label='Message list']")).getText().contains("We didn't find anything.")) {
                break;
            }
            driver.findElement(By.cssSelector("input[aria-label='Search']")).clear();
            driver.findElement(By.cssSelector("input[aria-label='Search']")).sendKeys(testerName);
            driver.findElement(By.cssSelector("button[aria-label='Search']")).click();
            i++;
            new WebDriverWait(driver, 1).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        }

        if (i == 30) {
            System.out.println("Go to junk folder");
            driver.findElement(By.cssSelector("div[title='Junk Email']")).click();
            wait.until(ExpectedConditions.and(
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("[name='Empty folder']")),
                    ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("[name='Empty folder']"))
            ));

            driver.findElement(By.cssSelector("input[aria-label='Search']")).clear();
            driver.findElement(By.cssSelector("input[aria-label='Search']")).sendKeys(testerName);
            driver.findElement(By.cssSelector("button[aria-label='Search']")).click();
            new WebDriverWait(driver, 1).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

            int j = 0;
            while (j < 120) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!driver.findElement(
                        By.cssSelector("div[aria-label='Message list']")).getText().contains("We didn't find anything.")) {
                    break;
                }
                driver.findElement(By.cssSelector("input[aria-label='Search']")).clear();
                driver.findElement(By.cssSelector("input[aria-label='Search']")).sendKeys(testerName);
                driver.findElement(By.cssSelector("button[aria-label='Search']")).click();
                j++;
                new WebDriverWait(driver, 1).until(
                        webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            }

            if (j == 120) {
                throw new AutomationException("Email was not found");
            }
        }

        wait.until(
                ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("div[aria-label*='" + testerName + "']"), 0));

        System.out.println("Select email that was searched for");
        driver.findElements(By.cssSelector("div[aria-label*='" + testerName + "']")).get(0).click();
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
        try {
            assertEquals(expectedString, actualString);
        } catch (AssertionError e) {
            throw new AutomationException("Expected string " + expectedString + " is not identical to actual string " + actualString);
        }

        System.out.println("Checking the details in the email are correct");
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(
                "div[class^='rps']>div>table:nth-of-type(4)>tbody>tr:nth-of-type(2)>td:nth-of-type(2)>p:first-of-type"), 0));
        String actualString1 = driver.findElement(By
                .cssSelector("div[class^='rps']>div>table:nth-of-type(4)>tbody>tr:nth-of-type(2)>td:nth-of-type(2)>p:first-of-type"))
                .getText();
        String expectedString1 =
                "Please find below the report for the activities conducted on " + startDate + " by " + testerName + ".";
        try {
            assertTrue(actualString1.contains(expectedString1));
        } catch (AssertionError e) {
            throw new AutomationException("Actual string '" + actualString1 + "' does not contain expected string '" + expectedString1 + "'");
        }

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
        try {
            assertTrue(siteVisitDetailsText.contains(expectedString2));
        } catch (AssertionError e) {
            throw new AutomationException("Actual string '" + siteVisitDetailsText + "' does not contain expected string '" + expectedString2 + "'");
        }
        try {
            assertTrue(siteVisitDetailsText.contains(expectedString3));
        } catch (AssertionError e) {
            throw new AutomationException("Actual string '" + siteVisitDetailsText + "' does not contain expected string '" + expectedString3 + "'");
        }
        try {
            assertTrue(siteVisitDetailsText.contains(expectedString4));
        } catch (AssertionError e) {
            throw new AutomationException("Actual string '" + siteVisitDetailsText + "' does not contain expected string '" + expectedString4 + "'");
        }
        try {
            assertTrue(siteVisitDetailsText.contains(expectedString5));
        } catch (AssertionError e) {
            throw new AutomationException("Actual string '" + siteVisitDetailsText + "' does not contain expected string '" + expectedString5 + "'");
        }

        driver.close();
        driver.quit();
    }

    public static void checkVsaEmailDetails(WebDriver driver, String randomVrm, String testName, String date) {
        System.out.println("Checking the email title details are correct");
        String actualString = driver.findElement(By.cssSelector("div[role='main']>div:nth-of-type(1)")).getText();
        String expectedString = randomVrm + " " + testName + "|" + date + " (Certificate 1 of 1)";
        try {
            assertEquals(expectedString, actualString);
        } catch (AssertionError e) {
            throw new AutomationException("Actual string " + actualString + " does not contain expected string " + expectedString);
        }

        System.out.println("Checking the details in the email are correct");
        String actualString1 = driver.findElement(By.cssSelector("div[role='main']>div:nth-of-type(2)")).getText();
        String expectedString1 = "Please see the link below to access the test certificate for vehicle(s) " + randomVrm + " conducted on " + date;
        try {
            assertTrue(actualString1.contains(expectedString1));
        } catch (AssertionError e) {
            throw new AutomationException("Actual string '" + actualString1 + "' does not contain expected string '" + expectedString1 + "'");
        }

        driver.close();
        driver.quit();
    }
}
