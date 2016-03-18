package mobile.screens.targets;

import libraries.utility.Common;
import mobile.screens.AbstractScreen;
import mobile.support.Device;
import mobile.support.Global;
import org.springframework.stereotype.Component;
import org.testng.Assert;

import java.time.LocalDateTime;

@Component
public class HTML5Screen extends AbstractScreen {

    @Override
    protected void load() {
        // TODO
    }

    @Override
    protected void isLoaded() throws Error {
        message = "Native form elements";
        if (Device.isIOS)
            element = mobileElementFinder.findElementByXPath(String.format("//UIAStaticText [@name='%s']", message));
        else
            element = mobileElementFinder.findElementByName(message);
        Assert.assertNotNull(element, "You are NOT on HTML5 screen");
        Global.currentTarget = Global.TARGET_HTML5;
        Common.wait(3);
        Common.log("You are on HTML5 screen");
    }

    public HTML5Screen typeTextbox(String text) {
        if (Device.isIOS) {
            elementList = mobileElementFinder.findElementsByXPath("//UIATextField", 0);
            element = elementList.get(0);
            mobileAction.typeIOS(element, text);
        } else {
            if (Device.isPhone) {
                mobileAction.tap(300, 1080);
                Common.wait(1);
                mobileAction.tap(100, 2050);
                mobileAction.tap(140, 1830);
                mobileAction.tap(860, 2050);
                mobileAction.tap(580, 2050);
                mobileAction.tap(Device.screenWidth - 200, Device.screenHeight / 2);
            } else {
                mobileAction.tap(280, 720);
                Common.wait(1);
                mobileAction.tap(80, 1740);
                mobileAction.tap(140, 1580);
                mobileAction.tap(780, 1740);
                mobileAction.tap(500, 1740);
                mobileAction.tap(Device.screenWidth - 200, Device.screenHeight / 2);
            }
        }
        Common.log(String.format("Type in edit box: '%s'", text));
        return this;
    }

    public HTML5Screen typeTextarea(String text) {
        if (Device.isIOS) {
            element = elementList.get(8);
            mobileAction.typeIOS(element, text);
        } else {
            if (Device.isPhone) {
                mobileAction.tap(250, 200);
                Common.wait(1);
                mobileAction.tap(650, 1620);
                mobileAction.tap(360, 1620);
                mobileAction.tap(430, 2060);
                mobileAction.tap(650, 1620);
                for (int i = 1; i <= 2; i++) {
                    mobileAction.tap(1330, 2260);
                    mobileAction.tap(650, 1620);
                    mobileAction.tap(360, 1620);
                    mobileAction.tap(430, 2060);
                    mobileAction.tap(650, 1620);
                }
                mobileAction.tap(Device.screenWidth - 200, Device.screenHeight / 2);
            } else {
                mobileAction.tap(250, 350);
                Common.wait(1);
                mobileAction.tap(620, 1430);
                mobileAction.tap(340, 1430);
                mobileAction.tap(360, 1740);
                mobileAction.tap(620, 1430);
                for (int i = 1; i <= 2; i++) {
                    mobileAction.tap(1420, 1580);
                    mobileAction.tap(620, 1430);
                    mobileAction.tap(340, 1430);
                    mobileAction.tap(360, 1740);
                    mobileAction.tap(620, 1430);
                }
                mobileAction.tap(Device.screenWidth - 200, Device.screenHeight / 2);
            }
        }
        Common.log(String.format("Type in textarea: '%s'", text));
        return this;
    }

    public HTML5Screen typePassword(String text) {
        if (Device.isIOS) {
            elementList = mobileElementFinder.findElementsByXPath("//UIASecureTextField", 0);
            element = elementList.get(0);
            mobileAction.typeIOS(element, text);
        } else {
            if (Device.isPhone) {
                mobileAction.tap(250, 820);
                Common.wait(1);
                mobileAction.tap(140, 1830);
                mobileAction.tap(860, 2050);
                mobileAction.tap(580, 2050);
                mobileAction.tap(Device.screenWidth - 200, Device.screenHeight / 2);
            } else {
                mobileAction.tap(250, 760);
                Common.wait(1);
                mobileAction.tap(140, 1580);
                mobileAction.tap(780, 1740);
                mobileAction.tap(500, 1740);
                mobileAction.tap(Device.screenWidth - 200, Device.screenHeight / 2);
            }
        }
        Common.log(String.format("Type in 'password' edit box: '%s'", text));
        return this;
    }

    public HTML5Screen selectRadioButton() {
        if (Device.isIOS) {
            elementList = mobileElementFinder.findElementsByXPath("//UIAElement", 0);
            element = elementList.get(0);
            mobileAction.tap(element);
        } else {
            if (Device.isPhone)
                mobileAction.tap(120, 1350);
            else
                mobileAction.tap(65, 1100);
        }
        Common.log("Tap on radio button");
        return this;
    }

    public HTML5Screen selectCheckbox() {
        if (Device.isIOS) {
            elementList = mobileElementFinder.findElementsByXPath("//UIASwitch", 0);
            element = elementList.get(0);
            mobileAction.tap(element);
        } else {
            if (Device.isPhone) {
                mobileAction.tap(120, 910);
            } else {
                mobileAction.tap(60, 1745);
            }
        }
        Common.log("Tap on check box");
        return this;
    }

    public HTML5Screen selectComboboxItem(String text) {
        if (Device.isIOS) {
            elementList = mobileElementFinder.findElementsByXPath("//UIAElement[@value='Option 1']", 0);
            element = elementList.get(0);
            mobileAction.tap(element);
            Common.log("Tap on combo box");

            if (Device.isPhone) {
                mobileAction.tap(Device.screenWidth / 4, Device.screenHeight - 70);
                mobileElementFinder.findElementByName("Done").click();
            } else {
                element = mobileElementFinder.findElementByXPath("//UIATableCell[@name='" + text + "']");
                mobileAction.tap(element);
            }
        } else {
            if (Device.isPhone) {
                mobileAction.tap(150, 1320);
                Common.log("Tap on combo box");
                mobileAction.tap(Device.screenWidth / 2, 1240);
            } else {
                mobileAction.tap(150, 1190);
                Common.log("Tap on combo box");
                mobileAction.tap(210, 1430);
            }
        }
        Common.log(String.format("Select '%s' combo item", text));
        return this;
    }

    public HTML5Screen tapButton(String text) {
        if (Device.isIOS) {
            elementList = mobileElementFinder.findElementsByXPath("//UIAButton[@name='" + text + "']", 0);
            if (text.equals("Choose File")) {
                element = elementList.get(0);
                mobileAction.tap(element);
                if (Device.isPhone)
                    mobileAction.tap(Device.screenWidth / 2, Device.screenHeight - 20);
                else
                    mobileAction.tap(Device.screenWidth - 20, Device.screenHeight / 2);
            } else if (text.equals("Submit")) {
                element = elementList.get(2);
                mobileAction.tap(element);
            }
        } else {
            if (Device.isPhone) {
                if (text.equals("Choose File"))
                    mobileAction.tap(150, 2020);
                else if (text.equals("Submit"))
                    mobileAction.tap(150, 2190);
            } else {
                if (text.equals("Choose File"))
                    mobileAction.tap(150, 970);
                else if (text.equals("Submit"))
                    mobileAction.tap(150, 410);
            }
        }
        Common.log(String.format("Tap on '%s' button", text));
        return this;
    }

    public HTML5Screen selectDate(String text) {
        if (Device.isIOS) {
            elementList = mobileElementFinder.findElementsByXPath("//UIAElement", 0);
            element = elementList.get(8);
            mobileAction.tap(element);
            Common.log("Select 'Date' control");

            elementList = mobileElementFinder.findElementsByXPath("//UIAPickerWheel", 0);
            element = elementList.get(0);
            element.sendKeys("December");
            element = elementList.get(1);
            element.sendKeys("20");
            mobileAction.hideIOSKeyboard();
            mobileAction.tap(Device.screenWidth - 20, Device.screenHeight / 2);
        } else {
            if (Device.isPhone) {
                // Tap on the control
                mobileAction.tap(150, 1530);
                // Tap on Month control
                mobileAction.tap(540, 1150);
                mobileAction.tapAndHold(1320, 2050, 1);
                mobileAction.tap(440, 1830);
                // Select Day
                mobileAction.tap(810, 630);
                mobileAction.tapAndHold(1320, 2050, 1);
                mobileAction.tap(570, 1600);
                mobileAction.tap(570, 2270);
                // Press Set button
                mobileAction.tap(350, 970);
            } else {
                // Tap on the control
                mobileAction.tap(150, 320);
                // Tap on Month control
                mobileAction.tap(360, 990);
                mobileAction.tapAndHold(1450, 1430, 1);
                mobileAction.tap(420, 1580);
                // Select Day
                mobileAction.tap(510, 600);
                mobileAction.tapAndHold(1450, 1430, 1);
                mobileAction.tap(890, 1430);
                mobileAction.tap(890, 1880);
                // Press Set button
                mobileAction.tap(400, 990);
            }
        }
        Common.log(String.format("Select 'Date' value: '%s'", text));
        return this;
    }

    public HTML5Screen selectMonth(String text) {
        if (Device.isIOS) {
            element = mobileElementFinder.findElementByXPath("//UIAStaticText[@label='<input type=month>']", 0);
            x = element.getLocation().x + 20;
            y = element.getLocation().y - 50;
            mobileAction.tap(x, y);
            Common.log("Select 'Month' control");

            elementList = mobileElementFinder.findElementsByXPath("//UIAPickerWheel", 0);
            element = elementList.get(0);
            element.sendKeys("December");
            element = elementList.get(1);
            element.sendKeys("20");
            mobileAction.hideIOSKeyboard();
        } else {
            if (Device.isPhone) {
                mobileAction.tap(150, 2055);
                mobileAction.tap(620, 1230);
                mobileAction.tapAndHold(1320, 2050, 1);
                mobileAction.tap(1220, 1620);
                // Press Set button
                mobileAction.tap(350, 1060);
            } else {
                mobileAction.tap(150, 640);
                mobileAction.tap(700, 1010);
                mobileAction.tapAndHold(1420, 1430, 1);
                mobileAction.tap(1170, 1430);
                // Press Set button
                mobileAction.tap(560, 850);
            }
        }
        Common.log(String.format("Select 'Month' value: '%s'", text));
        return this;
    }

    public HTML5Screen selectTime(String text) {
        if (Device.isIOS) {

        } else {
            if (Device.isPhone) {
                mobileAction.tap(150, 980);
                // Select hour
                mobileAction.tap(500, 1210);
                mobileAction.tapAndHold(1300, 2050, 1);
                mobileAction.tap(950, 1830);
                // Select minute
                mobileAction.tap(830, 770);
                mobileAction.tapAndHold(1300, 2050, 1);
                mobileAction.tap(960, 1600);
                mobileAction.tap(580, 2270);
                // Select morning
                mobileAction.tap(1120, 770);
                mobileAction.tapAndHold(1300, 2050, 1);
                mobileAction.tap(140, 1830);
                // Press Set button
                mobileAction.tap(350, 1060);
            } else {
                mobileAction.tap(150, 310);
                // Select hour
                mobileAction.tap(650, 1000);
                mobileAction.tapAndHold(1420, 1430, 1);
                mobileAction.tap(1170, 1590);
                // Select minute
                mobileAction.tap(830, 690);
                mobileAction.tapAndHold(1420, 1430, 1);
                mobileAction.tap(1170, 1430);
                mobileAction.tap(890, 1880);
                // Select morning
                mobileAction.tap(1000, 700);
                mobileAction.tapAndHold(1420, 1430, 1);
                mobileAction.tap(140, 1580);
                // Press Set button
                mobileAction.tap(560, 850);
            }
        }
        Common.log(String.format("Select 'Time' value: '%s'", text));
        return this;
    }

    public HTML5Screen selectDatetimeLocal(String text) {
        if (Device.isIOS) {

        } else {
            if (Device.isPhone) {
                mobileAction.tap(150, 2030);
                // Select hour
                mobileAction.tap(530, 1470);
                mobileAction.tapAndHold(1300, 2050, 1);
                mobileAction.tap(580, 1830);
                // Select minute
                mobileAction.tap(800, 1060);
                mobileAction.tapAndHold(1300, 2050, 1);
                mobileAction.tap(580, 1830);
                mobileAction.tap(960, 2050);
                // Select morning
                LocalDateTime now = LocalDateTime.now();
                if (now.getHour() >= 12)
                    mobileAction.tap(980, 1050);
                // Press Set button
                mobileAction.tap(360, 1400);
            } else {
                mobileAction.tap(150, 970);
                // Select hour
                mobileAction.tap(650, 1150);
                mobileAction.tapAndHold(1420, 1430, 1);
                mobileAction.tap(900, 1580);
                // Select minute
                mobileAction.tap(800, 850);
                mobileAction.tapAndHold(1420, 1430, 1);
                mobileAction.tap(900, 1580);
                mobileAction.tap(1160, 1730);
                // Select morning
                LocalDateTime now = LocalDateTime.now();
                if (now.getHour() >= 12)
                    mobileAction.tap(920, 840);
                // Press Set button
                mobileAction.tap(560, 1060);
            }
        }
        Common.log(String.format("Select 'DatetimeLocal' value: '%s'", text));
        return this;
    }

    public HTML5Screen back() {
        if (Device.isAndroid)
            driver.navigate().back();
        return this;
    }

}
