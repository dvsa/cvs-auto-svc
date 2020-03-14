package util;

import exceptions.AutomationException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.NoSuchElementException;

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
                .withTimeout(Duration.ofSeconds(10))
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
        wait.until(ExpectedConditions.and(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("input[name='passwd']")),
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("input[name='passwd']"))
        ));
        driver.findElement(By.cssSelector("input[name='passwd']")).sendKeys(loader.getEmailPass());
        driver.findElement(By.cssSelector("#idSIButton9")).click();

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

        System.out.println("Confirming search returned at least one result");
        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElements(By.cssSelector("div[aria-level='3']")).get(0), "Top results"));
        driver.findElements(By.cssSelector("div[role='option']")).get(0).click();
        return driver;
    }

    public static WebDriver checkAtfEmail(String testerName) {
        WebDriverBrowsertack.setup();
        FluentWait wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
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
        wait.until(ExpectedConditions.and(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("input[name='passwd']")),
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("input[name='passwd']"))
        ));
        driver.findElement(By.cssSelector("input[name='passwd']")).sendKeys(loader.getEmailPass());
        driver.findElement(By.cssSelector("#idSIButton9")).click();

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

        System.out.println("Confirming search returned at least one result");
        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElements(By.cssSelector("div[aria-level='3']")).get(0), "Top results"));
        driver.findElements(By.cssSelector("div[role='option']")).get(0).click();
        return driver;
    }
}
