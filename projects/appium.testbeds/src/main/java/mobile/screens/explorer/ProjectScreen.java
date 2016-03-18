package mobile.screens.explorer;

import libraries.core.DataReader;
import libraries.utility.Common;
import mobile.screens.AbstractScreen;
import mobile.support.Device;
import mobile.support.Global;
import org.openqa.selenium.Point;
import org.springframework.stereotype.Component;
import org.testng.Assert;

@Component
public class ProjectScreen extends AbstractScreen {

    private final String SELECTED_PROJECT_NAME = "Auto-Project";
    private final String SELECTING_PROJECT_NAME = "SELECT WORKING PROJECT";
    private boolean isProjectSelectedAlready;

    @Override
    protected void load() {
        DataReader loginInfo = new DataReader(Global.FILE_DATA);
        String url = loginInfo.getProperty("qTest_url");
        String email = loginInfo.getProperty("qTest_email");
        String password = loginInfo.getProperty("qTest_password");

        LoginScreen loginScreen = screenFactory.loadExplorerLoginScreen();
        loginScreen.loginAs(url, email, password);
    }

    @Override
    protected void isLoaded() throws Error {
        xpath = Device.isIOS ? "//UIAStaticText[@name='%s']" : "//android.widget.TextView[@text='%s']";
        element = mobileElementFinder.findElementByXPath(String.format(xpath, SELECTED_PROJECT_NAME), Global.SHORT_TIMEOUT);
        isProjectSelectedAlready = element != null;

        if (element == null) {
            element = mobileElementFinder.findElementByXPath(String.format(xpath, SELECTING_PROJECT_NAME), Global.SHORT_TIMEOUT);
            Assert.assertNotNull(element, String.format("You are NOT on %s screen", SELECTING_PROJECT_NAME));
            Common.log(String.format("You are on %s screen", SELECTING_PROJECT_NAME));
        } else
            Common.log(String.format("You are on %s screen", SELECTED_PROJECT_NAME));
    }

    public ProjectScreen selectProject(String projectName) {
        if (!isProjectSelectedAlready) {
            driver.scrollTo(projectName);
            xpath = Device.isIOS ? "//UIATableCell[@name='%s']" : "//android.widget.TextView[@text='%s']";
            element = mobileElementFinder.findElementByXPath(String.format(xpath, projectName));
            mobileAction.tap(element);
            Common.log(String.format("Tap on '%s' project", projectName));
        }
        return this;
    }

    public ProjectScreen tapCreateNewSession() {
        if (Device.isIOS)
            element = mobileElementFinder.findElementByName("Create New");
        else
            element = mobileElementFinder.findElementByXPath("//android.widget.TextView[contains(@text,'Create New')]");
        mobileAction.tap(element);
        Common.log("Tap on 'Create New' session");
        return this;
    }

    public ProjectScreen typeTitleEditBox(String text) {
        if (Device.isIOS) {
            element = mobileElementFinder.findElementByXPath("//UIATextField[@value='Title']");
            mobileAction.typeIOS(element, text);
        } else {
            element = mobileElementFinder.findElementByXPath("//android.widget.EditText[@text='Title']");
            mobileAction.typeAndroid(element, text);
        }
        Common.log(String.format("Type in 'Title' edit box: '%s'", text));
        return this;
    }

    public ProjectScreen typeDescriptionEditBox(String text) {
        if (Device.isIOS) {
            element = mobileElementFinder.findElementByXPath("//UIATextField[@value='Description']");
            mobileAction.typeIOS(element, text);
        } else {
            mobileElementFinder.findElementByXPath("//android.widget.EditText[@text='Description']");
            mobileAction.typeAndroid(element, text);
        }
        Common.log(String.format("Type in 'Description' edit box: '%s'", text));
        return this;
    }

    public void tapCreateSessionButton() {
        Point btnCreate = new Point(0, 0);
        if (Device.isIOS) {
            element = mobileElementFinder.findElementByXPath("//UIATextField[@value='Automation test']");
            btnCreate.x = element.getLocation().x + element.getSize().getWidth() + 50;
            btnCreate.y = element.getLocation().y + 25;
        } else {
            element = mobileElementFinder.findElementByXPath("//android.widget.EditText[@text='Automation test']");
            btnCreate.x = element.getLocation().x + element.getSize().getWidth() + 100;
            btnCreate.y = element.getLocation().y + 50;
        }
        mobileAction.tap(btnCreate.x, btnCreate.y);
        Common.log("Tap on '+' button");
        Common.wait(2);
    }

}
