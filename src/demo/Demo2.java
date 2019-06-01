package demo;

import java.util.Vector;

/**
 * 等待线程共同执行 join
 * 使用Thread的join()等待所有的子线程执行完毕，主线程在执行，thread.join()把指定的线程加入到当前线程，
 * 可以将两个交替执行的线程合并为顺序执行的线程。比如在线程B中调用了线程A的Join()方法，直到线程A执行完毕后，才会继续执行线程B。
 */
public class Demo2 {
    public static void main(String[] args) {
        try {
            Demo2.ThreadGo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void ThreadGo() throws Exception{
        Vector<Thread> vector = new Vector<Thread>();
        for(int i=0;i<5;i++){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                        System.out.println("线程启动");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            vector.add(thread);
            thread.start();
        }
        for(Thread thread : vector){
            thread.join();
        }
        System.out.println("主线程启动");
    }
}
