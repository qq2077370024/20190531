package demo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier方法可以使用reset()方法重置，所以CyclicBarrier方法可以能处理更为复杂的业务场景。
 *
 * 一个关于countDownLatch和cyclicBarrier的形象比喻，就是在百米赛跑的比赛中若使用 countDownLatch的话
 * 冲过终点线一个人就给评委发送一个人的成绩，10个人比赛发送10次，如果用CyclicBarrier，则只在最后一个人冲过终点线的时候发送
 * 所有人的数据，仅仅发送一次，这就是区别。
 *
 */
public class Demo4 {
    public static void main(String[] args) {
        try {
            Demo4.threadGo();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void threadGo() throws BrokenBarrierException, InterruptedException {
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
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
                    try {
                        cyclicBarrier.await();//到达屏障
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        cyclicBarrier.await();
        System.out.println("主线程启动");
    }
}
