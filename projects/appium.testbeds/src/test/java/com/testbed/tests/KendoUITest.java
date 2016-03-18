package com.testbed.tests;

import libraries.utility.Common;
import mobile.screens.targets.KendoUIScreen;
import mobile.support.Device;
import mobile.support.Global;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class KendoUITest extends AbstractTest {

    private KendoUIScreen kendoUIScreen;

    @BeforeClass
    public void setUp() {
        kendoUIScreen = screenFactory.loadTargetKendoUIScreen();
        startNewSession();
    }

    @BeforeMethod
    public void beforeMethod() {
        Common.log("");
        Common.log("          Start the flow of KendoUI target");
        Common.log("------------------------------------------------");
    }

    @AfterMethod
    public void afterMethod() {
        Common.log("          End the flow of KendoUI target");
        Common.log("------------------------------------------------");
        editorScreen = stopSession();
        editorScreen.readActualResult();
        Global.initializeExpectedResultKendoUI();
        Global.evaluateResultList();
        Common.log("");
        Common.log("------------------------------------------------");
        Common.log("");
    }

    @Test
    public void testFlowTargetControls() {
        kendoUIScreen.selectMenuItem("Application>View transitions")
                .typeTextbox("Jolie")
                .back()
                .selectMenuItem("Button>Basic usage")
                .tapButton("Default Button")
                .back();
        if (Device.isIOS) {
            if (Device.isPhone)
                mobileAction.swipeBottomToTop(Device.screenWidth / 2, Device.screenHeight - 100, 500, 3);
            else {

            }
        } else {
            Common.wait(1);
            if (Device.isPhone)
                mobileAction.swipeBottomToTop(Device.screenWidth / 2, Device.screenHeight - 100, 1300, 4, 5);
            else
                mobileAction.swipeBottomToTop(Device.screenWidth / 2, Device.screenHeight - 100, 1000, 4, 5);
        }
        kendoUIScreen.selectMenuItem("Forms>Checks and Radios")
                .selectCheckbox("Carrots")
                .selectMenuTab("Radio Buttons")
                .selectRadioButton("Delete")
                .back()
                .selectMenuItem("Forms>Form types")
                .selectDropDownItem("Second Option");
        if (Device.isIOS) {
            if (Device.isPhone)
                mobileAction.swipeBottomToTop(Device.screenWidth / 2, Device.screenHeight - 100, 500);
            else {

            }
        } else
            mobileAction.swipeBottomToTop(Device.screenWidth / 2, Device.screenHeight - 100, 1200);
        kendoUIScreen.typeTextarea("Text\nText\nText")
                .back();
        if (Device.isIOS) {
            if (Device.isPhone)
                mobileAction.swipeBottomToTop(Device.screenWidth / 2, Device.screenHeight - 100, 500, 4);
            else {

            }
        } else
            mobileAction.swipeBottomToTop(Device.screenWidth / 2, Device.screenHeight - 100, 1500, 6);
        kendoUIScreen.selectMenuItem("TabStrip>Basic usage")
                .selectMenuTab("Sales")
                .selectListItem("February>↓ $ 25,000")
                .selectListItem("March>↓ $ 23,000")
                .selectListItem("April>↑ $ 30,000");
    }

}
