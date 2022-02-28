public class MT04ClassLevelSynchronization {
    /*
       Thread'ler farklı obj'ler kullanılırsa durum nasıl değişir?
     Dolayısıyla method seviyesi synchronization işe yaramayacaktır.
     Çünkü her iki thread, farklı obj'lerin parantezKoy() methodunu calistirmaktadir.
     Burada çözüm için class seviyesinde blocklama yapmaktır.
     Çünkü her iki obje aynı class'dan create edilmektedir.
     */
    public static void main(String[] args) {

        Parantez1 p1=new Parantez1();
        Parantez1 p2=new Parantez1();

        Thread thHabil = new Thread(new Runnable() {

            @Override
            public void run() {//5 satır [[[[[ ]]]]] print eder
                for (int i = 1; i <= 5; i++) {
                    p1.parantezKoy();
                }
            }
        });

        Thread thKabil = new Thread(new Runnable() {//5 satır [[[[[ ]]]]] print eder
            @Override
            public void run() {
                for (int i = 1; i <= 5; i++) {
                    p2.parantezKoy();
                }
            }
        });
        thHabil.start();
        thKabil.start();
    }
}
class Parantez1 {

    public synchronized static  void parantezKoy() {//synchronized static-->keyword ile static kelimesi class level synchronized yapar
        // public  synchronized  void parantezKoy() { //synchronized -->keyword ile method level synchronized yapar
        //Process--> [[[[[ ]]]]] & 25 ms bekleme

        for (int i = 1; i <= 10; i++) {//bu action : kıza sevdalanma eş zamanlı olamazzz [[[[[ ]]]]]
            if (i <= 5) {
                System.out.print("[");
            } else System.out.print("]");
        }

        System.out.println();

        for (int i = 1; i <= 10; i++) {//bu action :  kuafore gitme eş zamanlı olmalı
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

