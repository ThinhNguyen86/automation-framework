package mobile.support;

import libraries.utility.Common;

import java.util.ArrayList;
import java.util.List;

public class Global {

    public static final String FILE_CONFIG = "config/config.properties";
    public static final String FILE_DATA = "config/data.properties";
    public static final int SHORT_TIMEOUT = 5;
    public static final int MAX_TIMEOUT = 30;
    public static boolean isScrolled;
    public static int scrollHeight;

    public static String currentTarget;
    public static final String TARGET_DOJO = "Dojo";
    public static final String TARGET_SENCHA_TOUCH = "Sencha Touch";
    public static final String TARGET_HTML_STANDARD = "HTML Standard";
    public static final String TARGET_KENDOUI = "KendoUI";
    public static final String TARGET_JQUERYUI = "jQueryUI";
    public static final String TARGET_HTML5 = "HTML5";

    public static List<String> actualList;
    public static List<String> expectedList;

    public static void initializeExpectedResultDojo() {
        expectedList = new ArrayList<>();
        expectedList.add("Tap on 'White' button");
        expectedList.add("Type in 'Full name' edit box: 'Jolie'");
        expectedList.add("Select 'Hide birthdate' check box");
        expectedList.add("Type in 'Goals' edit box: 'Text\nText\nText'");
        expectedList.add("Select 'All messages' radio button");
        expectedList.add("Pan left");
        expectedList.add("Tap on 'Toggle Button' button");
        expectedList.add("Select 'Igor Stravinksy' item");
        expectedList.add("Type in edit box: 'Alaska'");
        expectedList.add("Select 'External Content' tab");
        expectedList.add("Tap on 'Visit us' link");
    }

    public static void initializeExpectedResultHTML5() {
        expectedList = new ArrayList<>();
        expectedList.add("Type in edit box: 'abc'");
        expectedList.add("Type in edit box: 'Text\nText\nText'");
        expectedList.add("Type in 'password' edit box: '********'");
        expectedList.add("Select radio button");
        expectedList.add("Select check box");
        expectedList.add("Select 'Option 2' item");
        expectedList.add("Tap on 'Choose File' button");
        expectedList.add("Tap on 'Submit' button");
        expectedList.add("Select '1/6/2016' item");
        expectedList.add("Select '10/2016' item");
        expectedList.add("Select '6:30 PM' item");
        expectedList.add("Select '5:59 AM' item");
    }

    public static void initializeExpectedResultHTMLStandard() {
        expectedList = new ArrayList<>();
        expectedList.add("Type in 'Text box' edit box: 'abc'");
        expectedList.add("Type in 'Password' edit box: '********'");
        expectedList.add("Select 'Option 1.2' item");
        expectedList.add("Select 'Checkbox 1' check box");
        expectedList.add("Select 'Male' radio button");
        expectedList.add("Tap on 'Hyperlink' link");
        expectedList.add("Select 'Watermelon' item");
        expectedList.add("Tap on 'Standard Button' button");
        expectedList.add("Type in edit box: 'Text\nText\nText'");
    }

    public static void initializeExpectedResultjQueryUI() {
        expectedList = new ArrayList<>();
        expectedList.add("Tap on 'Anchor' button");
        expectedList.add("Tap on 'No text' button");
        expectedList.add("Select 'Check me' check box");
        expectedList.add("Select 'Two' radio button");
        expectedList.add("Tap on '20' button");
        expectedList.add("Tap on 'Mike Taylor' item");
        expectedList.add("Pan right on 'Mike Taylor' item");
        expectedList.add("Type in edit box: 'Audi'");
        expectedList.add("Tap on 'Off' button");
        expectedList.add("Tap on 'On' button");
        expectedList.add("Type in 'Text Input:' edit box: 'abc'");
        expectedList.add("Type in 'Search Input:' edit box: 'def'");
        expectedList.add("Type in 'Textarea:' edit box: 'Text\nText\nText'");
        expectedList.add("Select 'Three' item");
        expectedList.add("Select 'One' check box");
        expectedList.add("Select 'Two' check box");
        expectedList.add("Select 'Acura' item");
        expectedList.add("Tap on 'To the homepage, with Ajax' link");
        expectedList.add("Tap on 'Paris, France' image");
    }

    public static void initializeExpectedResultKendoUI() {
        expectedList = new ArrayList<>();
        expectedList.add("Type in 'Username:' edit box: 'Jolie'");
        expectedList.add("Tap on 'Default Button' button");
        expectedList.add("Select 'Carrots' check box");
        expectedList.add("Select 'Delete' radio button");
        expectedList.add("Select 'Second Option' item");
        expectedList.add("Type in 'Textarea' edit box: 'Text\nText\nText'");
        expectedList.add("Select 'Sales' tab");
        expectedList.add("Select 'February ↓ $ 25,000' item");
        expectedList.add("Select 'March ↓ $ 23,000' item");
        expectedList.add("Select 'April ↑ $ 30,000' item");
    }

    public static void initializeExpectedResultSenchaTouchForiOS() {
        expectedList = new ArrayList<>();
        expectedList.add("Tap on 'Normal' button");
        expectedList.add("Type in 'Name' edit box: 'Jolie'");
        expectedList.add("Select 'Cool' check box");
        expectedList.add("Select 'Red' radio button");
        expectedList.add("Tap on 'Toggle' button");
        expectedList.add("Select 'Tab 3' tab");
        expectedList.add("Select 'Allan Disbrow' item");
    }

    public static void initializeExpectedResultSenchaTouchForAndroid() {
        expectedList = new ArrayList<>();
        expectedList.add("Tap on 'Normal' button");
        expectedList.add("Type in 'NAME' edit box: 'Jolie'");
        expectedList.add("Select 'COOL' check box");
        expectedList.add("Tap on 'Toggle' button");
        expectedList.add("Tap on 'Tab 3' tab");
        expectedList.add("Select 'Allan Disbrow' item");
    }

    public static void evaluateResultList() {
        Common.log("");
        Common.log("Start evaluating actual results list with expected results list...");
        for (String expected : expectedList) {
            boolean found = false;

            for (String actual : actualList) {
                if (actual.equals(expected)) {
                    Common.log(String.format("   . %s: Passed", expected));
                    found = true;
                    break;
                }
            }
            if (!found)
                Common.log(String.format("   . %s: Failed", expected));
        }
    }

}
