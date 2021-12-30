package class7_hw.question7;

public class ThreadSafeMain  {
   public static void main(String[] args) {

      Thread t1 = new Thread(ThreadSafeSingleton.getInstance());
      t1.start();
      Thread t2 = new Thread(ThreadSafeSingleton.getInstance());
      t2.start();
   }
}
