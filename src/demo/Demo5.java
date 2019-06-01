package demo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 *CyclicBarrier
 */
public class Demo5 {
    private static class Task implements Runnable{
        private CyclicBarrier cyclicBarrier;
        public Task(CyclicBarrier cyclicBarrier){
            this.cyclicBarrier = cyclicBarrier;
        }
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+"准备");
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"开始");
        }
    }

    public static void main(String[] args) {
        final CyclicBarrier cb = new CyclicBarrier(10, new Runnable() {
            @Override
            public void run() {
                System.out.println("全部就绪可以开始");
            }
        });
        for(int i=0;i<10;i++){
            new Thread(new Task(cb),i+"号").start();
        }
    }


}
