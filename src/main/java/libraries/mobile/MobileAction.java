package libraries.mobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import libraries.utility.Common;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MobileAction {

    @Autowired
    private AppiumDriver driver;

    @Autowired
    private MobileElementFinder mobileElementFinder;

    private TouchAction touchAction;

    public void initialize() {
        touchAction = new TouchAction(driver);
    }

    public void tap(WebElement element) {
        element.click();
        Common.wait(1);
    }

    public void tap(int x, int y) {
        touchAction.tap(x, y).perform();
        Common.wait(1);
    }

    public void tapAndHold(int x, int y, int duration) {
        touchAction.longPress(x, y, duration * 1000).perform();
        Common.wait(1);
    }

    public void typeIOS(WebElement element, String text) {
        element.clear();
        element.click();
        element.sendKeys(text);
        hideIOSKeyboard();
        Common.wait(1);
    }

    public void typeAndroid(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
        hideAndroidKeyboard();
        Common.wait(1);
    }

    public void typeAndroid(String text) {
        (new Actions(driver)).sendKeys(text).perform();
        hideAndroidKeyboard();
        Common.wait(1);
    }

    public void androidPressDeleteKey(int loop) {
//        for (int i = 0; i < loop; i++) {
//            if (Device.isPhone)
//                if (driver.getOrientation().value().equals("portrait"))
//                    touchAction.tap(1340, 2050).perform();
//                else
//                    touchAction.tap(2200, 1200).perform();
//            else
//                touchAction.tap(1450, 1440).perform();
//            Common.wait(1 / 10);
//        }
    }

    public void swipeRightToLeft(int startX, int startY, int length) {
        driver.swipe(startX, startY, startX - length, startY, 1000);
        Common.wait(1);
    }

    public void swipeLeftToRight(int startX, int startY, int length) {
        driver.swipe(startX, startY, startX + length, startY, 1000);
        Common.wait(1);
    }

    public void swipeBottomToTop(int startX, int startY, int length, Object... objects) {
        try {
            switch (objects.length) {
                case 0:
                    driver.swipe(startX, startY, startX, startY - length, 1000);
                    Common.wait(1);
                    break;

                case 1:
                    for (int i = 1; i <= (int) objects[0]; i++) {
                        driver.swipe(startX, startY, startX, startY - length, 1000);
                        Common.wait(1);
                    }
                    break;

                case 2:
                    for (int i = 1; i <= (int) objects[0]; i++) {
                        driver.swipe(startX, startY, startX, startY - length, (int) objects[1] * 1000);
                        Common.wait(1);
                    }
                    break;
            }
        } catch (Exception e) {
        }
    }

    public void rotateScreenToPortrait() {
        driver.rotate(ScreenOrientation.PORTRAIT);
        Common.wait(2);
    }

    public void rotateScreenToLandscape() {
        driver.rotate(ScreenOrientation.LANDSCAPE);
        Common.wait(2);
    }

    public void hideIOSKeyboard() {
        try {
            mobileElementFinder.findElementByName("Done").click();
        } catch (Exception ex) {
            mobileElementFinder.findElementByName("Hide keyboard", 0).click();
        }
    }

    public void hideAndroidKeyboard() {
        driver.hideKeyboard();
    }

}
