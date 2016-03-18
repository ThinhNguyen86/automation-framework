package mobile.screens.explorer;

import libraries.utility.Common;
import mobile.screens.AbstractScreen;
import mobile.support.Device;
import mobile.support.Global;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;
import org.testng.Assert;

@Component
public class LoginScreen extends AbstractScreen {

    @Override
    protected void load() {
        // TODO
    }

    @Override
    protected void isLoaded() throws Error {
        if (Device.isIOS)
            element = mobileElementFinder.findElementByXPath("//UIATextField[@value='Email/User name']", Global.SHORT_TIMEOUT);
        else
            element = mobileElementFinder.findElementByXPath("//android.widget.EditText[@text='Email/User name']", Global.SHORT_TIMEOUT);
        Assert.assertNotNull(element, "You are NOT on qTest User login screen.");
        Common.log("You are on qTest User login screen.");
    }

    // qTest Host edit box
    @FindBy(xpath = "//UIATextField[@value='URL']")
    private WebElement qTestHostiOS;
    @FindBy(xpath = "//android.widget.EditText[@text='URL']")
    private WebElement qTestHostAndroid;

    // qTest ID edit box
    @FindBy(xpath = "//UIATextField[@value='Email/User name']")
    private WebElement qTestEmailiOS;
    @FindBy(xpath = "//android.widget.EditText[@text='Email/User name']")
    private WebElement qTestEmailAndroid;

    // qTest Password edit box
    @FindBy(xpath = "//UIASecureTextField[@value='Password']")
    private WebElement qTestPasswordiOS;
    @FindBy(xpath = "//android.widget.EditText[@text='']")
    private WebElement qTestPasswordAndroid;

    // qTest Login button
    @FindBy(name = "light btn login")
    private WebElement qTestLoginiOS;
    @FindBy(xpath = "//android.widget.Button[contains(@resource-id,'loginBtn')]")
    private WebElement qTestLoginAndroid;

    public LoginScreen typeUrl(String host) {
        if (Device.isIOS)
            qTestHostiOS.sendKeys(host);
        else
            qTestHostAndroid.sendKeys(host);
        Common.log(String.format("Type '%s' in URL edit box", host));
        return this;
    }

    public LoginScreen typeEmail(String email) {
        if (Device.isIOS)
            qTestEmailiOS.sendKeys(email);
        else
            qTestEmailAndroid.sendKeys(email);
        Common.log(String.format("Type '%s' in Email/User name edit box", email));
        return this;
    }

    public LoginScreen typePassword(String password) {
        if (Device.isIOS)
            qTestPasswordiOS.sendKeys(password);
        else
            qTestPasswordAndroid.sendKeys(password);
        Common.log(String.format("Type '%s' in qTest Password edit box", password));
        return this;
    }

    public ProjectScreen tapLogin() {
        if (Device.isIOS)
            qTestLoginiOS.click();
        else
            qTestLoginAndroid.click();
        Common.log("Tap on 'qTest Login' button");
        return null;
    }

    public ProjectScreen loginAs(String url, String email, String password) {
        typeUrl(url);
        typeEmail(email);
        typePassword(password);
        return tapLogin();
    }

}
