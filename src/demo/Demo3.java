package demo;

import java.util.concurrent.CountDownLatch;

/**
 * 等待多线程完成的CountDownLatch
 * countDownLatch不可能重新初始化或者修改CountDownLatch对象内部计数器的值，
 * 一个线程调用countdown方法happen-before另外一个线程调用await方法
 */
public class Demo3 {
    public static void main(String[] args) {
        try {
            Demo3.threadGo();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void threadGo() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(5);
        for(int i=0;i<5;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程启动");
                    countDownLatch.countDown();//countDownLatch 中的数减1
                }
            }).start();
        }
        countDownLatch.await();
        System.out.println("主线程启动");
    }
}
