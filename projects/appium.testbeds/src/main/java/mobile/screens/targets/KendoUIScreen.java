package mobile.screens.targets;

import libraries.utility.Common;
import mobile.screens.AbstractScreen;
import mobile.support.Device;
import mobile.support.Global;
import org.springframework.stereotype.Component;
import org.testng.Assert;

@Component
public class KendoUIScreen extends AbstractScreen {

    @Override
    protected void load() {
        // TODO
    }

    @Override
    protected void isLoaded() throws Error {
        message = "Kendo UI";
        if (Device.isIOS)
            element = mobileElementFinder.findElementByXPath(String.format("//UIAStaticText [@name='%s']", message));
        else
            element = mobileElementFinder.findElementByName(message);
        Assert.assertNotNull(element, "You are NOT on KendoUI screen");
        Global.currentTarget = Global.TARGET_KENDOUI;
        Common.wait(3);
        Common.log("You are on KendoUI screen");
    }

    public KendoUIScreen selectMenuItem(String text) {
        String[] sub = text.split(">");

        if (Device.isIOS) {
            xpath = String.format("//UIAStaticText[@name='%s']/..//UIALink[contains(@name,'%s')]", sub[0], sub[1]);
            if (sub[0].equals("Button")) {
                elementList = mobileElementFinder.findElementsByXPath(xpath, 0);
                element = elementList.get(1);
            } else if (sub[0].equals("TabStrip")) {
                elementList = mobileElementFinder.findElementsByXPath(xpath, 0);
                element = elementList.get(12);
            } else {
                element = mobileElementFinder.findElementByXPath(xpath);
            }
            mobileAction.tap(element);
        } else {
            xpath = String.format("//android.view.View[contains(@content-desc,'%s')]//android.view.View[contains(@content-desc,'%s')]", sub[0], sub[1]);
            if (sub[0].equals("Button")) {
                elementList = mobileElementFinder.findElementsByXPath(xpath);
                mobileAction.tap(elementList.get(0));
            } else if (sub[0].equals("Forms")) {
                if (sub[1].equals("Checks and Radios")) {
                    element = mobileElementFinder.findElementByXPath(xpath);
                    x = element.getLocation().x + Device.screenWidth;
                    if (Device.isPhone)
                        y = Device.screenHeight / 2 - 166;
                    else
                        y = Device.screenHeight / 2;
                    mobileAction.tap(x, y);
                } else if (sub[1].equals("Form types")) {
                    element = mobileElementFinder.findElementByXPath(xpath);
                    x = element.getLocation().x - Device.screenWidth + 200;
                    y = element.getLocation().y + 50;
                    mobileAction.tap(x, y);
                }
            } else if (sub[0].equals("TabStrip")) {
                mobileAction.tap(Device.screenWidth / 2, Device.screenHeight / 2);
            } else {
                element = mobileElementFinder.findElementByXPath(xpath);
                mobileAction.tap(element);
            }
        }
        Common.log(String.format("Select '%s' > '%s' menu item", sub[0], sub[1]));
        return this;
    }

    public KendoUIScreen back() {
        if (Device.isIOS) {
            elementList = mobileElementFinder.findElementsByXPath("//UIALink/UIAStaticText", 0);
            element = elementList.get(0);
            x = element.getLocation().x + 20;
            y = element.getLocation().y + 10;
            mobileAction.tap(x, y);
        } else {
            if (Device.isPhone) {
                elementList = mobileElementFinder.findElementsByXPath("//android.view.View[@content-desc='']");
                element = elementList.get(0);
                x = element.getLocation().x + 60;
                y = element.getLocation().y + 60;
                mobileAction.tap(x, y);
            } else {
                elementList = mobileElementFinder.findElementsByXPath("//android.view.View[@content-desc='']");
                element = elementList.get(0);
                x = element.getLocation().x + 60;
                y = element.getLocation().y + 60;
                mobileAction.tap(x, y);
            }
        }
        Common.log("Tap on 'Back' button");
        return this;
    }

    public KendoUIScreen tapButton(String text) {
        if (Device.isIOS) {
            element = mobileElementFinder.findElementByXPath(String.format("//UIAStaticText[@name='%s']", text));
            mobileAction.tap(element);
        } else {
            element = mobileElementFinder.findElementByName(text);
            x = element.getLocation().x - Device.screenWidth + 200;
            y = element.getLocation().y + 110;
            mobileAction.tap(x, y);
        }
        Common.log(String.format("Tap on '%s' button", text));
        return this;
    }

    public KendoUIScreen typeTextbox(String text) {
        if (Device.isIOS) {
            mobileElementFinder.findElementByXPath("//UIAStaticText[@name='Login/Sign-up']").click();
            element = mobileElementFinder.findElementByXPath("//UIATextField");
            mobileAction.typeIOS(element, text);
            element = mobileElementFinder.findElementByXPath("//UIAStaticText[@name='Cancel']");
            mobileAction.tap(element);
        } else {
            element = mobileElementFinder.findElementByName("Login/Sign-up");
            x = element.getLocation().x - Device.screenWidth + Device.screenWidth / 2;
            y = element.getLocation().y + 100;
            mobileAction.tap(x, y);
            Common.log("Tap on 'Login/Sign-up' button");
            element = mobileElementFinder.findElementByXPath("//android.view.View[@content-desc='Username:']//android.widget.EditText");
            x = element.getLocation().x;
            y = element.getLocation().y - Device.screenHeight + 100;
            mobileAction.tap(x, y);
            mobileAction.typeAndroid(text);
            mobileElementFinder.findElementByName("Cancel").click();
        }
        Common.log(String.format("Type in 'Username' edit box: '%s'", text));
        return this;
    }

    public KendoUIScreen selectCheckbox(String text) {
        if (Device.isIOS) {
            elementList = mobileElementFinder.findElementsByXPath("//UIASwitch");
            element = elementList.get(1);
            mobileAction.tap(element);
        } else {
            Common.wait(3);
            element = mobileElementFinder.findElementByXPath(String.format("//android.view.View[@content-desc='%s']", text), 0);
            x = element.getLocation().x - 110;
            y = element.getLocation().y + 110;
            mobileAction.tap(x, y);
        }
        Common.log(String.format("Tap on '%s' check box", text));
        return this;
    }

    public KendoUIScreen selectMenuTab(String text) {
        if (Device.isIOS) {
            element = mobileElementFinder.findElementByXPath(String.format("//UIALink[contains(@name,'%s')]", text));
            mobileAction.tap(element);
        } else {
            element = mobileElementFinder.findElementByXPath(String.format("//android.view.View[contains(@content-desc,'%s')]", text));
            if (text.equals("Radio Buttons")) {
                x = Device.screenWidth - Device.screenWidth / 4;
                y = element.getLocation().y + 110;
            } else if (text.equals("Sales")) {
                x = Device.screenWidth / 2 - 200;
                y = Device.screenHeight - 100;
                Common.wait(2);
            }
            mobileAction.tap(x, y);
        }
        Common.log(String.format("Tap on '%s' menu tab", text));
        return this;
    }

    public KendoUIScreen selectRadioButton(String text) {
        if (Device.isIOS) {
            elementList = mobileElementFinder.findElementsByXPath("//UIAElement", 0);
            element = elementList.get(1);
            x = Device.screenWidth - 30;
            y = element.getLocation().y + 20;
            mobileAction.tap(x, y);
        } else {
            element = mobileElementFinder.findElementByXPath(String.format("//android.widget.RadioButton[@content-desc='%s']", text));
            x = Device.screenWidth - 90;
            y = element.getLocation().y + 70;
            mobileAction.tap(x, y);
        }
        Common.log(String.format("Tap on '%s' radio button", text));
        return this;
    }

    public KendoUIScreen selectDropDownItem(String text) {
        if (Device.isIOS) {
            element = mobileElementFinder.findElementByXPath("//UIAElement[@name='Select element']");
            mobileAction.tap(element);
            Common.log("Tap on 'Select element' combo box");
            element = mobileElementFinder.findElementByXPath("//UIAPickerWheel");
            mobileAction.typeIOS(element, text);
        } else {
            if (Device.isPhone) {
                element = mobileElementFinder.findElementsByXPath("//android.widget.Spinner[@content-desc='First Option']").get(0);
                x = Device.screenWidth - Device.screenWidth / 4;
                y = element.getLocation().y + 10;
                mobileAction.tap(x, y);
                Common.log("Tap on 'Select element' combo box");
                x = 700;
                y = 1120;
                mobileAction.tap(x, y);
            } else {
                element = mobileElementFinder.findElementsByXPath("//android.widget.Spinner[@content-desc='First Option']").get(0);
                x = Device.screenWidth - Device.screenWidth / 4;
                y = element.getLocation().y + 10;
                mobileAction.tap(x, y);
                Common.log("Tap on 'Select element' combo box");
                mobileAction.tap(x - 200, y + 200);
            }
        }
        Common.log(String.format("Select '%s' combo item", text));
        return this;
    }

    public KendoUIScreen typeTextarea(String text) {
        String[] sub = text.split("\n");

        if (Device.isIOS) {
            if (Device.isPhone) {
                element = mobileElementFinder.findElementByXPath("//UIATextField[@name='Textarea']");
                element.clear();
                element.sendKeys(sub[0]);
                for (int i = 1; i < sub.length; i++) {
                    mobileAction.tap(Device.screenWidth - 70, Device.screenHeight - 20);
                    element.sendKeys(sub[i]);
                }
                mobileAction.hideIOSKeyboard();
            }
        } else {
            x = Device.screenWidth / 2;
            y = Device.screenHeight - 50;
            mobileAction.tap(x, y);

            if (Device.isPhone)
                mobileAction.tapAndHold(Device.hotspotX - 30, Device.hotspotY + 360, 2);
            else
                mobileAction.tapAndHold(Device.hotspotX - 30, Device.hotspotY + 60, 2);
            mobileAction.typeAndroid(text);
        }
        Common.log(String.format("Type in 'Textarea' edit box: '%s'", text));
        return this;
    }

    public KendoUIScreen selectListItem(String text) {
        String[] sub = text.split(">");

        if (Device.isIOS)
            xpath = String.format("//UIAStaticText[contains(@name,'%s')]", sub[0]);
        else
            xpath = String.format("//android.view.View[contains(@content-desc,'%s')]", sub[0]);
        element = mobileElementFinder.findElementByXPath(xpath);

        switch (sub[0]) {
            case "February":
                x = Device.isIOS ? 40 : 100;
                break;

            case "March":
                x = Device.screenWidth / 2;
                break;

            case "April":
                x = Device.isIOS ? Device.screenWidth - 50 : Device.screenWidth - 150;
                break;
        }
        y = Device.isIOS ? element.getLocation().y + 5 : element.getLocation().y + 100;
        mobileAction.tap(x, y);
        Common.log(String.format("Select '%s' list item", text));
        return this;
    }
}
