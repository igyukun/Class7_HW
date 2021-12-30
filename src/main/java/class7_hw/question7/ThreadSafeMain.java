package class7_hw.question7;

/**
 * The main class, which creates two threads to access a shared singleton instance
 */

public class ThreadSafeMain  {
   public static void main(String[] args) {

      Thread t1 = new Thread(ThreadSafeSingleton.getInstance());
      t1.start();
      Thread t2 = new Thread(ThreadSafeSingleton.getInstance());
      t2.start();
   }
}
