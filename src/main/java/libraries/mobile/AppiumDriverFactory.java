package libraries.mobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import libraries.utility.Common;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class AppiumDriverFactory {

    /**
     * Return a mobile driver instance based on parameters
     * Initializing Appium client driver responsible for the connection to the Appium server
     */
    public static AppiumDriver getInstance(String serverUrl,
                                           String platform,
                                           boolean realDevice,
                                           String iosDeviceName,
                                           String iosDeviceUdid,
                                           String androidDeviceName,
                                           String appPackage,
                                           String appActivity) {
        Common.log(String.format("Start initializing Appium driver for %s platform.", platform.toUpperCase()));
        try {
            return buildDriver(serverUrl, platform, realDevice, iosDeviceName, iosDeviceUdid, androidDeviceName, appPackage, appActivity);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
//        AppiumDriver driver = null;
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        DataReader dataReader = new DataReader("config.properties");
//        File appDir, app;
//        String appiumServerUrl, cordovaAppName, runOnRealDevice, deviceID = "";
//
//        appiumServerUrl = dataReader.getProperty("server_url");
//        cordovaAppName = dataReader.getProperty("cordova_app_name");
//        runOnRealDevice = dataReader.getProperty("real_device");
//        deviceID = dataReader.getProperty("platform").equals("iOS") ? dataReader.getProperty("ios_device_udid") : dataReader.getProperty("android_device_id");
//        Device.setDeviceInfo(deviceID);

//        if (Device.isiOS) {
//            switch (runOnRealDevice) {
//                case "true":
//                    capabilities.setCapability("deviceName", dataReader.getProperty("ios_device_name"));
//                    capabilities.setCapability("udid", dataReader.getProperty("ios_device_udid"));
//                    capabilities.setCapability("bundleId", cordovaAppName);
//                    break;
//
//                case "false":
//                    appDir = new File(dataReader.getProperty("ios_simulator_app_dir"));
//                    app = new File(appDir, dataReader.getProperty("ios_simulator_app_name"));
//                    capabilities.setCapability("platform", "Mac");
//                    capabilities.setCapability("platformName", "iOS");
//                    capabilities.setCapability("deviceName", dataReader.getProperty("ios_simulator_device_name"));
//                    capabilities.setCapability("platformVersion", dataReader.getProperty("ios_simulator_os_version"));
//                    capabilities.setCapability("app", app.getAbsolutePath());
//                    break;
//            }
//            driver = new IOSDriver(new URL(appiumServerUrl), capabilities);
//        } else {
//            switch (runOnRealDevice) {
//                case "true":
//                    capabilities.setCapability("deviceName", deviceID);
//                    capabilities.setCapability("androidPackage", cordovaAppName);
//                    capabilities.setCapability("androidActivity", dataReader.getProperty("android_cordova_activity_name"));
//                    break;
//
//                case "false":
////                    appDir = new File(dataReader.getProperty("android_app_dir"));
////                    app = new File(appDir, dataReader.getProperty("android_app_name"));
////                    capabilities.setCapability("device", "Android");
////                    capabilities.setCapability("platformName", "Android");
////                    capabilities.setCapability("app-package", cordovaAppName);
////                    capabilities.setCapability("app", app.getAbsolutePath());
//                    break;
//            }
//            driver = new AndroidDriver(new URL(appiumServerUrl), capabilities);
//        }

    private static AppiumDriver buildDriver(String serverUrl,
                                            String platform,
                                            boolean realDevice,
                                            String iosDeviceName,
                                            String iosDeviceUdid,
                                            String androidDeviceName,
                                            String appPackage,
                                            String appActivity) throws MalformedURLException {
        AppiumDriver driver = null;
        DesiredCapabilities capabilities = new DesiredCapabilities();

        switch (platform.toLowerCase()) {
            case "ios":
                if (realDevice) {
                    capabilities.setCapability("deviceName", iosDeviceName);
                    capabilities.setCapability("udid", iosDeviceUdid);
                    capabilities.setCapability("bundleId", appPackage);
                } else {

                }
                break;

            case "android":
                if (realDevice) {
                    capabilities.setCapability("deviceName", androidDeviceName);
                    capabilities.setCapability("androidPackage", appPackage);
                    capabilities.setCapability("androidActivity", appActivity);
                } else {

                }
                driver = new AndroidDriver(new URL(serverUrl), capabilities);
                break;
        }

        if (driver != null)
            Common.log(String.format("%s driver has been initialized successfully.", platform.toUpperCase()));
        else
            Common.log(String.format("Initializing %s driver failed.", platform.toUpperCase()));

        return driver;
    }

}
