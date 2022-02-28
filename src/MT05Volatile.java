/*
============================================= VOLATILE ===============================================================
 Volatile (uçucu, geçici, kalıcı olmayan) keyword'u, Bir degiskenin farklı threadler tarafından kullanılırken degerinin
 degismesini saglamak icin kullanılmaktadir. Aynı zamanda bir class'ı thread-safe(thread güvenliği ile çalışma) yapmak
 icin de kullanılır.  Yani eş zamanlı olarak volatile veriable threadler tarafından erişip güncelleyebilir ve Volatile
  keywordu sadece degiskenler ile (primitif veya non-primitif)  kullanılabilir. Obj, method ve Class'lara konulmaz
 Volatile keywordu kullanılan bir veriable'ın degeri cache bellege saklanmaz. Her defasında ilgili class'ın process bellegi
 (MAIN MEMORY) den okunur. Dolayısıyla farklı thread'ler degiskeni guncellese de her defasında en guncel deger okunmus olur.
 Bu özellikleri sayesinde Synchronization yönteminin daha iyi bir alternatifi olarak düşünülebilir.
 */


public class MT05Volatile {
    volatile public static int yas=0;//class level'de birden cok thread'in erişecegi ve update etmek istediği veriable
    // (sut cekilecek top)

    public static void main(String[] args) {

        Thread thTopcu1 = new Thread(new Runnable() {

            @Override
            public void run() {
                while(true){
                    if (yas==0){//yas veriable 0 oldg surece print çalış değilse donguden cık
                        System.out.println("Topcu1 sutu cekti : yas hala 0 buyumedi bu veled :) ");
                    }else
                        break;
                }

            }
        });
        Thread thTopcu2 = new Thread(new Runnable() {

            @Override
            public void run() {
                // try {
                //     Thread.sleep(5000);
                // } catch (InterruptedException e) {
                //     e.printStackTrace();
                // }
                yas=01;
                System.out.println("Topcu2 sutu cekti :  veled nıhayet yasina basti  :) ");
            }
        });
        thTopcu1.start();
        thTopcu2.start();
    }
}