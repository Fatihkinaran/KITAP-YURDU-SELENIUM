package com.proje.tests;

import com.proje.pages.AnaSayfa;
import com.proje.pages.AramaSonucuSayfasi;
import com.proje.pages.UrunDetaySayfasi;
import com.proje.pages.SepetSayfasi;
import com.proje.utils.TemelTest;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import java.time.Duration;
import java.util.List;

public class AlisverisTest extends TemelTest {

    private WebDriverWait wait;

    @Override
    public void setUp() {
        super.setUp();
        wait = new WebDriverWait(surucu, Duration.ofSeconds(10));
    }

    @Test
    public void alisverisSenaryosuTesti() {
        AnaSayfa anaSayfa = new AnaSayfa(surucu);
        AramaSonucuSayfasi aramaSonucuSayfasi = new AramaSonucuSayfasi(surucu);
        UrunDetaySayfasi urunDetaySayfasi = new UrunDetaySayfasi(surucu);
        SepetSayfasi sepetSayfasi = new SepetSayfasi(surucu);

        // Adım 1: Kitap Yurdu anasayfasına git
        anaSayfa.anaSayfayaGit();

        // Adım 2: Çerez kutusunu kapat
        try {
            By cerezKutusuKapatmaButonu = By.id("cookiescript_accept");
            wait.until(ExpectedConditions.elementToBeClickable(cerezKutusuKapatmaButonu)).click();
        } catch (Exception e) {
            System.out.println("Çerez kutusu bulunamadı veya kapatılamadı: " + e.getMessage());
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"search\"]/span")));
        anaSayfa.kitapAra("Sabahattin Ali");
        aramaSonucuSayfasi.alfabetikSiralamaSec();
        try {
            // 10 saniye bekle
            Thread.sleep(2000); // 1000 milisaniye =2 saniye
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        aramaSonucuSayfasi.stoktaOlanlariFiltrele();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div[3]/div[4]/div[1]/div/label[2]/span")));
        aramaSonucuSayfasi.ikinciSayfayaGit();

        try {
            // 10 saniye bekle
            Thread.sleep(2000); // 1000 milisaniye =2 saniye
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Ürünlerin yüklenmesini bekleyiyoruz
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div[3]/div[4]/div[3]/div[2]"))); // Ürün listeleme alanı

        WebElement urunTablosu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"product-table\"]")));
        try {
            // 10 saniye bekle
            Thread.sleep(2000); // 1000 milisaniye =2 saniye
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Ürünlerin tümünü al
        List<WebElement> urunler = urunTablosu.findElements(By.xpath(".//div[contains(@id, 'product-')]"));
        System.out.println("Toplam Ürün Sayısı: " + urunler.size());

        // Ürünlerin ID'lerini yazdırın
        for (int i = 0; i < urunler.size(); i++) {
            String urunId = urunler.get(i).getAttribute("id");
            System.out.println("Ürün " + (i + 1) + " ID: " + urunId);
        }

        try {
            Thread.sleep(2000); // 1000 milisaniye =2 saniye
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 3. ürüne tıklayın
        if (urunler.size() >= 3) {
            WebElement ucuncuUrun = urunler.get(2); // Üçüncü ürünü al
            ucuncuUrun.click(); // Üçüncü ürüne tıklayın
            // İd bilgisine tıklanmıyor. ama ürün doğruluğu teyit edilebilir.
        } else {
            System.out.println("Yeterli ürün bulunamadı.");
        }


        wait.until(ExpectedConditions.elementToBeClickable(By.id("product-table")));

        try {
            Thread.sleep(2000); // 1000 milisaniye =2 saniye
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        aramaSonucuSayfasi.ucuncu_urune_tikla();

        try {
            Thread.sleep(2000); // 1000 milisaniye =2 saniye
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String urunFiyati = urunDetaySayfasi.urunFiyatiniOku();
        System.out.println(urunFiyati);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-cart")));
        urunDetaySayfasi.urunuSepeteEkle();
        By sepetIkonSayisi = By.id("cart-items-text");
        String urunSayisi = wait.until(ExpectedConditions.visibilityOfElementLocated(sepetIkonSayisi)).getText();
        if (urunSayisi.equals("1")) {
            System.out.println("Sepetteki ürün sayısı doğru: " + urunSayisi);
            // Sepette ürün sayısı doğru, diğer test adımlarına devam et
        } else {
            System.err.println("Hata: Sepetteki ürün sayısı beklenen değil. Beklenen: 1, Bulunan: " + urunSayisi);
            Assert.fail("Sepetteki ürün sayısı yanlış! Test başarısız oldu."); // Testi durdur ve başarısız say
        }

        UrunDetaySayfasi.SepeteGit();

        try {
            Thread.sleep(3000); // 3000 milisaniye = 5 saniye
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        UrunDetaySayfasi.SepeteGitButonu();

        sepetSayfasi.urunAdediniDegistir(2);
        try {
            Thread.sleep(1000); // 1000 milisaniye =1 saniye
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        sepetSayfasi.urunadetitıklama();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        String toplamTutar = sepetSayfasi.toplamTutariOku();

        System.out.println("Toplam Tutar" + toplamTutar);

        double urunFiyatiDouble = Double.parseDouble(urunFiyati.replace(",", ".").replaceAll("[^\\d.]", ""));
        double toplamTutarDouble = Double.parseDouble(toplamTutar.replace(",", ".").replaceAll("[^\\d.]", ""));
        double ikiKatiUrunFiyati = urunFiyatiDouble * 2;

        Assert.assertTrue("Toplam tutar, ürün fiyatının iki katı olmalıdır.", toplamTutarDouble == ikiKatiUrunFiyati);

        sepetSayfasi.sepetiTemizle();

        try {

            Thread.sleep(2000); //
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("swal2-title")));
        Assert.assertTrue(sepetSayfasi.sepetBosMu());


    }
}
