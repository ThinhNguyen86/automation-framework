package mobile.screens.explorer;

import libraries.utility.Common;
import mobile.screens.AbstractScreen;
import mobile.support.Device;
import org.springframework.stereotype.Component;

@Component
public class Hotspot extends AbstractScreen {

    @Override
    protected void load() {
        // TODO
    }

    @Override
    protected void isLoaded() throws Error {
        // TODO
    }

    public Hotspot tapHotspot() {
        if (Device.isIOS) {
            element = mobileElementFinder.findElementByXPath("//UIAImage[contains(@name, 'light_hotspot.png')]", 10);
            if (element == null)
                element = mobileElementFinder.findElementByXPath("//UIAStaticText[contains(@value, '00')]", 0);
            try {
                mobileAction.tap(element);
            } catch (Exception ex) {
                mobileAction.tap(element.getLocation().getX() + 20, element.getLocation().getY() + 20);
            }
        } else
            mobileAction.tap(Device.hotspotX, Device.hotspotY);
        Common.log("Tap on eXplorer hotspot");
        Common.wait(1);
        return this;
    }

    public ProjectScreen tapTopMenuOpenProjectScreen() {
        message = "";
        if (Device.isIOS) {
            element = mobileElementFinder.findElementByXPath("//UIAButton[contains(@name, 'light pie')]");
            mobileAction.tap(element);
        } else {
            if (Device.isPhone)
                mobileAction.tap(Device.hotspotX, Device.hotspotY - 160);
            else
                mobileAction.tap(Device.hotspotX, Device.hotspotY - 110);
        }
        Common.log("Tap on eXplorer hotspot top menu open Project screen");
        return screenFactory.loadExplorerProjectScreen();
    }

    public EditorScreen tapTopMenuOpenEditorScreen() {
        message = "hotspot top menu open Editor screen";
        if (Device.isIOS) {
            element = mobileElementFinder.findElementByXPath("//UIAButton[@name='light btn stop']");
            mobileAction.tap(element);
        } else {
            if (Device.isPhone) {
                mobileAction.tap(Device.hotspotX, Device.hotspotY - 200);
            } else
                mobileAction.tap(Device.hotspotX, Device.hotspotY - 110);
        }
        Common.log("Tap on eXplorer hotspot top menu open Editor screen");
        return screenFactory.loadEditorScreen();
    }

}
