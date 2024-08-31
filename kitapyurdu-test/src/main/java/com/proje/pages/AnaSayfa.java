package com.proje.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AnaSayfa {
    private WebDriver surucu;
    private By aramaKutusu = By.id("search-input");
    //private By aramaButonu = By.xpath("//*[@id=\"search\"]/span"); Bu yöntemde kullanılabilir.
    private By aramaButonu = By.cssSelector("span.common-sprite.button-search");

    public AnaSayfa(WebDriver surucu) {
        this.surucu = surucu;
    }

    public void anaSayfayaGit() {
        surucu.get("https://www.kitapyurdu.com");
    }

    public void kitapAra(String kitapAdi) {
        surucu.findElement(aramaKutusu).sendKeys(kitapAdi);
        surucu.findElement(aramaButonu).click();
    }
}