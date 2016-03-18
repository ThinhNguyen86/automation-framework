package mobile.support;

import io.appium.java_client.AppiumDriver;
import libraries.core.DataReader;
import libraries.mobile.MobileAction;
import libraries.mobile.MobileElementFinder;
import libraries.utility.Common;
import mobile.screens.AbstractScreen;
import mobile.screens.explorer.EditorScreen;
import mobile.screens.explorer.LoginScreen;
import mobile.screens.explorer.ProjectScreen;
import mobile.screens.targets.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScreenFactory {

    @Autowired
    protected AppiumDriver driver;
    @Autowired
    protected MobileElementFinder mobileElementFinder;
    @Autowired
    protected MobileAction mobileAction;

    @Autowired
    private DojoScreen dojoScreen;
    @Autowired
    private HTML5Screen html5Screen;
    @Autowired
    private HTMLStandardScreen htmlStandardScreen;
    @Autowired
    private JQueryUIScreen jQueryUIScreen;
    //    @Autowired
//    private SenchaTouchScreen senchaTouchScreen;
    @Autowired
    private KendoUIScreen kendoUIScreen;

    @Autowired
    private LoginScreen loginScreen;
    @Autowired
    private ProjectScreen projectScreen;
    @Autowired
    private EditorScreen editorScreen;

    private DataReader dataReader;
    private String targetName;
    protected WebElement element;

    public ScreenFactory() {
        dataReader = new DataReader(Global.FILE_DATA);
    }

    private void loadTargetTemplate(String targetName, AbstractScreen screen) {
        if (Device.isIOS)
            element = mobileElementFinder.findElementByXPath(String.format("//UIAStaticText[@name='%s']", targetName));
        else
            element = mobileElementFinder.findElementByName(targetName);

        mobileAction.tap(element);
        Common.log(String.format("Tap on '%s' target link", targetName));
        PageFactory.initElements(driver, screen);
        screen.get();
    }

    public DojoScreen loadTargetDojoScreen() {
        targetName = dataReader.getProperty("target_dojo");
        loadTargetTemplate(targetName, dojoScreen);
        return dojoScreen;
    }

    public HTML5Screen loadTargetHTML5Screen() {
        targetName = dataReader.getProperty("target_html5");
        loadTargetTemplate(targetName, html5Screen);
        return html5Screen;
    }

    public HTMLStandardScreen loadTargetHTMLStandardScreen() {
        targetName = dataReader.getProperty("target_html_standard");
        loadTargetTemplate(targetName, htmlStandardScreen);
        return htmlStandardScreen;
    }

    public JQueryUIScreen loadTargetJQueryUIScreen() {
        targetName = dataReader.getProperty("target_jqueryui");
        loadTargetTemplate(targetName, jQueryUIScreen);
        return jQueryUIScreen;
    }

//    public SenchaTouchScreen loadTargetSenchaTouchScreen() {
//        targetName = dataReader.getProperty("target_sencha_touch");
//        loadTargetTemplate(targetName, senchaTouchScreen);
//        return senchaTouchScreen;
//    }

    public KendoUIScreen loadTargetKendoUIScreen() {
        targetName = dataReader.getProperty("target_kendoui");
        loadTargetTemplate(targetName, kendoUIScreen);
        return kendoUIScreen;
    }

    public LoginScreen loadExplorerLoginScreen() {
        PageFactory.initElements(driver, loginScreen);
        loginScreen.get();
        return loginScreen;
    }

    public ProjectScreen loadExplorerProjectScreen() {
        PageFactory.initElements(driver, projectScreen);
        projectScreen.get();
        return projectScreen;
    }

    public EditorScreen loadEditorScreen() {
        PageFactory.initElements(driver, editorScreen);
        editorScreen.get();
        return editorScreen;
    }

}
