package com.page.support;

import com.page.objects.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WebPageFactory {

    @Autowired
    protected WebDriver driver;
    @Autowired
    private LoginPage loginPage;

    public LoginPage loadLoginPage() {
        PageFactory.initElements(driver, loginPage);
        loginPage.get();
        return loginPage;
    }

}
