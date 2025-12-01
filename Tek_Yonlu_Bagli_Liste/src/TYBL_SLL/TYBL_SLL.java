package TYBL_SLL;

// burada listemi yöneteceğim değişkenler ve hareket değişkenimi tuttuğum sınıfımı oluşturuyorum.

class Dugum{
    int veri;
    Dugum sonraki;
    public Dugum(int veri){
        this.veri = veri;
        sonraki = null;
    }
}

//""" burada liste içi elemanlarda yapacağım değişiklikleri yöneteceim metotları tutan sınıfımı oluşturuyorum.
class Liste{
    Dugum bas;
    Dugum son;
    private int boyut;
    public Liste(){
        this.bas = null;
        this.son = null;
        this.boyut = 0;
    }
    
    //*** listenin başına eleman ekleme metodu
    public void basaEkle(Dugum yeniDugum){
        if (bas==null) {
            bas = yeniDugum;
            son = yeniDugum;
        }else{
            yeniDugum.sonraki = bas;
            bas = yeniDugum;
        }
        boyut++;
    }
    
    //*** listenin sonuna eleman ekleme
    public void sonaEkle(Dugum yeniDugum){
        if (son==null) {
            bas = yeniDugum;
            son = yeniDugum;
        }else{
            son.sonraki = yeniDugum;
            yeniDugum.sonraki = null;
            son = yeniDugum;
        }
        boyut++;
    }
    
    //*** listenin başından eleman silme
    public void bastanSil(){
        if (bas==null) {
            System.out.println("liste bos, silinecek eleman bulunamadi.");
            return;
        }
        System.out.println(bas.veri+" bastan silindi.");
        if (bas==son) {
            bas = null;
            son = null;
        }else{
            bas = bas.sonraki;
        }
        boyut--;
    }
    
    //*** aradan eleman silme 
    public void aradanSil(int silinecekVeri){
        if (bas==null) {
            System.out.println("liste bos, silinecek eleman yok.");
            return;
        }
        
        if (bas.veri==silinecekVeri) {
            System.out.println("silinecek eleman listenin basında, bastanSil metodu aktiflestiriliyor...");
            bastanSil();
            return;
        }
        
        Dugum gezici = bas.sonraki;
        Dugum onceki = bas;
        while(gezici!=null && gezici.veri!=silinecekVeri){
            onceki = gezici;
            gezici = gezici.sonraki;
        }
        
        if (gezici==null) {
            System.out.println(silinecekVeri+" listede mevcut degil.");
            return;
        }
        
        onceki.sonraki = gezici.sonraki;
        System.out.println(gezici.veri +" aradan silindi.");
        
        if (gezici==son) {
            son = onceki;
        }
        boyut--;
    }
    
    //*** sondan eleman silme
    public void sondanSil() {
        if (bas == null) {
            System.out.println("Liste boş.");
            return;
        }
        
        if (bas == son) {
            System.out.println(bas.veri + " (tek eleman) sondan silindi.");
            bas = null;
            son = null;
            return;
        }

        Dugum gezici = bas;
        while (gezici.sonraki != son) { 
            gezici = gezici.sonraki;
        }
        
        System.out.println(son.veri + " sondan silindi.");
        gezici.sonraki = null;
        son = gezici;
        boyut--;
    }
    
    //*** listeyi ters çevirme
    public void listeyiTersCevir(){
        if (bas==null || bas.sonraki==null) {
            return;
        }
        Dugum onceki = null;
        Dugum gezici = bas;
        Dugum sonrakiDugum = null;
        
        Dugum eskiBas = bas;
        
        while(gezici!=null){
            sonrakiDugum = gezici.sonraki;
            gezici.sonraki = onceki;
            onceki = gezici;
            gezici = sonrakiDugum;
        }
        
        bas = onceki;
        son = eskiBas;
        System.out.println("--- LİSTE TERS CEVIRILDI ---");
    }
    
    //*** lisyeyi ekrana yazdırma
    public void listeyiYazdir(){
        Dugum gezici = bas;
        if (gezici==null) {
            System.out.println("liste bos.");
            return;
        }
        while(gezici!=null){
            System.out.print("isim : "+gezici.veri+"\n");
            gezici = gezici.sonraki;
        }
    }
    
    //*** eleman sayisi döndürme
    public int elemanSayisi(){
        return this.boyut;
    }
    
    //*** listenin boş olduğunu kontrol etme
    public boolean bosMu(){
        return this.boyut==0;
    }
    
    //*** girilen index'e göre düğümü döndürme
    public Dugum get(int index){
        if (index<0 || index>boyut-1) {
            System.out.println("gecersiz index.");
            return null;
        }
        Dugum gezici = bas;
        for (int i = 0; i < index; i++) {
            gezici = gezici.sonraki;
        }
        return gezici;
    }
    
    //*** Belirtilen indexteki veriyi güncelleme
    public void set(int index, int yeniVeri){
        Dugum hedefDugum = get(index);
        if (hedefDugum != null) {
            hedefDugum.veri = yeniVeri;
            System.out.println(index + ". indexteki veri " + yeniVeri + " olarak güncellendi.");
        } else {
            System.out.println("Güncelleme başarısız, index geçersiz.");
        }
    }
    
    //*** girilen indexe göre araya eleman ekleme
    public void arayaEkle(int index, Dugum yeniDugum){
        
        if (index<0 || index>boyut-1) {
            System.out.println("gecersis index.");
            return;
        }
        
        if (index==0) {
            basaEkle(yeniDugum);
            return;
        }
        if (index==boyut) {
            sonaEkle(yeniDugum);
            return;
        }
        
        Dugum onceki = get(index-1);
        
        yeniDugum.sonraki = onceki.sonraki;
        onceki.sonraki = yeniDugum;
        
        boyut++;
    }
    
    //*** listeden belirtilen indexteki elemanı silme
    public void indextenSil(int index) {
        
        if (index < 0 || index >= boyut) {
            System.out.println("Geçersiz index: " + index);
            return;
        }
        
        if (index == 0) {
            bastanSil(); 
            return;
        }
        
        Dugum onceki = get(index - 1);
        
        Dugum silinecekDugum = onceki.sonraki;
        System.out.println(silinecekDugum.veri + " (" + index + ". index) silindi.");
        
        onceki.sonraki = silinecekDugum.sonraki;
        
        if (index == boyut - 1) {
            son = onceki;
        }
        boyut--;
    }
    
    //*** recursive olarak liste elemanlarını yazdırma
    public void yazdirRecursive(){
        System.out.println("BAS --> ");
        yazdirRecursiveYardimcisi(bas);
        System.out.println("NULL");
    }
    
    private void yazdirRecursiveYardimcisi(Dugum gezici){
        if (gezici == null) {
            return;
        }
        
        System.out.println("[" + gezici.veri + "] -> ");
        yazdirRecursiveYardimcisi(gezici.sonraki);
    }
    
    //*** recursive listeyi tersten yazdırma
    public void tersYazdir(){
        System.out.println("NULL <-");
        tersYazdirYardimcisi(bas);
        System.out.println("BAS");
    }
    private void tersYazdirYardimcisi(Dugum gezici){
        if (gezici==null) {
            return;
        }
        
        tersYazdirYardimcisi(gezici.sonraki);
        System.out.print("[" + gezici.veri +"] <- ");
    }
    
    //*** recursive ile listeyi kalıcı olarak ter çevirme
    public void listeyiTersCevirRecursive() {
        if (bas == null) return;
        Dugum eskiBas = bas;
        this.bas = tersCevirYardimcisi(bas);
        this.son = eskiBas;
        this.son.sonraki = null;
    }
    
    private Dugum tersCevirYardimcisi(Dugum gezici) {
         if (gezici == null || gezici.sonraki == null) {
            return gezici; 
        }
        Dugum yeniBas = tersCevirYardimcisi(gezici.sonraki);
        gezici.sonraki.sonraki = gezici;
        gezici.sonraki = null; 
        return yeniBas;
    }
    
    //***
    public void siraliBirlestir(Liste digerListe) {
        if (digerListe == null || digerListe.bas == null) {
            return; 
        }
        if (this.bas == null) {
            this.bas = digerListe.bas;
            this.son = digerListe.son;
            this.boyut = digerListe.boyut;
            digerListe.sifirla(); 
            return;
        }

        Dugum p1 = this.bas;     
        Dugum p2 = digerListe.bas; 
        
        Dugum yeniBas = null;
        Dugum gezici = null; 

        
        if (p1.veri <= p2.veri) {
            yeniBas = p1;
            p1 = p1.sonraki;
        } else {
            yeniBas = p2;
            p2 = p2.sonraki;
        }
        gezici = yeniBas; 

        while (p1 != null && p2 != null) {
            if (p1.veri <= p2.veri) {
                gezici.sonraki = p1; 
                p1 = p1.sonraki;     
            } else {
                gezici.sonraki = p2; 
                p2 = p2.sonraki;     
            }
            gezici = gezici.sonraki; 
        }

        if (p1 != null) {
            gezici.sonraki = p1;
            this.son = digerListe.son; 
            this.son = this.son; 
        }
        if (p2 != null) {
            gezici.sonraki = p2;
            this.son = digerListe.son; 
        }

        this.bas = yeniBas; 
        this.boyut = this.boyut + digerListe.boyut;
        
        digerListe.sifirla(); 
    }
    
    //*** bu metot tek yönlü listenin içini sıfırlar.
    private void sifirla() {
        this.bas = null;
        this.son = null;
        this.boyut = 0;
    }

    //*** bu metot iki tek yönlü listeyi iç kuralına göre birleştirir.
    public void fermuarBirlestir(Liste digerListe) {
    if (digerListe == null || digerListe.bas == null) {
        return; 
    }
    if (this.bas == null) { 
        this.bas = digerListe.bas;
        this.son = digerListe.son;
        this.boyut = digerListe.boyut;
        digerListe.sifirla();
        return;
    }

    Dugum p1 = this.bas;
    Dugum p2 = digerListe.bas;
    Dugum p1Sonraki, p2Sonraki;

    while (p1 != null && p2 != null) {
        p1Sonraki = p1.sonraki;
        p2Sonraki = p2.sonraki;

        p1.sonraki = p2;
        
        if (p1Sonraki != null) {
            p2.sonraki = p1Sonraki;
        }

        p1 = p1Sonraki;
        p2 = p2Sonraki;
    }

    if (digerListe.son != null && p1 == null) {
         this.son = digerListe.son;
    }

    this.boyut += digerListe.boyut;
    digerListe.sifirla();
}



// bu şekilde birçok metot oluşturulabilir.(sondan eleman silme,baştan eleman silme, listeye yeni liste ekleme...)
}

public class TYBL_SLL {

    
    
    public static void main(String[] args) {

    
    Liste l = new Liste();
    
    l.sonaEkle(new Dugum(80));
    l.sonaEkle(new Dugum(75));
    l.sonaEkle(new Dugum(50));
    l.sonaEkle(new Dugum(90));
    
    System.out.println("--- ORJINAL LISTE ---");
    l.listeyiYazdir();
    System.out.println("---------------------");
    
    System.out.println("\n--- 75 Siliniyor ---");
    l.aradanSil(75);
    System.out.println("--- ORJINAL LISTE ---");
    l.listeyiYazdir();
    System.out.println("---------------------");
    
    System.out.println("\n--- Sondaki 90 Siliniyor ---");
    l.aradanSil(90);
    System.out.println("--- ORJINAL LISTE ---");
    l.listeyiYazdir();
    System.out.println("---------------------");
    
    if(l.son != null)
        System.out.println(">>> GÜNCEL SON: " + l.son.veri);
    
    System.out.println("\n--- Sondan Silme ---");
    l.sondanSil();
    System.out.println("--- ORJINAL LISTE ---");
    l.listeyiYazdir();
    System.out.println("---------------------");
    
    if(l.son != null)
        System.out.println(">>> GÜNCEL SON: " + l.son.veri);
    
    l.sonaEkle(new Dugum(10)); 
    l.sonaEkle(new Dugum(20));
    System.out.println("\n--- Liste Dolduruldu ---");
    System.out.println("--- ORJINAL LISTE ---");
    l.listeyiYazdir();
    System.out.println("---------------------");
    
    
    System.out.println("\n--- Liste Ters Çevriliyor ---");
    l.listeyiTersCevir();
    System.out.println("--- ORJINAL LISTE ---");
    l.listeyiYazdir();
    System.out.println("---------------------");
    System.out.println(">>> GÜNCEL BAŞ: " + l.bas.veri); 
    System.out.println(">>> GÜNCEL SON: " + l.son.veri);
    
    l.yazdirRecursive();
    
    
    
    System.out.println("--- Test 1: L1 daha uzun ---");
        Liste L1 = new Liste(); // A, B, C
        L1.sonaEkle(new Dugum(80));
        L1.sonaEkle(new Dugum(75));
        L1.sonaEkle(new Dugum(50));
    
        
        Liste L2 = new Liste(); // X, Y
        L2.sonaEkle(new Dugum(99)); // X
        L2.sonaEkle(new Dugum(88)); // Y
        
        L1.fermuarBirlestir(L2);
        
        System.out.println("L1 Sonuc: ");
        L1.listeyiYazdir(); // Beklenen: 10 -> 99 -> 20 -> 88 -> 30 -> NULL
        System.out.println("L1'in 'son'u (degismemeli): " + L1.son.veri); // 30 olmalı
        System.out.print("L2 Sonuc (bos olmali): ");
        L2.listeyiYazdir();// Beklenen: Liste: NULL
        
    
    
        System.out.println("\n--- Test 2: L2 daha uzun ---");
        Liste L3 = new Liste(); // A, B
        L3.sonaEkle(new Dugum(1)); // A
        L3.sonaEkle(new Dugum(2)); // B
        
        Liste L4 = new Liste(); // X, Y, Z
        L4.sonaEkle(new Dugum(77)); // X
        L4.sonaEkle(new Dugum(66)); // Y
        L4.sonaEkle(new Dugum(55)); // Z
        
        L3.fermuarBirlestir(L4);
        
        System.out.print("L3 Sonuç: ");
        L3.listeyiYazdir(); // Beklenen: 1 -> 77 -> 2 -> 66 -> 55 -> NULL
        System.out.println("L3'ün yeni 'son'u (güncellenmeli): " + L3.son.veri); // 55 olmalı
        System.out.print("L4 Sonuç (boş olmalı): ");
        L4.listeyiYazdir();
    }
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
  
