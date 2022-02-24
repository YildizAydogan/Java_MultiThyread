public class MT02BlockLevelSynchronization {
     /*
      Uygulamalarda bazen tüm metotudun synchronized yapılması gerekmeyebilir.
	  Bu durumda, sadece ilgili kısımları synchronized yapıp diğer kısımların klasik multi-threading mantığı ile
	  calışmasına izin vermek performans acisindan onemli katkı saglayacaktır.
	  İstenilen kısımların synchronized yapılması için "synchronized block" kullanılır.
	  Bu durumda block içerisindeki kısıma aynı anda birden fazla thread'in erişimine izin verilmez iken dışında kalan
	  kısımlara, aktif olan threadlar tarafından eş zamanlı erişim sağlabilir.
     */


    public static void main(String[] args) throws InterruptedException {
        Parantez p1 = new Parantez();//method call için obj create edildi
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
                    p1.parantezKoy();
                }
            }
        });
        long basla=System.currentTimeMillis();//thread run baslama zamanı
        thHabil.start();
        thKabil.start();
        thHabil.join();
        thKabil.join();
        long bitis =System.currentTimeMillis();//thread run bitis zamanı

        System.out.println("synchronized thread toplam sure :"+(bitis-basla));
    }
}

class Parantez {

    public synchronized void parantezKoy() {// [[[[[ ]]]]] & 25 ms bekleme

        // synchronized (this) {
        for (int i = 1; i <= 10; i++) {//bu action kıza sevdalanma eş zamanlı olamazzz [[[[[ ]]]]]
            if (i <= 5) {
                System.out.print("[");
            } else System.out.print("]");
        }
        // }
        System.out.println();

        for (int i = 1; i <= 10; i++) {//bu action kuafore gitme eş zamanlı olmalı
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}


