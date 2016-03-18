package com.testbed.tests;

import libraries.utility.Common;
import mobile.screens.targets.JQueryUIScreen;
import mobile.support.Device;
import mobile.support.Global;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class JQueryUITest extends AbstractTest {

    private JQueryUIScreen jQueryUIScreen;

    @BeforeClass
    public void setUp() {
        jQueryUIScreen = screenFactory.loadTargetJQueryUIScreen();
        startNewSession();
    }

    @BeforeMethod
    public void beforeMethod() {
        Common.log("");
        Common.log("          Start the flow of jQueryUI target");
        Common.log("---------------------------------------");
    }

    @AfterMethod
    public void afterMethod() {
        Common.log("          End the flow of jQueryUI target");
        Common.log("-------------------------------------");
        editorScreen = stopSession();
        editorScreen.readActualResult();
        Global.initializeExpectedResultjQueryUI();
        Global.evaluateResultList();
        Common.log("");
        Common.log("-------------------------------------");
        Common.log("");
    }

    @Test
    public void testFlowTargetControls() {
        jQueryUIScreen.selectMenuIconAndMenuItem("Buttons");
        if (Device.isPhone) {
            if (Device.isIOS)
                mobileAction.swipeBottomToTop(Device.screenWidth / 2, Device.screenHeight - 150, 350, 2, 4);
            else
                mobileAction.swipeBottomToTop(Device.screenWidth / 2, Device.screenHeight - 100, 1000);
        }
        jQueryUIScreen.tapButton("Anchor");
        if (Device.isIOS) {

        } else {
            if (Device.isPhone)
                mobileAction.swipeBottomToTop(Device.screenWidth / 2, Device.screenHeight - 100, 1100);
            else
                mobileAction.swipeBottomToTop(Device.screenWidth / 2, Device.screenHeight - 100, 1500, 1, 10);
        }
        jQueryUIScreen.tapIcon("No text")
                .back()
                .selectMenuIconAndMenuItem("Checkboxradio widget>Checkboxes");
        if (Device.isPhone)
            if (Device.isIOS)
                mobileAction.swipeBottomToTop(Device.screenWidth / 2, Device.screenHeight - 100, 350);
            else
                mobileAction.swipeBottomToTop(Device.screenWidth / 2, Device.screenHeight - 100, 1000);
        jQueryUIScreen.selectCheckbox("Check me")
                .back()
                .selectMenuIconAndMenuItem("Checkboxradio widget>Radio buttons");
        if (Device.isPhone)
            if (Device.isIOS)
                mobileAction.swipeBottomToTop(Device.screenWidth / 2, Device.screenHeight - 100, 350);
            else
                mobileAction.swipeBottomToTop(Device.screenWidth / 2, Device.screenHeight - 100, 1000);
        jQueryUIScreen.selectRadioButton("Two")
                .back()
                .selectMenuIconAndMenuItem("Datepicker");
        if (Device.isIOS) {
            if (Device.isPhone)
                mobileAction.swipeBottomToTop(Device.screenWidth / 2, Device.screenHeight - 100, 350, 3);
        } else
            mobileAction.swipeBottomToTop(Device.screenWidth / 2, Device.screenHeight - 100, 1500, 3, 5);
        jQueryUIScreen.selectDatePicker("20")
                .back()
                .selectMenuIconAndMenuItem("Events>Swipe list items")
                .selectListItem("Mike Taylor")
                .swipeToRightListItem("Mike Taylor")
                .back()
                .selectMenuIconAndMenuItem("Filterable widget");
        if (Device.isIOS) {
            if (Device.isPhone) {
                mobileAction.swipeBottomToTop(Device.screenWidth / 2, Device.screenHeight - 100, 350, 3);
            }
        } else {
            if (Device.isPhone)
                mobileAction.swipeBottomToTop(Device.screenWidth / 2, Device.screenHeight - 100, 1500, 2, 5);
            else
                mobileAction.swipeBottomToTop(Device.screenWidth / 2, Device.screenHeight - 100, 1500, 1, 10);
        }
        jQueryUIScreen.typeSearchFilterTextbox("Audi")
                .back()
                .selectMenuIconAndMenuItem("Flipswitch widget")
                .tapToggleSwitch()
                .back();
        if (Device.isPhone) {
            jQueryUIScreen.selectMenuIcon();
            if (Device.isIOS)
                mobileAction.swipeBottomToTop(Device.screenWidth / 4, Device.screenHeight - 100, 350);
            else
                mobileAction.swipeBottomToTop(Device.screenWidth / 4, Device.screenHeight - 100, 1000, 1, 3);
            jQueryUIScreen.selectMenuItem("Forms>Forms gallery");
        } else {
            jQueryUIScreen.selectMenuIconAndMenuItem("Forms>Forms gallery");
        }
        jQueryUIScreen.typeTextinput("abc")
                .typeSearchinput("def")
                .typeTextarea("Text\nText\nText")
                .selectComboboxItem("Three")
                .selectChoiceItems("One;Two")
                .back()
                .selectMenuIcon();
        if (Device.isIOS) {
            if (Device.isPhone)
                mobileAction.swipeBottomToTop(Device.screenWidth / 4, Device.screenHeight - 100, 350);
        } else {
            if (Device.isPhone)
                mobileAction.swipeBottomToTop(Device.screenWidth / 2, Device.screenHeight - 100, 1000);
            else
                mobileAction.swipeBottomToTop(Device.screenWidth / 4, Device.screenHeight - 100, 500, 1, 10);
        }
        jQueryUIScreen.selectMenuItem("Listview widget>Listview")
                .selectListviewItem("Acura")
                .selectMenuIcon();
        if (Device.isIOS) {
            if (Device.isPhone)
                mobileAction.swipeBottomToTop(Device.screenWidth / 4, Device.screenHeight - 100, 350);
        } else {
            if (Device.isPhone)
                mobileAction.swipeBottomToTop(Device.screenWidth / 2, Device.screenHeight - 100, 1000, 1, 5);
            else
                mobileAction.swipeBottomToTop(Device.screenWidth / 4, Device.screenHeight - 100, 500, 1, 5);
        }
        jQueryUIScreen.selectMenuItem("Navigation>Linking pages");
        if (Device.isIOS) {
            if (Device.isPhone)
                mobileAction.swipeBottomToTop(Device.screenWidth / 4, Device.screenHeight - 100, 500, 3);
        } else {
            if (Device.isPhone)
                mobileAction.swipeBottomToTop(Device.screenWidth / 2, Device.screenHeight - 100, 1800, 3, 5);
            else
                mobileAction.swipeBottomToTop(Device.screenWidth / 2, Device.screenHeight - 100, 1500, 2, 10);
        }
        jQueryUIScreen.tapLink("To the homepage, with Ajax")
                .selectMenuIcon();
        if (Device.isIOS) {
            if (Device.isPhone)
                mobileAction.swipeBottomToTop(Device.screenWidth / 4, Device.screenHeight - 100, 400, 2);
        } else {
            if (Device.isPhone)
                mobileAction.swipeBottomToTop(Device.screenWidth / 2, Device.screenHeight - 100, 1500);
            else
                mobileAction.swipeBottomToTop(Device.screenWidth / 4, Device.screenHeight - 100, 800, 1, 10);
        }
        jQueryUIScreen.selectMenuItem("Popup widget>Popup");
        if (Device.isIOS) {
            if (Device.isPhone)
                mobileAction.swipeBottomToTop(Device.screenWidth / 4, Device.screenHeight - 100, 500, 3);
        } else {
            if (Device.isPhone)
                mobileAction.swipeBottomToTop(Device.screenWidth / 2, Device.screenHeight - 100, 1500, 3, 5);
            else
                mobileAction.swipeBottomToTop(Device.screenWidth / 4, Device.screenHeight - 100, 1000, 2, 10);
        }
        jQueryUIScreen.tapImage("Paris, France");
    }

}

