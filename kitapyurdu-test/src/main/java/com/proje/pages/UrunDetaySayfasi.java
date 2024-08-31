package com.proje.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UrunDetaySayfasi {
    private static WebDriver surucu;
    private By urunFiyati = By.cssSelector(".price__item"); // Ürün fiyatını temsil eden elemanın locator'ı

    private By sepeteEkleButonu = By.id("button-cart"); // Sepete ekleme butonunun locator'ı

    private static By SepetButonu = By.xpath("//*[@id=\"cart-items-text\"]");

    private static By SepeteGitButonu = By.xpath("//*[@id='js-cart']");



    public UrunDetaySayfasi(WebDriver surucu) {
        this.surucu = surucu;
    }

    public String urunFiyatiniOku() {
        return surucu.findElement(urunFiyati).getText();
    }

    public void urunuSepeteEkle() {
        surucu.findElement(sepeteEkleButonu).click();
    }

    public static void SepeteGit() {
        surucu.findElement(SepetButonu).click();
    }

    public static void SepeteGitButonu () {
        surucu.findElement(SepeteGitButonu).click();
     }


}