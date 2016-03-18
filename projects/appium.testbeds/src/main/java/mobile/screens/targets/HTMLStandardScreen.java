package mobile.screens.targets;

import libraries.utility.Common;
import mobile.screens.AbstractScreen;
import mobile.support.Device;
import mobile.support.Global;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;
import org.testng.Assert;

@Component
public class HTMLStandardScreen extends AbstractScreen {

    // Element: Text box
    @FindBy(xpath = "//UIATextField[@name='Text box']")
    private WebElement eTextboxiOS;
    @FindBy(xpath = "//android.widget.EditText[@content-desc='Text box']")
    private WebElement eTextboxAndroid;

    // Element: Password
    @FindBy(xpath = "//UIASecureTextField[@name='Password']")
    private WebElement ePasswordiOS;
    @FindBy(xpath = "//android.widget.EditText[@password='true']")
    private WebElement ePasswordAndroid;

    // Element: Combo box
    @FindBy(xpath = "//UIAElement[@value='Option 1.1']")
    private WebElement eComboboxiOS;
    @FindBy(xpath = "//android.widget.Spinner[@content-desc='Option 1.1']")
    private WebElement eComboboxAndroid;

    // Element: Checkbox
    @FindBy(xpath = "//UIASwitch[@name='Checkbox 1']")
    private WebElement eCheckboxiOS;
    @FindBy(name = "Checkbox 1")
    private WebElement eCheckboxAndroid;

    // Element: Radio button
    @FindBy(xpath = "//UIAElement[@name='Male']")
    private WebElement eRadioButtoniOS;
    @FindBy(name = "Male")
    private WebElement eRadioButtonAndroid;

    // Element: Hyperlink
    @FindBy(xpath = "//UIAStaticText[@name='Hyperlink']")
    private WebElement eHyperlinkiOS;
    @FindBy(name = "Hyperlink")
    private WebElement eHyperlinkAndroid;

    // Element: List box
    @FindBy(xpath = "//UIAElement[@value='0 Items']")
    private WebElement eListboxiOS;
    @FindBy(xpath = "//android.widget.Spinner[@content-desc='0 selected']")
    private WebElement eListboxAndroid;

    // Element: Standard Button
    @FindBy(xpath = "//UIAButton[@name='Standard Button']")
    private WebElement eButtoniOS;
    @FindBy(name = "Standard Button")
    private WebElement eButtonAndroid;

    // Element: Textarea
    @FindBy(xpath = "//UIATextField[2]")
    private WebElement eTextareaiOS;
    @FindBy(xpath = "//android.widget.EditText[@index='2']")
    private WebElement eTextareaAndroid;

    @Override
    protected void load() {
        // TODO
    }

    @Override
    protected void isLoaded() throws Error {
        message = "HTML Standard Test";
        if (Device.isIOS)
            element = mobileElementFinder.findElementByXPath(String.format("//UIAStaticText[@name='%s']", message));
        else
            element = mobileElementFinder.findElementByName(message);
        Assert.assertNotNull(element, "You are NOT on HTML Standard screen");
        Global.currentTarget = Global.TARGET_HTML_STANDARD;
        Common.wait(3);
        Common.log("You are on HTML Standard screen");
    }

    public HTMLStandardScreen typeTextbox(String text) {
        message = String.format("'Text box' edit box: '%s'", text);
        element = Device.isIOS ? eTextboxiOS : eTextboxAndroid;
        if (Device.isIOS)
            mobileAction.typeIOS(element, text);
        else
            mobileAction.typeAndroid(element, text);
        Common.log("Type in " + message);
        return this;
    }

    public HTMLStandardScreen typePassword(String text) {
        message = String.format("'Password' edit box: '%s'", text);
        element = Device.isIOS ? ePasswordiOS : ePasswordAndroid;
        if (Device.isIOS)
            mobileAction.typeIOS(element, text);
        else
            mobileAction.typeAndroid(element, text);
        Common.log("Type in " + message);
        return this;
    }

    public HTMLStandardScreen selectCombobox(String text) {
        message = String.format("'Combo box' combo box: '%s'", text);
        element = Device.isIOS ? eComboboxiOS : eComboboxAndroid;
        mobileAction.tap(element);
        Common.log("Tap on 'Combo box' combo box");

        if (Device.isIOS) {
            if (Device.isPhone) {
                mobileElementFinder.findElementByXPath("//UIAPickerWheel", 0).sendKeys(text);
                mobileElementFinder.findElementByName("Done", 0).click();
            } else {
                element = mobileElementFinder.findElementByXPath("//UIATableCell[@name='" + text + "']");
                mobileAction.tap(element);
            }
        } else {
            if (Device.isPhone) {
                element = mobileElementFinder.findElementByXPath("//android.widget.CheckedTextView[contains(@text,'" + text + "')]");
                mobileAction.tap(element);
            } else
                mobileAction.tap(element.getLocation().x + 160, element.getLocation().y + 270);
        }
        Common.log(String.format("Select '%s' item", text));
        return this;
    }

    public HTMLStandardScreen selectCheckbox(String text) {
        element = Device.isIOS ? eCheckboxiOS : eCheckboxAndroid;
        mobileAction.tap(element.getLocation().x + 10, element.getLocation().y + 10);
        Common.log(String.format("Tap on '%s' check box", text));
        return this;
    }

    public HTMLStandardScreen selectRadioButton(String text) {
        element = Device.isIOS ? eRadioButtoniOS : eRadioButtonAndroid;
        mobileAction.tap(element.getLocation().x + 10, element.getLocation().y + 10);
        Common.log(String.format("Tap on '%s' radio button", text));
        return this;
    }

    public HTMLStandardScreen tapLink(String text) {
        element = Device.isIOS ? eHyperlinkiOS : eHyperlinkAndroid;
        mobileAction.tap(element);
        Common.log(String.format("Tap on '%s' link", text));
        return this;
    }

    public HTMLStandardScreen selectListItem(String text) {
        element = Device.isIOS ? eListboxiOS : eListboxAndroid;
        int x = element.getLocation().x;
        int y = element.getLocation().y;
        mobileAction.tap(element);
        Common.log("Tap on 'Fruits List box' list box");

        if (Device.isIOS) {
            if (Device.isPhone) {
                mobileAction.swipeBottomToTop(Device.screenWidth / 2, Device.screenHeight - 100, 100);
                mobileAction.tap(Device.screenWidth / 4, Device.screenHeight - 100);
                mobileElementFinder.findElementByName("Done", 0).click();
            } else {
                element = mobileElementFinder.findElementByXPath("//UIATableCell[@name='" + text + "']");
                mobileAction.tap(element);
                mobileAction.tap(x, y);
            }
        } else {
            element = mobileElementFinder.findElementByXPath("//android.widget.CheckedTextView[contains(@text,'" + text + "')]");
            mobileAction.tap(element);
            mobileElementFinder.findElementByXPath("//android.widget.Button[@text='OK']").click();
        }
        Common.log(String.format("Select '%s' item", text));

        return this;
    }

    public HTMLStandardScreen tapButton(String text) {
        message = String.format("'%s' button", text);
        element = Device.isIOS ? eButtoniOS : eButtonAndroid;
        mobileAction.tap(element);
        return this;
    }

    public HTMLStandardScreen typeTextarea(String text) {
        element = Device.isIOS ? eTextareaiOS : eTextareaAndroid;
        if (Device.isIOS)
            mobileAction.typeIOS(element, text);
        else
            mobileAction.typeAndroid(element, text);
        if (Device.isAndroid)
            mobileAction.tap(element.getLocation().x + 400, element.getLocation().y - 50);
        Common.log(String.format("Type in 'Textarea' edit box: '%s'", text));
        return this;
    }

}
