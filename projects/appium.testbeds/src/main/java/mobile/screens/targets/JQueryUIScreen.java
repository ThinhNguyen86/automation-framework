package mobile.screens.targets;

import libraries.utility.Common;
import mobile.screens.AbstractScreen;
import mobile.support.Device;
import mobile.support.Global;
import org.springframework.stereotype.Component;
import org.testng.Assert;

@Component
public class JQueryUIScreen extends AbstractScreen {

    private int menuY;

    @Override
    protected void load() {
        // TODO
    }

    @Override
    protected void isLoaded() throws Error {
        message = "Demos";
        if (Device.isIOS)
            element = mobileElementFinder.findElementByXPath(String.format("//UIAStaticText[@name='%s']", message));
        else
            element = mobileElementFinder.findElementByName(message);
        Assert.assertNotNull(element, "You are NOT on jQueryUI screen");
        Global.currentTarget = Global.TARGET_JQUERYUI;
        if (Device.isIOS) {
            if (Device.isPhone)
                menuY = Device.screenHeight * 2 + 150;
        } else {
            if (Device.isPhone)
                menuY = Device.screenHeight + 1614;
        }
        Common.wait(3);
        Common.log("You are on jQueryUI screen");
    }

    public JQueryUIScreen selectMenuIconAndMenuItem(String text) {
        selectMenuIcon();
        selectMenuItem(text);
        return this;
    }

    public JQueryUIScreen selectMenuItem(String text) {
        String[] sub = text.split(">");
        x = 140;

        if (sub.length == 1) {
            if (Device.isIOS) {
                if (Device.isPhone) {
                    if (sub[0].equals("Buttons")) {
                        elementList = mobileElementFinder.findElementsByXPath(String.format("//UIAStaticText[@name='%s']", sub[0]), 0);
                        element = elementList.get(0);
                        mobileAction.tap(x, 110);
                    } else {
                        element = mobileElementFinder.findElementByXPath(String.format("//UIAStaticText[@name='%s']", sub[0]), 0);
                        mobileAction.tap(element);
                    }
                }
            } else {
                xpath = String.format("//android.view.View[contains(@content-desc,'%s')]", sub[0]);
                element = mobileElementFinder.findElementByXPath(xpath, 0);
                if (Device.isPhone) {
                    if (sub[0].equals("Buttons"))
                        y = element.getLocation().y - menuY;
                    else
                        y = element.getLocation().y + 60;
                } else {
                    if (sub[0].equals("Buttons"))
                        y = element.getLocation().y - Device.screenHeight + 50;
                    else
                        y = element.getLocation().y + 50;
                }
                mobileAction.tap(x, y);
            }
            Common.log(String.format("Select '%s' menu item", sub[0]));
            checkWeAreOnTheRightMenu(sub[0]);
        } else {
            if (Device.isIOS) {
                if (Device.isPhone) {
                    // Select parent menu item
                    xpath = String.format("//UIALink[contains(@name,'%s')]", sub[0]);
                    element = mobileElementFinder.findElementByXPath(xpath, 0);
                    y = element.getLocation().y + 10;
                    if (sub[0].equals("Navigation"))
                        mobileAction.tap(x, Device.screenHeight - 200);
                    else
                        mobileAction.tap(element);

                    // Select child menu item
                    xpath = String.format("//UIALink[contains(@name,'%s')]", sub[1]);
                    element = mobileElementFinder.findElementsByXPath(String.format("//UIAStaticText[@name='%s']", sub[1]), 0).get(0);
                    if (sub[1].equals("Checkboxes"))
                        y = y + 60;
                    else if (sub[1].equals("Radio buttons"))
                        y = y + 110;
                    else if (sub[1].equals("Listview"))
                        y = Device.screenHeight / 2 + 50;
                    else if (sub[1].equals("Popup"))
                        y = y + 60;
                    else
                        y = element.getLocation().y + 20;
                    mobileAction.tap(x, y);
                }
            } else {
                if (Device.isPhone) {
                    // Select parent menu item
                    xpath = String.format("//android.view.View[contains(@content-desc,'%s')]", sub[0]);
                    element = mobileElementFinder.findElementByXPath(xpath);
                    y = element.getLocation().y + 100;
                    if (sub[0].equals("Navigation"))
                        mobileAction.tap(x, 1900);
                    else
                        mobileAction.tap(x, y);

                    // Select child menu item
                    xpath = String.format("//android.view.View[contains(@content-desc,'%s')]", sub[1]);
                    element = mobileElementFinder.findElementByXPath(xpath);
                    switch (sub[1]) {
                        case "Checkboxes":
                            y = y + 150;
                            break;

                        case "Radio buttons":
                            y = y + 290;
                            break;

                        case "Listview":
                            y = y + 150;
                            break;

                        case "Popup":
                            y = y + 150;
                            break;

                        default:
                            y = element.getLocation().y + 60;
                            break;
                    }
                    mobileAction.tap(x, y);
                } else {
                    // Select parent menu item
                    xpath = String.format("//android.view.View[contains(@content-desc,'%s')]", sub[0]);
                    element = mobileElementFinder.findElementByXPath(xpath);
                    y = element.getLocation().y + 50;
                    mobileAction.tap(x, y);

                    // Select child menu item
                    xpath = String.format("//android.view.View[contains(@content-desc,'%s')]", sub[1]);
                    element = mobileElementFinder.findElementByXPath(xpath);
                    switch (sub[1]) {
                        case "Checkboxes":
                            y = element.getLocation().y - Device.screenHeight - 400;
                            break;

                        case "Radio buttons":
                            y = element.getLocation().y - Device.screenHeight - 300;
                            break;

                        case "Listview":
                            y = y + 150;
                            break;

                        case "Popup":
                            y = y + 150;
                            break;

                        case "Linking pages":
                            y = y + 240;
                            break;

                        default:
                            y = element.getLocation().y + 50;
                            break;
                    }
                    mobileAction.tap(x, y);
                }
            }
            Common.log(String.format("Select '%s > %s' menu item", sub[0], sub[1]));
            checkWeAreOnTheRightMenu(sub[1]);
        }
        return this;
    }

    public JQueryUIScreen selectMenuIcon() {
        if (Device.isIOS) {
            element = mobileElementFinder.findElementByXPath("//UIAButton[@name='Menu']");
        } else
            element = mobileElementFinder.findElementByName("Menu ");
        mobileAction.tap(element);
        Common.log("Select Menu icon");
        Common.wait(1);
        return this;
    }

    public void checkWeAreOnTheRightMenu(String text) {
        if (Device.isIOS) {
            switch (text) {
                case "Buttons":
                    mobileElementFinder.findElementByXPath("//UIAStaticText[@name='Button markup']");
                    break;

                case "Checkboxes":
                    mobileElementFinder.findElementByXPath("//UIAStaticText[@name='Checkbox']");
                    break;

                case "Radio buttons":
                    mobileElementFinder.findElementByXPath("//UIAStaticText[@name='Radio buttons']");
                    break;

                case "Datepicker":
                    mobileElementFinder.findElementByXPath("//UIAStaticText[@name='Datepicker Widget']");
                    break;

                case "Swipe list items":
                    mobileElementFinder.findElementByXPath("//UIAStaticText[@name='Swipe to delete list item']");
                    break;

                case "Filterable widget":
                    mobileElementFinder.findElementByXPath("//UIAStaticText[@name='Filterable']");
                    break;

                case "Flipswitch widget":
                    mobileElementFinder.findElementByXPath("//UIAStaticText[@name='Flip switch']");
                    break;

                case "Forms gallery":
                    mobileElementFinder.findElementByXPath("//UIAStaticText[@name='Form gallery']");
                    break;

                case "Listview":
                    mobileElementFinder.findElementByXPath("//UIAStaticText[@name='Listview']");
                    break;

                case "Linking pages":
                    mobileElementFinder.findElementByXPath("//UIAStaticText[@name='Linking pages']");
                    break;

                case "Popup":
                    mobileElementFinder.findElementByXPath("//UIAStaticText[@name='Popup']");
                    break;

            }
        } else {
            switch (text) {
                case "Buttons":
                    mobileElementFinder.findElementByName("Button markup");
                    break;

                case "Checkboxes":
                    mobileElementFinder.findElementByName("Checkbox");
                    break;

                case "Radio buttons":
                    mobileElementFinder.findElementByName("Radio buttons");
                    break;

                case "Datepicker":
                    mobileElementFinder.findElementByName("Datepicker Widget");
                    break;

                case "Swipe list items":
                    mobileElementFinder.findElementByName("Swipe to delete list item");
                    break;

                case "Filterable widget":
                    mobileElementFinder.findElementByName("Filterable");
                    break;

                case "Flipswitch widget":
                    mobileElementFinder.findElementByName("Flip switch");
                    break;

                case "Forms gallery":
                    mobileElementFinder.findElementByName("Form gallery");
                    break;

                case "Listview":
                    mobileElementFinder.findElementByName("Listview");
                    break;

                case "Linking pages":
                    mobileElementFinder.findElementByName("Linking pages");
                    break;

                case "Popup":
                    mobileElementFinder.findElementByName("Popup");
                    break;
            }
        }
        Common.wait(1);
    }

    public JQueryUIScreen back() {
        if (Device.isIOS)
            mobileElementFinder.findElementByXPath("//UIAButton[@name='◄']").click();
        else
            driver.navigate().back();
        Common.wait(2);
        return this;
    }

    public JQueryUIScreen tapButton(String text) {
        if (Device.isIOS)
            elementList = mobileElementFinder.findElementsByXPath(String.format("//UIAStaticText[@name='%s']", text), 0);
        else
            elementList = mobileElementFinder.findElementsByName(text);
        mobileAction.tap(elementList.get(0));
        Common.log(String.format("Tap on '%s' button", text));
        return this;
    }

    public JQueryUIScreen tapIcon(String text) {
        if (Device.isIOS) {
            elementList = mobileElementFinder.findElementsByXPath(String.format("//UIAStaticText[@name='%s']", text), 0);
            element = elementList.get(0);
            x = element.getLocation().x + 30;
            y = element.getLocation().y + 16;
            if (Device.isPhone)
                mobileAction.tap(x, y);
        } else {
            String temp = text + " ";
            elementList = mobileElementFinder.findElementsByName(temp);
            mobileAction.tap(elementList.get(0));
        }
        Common.log(String.format("Tap on '%s' icon button", text));
        return this;
    }

    public JQueryUIScreen selectCheckbox(String text) {
        if (Device.isIOS)
            element = mobileElementFinder.findElementByXPath("//UIAStaticText[@name='" + text + "']");
        else
            element = mobileElementFinder.findElementByName(text);
        mobileAction.tap(element);
        Common.log(String.format("Tap on '%s' check box", text));
        return this;
    }

    public JQueryUIScreen selectRadioButton(String text) {
        if (Device.isIOS) {
            elementList = mobileElementFinder.findElementsByXPath("//UIAElement[@name='" + text + "']", 0);
            element = elementList.get(0);
            x = element.getLocation().x + 10;
            y = element.getLocation().y + 10;
            mobileAction.tap(x, y);
        } else {
            elementList = mobileElementFinder.findElementsByName(text);
            mobileAction.tap(elementList.get(0));
        }
        Common.log(String.format("Tap on '%s' radio button", text));
        return this;
    }

    public JQueryUIScreen selectDatePicker(String text) {
        Common.wait(3);
        if (Device.isIOS) {
            element = mobileElementFinder.findElementByXPath(String.format("//UIAStaticText[@name='%s']", text));
            mobileAction.tap(element);
        } else {
            element = mobileElementFinder.findElementByXPath(String.format("//android.view.View[@content-desc='%s']", text));
            x = element.getLocation().x + 60;
            if (Device.isPhone)
                y = element.getLocation().y + 60;
            else
                y = 1300;
            mobileAction.tap(x, y);
        }
        Common.log(String.format("Select '%s' date picker item", text));
        return this;
    }

    public JQueryUIScreen selectListItem(String text) {
        message = "Open swipe list demo";

        if (Device.isIOS) {
            element = mobileElementFinder.findElementByXPath(String.format("//UIAStaticText[@name='%s']", message));
            mobileAction.tap(element);

            element = mobileElementFinder.findElementByXPath(String.format("//UIAStaticText[@name='%s']", text));
            x = Device.screenWidth / 4;
            y = element.getLocation().y + 30;
            mobileAction.tap(element);
        } else {
            element = mobileElementFinder.findElementByName(message);
            mobileAction.tap(element);

            xpath = String.format("//android.view.View[contains(@content-desc,'%s')]", text);
            elementList = mobileElementFinder.findElementsByXPath(xpath);
            element = elementList.get(0);
            x = Device.screenWidth / 4;
            y = element.getLocation().y + element.getSize().getHeight() / 2;
            mobileAction.tap(element);
        }
        Common.log(String.format("Select '%s' list item", text));
        return this;
    }

    public JQueryUIScreen swipeToRightListItem(String text) {
        if (Device.isIOS) {
            mobileAction.swipeLeftToRight(x, y, Device.screenWidth / 2);
            Common.log(String.format("Swipe from left to right on '%s' list item", text));
            mobileElementFinder.findElementByXPath("//UIAStaticText[@name='Cancel']").click();
        } else {
            xpath = String.format("//android.view.View[contains(@content-desc,'%s')]", text);
            elementList = mobileElementFinder.findElementsByXPath(xpath);
            mobileAction.swipeLeftToRight(x, y, Device.screenWidth / 2);
            Common.log(String.format("Swipe from left to right on '%s' list item", text));
            mobileElementFinder.findElementByName("Cancel").click();
        }
        return this;
    }

    public JQueryUIScreen typeSearchFilterTextbox(String text) {
        if (Device.isIOS) {
            elementList = mobileElementFinder.findElementsByXPath("//UIATextField", 0);
            element = elementList.get(0);
            mobileAction.typeIOS(element, text);
        } else {
            xpath = "//android.widget.EditText[contains(@resource-id,'filterBasic-input')]";
            element = mobileElementFinder.findElementByXPath(xpath);
            mobileAction.tap(Device.screenWidth / 2, element.getLocation().y + 20);
            Common.wait(2);

            // Type the value manually on popup keyboard
            if (Device.isPhone) {
                mobileAction.tap(150, 1830);
                mobileAction.tap(940, 1610);
                mobileAction.tap(440, 1830);
                mobileAction.tap(1080, 1610);
                mobileAction.hideAndroidKeyboard();
                mobileAction.tap(Device.screenWidth / 2, 100);
            } else {
                mobileAction.tap(135, 1580);
                mobileAction.tap(900, 1430);
                mobileAction.tap(410, 1580);
                mobileAction.tap(1030, 1430);
                mobileAction.hideAndroidKeyboard();
                mobileAction.tap(Device.screenWidth / 2, Device.screenHeight / 2);
            }
        }
        Common.log(String.format("Type in 'Search' edit box: '%s'", text));
        return this;
    }

    public JQueryUIScreen tapToggleSwitch() {
        if (Device.isIOS) {
            elementList = mobileElementFinder.findElementsByXPath("//UIAStaticText[@name='Off']", 0);
            element = elementList.get(0);
            mobileAction.tap(element);
            Common.log("Tap on 'Toggle switch checkbox' value: Off");
            elementList = mobileElementFinder.findElementsByXPath("//UIAStaticText[@name='On']", 0);
        } else {
            elementList = mobileElementFinder.findElementsByName("Off");
            element = elementList.get(0);
            mobileAction.tap(element);
            Common.log("Tap on 'Toggle switch checkbox' value: Off");
            Common.wait(1);
            elementList = mobileElementFinder.findElementsByName("On");
        }
        element = elementList.get(0);
        mobileAction.tap(element);
        Common.log("Tap on 'Toggle switch checkbox' value: On");
        return this;
    }

    public JQueryUIScreen typeTextinput(String text) {
        if (Device.isIOS) {
            element = mobileElementFinder.findElementsByXPath("//UIATextField[@name='Text Input:']", 0).get(0);
            mobileAction.typeIOS(element, text);
        } else {
            xpath = "//android.widget.EditText[@content-desc='Text input']";
            elementList = mobileElementFinder.findElementsByXPath(xpath);
            element = elementList.get(0);
            mobileAction.typeAndroid(element, text);
        }
        Common.log(String.format("Type in 'Text input' edit box: '%s'", text));
        return this;
    }

    public JQueryUIScreen typeSearchinput(String text) {
        if (Device.isIOS) {
            element = mobileElementFinder.findElementsByXPath("//UIATextField[@name='Search Input:']", 0).get(0);
            mobileAction.typeIOS(element, text);
        } else {
            xpath = "//android.widget.EditText[@resource-id='search-2']";
            element = mobileElementFinder.findElementByXPath(xpath);
            mobileAction.tap(Device.screenWidth / 2, element.getLocation().y + 30);

            // Type the value manually on popup keyboard
            if (Device.isPhone) {
                mobileAction.tap(140, 2050);
                mobileAction.tap(440, 1830);
                mobileAction.tap(360, 1610);
                mobileAction.tap(570, 1830);
            } else {
                mobileAction.tap(80, 1730);
                mobileAction.tap(420, 1580);
                mobileAction.tap(350, 1430);
                mobileAction.tap(560, 1580);
            }
            mobileAction.hideAndroidKeyboard();
        }
        Common.log(String.format("Type in 'Search input' edit box: '%s'", text));
        return this;
    }

    public JQueryUIScreen typeTextarea(String text) {
        if (Device.isIOS) {
            String[] sub = text.split("\n");
            if (Device.isPhone) {
                element = mobileElementFinder.findElementsByXPath("//UIATextField[@name='Textarea:']", 0).get(0);
                element.clear();
                element.sendKeys(sub[0]);
                for (int i = 1; i < sub.length; i++) {
                    mobileAction.tap(Device.screenWidth - 70, Device.screenHeight - 20);
                    element.sendKeys(sub[i]);
                }
                mobileElementFinder.findElementByName("Done").click();
            }
        } else {
            xpath = "//android.widget.EditText[@resource-id='textarea-2']";
            element = mobileElementFinder.findElementByXPath(xpath);
            mobileAction.tap(Device.screenWidth / 2, element.getLocation().y + 50);
            Common.wait(1);

            if (Device.isPhone)
                mobileAction.tapAndHold(1340, 2050, 3);
            else
                mobileAction.tapAndHold(1420, 1430, 3);
            mobileAction.typeAndroid(element, text);
        }
        Common.log(String.format("Type in 'Textarea' edit box: %s", text));
        return this;
    }

    public JQueryUIScreen selectComboboxItem(String text) {
        if (Device.isIOS) {
            element = mobileElementFinder.findElementsByXPath("//UIAElement[@name='Native select:']", 0).get(0);
            mobileAction.tap(element);
            Common.log("Tap on 'Native select' combobox");
            Common.wait(1);
            mobileAction.tap(Device.screenWidth / 2, Device.screenHeight - 30);
            mobileElementFinder.findElementByName("Done").click();
        } else {
            xpath = "//android.widget.Spinner[@resource-id='select-native-2']";
            element = mobileElementFinder.findElementByXPath(xpath);
            mobileAction.tap(element);
            Common.log("Tap on 'Native select' combobox");
            Common.wait(1);

            if (Device.isPhone)
                mobileAction.tap(Device.screenWidth / 2, 1460);
            else
                mobileAction.tap(Device.screenWidth / 2, element.getLocation().y + 320);
        }
        Common.log(String.format("Select '%s' combo item", text));
        return this;
    }

    public JQueryUIScreen selectChoiceItems(String text) {
        String[] sub = text.split(";");

        if (Device.isIOS) {
            element = mobileElementFinder.findElementsByXPath("//UIAElement[@name='Choices:']", 0).get(0);
            mobileAction.tap(element);
            Common.log("Tap on 'Choices' list box");
            Common.wait(1);
            mobileAction.tap(Device.screenWidth / 2, Device.screenHeight / 2);
            mobileAction.tap(Device.screenWidth / 2, Device.screenHeight / 2 + 50);
            mobileAction.tap(10, Device.screenHeight / 2 + 30);
        } else {
            xpath = "//android.widget.Spinner[@resource-id='select-multiple-2-button']";
            element = mobileElementFinder.findElementByXPath(xpath);
            mobileAction.tap(element);
            Common.log("Tap on 'Choices' list box");
            Common.wait(1);

            xpath = "//android.widget.ListView//android.view.View";
            elementList = mobileElementFinder.findElementsByXPath(xpath);
            mobileAction.tap(elementList.get(0));
            mobileAction.tap(elementList.get(1));
            back();
        }
        Common.log(String.format("Select '%s' list item", text));
        return this;
    }

    public JQueryUIScreen selectListviewItem(String text) {
        if (Device.isIOS) {
            elementList = mobileElementFinder.findElementsByXPath("//UIAStaticText[@name='Acura']", 0);
            element = elementList.get(0);
        } else {
            element = mobileElementFinder.findElementByName(text);
        }
        mobileAction.tap(element);
        Common.log(String.format("Select '%s' list view item", text));
        return this;
    }

    public JQueryUIScreen tapLink(String text) {
        if (Device.isIOS) {
            element = mobileElementFinder.findElementByXPath(String.format("//UIAStaticText[@name='%s']", text), 0);
            mobileAction.tap(element);
        } else {
            element = mobileElementFinder.findElementByName(text);
            if (Device.isPhone) {
                x = Device.screenWidth / 4;
                y = element.getLocation().y + 20;
                mobileAction.tap(x, y);
            } else {
                mobileAction.tap(element);
            }
        }
        Common.log(String.format("Tap on '%s' link", text));
        Common.wait(2);
        return this;
    }

    public JQueryUIScreen tapImage(String text) {
        if (Device.isIOS) {
            element = mobileElementFinder.findElementByXPath(String.format("//UIAImage[@name='%s']", text), 0);
            mobileAction.tap(element);
        } else {
            element = mobileElementFinder.findElementByName(text);
            if (Device.isPhone) {
                x = element.getLocation().x + 50;
                y = element.getLocation().y + 50;
                mobileAction.tap(x, y);
            } else {
                mobileAction.tap(element);
            }
        }
        Common.log(String.format("Tap on '%s' image", text));
        return this;
    }

}
