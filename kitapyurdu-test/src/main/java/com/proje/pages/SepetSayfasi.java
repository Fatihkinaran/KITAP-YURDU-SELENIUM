package com.proje.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SepetSayfasi {
    private WebDriver surucu;
    private By urunAdediInput = By.cssSelector(".quantity input"); // Ürün adedi giriş kutusu
    private By urunAdediGuncelleme = By.xpath("//*[@id=\"cart_module\"]/div[2]/table/tbody/tr/td[4]/form/i"); // adet güncelleme butonu
    private By toplamTutar = By.cssSelector(".grand-total"); // Toplam tutar elemanı
    private By sepetiTemizleButonu = By.cssSelector("i.fa.fa-times.red-icon"); // Sepeti temizleme butonu
    private By sepetBosMesaji = By.id("cart-items-empty"); // Sepetin boş olduğu mesajı


    public SepetSayfasi(WebDriver surucu) {
        this.surucu = surucu;
    }

    public void urunAdediniDegistir(int adet) {
        surucu.findElement(urunAdediInput).clear();
        surucu.findElement(urunAdediInput).sendKeys(String.valueOf(adet));
    }

    public String toplamTutariOku() {
        return surucu.findElement(toplamTutar).getText();
    }

    public void sepetiTemizle() {
        surucu.findElement(sepetiTemizleButonu).click();
    }

    public boolean sepetBosMu() {
        return surucu.findElements(sepetBosMesaji).size() > 0;
    }

    public void urunadetitıklama() {
        surucu.findElement(urunAdediGuncelleme).click();
    }

}