package com.testbed.tests;

import io.appium.java_client.AppiumDriver;
import libraries.core.DataReader;
import libraries.mobile.AppiumServerBuilder;
import libraries.mobile.MobileAction;
import libraries.mobile.MobileElementFinder;
import libraries.utility.Common;
import mobile.screens.explorer.EditorScreen;
import mobile.screens.explorer.Hotspot;
import mobile.screens.explorer.LoginScreen;
import mobile.screens.explorer.ProjectScreen;
import mobile.support.Device;
import mobile.support.Global;
import mobile.support.ScreenFactory;
import org.apache.commons.exec.OS;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

@ContextConfiguration(locations = {"classpath:config/applicationContext.xml"})
public abstract class AbstractTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private AppiumDriver appiumDriver;
    @Autowired
    protected ScreenFactory screenFactory;
    @Autowired
    protected MobileElementFinder mobileElementFinder;
    @Autowired
    protected MobileAction mobileAction;

    @Autowired
    protected Hotspot hotspot;
    @Autowired
    private LoginScreen loginScreen;
    @Autowired
    private ProjectScreen projectScreen;
    @Autowired
    protected EditorScreen editorScreen;

    private AppiumServerBuilder appiumServer;

    @BeforeSuite
    public void startAppiumServer() {
        DataReader dataReader = new DataReader(Global.FILE_CONFIG);
        appiumServer = new AppiumServerBuilder();

        if (OS.isFamilyWindows())
            appiumServer.setAppiumDirectoryOnWindowsOS(dataReader.getProperty("server_directory_windows"));

        appiumServer.startServer(dataReader.getProperty("address"), dataReader.getProperty("port"));

        // Set device information
        switch (dataReader.getProperty("platform").toLowerCase()) {
            case "ios":
                Device.setDeviceInfo(dataReader.getProperty("ios_device_udid"));
                break;

            case "android":
                Device.setDeviceInfo(dataReader.getProperty("android_device_id"));
                break;
        }
    }

    @AfterSuite
    public void cleanUP() {
        appiumDriver.quit();
        appiumServer.stopServer();
    }

    @BeforeClass
    public void initTest() {
        String appTitle = "Ionic Testbeds";
        int timeout = 5;
        WebElement element;

        if (Device.isIOS)
            element = mobileElementFinder.findElementByXPath("//UIAStaticText[@name='" + appTitle + "']", timeout);
        else
            element = mobileElementFinder.findElementByName(appTitle, timeout);

        if (element == null) {
            Common.log(appTitle + " app is NOT ready");
            appiumDriver.quit();
            appiumServer.stopServer();
        } else
            Common.log(appTitle + " app is ready");
    }

    public void startNewSession() {
        hotspot.tapHotspot()
                .tapTopMenuOpenProjectScreen();

        projectScreen.selectProject("Auto-Project")
                .tapCreateNewSession()
                .typeTitleEditBox("Automation test")
                //.typeDescriptionEditBox("Description of automation test")
                .tapCreateSessionButton();
    }

    public EditorScreen stopSession() {
        editorScreen = hotspot.tapHotspot()
                .tapTopMenuOpenEditorScreen()
                .tapActionMenu();
        return editorScreen;
    }

}
