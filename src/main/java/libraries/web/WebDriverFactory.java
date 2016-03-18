package libraries.web;

import libraries.utility.Common;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

public class WebDriverFactory {
    /**
     * Return a browser instance based on parameters
     */
    public static WebDriver getInstance(String browserType, boolean isUserProfileLoaded) {
        Common.log(String.format("Start initializing Web driver for %s browser", browserType.toUpperCase()));
//        browserPath = String.format("%s\\resources\\browsers\\", System.getProperty("user.dir"));
        return buildDriver(browserType, isUserProfileLoaded);
    }

    /**
     * Build a "local" browser instance
     */
    private static WebDriver buildDriver(String browserType, boolean loadUserProfile) {
        WebDriver driver = null;
        DesiredCapabilities capabilities;

        switch (browserType.toLowerCase()) {
            case "firefox":
                capabilities = DesiredCapabilities.firefox();
                capabilities.setJavascriptEnabled(true);

                if (loadUserProfile) {  // Get default user profile of Firefox
                    final ProfilesIni profilesIni = new ProfilesIni();
                    final FirefoxProfile fp = profilesIni.getProfile("default");
                    capabilities.setCapability(FirefoxDriver.PROFILE, fp);
                    Common.log("Load default user profile successfully");
                }
                driver = new FirefoxDriver(capabilities);
                break;

            case "chrome":
                checkBrowserDriver("chromedriver.exe");
                capabilities = DesiredCapabilities.chrome();
                capabilities.setJavascriptEnabled(true);
//                System.setProperty("webdriver.chrome.driver", browserPath + "\\chromedriver.exe");
                driver = new ChromeDriver(capabilities);
                break;

//            case "ie":
//                checkBrowserDriver("IEDriverServer.exe");
//                capabilities = DesiredCapabilities.internetExplorer();
//                capabilities.setJavascriptEnabled(true);
//                capabilities.setCapability(CapabilityType.LOGGING_PREFS, logs);
//                System.setProperty("webdriver.ie.driver", browserPath + "\\IEDriverServer.exe");
//                driver = new InternetExplorerDriver(capabilities);
//                break;
        }

        if (driver != null) {
            driver.manage().window().maximize();
            Common.log(String.format("%s driver has been initialized successfully", browserType.toUpperCase()));
        } else
            Common.log(String.format("Initializing %s driver failed", browserType.toUpperCase()));

        return driver;
    }

    /**
     * Looks to see if the browser driver is installed and if not attempts to copy it from a known location
     */
    private static void checkBrowserDriver(String driverExeName) {
        // See if the driver exists and can be executed at the default location of user's home directory.
        String location = String.format("%s\\browsers\\", System.getProperty("user.dir"));
        File file = new File(location + driverExeName);

        if (file.isFile()) {
            if (!file.canExecute())
                file.setExecutable(true);
            System.out.println(String.format("Found and using %s driver: %s", location, file.getAbsolutePath()));
            return;
        }

        // If the driver not found, see if the source is available at the location in the project.
        System.out.println(String.format("System does not found the driver at the path: %s", file.getAbsolutePath()));
    }

}
