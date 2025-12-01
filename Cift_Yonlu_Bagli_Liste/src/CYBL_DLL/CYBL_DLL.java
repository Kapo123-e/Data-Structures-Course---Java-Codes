package CYBL_DLL;



class CiftYonluDugum{
            String isim;
            int not;
            CiftYonluDugum sonraki;
            CiftYonluDugum onceki;
            
            public CiftYonluDugum(String isim, int not){
                this.isim = isim;
                this.not = not;
                this.sonraki = null;
                this.onceki = null;
            }

        }
        
        class CiftYonluListe{
            CiftYonluDugum bas;
            CiftYonluDugum son;
            private int boyut;
            
            public CiftYonluListe(){
                this.bas = null;
                this.son = null;
                this.boyut = 0;
            }
            
            //************************************************************
            
            public int elemanSayisi(){
                return this.boyut;
            }
           
            //************************************************************
            
            public String basaEkleme(CiftYonluDugum yeniDugum){
                if (bas==null) {
                    bas = yeniDugum;
                    son = yeniDugum;
                }else{
                    yeniDugum.sonraki = bas;
                    bas.onceki = yeniDugum;
                    bas = yeniDugum;
                }
                boyut++;
                return "["+yeniDugum.isim+"]"+" listenin basına eklendi.";
            }
            
            //************************************************************
            
            public String sonaEkleme(CiftYonluDugum yeniDugum){
                if (bas==null) {
                    bas = yeniDugum;
                    son = yeniDugum;
                }else{
                    son.sonraki = yeniDugum;
                    yeniDugum.onceki = son;
                    son = yeniDugum;
                }
                boyut++;
                return "["+yeniDugum.isim+"]"+" listenin sonuna eklendi.";
            }
            
            //************************************************************
            
            public void listeyiYazdir(){
                System.out.println("--- LISTENIN DUZ HALI ---");
                CiftYonluDugum gezici = bas;
                System.out.print("BAS ");
                while(gezici!=null){
                    System.out.print("<-> "+"["+gezici.isim+"]");
                    gezici = gezici.sonraki;
                }
                System.out.println(" <-> NULL");
            }
            
            //************************************************************
            
            public void terstenYazdir(){
                System.out.println("--- LISTENIN TERSTEN YAZILMIS HALI ---");
                CiftYonluDugum gezici = son;
                System.out.print("NULL <-> ");
                while(gezici!=null){
                    System.out.print("["+gezici.isim+"]"+" <-> ");
                    gezici = gezici.onceki;
                }
                System.out.println("BAS");
            }
            
            //************************************************************
            
            public String bastanSil(){
                if (boyut==0) {
                    return "Liste bos.";
                }
               
                String silinenIsim = bas.isim;
                
                if (boyut==1) {
                    bas = null;
                    son = null;
                }else{
                    bas = bas.sonraki;
                    bas.onceki = null;
                }
                boyut--;
                return "[" + silinenIsim + "] baştan silindi.";
            }
            
            //************************************************************
            
            public String sondanSil(){
                if (boyut==0) {
                    return "liste bos.";
                }
                
                String silinenIsim = son.isim;
                
                if (boyut==1) {
                    bas = null;
                    son = null;
                }else{
                    son = son.onceki;
                    son.sonraki = null;
                }
                boyut--;
                return "[" + silinenIsim + "] sondan silindi.";
            }
            
            //************************************************************
            
            public String aradanSil(String silinecekIsim){
                if (boyut==0) {
                    return "Liste Bos.";
                }
               
                CiftYonluDugum gezici = bas;
                
                while(gezici!=null && !silinecekIsim.equals(gezici.isim)){
                   gezici = gezici.sonraki; 
                }
                
                if (gezici==null) {
                    return "[" + silinecekIsim + "] listede bulunamadı.";
                }
                
                if (gezici==bas) {
                    return bastanSil();
                }
                if (gezici==son) {
                    return sondanSil();
                }
                
                gezici.onceki.sonraki = gezici.sonraki;
                gezici.sonraki.onceki = gezici.onceki;
                
                boyut--;
                return "[" + silinecekIsim + "] aradan silindi.";
            }
            
            //************************************************************
            
            public CiftYonluDugum get(int index){
                if (index<0 || index>=boyut) {
                    System.out.println("GECERSIZ INDEX GIRISI");
                    return null;
                }
                
                CiftYonluDugum gezici;
                
                if (index<(boyut/2)) {
                    gezici = bas;
                    for (int i = 0; i < index; i++) {
                        gezici = gezici.sonraki;
                    }
                }else{
                    gezici = son;
                    for (int i = 0; i <(boyut-(index+1)) ; i++) {
                        gezici = gezici.onceki;
                    }
                }
                return gezici;
            }
            
            //************************************************************
            
            public String arayaEkle(int index, CiftYonluDugum yeniDugum){
                if (index<0 || index>=boyut) {
                    return "GECERSIZ INDEX GIRISI";
                }
                if (index==0) {
                    basaEkleme(yeniDugum);
                    return "[" + yeniDugum.isim + "] basa eklendi.";
                }
                if (index==(boyut)) {
                    sonaEkleme(yeniDugum);
                    return "[" + yeniDugum.isim + "] sona eklendi.";
                }
                
                CiftYonluDugum sagDugum = get(index);
                CiftYonluDugum solDugum = sagDugum.onceki;
                yeniDugum.sonraki = sagDugum;
                yeniDugum.onceki = solDugum;
                solDugum.sonraki = yeniDugum;
                sagDugum.onceki = yeniDugum;
                
                boyut++;
                return "[" + yeniDugum.isim + "] " + index + ". indekse eklendi.";
            }
            
            //************************************************************
            
            public String indextenSil(int index){
                if (index < 0 || index >= boyut) {
                    return "Hata: Geçersiz index.";
                }
                if (index == 0) {
                    return bastanSil();
                }
                if (index == boyut - 1) { // Son elemanın indeksi
                    return sondanSil();
                }
                
                CiftYonluDugum silinecek = get(index);
                silinecek.sonraki.onceki = silinecek.onceki;
                silinecek.onceki.sonraki = silinecek.sonraki;
                
                boyut--;
                return "[" + silinecek.isim + "] (" + index + ". index) silindi.";
            }
            
            //************************************************************
            
            public CiftYonluDugum ortadakiElemaniBul(){
                if (bas==null) {
                    System.out.println("liste bos.");
                    return null;
                }
                CiftYonluDugum yavas = bas;
                CiftYonluDugum hizli = bas;
                
                while(hizli.sonraki!=null && hizli.sonraki.sonraki!=null){
                    hizli = hizli.sonraki.sonraki;
                    yavas = yavas.sonraki;
                }
                return yavas;
            }
            
            //************************************************************
            
            public boolean donguVarMi(){
                if (bas == null || bas.sonraki == null) {
                    return false;
                }
                
                CiftYonluDugum yavas = bas;
                CiftYonluDugum hizli = bas;
                
                while (hizli != null && hizli.sonraki != null) {
                    yavas = yavas.sonraki;
                    hizli = hizli.sonraki.sonraki;
                    
                    if (hizli == yavas) {
                        return true;
                    }
                }
                return false;
            }
            
            
            
            
            
        }

            





public class CYBL_DLL {

    public static void main(String[] args) {
    
        CiftYonluListe liste = new CiftYonluListe();
        
        System.out.println(liste.sonaEkleme(new CiftYonluDugum("Ali", 80)));
        System.out.println(liste.sonaEkleme(new CiftYonluDugum("Veli", 90)));
        System.out.println(liste.basaEkleme(new CiftYonluDugum("Ayşe", 100)));
        System.out.println(liste.sonaEkleme(new CiftYonluDugum("Zeynep", 70)));
        
        liste.listeyiYazdir();
        liste.terstenYazdir();
        
         System.out.println("\n--- Silme İşlemleri ---");
        System.out.println(liste.sondanSil()); 
        System.out.println(liste.bastanSil()); 
        
        liste.listeyiYazdir();
        
        System.out.println(liste.sonaEkleme(new CiftYonluDugum("Can", 50)));
        System.out.println(liste.sonaEkleme(new CiftYonluDugum("Buse", 60)));
        
        liste.listeyiYazdir();
        
        System.out.println("\n--- Aradan Silme ---");
        System.out.println(liste.aradanSil("Can"));
        
        liste.listeyiYazdir();
        liste.terstenYazdir();
        
        System.out.println("listenin ilk elemanı: "+liste.get(0).isim);
        System.out.println("listenin ikinci elemanı: "+liste.get(1).isim);
        
        System.out.println(liste.arayaEkle(2, new CiftYonluDugum("trrek",10)));
        liste.listeyiYazdir();
        
    }
    
}
