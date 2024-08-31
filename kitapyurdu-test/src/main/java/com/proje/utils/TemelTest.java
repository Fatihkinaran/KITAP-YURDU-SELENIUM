package com.proje.utils;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TemelTest {
    protected WebDriver surucu;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\project\\chromeDriver\\chromedriver.exe");
        surucu = new ChromeDriver();
        surucu.manage().window().maximize();
    }

    @After
    public void tearDown() {
        if (surucu != null) {
            surucu.quit();
        }
    }
}
