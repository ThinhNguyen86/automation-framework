package mobile.screens;

import io.appium.java_client.AppiumDriver;
import libraries.mobile.MobileAction;
import libraries.mobile.MobileElementFinder;
import libraries.utility.Common;
import mobile.support.Global;
import mobile.support.ScreenFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractScreen extends LoadableComponent<AbstractScreen> {

    @Autowired
    protected AppiumDriver driver;
    @Autowired
    protected ScreenFactory screenFactory;
    @Autowired
    protected MobileElementFinder mobileElementFinder;
    @Autowired
    protected MobileAction mobileAction;

    protected WebElement element;
    protected List<WebElement> elementList;
    protected String xpath, message;
    protected int x, y;

//    protected AbstractScreen tapButtonTemplate(String text, WebElement element) {
//        message = String.format("'%s' button", text);
//        mobileAction.tap(message, element);
//        return this;
//    }
//
//    protected AbstractScreen typeTextboxTemplate(String text, String value, WebElement element) {
//        message = String.format("'%s' edit box: '%s'", text, value);
//        mobileAction.sendKeys(message, element, value);
//        Common.wait(1);
//        return this;
//    }
//
//    protected AbstractScreen selectCheckboxTemplate(String text, WebElement element) {
//        message = String.format("'%s' checkbox", text);
//        mobileAction.tap(message, element);
//        return this;
//    }
//
//    protected AbstractScreen selectRadioButtonTemplate(String text, WebElement element) {
//        message = String.format("'%s' radio button", text);
//        if (Global.isScrolled) {
//            int x = element.getLocation().getX() + 10;
//            int y = element.getLocation().getY() - Global.scrollHeight;
//            mobileAction.tap(message, x, y);
//        } else
//            mobileAction.tap(message, element);
//        return this;
//    }
//
//    protected AbstractScreen tapToggleButtonTemplate(String text, WebElement element) {
//        message = String.format("'%s' toggle button", text);
//        mobileAction.tap(message, element);
//        return this;
//    }
//
//    protected AbstractScreen selectListItemTemplate(String text, WebElement element) {
//        message = String.format("'%s' item", text);
//        mobileAction.tap(message, element);
//        return this;
//    }
//
//    protected AbstractScreen selectTabTemplate(String text, WebElement element) {
//        message = String.format("'%s' tab", text);
//        mobileAction.tap(message, element);
//        return this;
//    }
//
//    protected AbstractScreen tapLinkTemplate(String text, WebElement element) {
//        message = String.format("'%s' link", text);
//        mobileAction.tap(message, element);
//        return this;
//    }

}
