public class MT01SingleThreadMultiThread {
    /*

      ============================================ THREAD, PROCESS=========================================================
    Thread : Kelime manası iplik olmasına karşın IT alanında iş parçacığı olarak adlandırılır.
    Thread, Process olarak adlandırılan ve her bir çalışan programın alt iş parçaçığı olarak düşünülebilir.
    Single-Thread: Tek iş parcacığı vardır. Ve tüm işler sıra ile yapılır.JVM default olarak Single-Thread create eder.
    Multi-Thread: Bir den fazla iş parçacığı vardır ve bu işler aynı anda yapılabilir (farklı işlemciler yardımıyla)

    Javada 2 yöntem ile Thread oluşturmak mümkündür.
    ----------------------------------------------
    1- Thread classından bir class extends (türeterek) edilerek
    2- Runnable Interface'inden implements edilerek.

    Thread class'ının yaygın kullanılan metotlari:
    -----------------------------------------------
  *  public void run(): Bir threat'in calistiracagi islemleri tanımlamak amacıyla kullanlır.
                        Bir thread create edildiğinde içinde ne çalışacagını run() method override edilerek olusturulur.
                        Bir thread'in yapacagı action run() method içinde tanımlanır.

  * public void start(): Bir thread'in baslatilmasini saglar.JVM, thread'in run() methodunu calistirir.

  *  public void sleep(long miliseconds): Bir thread'in belirtilen sure (ms) boyunca bekletilmesini saglar.
  *  public void join(): Bir thread olmesini (bitmesi) beklemek icin kullanilir.
                        Thread'in olmesi beklendikten sonra sıradaki komutlar run edilir.Sıralı run işlemlerinde gayet önemlidir.
  *  public int getPriority(): Aynı anda birden cok çalışan Thread'ler için bir thread'in önceligini dondurur.
    public int setPriority(int priority): Bir thread icin oncelik ayarlamaya yarar.
    public String getName(): Bir thread'in adini dondurur.
    public void setName(String name): Bir thread'e isim vermeye yarar.
    public Thread currentThread(): Su an calisan thread'in referansını dondurur.
    public int getId(): Bir thread'in id numarasını dondurur.
    public Thread.State getState(): Bir thread'in state (durum)'ini dondurur.
    public boolean isAlive(): Bir thread'in canlı (alive) olup olmadığını soyler.
    public void yield(): Aktif olan bir thread'in gecici olarak durdurulmasini ve baska thread'lerin calistirilmasini saglar.
    public boolean isDaemon(): Bir thread'in Deamon(şeytan:arka planda çalışan garbage collector gibi JVM'in kapanmasını engellemeyen önceliği düşük thread'ler)
                               threat olup olmadıgını test eder. Gerekmedikçe kullanılmamalı. Arka planda çalışmayacak thread'ler için kulanılırsa JVM kapatabilir
    public void setDaemon(boolean b): Bir thread'i deoman thread olarak işaretler.
  *  public void interrupt(): Thread kesintiye ugratır.mevcut App çalışırken flash bellek takma gibi eş zamalılıgı kesmek için kullanılır.
                              Usp klavye port bu thread'i kullanır
    public boolean isInterrupted(): Bir thread'in kesilip kesilmediğni test eder.


     */
    public static void main(String[] args) throws InterruptedException {
        long singleBasla = System.currentTimeMillis();
        System.out.println("    *****    Single-Thread    *****    ");
        SingleThreadSayac s1 = new SingleThreadSayac(1);
        s1.sayac();
        SingleThreadSayac s2 = new SingleThreadSayac(2);
        s2.sayac();
        long singleBitis = System.currentTimeMillis();
        System.out.println("single thread run suresi :  " + (singleBitis - singleBasla));

        long multiBasla = System.currentTimeMillis();
        System.out.println("    *****    Multi -Thread    *****    ");
        MultiThreadSayac s3 = new MultiThreadSayac(3);
        s3.start();
        MultiThreadSayac s4 = new MultiThreadSayac(4);
        s4.start();
        s3.join();//Bir thread'in bitmesini bekletmek için kullanılır.
        // Gereksiz yere kullanılması multithread'i yavaşlatır. eşzamanlılıga yakırı kısıtlama getiri.
        s4.join();
        long multiBitis = System.currentTimeMillis();
        System.out.println("multi thread run suresi :  " + (multiBitis - multiBasla));

    }

}

class SingleThreadSayac {

    private int threadNo;

    public SingleThreadSayac(int threadNo) {
        this.threadNo = threadNo;
    }


    public void sayac() {
        for (int i = 1; i <= 10; i++) {
            System.out.println("i " + i + " için  çalışan thread no : " + +threadNo);

            try {
                Thread.sleep(50);//500 ms pc için çok buyuk miktar
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

class MultiThreadSayac extends Thread {

    private int threadNo;

    public MultiThreadSayac(int threadNo) {
        this.threadNo = threadNo;
    }

    public void sayac() {
        for (int i = 1; i <= 10; i++) {
            System.out.println("i " + i + " için  çalışan thread no : " + +threadNo);

            try {
                Thread.sleep(50);//500 ms pc için çok buyuk miktar
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void run() {//hizmetçiye iş tanımı yapılıyor (Thread: hizmetçi) run() olmazsa thread çalışmaz
        sayac();
    }
}