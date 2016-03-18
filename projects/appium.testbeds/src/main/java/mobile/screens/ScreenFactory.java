package mobile.screens;

import io.appium.java_client.AppiumDriver;
import mobile.screens.targets.KendoUIScreen;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScreenFactory {

    @Autowired
    protected AppiumDriver driver;
    //    @Autowired
//    protected MobileElementFinder mobileElementFinder;
//    @Autowired
//    protected MobileAction mobileAction;
//
//    @Autowired
//    private DojoScreen dojoScreen;
//    @Autowired
//    private JQueryUIScreen jQueryUIScreen;
//    @Autowired
//    private SenchaTouchScreen senchaTouchScreen;
    @Autowired
    private KendoUIScreen kendoUIScreen;
//    @Autowired
//    private HTML5Screen html5Screen;
//    @Autowired
//    private HTMLStandardScreen htmlStandardScreen;
//
//    @Autowired
//    private LoginScreen loginScreen;
//    @Autowired
//    private ProjectScreen projectScreen;
//    @Autowired
//    private EditorScreen editorScreen;

    //    private DataReader dataReader;
    private String targetName;
    protected WebElement element;

    public ScreenFactory() {
//        dataReader = new DataReader(Global.FILE_CONFIG);
    }

    private void loadTargetTemplate(String targetName, AbstractScreen screen) {
//        if (Device.isiOS)
//            element = mobileElementFinder.findElementByXPath(String.format("//UIAStaticText[@name='%s']", targetName));
//        else
//            element = mobileElementFinder.findElementByName(targetName);
//
//        mobileAction.tap(String.format("'%s' target link", targetName), element);
        PageFactory.initElements(driver, screen);
        screen.get();
    }

//    public DojoScreen loadTargetDojoScreen() {
//        targetName = dataReader.getProperty("target_dojo");
//        loadTargetTemplate(targetName, dojoScreen);
//        return dojoScreen;
//    }
//
//    public JQueryUIScreen loadTargetJQueryUIScreen() {
//        targetName = dataReader.getProperty("target_jqueryui");
//        loadTargetTemplate(targetName, jQueryUIScreen);
//        return jQueryUIScreen;
//    }
//
//    public SenchaTouchScreen loadTargetSenchaTouchScreen() {
//        targetName = dataReader.getProperty("target_sencha_touch");
//        loadTargetTemplate(targetName, senchaTouchScreen);
//        return senchaTouchScreen;
//    }

    public KendoUIScreen loadTargetKendoUIScreen() {
//        targetName = dataReader.getProperty("target_kendoui");
        loadTargetTemplate(targetName, kendoUIScreen);
        return kendoUIScreen;
    }

//    public HTML5Screen loadTargetHTML5Screen() {
//        targetName = dataReader.getProperty("target_html5");
//        loadTargetTemplate(targetName, html5Screen);
//        return html5Screen;
//    }
//
//    public HTMLStandardScreen loadTargetHTMLStandardScreen() {
//        targetName = dataReader.getProperty("target_html_standard");
//        loadTargetTemplate(targetName, htmlStandardScreen);
//        return htmlStandardScreen;
//    }
//
//    public LoginScreen loadExplorerLoginScreen() {
//        PageFactory.initElements(driver, loginScreen);
//        loginScreen.get();
//        return loginScreen;
//    }
//
//    public ProjectScreen loadExplorerProjectScreen() {
//        PageFactory.initElements(driver, projectScreen);
//        projectScreen.get();
//        return projectScreen;
//    }
//
//    public EditorScreen loadEditorScreen() {
//        PageFactory.initElements(driver, editorScreen);
//        editorScreen.get();
//        return editorScreen;
//    }

}
