package com.proje.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class AramaSonucuSayfasi {
    private static WebDriver surucu;
    private By siralamaDropdown = By.xpath("//*[@id=\"content\"]/div[3]/div[4]/div[1]/div/div[1]/select"); // Dropdown menüsünü hedefle
    private By stoktaFiltre = By.xpath("//*[@id=\"content\"]/div[3]/div[4]/div[1]/div/label[2]/span");
    private By ikinciSayfaLinki = By.linkText("2");
    private By ucuncu_urun = By.xpath("//*[@id=\"product-492199\"]/div[1]/div[2]/a/span");

    public AramaSonucuSayfasi(WebDriver surucu) {
        this.surucu = surucu;
    }

    public void alfabetikSiralamaSec() {
        Select select = new Select(surucu.findElement(siralamaDropdown));
        select.selectByVisibleText("Alfabetik"); // Eğer "Alfabetik" seçeneği mevcutsa
    }

    public void stoktaOlanlariFiltrele() {
        surucu.findElement(stoktaFiltre).click();
    }

    public void ikinciSayfayaGit() {
        surucu.findElement(ikinciSayfaLinki).click();
    }
    public void ucuncu_urune_tikla() {
        surucu.findElement(ucuncu_urun).click();
    }
}
