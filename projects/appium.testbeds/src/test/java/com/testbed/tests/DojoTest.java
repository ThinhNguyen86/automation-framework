package com.testbed.tests;

import libraries.utility.Common;
import mobile.screens.targets.DojoScreen;
import mobile.support.Device;
import mobile.support.Global;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DojoTest extends AbstractTest {

    private DojoScreen dojoScreen;

    @BeforeClass
    public void setUp() {
        dojoScreen = screenFactory.loadTargetDojoScreen();
        startNewSession();
        if (Device.isAndroid && Device.isPhone) {
            mobileAction.rotateScreenToLandscape();
            Device.hotspotX = 2300;
            Device.hotspotY = 1110;
            hotspot.tapHotspot();
            Device.hotspotY = Device.hotspotY - 80;
            hotspot.tapHotspot();
        }
    }

    @BeforeMethod
    public void beforeMethod() {
        Common.log("");
        Common.log("          Start the flow of Dojo target");
        Common.log("---------------------------------------");
    }

    @AfterMethod
    public void afterMethod() {
        Common.log("          End the flow of Dojo target");
        Common.log("-------------------------------------");
        editorScreen = stopSession();
        if (Device.isAndroid && Device.isPhone)
            mobileAction.rotateScreenToPortrait();
        editorScreen.readActualResult();
        Global.initializeExpectedResultDojo();
        Global.evaluateResultList();
        Common.log("");
        Common.log("-------------------------------------");
        Common.log("");
    }

    @Test
    public void testFlowTargetControls() {
        dojoScreen.selectMenuItem("Buttons")
                .tapButton("White")
//                .back()
                .selectMenuItem("Forms")
                .typeTextbox("Jolie")
                .selectCheckbox("Hide birthday")
                .typeTextarea("Text\nText\nText");
        if (Device.isPhone) {
            if (Device.isIOS)
                mobileAction.swipeBottomToTop(Device.screenWidth / 2, Device.screenHeight - 100, 100);
            else
                mobileAction.swipeBottomToTop(Device.screenHeight / 2, Device.screenWidth - 100, Device.screenWidth - 200);
        }
        dojoScreen.selectRadioButton("All messages")
//                .back()
                .selectMenuItem("Swap View");
        if (Device.isPhone) {
            if (Device.isIOS)
                mobileAction.swipeRightToLeft(Device.screenWidth / 2, Device.screenHeight / 2, 100);
            else
                mobileAction.swipeRightToLeft(Device.screenHeight - 100, Device.screenWidth / 2, 500);
        } else
            mobileAction.swipeRightToLeft(Device.screenWidth / 2, Device.screenHeight / 2, 500);
//        dojoScreen.back();
        if (Device.isAndroid && Device.isPhone) {
            mobileAction.swipeBottomToTop(600, 1000, 600);
            dojoScreen.scrollY = 600;
            Common.wait(2);
        }
        dojoScreen.selectMenuItem("Headings");
        dojoScreen.tapToggleButton("Toggle Button")
//                .back()
                .selectMenuItem("Lists")
                .selectListItem("Igor Stravinksy")
//                .back()
                .selectMenuItem("Filtered Lists")
                .typeFilteredListSearch("Alaska");
//                .back();
        if (Device.isIOS & Device.isPhone)
            mobileAction.swipeBottomToTop(Device.screenWidth / 2, Device.screenHeight - 100, 100);
        dojoScreen.selectMenuItem("Accordion")
                .selectTab("External Content")
                .back();
        if (Device.isPhone) {
            if (Device.isAndroid) {
                mobileAction.swipeBottomToTop(600, Device.screenWidth - 100, Device.screenWidth - 200);
                mobileAction.swipeBottomToTop(600, Device.screenWidth - 100, Device.screenWidth - 200);
                dojoScreen.selectMenuItem("AJAX");
            } else {
                mobileAction.swipeBottomToTop(Device.screenWidth / 2, Device.screenHeight - 100, Device.screenHeight - 200);
                mobileAction.swipeBottomToTop(Device.screenWidth / 2, Device.screenHeight - 100, Device.screenHeight - 200);
                dojoScreen.selectMenuItem("AJAX");
            }
        } else
            dojoScreen.selectMenuItem("AJAX");
        dojoScreen.tapButton("Load using AJAX");
        mobileAction.swipeBottomToTop(Device.screenHeight / 2, Device.screenWidth - 100, 200);
        dojoScreen.tapLink("Visit us");
    }

}
