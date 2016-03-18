package mobile.screens.explorer;

import libraries.utility.Common;
import mobile.screens.AbstractScreen;
import mobile.support.Device;
import mobile.support.Global;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;
import org.testng.Assert;

import java.util.ArrayList;

@Component
public class EditorScreen extends AbstractScreen {

    @Override
    protected void load() {
        // TODO
    }

    @Override
    protected void isLoaded() throws Error {
        if (Device.isIOS)
            element = mobileElementFinder.findElementByName("light btn close", Global.SHORT_TIMEOUT);
        else
            element = mobileElementFinder.findElementByXPath("//android.widget.Button[contains(@resource-id,'closeBtn')]", Global.SHORT_TIMEOUT);
        Assert.assertNotNull(element, "You are NOT on Editor screen");
        Common.log("You are on Editor screen");
    }

    public EditorScreen tapActionMenu() {
        if (Device.isIOS)
            element = mobileElementFinder.findElementByName("light btn action");
        else
            element = mobileElementFinder.findElementByXPath("//android.widget.Button[contains(@resource-id,'action_btn')]");

        mobileAction.tap(element);
        Common.log("Action Menu on Editor screen");
        Common.wait(3);
        return this;
    }

    public EditorScreen readActualResult() {
        Global.actualList = new ArrayList<>();

        if (Device.isIOS)
            readMultipleActualResult(0);
        else {
            switch (Global.currentTarget) {
                case Global.TARGET_DOJO:
                    if (Device.isPhone)
                        readMultipleActualResult(3);
                    break;

                case Global.TARGET_HTML5:
                    if (Device.isPhone)
                        readMultipleActualResult(4);
                    else
                        readMultipleActualResult(2);
                    break;

                case Global.TARGET_HTML_STANDARD:
                    if (Device.isPhone)
                        readMultipleActualResult(2);
                    else
                        readMultipleActualResult(0);
                    break;

                case Global.TARGET_JQUERYUI:
                    if (Device.isPhone)
                        readMultipleActualResult(10);
                    else
                        readMultipleActualResult(6);
                    break;

                case Global.TARGET_KENDOUI:
                    if (Device.isPhone)
                        readMultipleActualResult(4);
                    else
                        readMultipleActualResult(2);
                    break;

                case Global.TARGET_SENCHA_TOUCH:
                    if (Device.isPhone)
                        readMultipleActualResult(2);
                    else
                        readMultipleActualResult(1);
                    break;
            }
        }
        return this;
    }

    private void readMultipleActualResult(int loopScroll) {
        readActualResultDisplayedOnScreen();
        for (int i = 1; i <= loopScroll; i++) {
            mobileAction.swipeBottomToTop(Device.screenWidth / 4, Device.screenHeight - 200, Device.screenHeight - 700);
            Common.wait(2);
            readActualResultDisplayedOnScreen();
        }
    }

    private void readActualResultDisplayedOnScreen() {
        String step;
        if (Device.isIOS)
            elementList = mobileElementFinder.findElementsByXPath("//UIATableCell/UIAStaticText", 0);
        else
            elementList = mobileElementFinder.findElementsByXPath("//android.widget.TextView", 0);

        for (WebElement e : elementList) {
            try {
                Integer.parseInt(e.getText());
            } catch (Exception ex) {
                step = e.getText();
                Global.actualList.add(step);
            }
        }
    }
}
