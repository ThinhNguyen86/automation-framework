package com.testbed.tests;

import libraries.utility.Common;
import mobile.screens.targets.HTMLStandardScreen;
import mobile.support.Global;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HTMLStandardTest extends AbstractTest {

    private HTMLStandardScreen htmlStandardScreen;

    @BeforeClass
    public void setUp() {
        htmlStandardScreen = screenFactory.loadTargetHTMLStandardScreen();
        startNewSession();
    }

    @BeforeMethod
    public void beforeMethod() {
        Common.log("");
        Common.log("          Start the flow of HTML Standard target");
        Common.log("------------------------------------------------");
    }

    @AfterMethod
    public void afterMethod() {
        Common.log("          End the flow of HTML Standard target");
        Common.log("------------------------------------------------");
        editorScreen = stopSession();
        editorScreen.readActualResult();
        Global.initializeExpectedResultHTMLStandard();
        Global.evaluateResultList();
        Common.log("");
        Common.log("------------------------------------------------");
        Common.log("");
    }

    @Test
    public void testFlowTargetControls() {
        htmlStandardScreen.typeTextbox("abc")
                          .typePassword("def")
                          .selectCombobox("Option 1.2")
                          .selectCheckbox("Checkbox 1")
                          .selectRadioButton("Male")
                          .tapLink("Hyperlink")
                          .selectListItem("Watermelon")
                          .tapButton("Standard Button")
                          .typeTextarea("Text\nText\nText");
    }

}
