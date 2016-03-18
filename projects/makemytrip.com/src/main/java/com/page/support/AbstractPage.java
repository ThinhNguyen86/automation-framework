package com.page.support;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractPage extends LoadableComponent<AbstractPage> {

    @Autowired
    private WebDriver driver;
    //    @Autowired
//    private AppiumDriver appiumDriver;
//    @Autowired
//    protected ElementsFinder elementsFinder;
//    @Autowired
//    protected UIAction uiAction;
//    @Autowired
//    protected DataReader dataReader;

//    protected WebElement element;
//    protected List<WebElement> elementList;

//    @Autowired
//    protected WebElementFinder webElementFinder;
//
//    @Autowired
//    protected WebPageFactory webPageFactory;
//
//    @Value("${site.url}")
//    private String siteBase;

//    protected void loadPage(String path) {
//        driver.get(siteBase + path);
//    }
//
//    public String title() {
//        return driver.getTitle();
//    }

}
