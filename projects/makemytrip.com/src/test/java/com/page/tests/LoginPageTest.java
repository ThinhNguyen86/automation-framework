package com.page.tests;

import com.page.objects.LoginPage;
import org.testng.annotations.BeforeClass;

public class LoginPageTest extends AbstractTest {

    private LoginPage loginPage;

    @BeforeClass
    public void setUp() {
        loginPage = webPageFactory.loadLoginPage();
    }

}
