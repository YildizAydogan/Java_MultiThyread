/*
============================ WAIT, NOTIFY ==========================
   object.wait()-->(obj ile çalışır Multi-Thread ile kullanılır) methodu bir thread'i suresiz olarak askıya alir(suspent).
    Diğer bir ifade ile bu thread'in kilitlemiş (locked) olduğu bir kaynağı salıvermesini ve askıya geçmesini sağlar.
    Thread bu durumdan bir başka thread Onu bilgilendirirse (notify) çıkabilir.
   object.notify()-->(obj ile çalışır Multi-Thread ile kullanılır) metodu ise aynı obj üzerinde wait (askıya alınan) bir
    thread'in uyanmasini saglar.  notify : Bildirmek, haber vermek, ihbar etmek
   Object.notifyAll() metodu bir nesne üzerinde askıya alınan tum thread'lerin uyandirilmasini saglar.
   Bu methodlar, thread'ler arasi iletişim (inter-Thread communication) metodu olarak kullanılır.
   Aynı  class'ta birden çok method'lar wait() ve notify() yapılabilir
 */


public class MT06WoitNotify {
    public static double bakiye = 0.0;

    public static void main(String[] args) {
        MT06WoitNotify islem = new MT06WoitNotify();
        Thread paraCekTh = new Thread(new Runnable() {

            @Override
            public void run() {
                islem.paraCek(200);
            }
        });
        Thread paraYatirTh = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                islem.paraYatir(500);
            }
        });
        paraCekTh.start();
        paraYatirTh.start();

    }


    public void paraCek(double cekilecekMiktar) {
        synchronized (this) {
            if (bakiye < 0 || bakiye < cekilecekMiktar) {
                System.out.println("agam niddinolmayan parayı mı cekicen :) ");
            }
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            bakiye -= cekilecekMiktar;
            System.out.println("paranız cekildi yeni bakıyeniz: " + bakiye);
        }
    }

    public void paraYatir(double yatirilacakMiktar) {
        bakiye += yatirilacakMiktar;
        System.out.println("agam para cepte gözün aydın :)" + bakiye);
        synchronized (this) {
            notify();
        }
    }
}