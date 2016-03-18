package mobile.screens.targets;

import libraries.utility.Common;
import mobile.screens.AbstractScreen;
import mobile.support.Device;
import mobile.support.Global;
import org.springframework.stereotype.Component;
import org.testng.Assert;

@Component
public class DojoScreen extends AbstractScreen {

    private int showcasePanelWidth;
    public int scrollY = 0;

    @Override
    protected void load() {
        // TODO
    }

    @Override
    protected void isLoaded() throws Error {
        message = "CONTROLS";
        if (Device.isIOS)
            element = mobileElementFinder.findElementByXPath(String.format("//UIAStaticText [@name='%s']", message));
        else {
            element = mobileElementFinder.findElementByName(message);
            showcasePanelWidth = Device.isPhone ? 880 : 500;
        }
        Assert.assertNotNull(element, "You are NOT on Dojo screen");
        Global.currentTarget = Global.TARGET_DOJO;
        Common.wait(3);
        Common.log("You are on Dojo screen");
    }

    public DojoScreen selectMenuItem(String text) {
        if (Device.isIOS) {
            element = mobileElementFinder.findElementByXPath(String.format("//UIAButton[@name='%s']", text));
            mobileAction.tap(element);
        } else {
            element = mobileElementFinder.findElementByXPath(String.format("//android.widget.Button[@content-desc='%s']", text));
            if (Device.isPhone) {
                if (scrollY == 0)
                    mobileAction.tap(element);
                else {
                    if (text.equals("AJAX"))
                        mobileAction.tap(300, 1170);
                    else if (text.equals("Accordion"))
                        mobileAction.tap(300, Device.screenWidth - 100);
                    else
                        mobileAction.tap(300, element.getLocation().getY() - Device.screenWidth + scrollY - 170);
                }
            } else {
                if (text.equals("AJAX")) {
                    Common.wait(2);
                    x = element.getLocation().getX() + 100;
                    y = element.getLocation().getY() - Device.screenHeight + 500;
                    mobileAction.tap(x, y);
                } else
                    mobileAction.tap(element);
            }
        }
        Common.log(String.format("Select '%s' menu item", text));
        return this;
    }

    public DojoScreen back() {
        if (Device.isIOS & Device.isPhone) {
            element = mobileElementFinder.findElementByXPath("//UIAButton[@name='Back']");
            mobileAction.tap(element);
            Common.log("Tap on 'Back' button to return menu list");
        }
        return this;
    }

    public DojoScreen tapButton(String text) {
        if (Device.isIOS) {
            element = mobileElementFinder.findElementByXPath(String.format("//UIAButton[@name='%s']", text));
            mobileAction.tap(element);
        } else {
            if (Device.isPhone) {
                if (text.equals("Load using AJAX"))
                    mobileAction.tap(1130, 320);
                else {
                    element = mobileElementFinder.findElementByName(text);
                    x = element.getLocation().getX() - Device.screenHeight + showcasePanelWidth;
                    y = element.getLocation().getY();
                    mobileAction.tap(x + 400, y + 60);
                }
            } else {
                element = mobileElementFinder.findElementByName(text);
                int x = element.getLocation().getX() - Device.screenWidth + 600;
                int y = element.getLocation().getY() + 30;
                if (text.equals("Load using AJAX"))
                    mobileAction.tap(x, y);
                else
                    mobileAction.tap(1010, 270);
            }
        }
        Common.log(String.format("Tap on '%s' button", text));
        return this;
    }

    public DojoScreen typeTextbox(String text) {
        if (Device.isIOS) {
            element = mobileElementFinder.findElementByXPath("//UIATextField[@value='Luke']");
            mobileAction.typeIOS(element, text);
        } else {
            element = mobileElementFinder.findElementByName("Luke");
            y = element.getLocation().getY() + 20;

            if (Device.isPhone) {
                x = element.getLocation().getX() - Device.screenWidth + 300;
                mobileAction.tap(x, y);
                mobileAction.tapAndHold(2220, 1200, 1);
            } else {
                x = element.getLocation().getX() - Device.screenWidth + 600;
                mobileAction.tap(x, y);
                mobileAction.tapAndHold(1450, 1430, 1);
            }
            driver.hideKeyboard();
            mobileAction.tap(x, y);
            elementList = mobileElementFinder.findElementsByXPath("//android.widget.EditText");
            element = elementList.get(0);
            mobileAction.typeAndroid(element, text);
            Common.wait(2);
        }
        Common.log(String.format("Type in 'Full name' edit box: '%s'", text));
        return this;
    }

    public DojoScreen selectCheckbox(String text) {
        if (Device.isIOS)
            element = mobileElementFinder.findElementByXPath("//UIASwitch[@name='']");
        else
            element = mobileElementFinder.findElementByXPath("//android.widget.CheckBox[@content-desc='']");
        mobileAction.tap(element);
        Common.log(String.format("Tap on '%s' check box", text));
        return this;
    }

    public DojoScreen typeTextarea(String text) {
        if (Device.isIOS) {
            element = mobileElementFinder.findElementByXPath("//UIATextField[2]");
            mobileAction.typeIOS(element, text);
        } else {
            elementList = mobileElementFinder.findElementsByXPath("//android.widget.EditText");
            element = elementList.get(1);
            mobileAction.typeAndroid(element, text);
        }
        Common.log(String.format("Type in 'Goals' textarea: '%s'", text));
        Common.wait(2);
        return this;
    }

    public DojoScreen selectRadioButton(String text) {
        if (Device.isIOS)
            element = mobileElementFinder.findElementByXPath("//UIAElement[@value='0']");
        else {
            elementList = mobileElementFinder.findElementsByXPath("//android.widget.RadioButton");
            element = elementList.get(0);
        }
        if (Device.isAndroid && Device.isPhone)
            mobileAction.tap(element.getLocation().getX() + 50, element.getLocation().getY() - Device.screenWidth - 190);
        else
            mobileAction.tap(element);

        Common.log(String.format("Tap on '%s' radio button", text));
        return this;
    }

    public DojoScreen tapToggleButton(String text) {
        if (Device.isIOS) {
            element = mobileElementFinder.findElementByXPath(String.format("//UIAButton[@name='%s']", text));
            mobileAction.tap(element);
        } else {
            element = mobileElementFinder.findElementByXPath("//android.widget.Button[@content-desc='Toggle Button']");
            if (Device.isPhone)
                x = element.getLocation().getX() - Device.screenHeight + showcasePanelWidth + 200;
            else
                x = element.getLocation().getX() - Device.screenWidth + 640;
            y = element.getLocation().getY() + 50;
            mobileAction.tap(x, y);
        }
        Common.log(String.format("Tap on '%s' button", text));
        return this;
    }

    public DojoScreen selectListItem(String text) {
        if (Device.isIOS) {
            mobileElementFinder.findElementByXPath("//UIAButton[@name='Music']").click();
            mobileElementFinder.findElementByXPath("//UIAButton[@name='Modern']").click();
            element = mobileElementFinder.findElementByXPath(String.format("//UIAStaticText[@name='%s']", text));
        } else {
            mobileElementFinder.findElementByXPath("//android.widget.Button[@content-desc='Music']").click();
            Common.wait(1);
            mobileElementFinder.findElementByXPath("//android.widget.Button[@content-desc='Modern']").click();
            Common.wait(1);
            element = mobileElementFinder.findElementByXPath(String.format("//android.view.View[@content-desc='%s']", text));
        }
        mobileAction.tap(element);

        if (Device.isIOS) {
            mobileElementFinder.findElementByXPath("//UIAButton[@name='Music']").click();
            mobileElementFinder.findElementByXPath("//UIAButton[@name='Lists']").click();
        }
        Common.log(String.format("Select '%s' list item", text));
        return this;
    }

    public DojoScreen typeFilteredListSearch(String text) {
        if (Device.isIOS) {
//            element = mobileElementFinder.findElementByXPath(String.format("//UIAStaticText[@name='%s']", text));
        } else {
            if (Device.isPhone) {
                mobileAction.tap(1300, 300);
                mobileAction.tap(230, 1060);
                mobileAction.tap(2150, 1060);
                mobileAction.tap(230, 1060);
                mobileAction.tap(490, 1070);
                mobileAction.tap(1910, 1060);
                mobileAction.tap(230, 1060);
                mobileAction.hideAndroidKeyboard();
                Common.wait(2);
            } else {
                mobileAction.tap(300, 300);
                mobileAction.typeAndroid(text);
            }
        }
        Common.log(String.format("Type in 'Search' edit box: '%s'", text));
        return this;
    }

    public DojoScreen selectTab(String text) {
        if (Device.isIOS) {
            mobileElementFinder.findElementByXPath("//UIAButton[@name='Fixed']").click();
            element = mobileElementFinder.findElementByXPath(String.format("//UIAButton[@name='%s']", text));
            mobileAction.tap(element);
        } else {
            if (Device.isPhone) {
                element = mobileElementFinder.findElementByXPath("//android.view.View[@content-desc='Fixed']");
                mobileAction.tap(element.getLocation().getX() - Device.screenWidth, element.getLocation().getY() + 50);
                element = mobileElementFinder.findElementByXPath(String.format("//android.view.View[@content-desc='%s']", text));
                mobileAction.tap(element.getLocation().getX() + 100, element.getLocation().getY() + 50);
            } else {
                int x, y;
                element = mobileElementFinder.findElementByXPath("//android.view.View[@content-desc='Fixed']");
                x = element.getLocation().getX() - Device.screenWidth + 600;
                y = element.getLocation().getY() + 20;
                mobileAction.tap(x, y);
                element = mobileElementFinder.findElementByXPath(String.format("//android.view.View[@content-desc='%s']", text));
                mobileAction.tap(element);
            }
        }
        Common.log(String.format("Select '%s' tab", text));
        return this;
    }

    public DojoScreen tapLink(String text) {
        if (Device.isIOS) {
            element = mobileElementFinder.findElementByXPath(String.format("//UIAStaticText[@name='%s']", text));
            mobileAction.tap(element);
        } else {
            if (Device.isPhone) {
                mobileAction.tap(1650, 1270);
            } else {
                element = mobileElementFinder.findElementByXPath(String.format("//android.view.View[@content-desc='%s']", text));
                mobileAction.tap(element);
            }
        }
        Common.log(String.format("Tap on '%s' link", text));
        return this;
    }

}
