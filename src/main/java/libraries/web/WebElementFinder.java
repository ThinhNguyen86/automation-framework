//package libraries.core;
//
//import libraries.abstracts.ElementFinder;
//import libraries.utility.Common;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
public class WebElementFinder {
//
//    private WebElement processFindingElement(String locator, String value, Object... objects) {
//        try {
//            Object param;
//            WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT); // Find an element with a default timeout
//
//            if (objects.length == 1) {
//                param = objects[0];
//
//                if (param == null) {
//                    // Find an element without any condition
//                    switch (locator) {
//                        case "id":
//                            return driver.findElementById(value);
//
//                        case "name":
//                            return driver.findElementByName(value);
//
//                        case "xpath":
//                            return driver.findElementByXPath(value);
//                    }
//                } else {
//                    // Find an element with a defined timeout param
//                    wait = new WebDriverWait(driver, (int) param);
//                }
//            }
//
//            switch (locator) {
//                case "id":
//                    return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(value)));
//
//                case "name":
//                    return wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(value)));
//
//                case "xpath":
//                    return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(value)));
//            }
//        } catch (Exception e) {
//            Common.log(String.format("!!! ERROR - Element not found with locator strategy: %s = %s", locator, value));
//        }
//        return null;
//    }
//
}
