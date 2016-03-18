package com.page.tests;

import com.page.support.WebPageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;

@ContextConfiguration(locations = {"classpath:conf/applicationContext.xml"})
public abstract class AbstractTest extends AbstractTestNGSpringContextTests {

    @Autowired
    protected WebPageFactory webPageFactory;

    @BeforeClass
    public void initTest() {
        // TODO
    }

}
