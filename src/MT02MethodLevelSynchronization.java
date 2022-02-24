public class MT02MethodLevelSynchronization {

    /*
    =========================================   SYNCHRONIZED  ==========================================================
multi-threading çalışma koşullarında birden fazla thread'in aynı kaynağa (değişken method, class, bellek vb)(Habil-Kabil kavgası )
erişimi (okuma veya yazma) sırasında verinin güncellenmesi ve tutarlılığı ile ilgili sorunlar çıkabilir.
Bu tutarsızlığı engellemek için synchronized keywordu kullanılabilir.
Kısaca, Syncronization bir kaynağın tread'ler tarafından eş zamanlı kullanımına kapatılması (Lock) işlemidir.
Synchronized keywordunun farklı kullanımları bulunmaktadır.
 1- Eğer bir metot "synchronized" yapılırsa (Method-Level Syncronization) bu metota aynı andan birden fazla thread'in
    erişimine izin verilmez.
 2- Eğer bir metot yerine o metodun ait olduğu class'ı aynı anda birden fazla thread'in kullanımına kapatmak
    (class-level Synchronization) istersek o zaman "synchronized static" kullanmalıyız.
 3- Eğer bir metot içerisinde belirli bir kismin eş zamanlı thread kullanımına kapatılmasını istenire
    "synchronized block" (block-level Synchronization) kullanılmalı.
     */

    public static int sayac = 0;//kavga edilecek ortak erişim sorunu olacak thread'lerin erişecegi veriable create edildi

    public static void main(String[] args) throws InterruptedException {

        Thread thHabil = new Thread(new Runnable() {
            @Override
            public void run() {
                Sayici.say("habil");//eş zamanlı olamaz
                //falan class'da filan method calış//eş zamanlı olabilir
            }
        });

        Thread thKabil = new Thread(new Runnable() {
            @Override
            public void run() {
                Sayici.say("kabil");//eş zamanlı olamaz
                //falan class'da filan method calış//eş zamanlı olabilir
            }
        });

        thHabil.start();
        //thHabil.join();// join() method muşti-thread'i tamamen kıstlar (öldürür).join() kullanılmasa thHabil ve thKabil
        // thread'ler eş zamanlı sayac veriable üzerinde action yapardı.Bu durumu engellmek için join() bir yöntem olsada riski vardır join()ile
        // SYNCHRONIZED kullanımı ii planlanmalı...
        thKabil.start();

    }


}

class Sayici {
    public synchronized static void say(String thread) {//synchronized key word--> bu methodun thread'ler tarafından eş zamanlı run edilmesini kısıtlar

        for (int i = 1; i <= 20; i++) {
            MT02MethodLevelSynchronization.sayac++;
            System.out.println("sayac-" + thread + ":" + MT02MethodLevelSynchronization.sayac);
        }
    }

}

