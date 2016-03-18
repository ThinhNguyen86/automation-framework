package com.testbed.tests;

import libraries.utility.Common;
import mobile.screens.targets.HTML5Screen;
import mobile.support.Device;
import mobile.support.Global;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HTML5Test extends AbstractTest {

    private HTML5Screen html5Screen;

    @BeforeClass
    public void setUp() {
        html5Screen = screenFactory.loadTargetHTML5Screen();
        startNewSession();
    }

    @BeforeMethod
    public void beforeMethod() {
        Common.log("");
        Common.log("          Start the flow of HTML5 target");
        Common.log("---------------------------------------");
    }

    @AfterMethod
    public void afterMethod() {
        Common.log("          End the flow of HTML5 target");
        Common.log("-------------------------------------");
        editorScreen = stopSession();
        editorScreen.readActualResult();
        Global.initializeExpectedResultHTML5();
        Global.evaluateResultList();
        Common.log("");
        Common.log("-------------------------------------");
        Common.log("");
    }

    @Test
    public void testFlowTargetControls() {
        html5Screen.typeTextbox("abc");
        if (Device.isAndroid) {
            if (Device.isPhone)
                mobileAction.swipeBottomToTop(Device.screenWidth - 200, Device.screenHeight - 100, 1500, 2, 5);
            else
                mobileAction.swipeBottomToTop(Device.screenWidth / 2, Device.screenHeight - 100, 1700, 1, 10);
        }
        html5Screen.typeTextarea("Text\nText\nText")
                .typePassword("abc")
                .selectRadioButton();
        if (Device.isIOS) {
            if (Device.isPhone)
                mobileAction.swipeBottomToTop(Device.screenWidth / 4, Device.screenHeight - 100, 350);
            else
                mobileAction.swipeBottomToTop(Device.screenWidth - 200, Device.screenHeight - 100, 1500, 1, 5);
        } else {
            if (Device.isPhone)
                mobileAction.swipeBottomToTop(Device.screenWidth - 200, Device.screenHeight - 100, 1500, 1, 5);
        }
        html5Screen.selectCheckbox();
        if (Device.isAndroid) {
            if (Device.isPhone)
                mobileAction.swipeBottomToTop(Device.screenWidth - 200, Device.screenHeight - 100, 1500, 1, 5);
            else
                mobileAction.swipeBottomToTop(Device.screenWidth / 2, Device.screenHeight - 100, 1700, 1, 10);
        }
        html5Screen.selectComboboxItem("Option 2");
        if (Device.isIOS) {
            if (Device.isPhone)
                mobileAction.swipeBottomToTop(Device.screenWidth / 4, Device.screenHeight - 100, 500);
        } else {
            if (Device.isPhone)
                mobileAction.swipeBottomToTop(Device.screenWidth - 200, Device.screenHeight - 100, 1500, 1, 5);
            else
                mobileAction.swipeBottomToTop(Device.screenWidth / 2, Device.screenHeight - 100, 1700, 1, 10);
        }
        html5Screen.tapButton("Choose File")
                .back();
        if (Device.isIOS) {
            if (Device.isPhone)
                mobileAction.swipeBottomToTop(Device.screenWidth / 4, Device.screenHeight - 100, 500);
            else
                mobileAction.swipeBottomToTop(Device.screenWidth - 200, Device.screenHeight - 100, 1500, 1, 5);
        } else {
            if (Device.isPhone)
                mobileAction.swipeBottomToTop(Device.screenWidth - 200, Device.screenHeight - 100, 1500, 1, 5);
            else
                mobileAction.swipeBottomToTop(Device.screenWidth / 2, Device.screenHeight - 100, 1700, 1, 10);
        }
        html5Screen.tapButton("Submit");
        if (Device.isAndroid) {
            if (Device.isPhone)
                mobileAction.swipeBottomToTop(Device.screenWidth - 200, Device.screenHeight - 100, 1500, 4, 5);
            else
                mobileAction.swipeBottomToTop(Device.screenWidth / 2, Device.screenHeight - 100, 1700, 2, 10);
        }
        html5Screen.selectDate("20/Dec/2016")
                .selectMonth("10/2016");
        if (Device.isIOS) {

        } else {
            if (Device.isPhone) {
                mobileAction.swipeBottomToTop(Device.screenWidth - 200, Device.screenHeight - 100, 2100, 1, 5);
            } else {
                mobileAction.swipeBottomToTop(Device.screenWidth / 2, Device.screenHeight - 100, 1000, 1, 5);
            }
        }
        html5Screen.selectTime("6:30 AM")
                .selectDatetimeLocal("5:59 AM");
    }

}
